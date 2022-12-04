package com.example.spring.reactive;

import com.example.spring.ioc.dependency.inject.model.User;
import io.netty.handler.codec.http.HttpHeaderValues;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;

import static com.example.spring.reactive.WebClientExample.logRequest;

public class WebClientExampleTest {
    @Test
    public void testCreate() {
        Mono<String> mono = WebClient.create()
                //方法调用，WebClient中提供了多种方法
                .method(HttpMethod.GET)
                //请求url
                .uri("http://localhost:8080/hello")
                //获取响应结果
                .retrieve()
                //将结果转换为指定类型
                .bodyToMono(String.class);
        //block方法返回最终调用结果，block方法是阻塞的
        System.out.println("响应结果：" + mono.block());
    }

    @Test
    public void testCreateBaseUrl() {
        Mono<String> mono = WebClient
                //创建WenClient实例，指定基础url，所以下面uri请求的路径都是基于这个路径
                .create("http://localhost:8080")
                //方法调用，WebClient中提供了多种方法
                .method(HttpMethod.GET)
                //请求url
                .uri("/hello")
                //获取响应结果
                .retrieve()
                //将结果转换为指定类型
                .bodyToMono(String.class);
        //block方法返回最终调用结果，block方法是阻塞的
        System.out.println("响应结果：" + mono.block());
    }

    @Test
    public void testBuilder() {
        Mono<String> mono = WebClient
                .builder()
                //配置头信息，或者其他信息
                .defaultHeader("token", "123456789")
                //创建WebClient实例
                .build()
                //方法调用，WebClient中提供了多种方法
                .method(HttpMethod.GET)
                //请求url
                .uri("http://localhost:8080/hello")
                //获取响应结果
                .retrieve()
                //将结果转换为指定类型
                .bodyToMono(String.class);
    }


    /**
     * block()阻塞式获取响应结果
     */
    @Test
    public void testMono() {
        Mono<User> userMono = WebClient
                .create()
                .method(HttpMethod.GET)
                .uri("http://localhost:8080/hello")
                .retrieve()
                .bodyToMono(User.class);
        User user = userMono.block();
    }

    @Test
    public void testFlux() {
        Flux<User> userFlux = WebClient
                .create()
                .method(HttpMethod.GET)
                .uri("http://localhost:8080/hello")
                .retrieve()
                .bodyToFlux(User.class);
        List<User> users = userFlux.collectList().block();
    }

    /**
     * subscribe()非阻塞式获取响应结果
     */
    @Test
    public void testSubscribe() {
        Mono<String> mono = WebClient
                .create()
                .method(HttpMethod.GET)
                .uri("http://localhost:8080/hello")
                .retrieve()
                .bodyToMono(String.class);
        mono.subscribe(WebClientExampleTest::handleMonoResp);
    }

    //响应回调
    private static void handleMonoResp(String monoResp) {
        System.out.println("请求结果为：" + monoResp);
    }


    /**
     * exchange()获取HTTP响应完整内容
     */
    @Test
    public void testExchange() {
        Mono<ClientResponse> clientResponseMono = WebClient
                .create()
                .method(HttpMethod.GET)
                .uri("http://localhost:8080/hello")
                .exchange();
        ClientResponse clientResponse = clientResponseMono.block();
        //响应头
        ClientResponse.Headers headers = clientResponse.headers();
        //响应状态
        HttpStatus httpStatus = clientResponse.statusCode();
        //响应状态码
        int rawStatusCode = clientResponse.rawStatusCode();
        //响应体
        Mono<String> mono = clientResponse.bodyToMono(String.class);
        String body = mono.block();
    }


