
### 启动服务

可以通过修改 application.yml 修改监听端口号

### 查询

浏览器访问 http://127.0.0.1:8080/session-example/query

此时返回 /login first

### 登录

浏览器访问 http://127.0.0.1:8080/session-example/login?userName=123&password=123，查看应答头发现有 

`
Set-Cookie: SESSION=OTZlMmRiZTYtMjZkNS00NWQ5LTg3YjItOTk0N2ExNDQ3YjI3; Path=/session-example; HttpOnly; SameSite=Lax
`



查看 redis 发现有

```
1、对于key: session.example:expirations:1672632060000

value 为

expires:96e2dbe6-26d5-45d9-87b2-9947a1447b27"

TTL 为 1523

2、对于key: session.example:sessions:96e2dbe6-26d5-45d9-87b2-9947a1447b27

value 为

lastAccessedTime：1672630258040
maxInactiveInterval：1800
sessionAttr:currentUser：{"@class":"com.example.session.entity.UserVo","userName":"123","userPassword":"123"}
creationTime：1672630126192

TTL 为 1523

3、对于key: session.example:sessions:expires:96e2dbe6-26d5-45d9-87b2-9947a1447b27

value 为 空字符

TTL 为 1223

```

### 查询 session

浏览器访问 http://127.0.0.1:8080/session-example/query

发现请求头有

`
Cookie: SESSION=OTZlMmRiZTYtMjZkNS00NWQ5LTg3YjItOTk0N2ExNDQ3YjI3
`

返回

2219e49c-389c-4c9a-8223-3f164bb53c96

### 登出

浏览器访问 http://127.0.0.1:8080/session-example/logout

查看应答头

`
Set-Cookie: SESSION=; Max-Age=0; Expires=Thu, 1 Jan 1970 00:00:00 GMT; Path=/session-example; HttpOnly; SameSite=Lax
`