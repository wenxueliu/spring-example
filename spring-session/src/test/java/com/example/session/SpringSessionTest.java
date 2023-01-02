package com.example.session;

import com.example.session.entity.LoginVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import javax.servlet.http.Cookie;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author liuwenxue
 * @date 2023-01-01
 */
@SpringBootTest
@AutoConfigureMockMvc
public class SpringSessionTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testLogin() throws Exception {
        LoginVo loginVo = new LoginVo("admin", "admin@123");
        String content = loginVo.toString();

        // mock登录
        ResultActions actions = this.mockMvc.perform(post("/login.do")
                .content(content).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect((ResultMatcher) content().string("ok"));
        String sessionId = actions.andReturn()
                .getResponse().getCookie("SESSION").getValue();

        // 使用登录的sessionId mock查询
        this.mockMvc.perform(get("/session/query.do")
                .cookie(new Cookie("SESSION", sessionId)))
                .andExpect(status().isOk()).andExpect((ResultMatcher) content().string("ok"));

        // mock登出
        this.mockMvc.perform(post("/logout.do")
                .cookie(new Cookie("SESSION", sessionId)))
                .andExpect(status().isOk()).andExpect((ResultMatcher) content().string("ok"));
    }
}