    /**
     * 数字占位符
     *
     * @throws Exception
     */
    @Test
    public void testDigitalPlaceHolder() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        String url = "http://localhost:8080/user/{1}/{2}";
        Mono<String> mono = WebClient.create()
                .method(HttpMethod.POST)
                .uri(url, list.toArray())
                .retrieve()
                .bodyToMono(String.class);
        String result = mono.block();
    }

    @Test
    public void testParamPlaceHolder() {
        String url = "http://localhost:8080/user/{id}/{name}";
        String id = "123";
        String name = "Boss";
        Mono<String> mono = WebClient.create()
                .method(HttpMethod.POST)
                .uri(url, id, name)
                .retrieve()
                .bodyToMono(String.class);
        String result = mono.block();
    }

    @Test
    public void testMapPlaceHolder() {
        String url = "http://localhost:8080/user/{id}/{name}";
        Map<String, String> params = new HashMap<>();
        params.put("id", "123");
        params.put("name", "Boss");
        Mono<String> mono = WebClient.create()
                .method(HttpMethod.POST)
                .uri(url, params)
                .retrieve()
                .bodyToMono(String.class);
        String result = mono.block();
    }

    @Test
    public void testGet() {
        WebClient webClient = WebClient.builder().baseUrl("https://www.baidu.com").build();
        Mono<String> mono = webClient.get().retrieve().bodyToMono(String.class);
        System.out.println(mono.block());
    }

    /**
     * 使用 webclient 设置 baseUrl，默认的 header, cookie, 过滤器等
     * 使用 uriBuilder 构造路径
     * 通过 attribute 设置属性
     */
    @Test
    public void testGetWithQueryParams() {
        WebClient webClient = WebClient.builder()
                .baseUrl("https://www.baidu.com/")
                .defaultHeader(HttpHeaders.USER_AGENT, "Spring 5 WebClient")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON.toString())
                .filter(logRequest())
                .filter((request, next) -> {
                    String token = (String) request.attribute("token").get();
                    ClientRequest newRequest =
                            ClientRequest.from(request).header("header1", "value1").build();
                    Mono<ClientResponse> responseMono = next.exchange(newRequest);
                    return Mono.fromCallable(() -> {
                        ClientResponse response = responseMono.block();
                        ClientResponse newResponse = ClientResponse.from(response).header("responseHeader1", "Value1").build();
                        return newResponse;
                    });

                })
                .build();
        MultiValueMap<String, String> urlVars = new LinkedMultiValueMap<>();
        urlVars.put("ie", Arrays.asList("UTF-8"));
        urlVars.put("wd", Arrays.asList("webClient"));
        Mono<String> mono = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("s")
                        .queryParams(urlVars)
                        .build())
                .attribute("token", "webclient")
                .retrieve()
                .bodyToMono(String.class)
                .doOnError(WebClientResponseException.class, err -> {
                    System.out.println(err.getRawStatusCode() + "," + err.getResponseBodyAsString());
                    throw new RuntimeException(err.getMessage());
                })
                .onErrorReturn("fallback");
        System.out.println(mono.block());
    }

    @Test
    public void testGetWithUrlVars() {
        WebClient webClient = WebClient.builder().baseUrl("https://www.baidu.com").build();
        Mono<String> mono = webClient.get().uri("/s?ie={ie}&wd={wd}", "UTF-8", "webClient")
                .retrieve().bodyToMono(String.class);
        System.out.println(mono.block());
    }

    /**
     * 通过 BodyInserters 设置 body
     */
    @Test
    public void testForUpload() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        HttpEntity<ClassPathResource> entity = new HttpEntity<>(new ClassPathResource("parallel.png"), headers);
        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
        parts.add("file", entity);
        Mono<String> resp = WebClient.builder().baseUrl("http://localhost:8080").build().post()
                .uri("upload")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(parts))
                .retrieve().bodyToMono(String.class);
    }

    /**
     * 通过 BodyInserters 设置 body
     */
    @Test
    public void testForm() {
        WebClient webClient = WebClient.builder()
                .baseUrl("https://www.baidu.com/")
                .build();
        MultiValueMap<String, String> formBody = new LinkedMultiValueMap<>();
        formBody.put("ie", Arrays.asList("UTF-8"));
        formBody.put("wd", Arrays.asList("webClient"));
        Mono<String> mono = webClient.post()
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .body(BodyInserters.fromFormData(formBody))
                .retrieve().bodyToMono(String.class);
        System.out.println(mono.block());
    }

    @Test
    public void testAttribute() {
        WebClient webClient = WebClient.builder()
                .baseUrl("https://ug.baidu.com/")
                .build();
        Body body = new Body();
        Mono<String> mono = webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/mcp/pc/pcsearch").build())
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON.toString())
                .body(BodyInserters.fromValue(body))
                .retrieve().bodyToMono(String.class);
        System.out.println(mono.block());
    }

    class Body {
        Info invoke_info = new Info();
    }

    class Info {
        List<String> pos_1 = new ArrayList<>();
        List<String> pos_2 = new ArrayList<>();
        List<String> pos_3 = new ArrayList<>();
    }


    @Test
    public void testContext() {

    }
}
