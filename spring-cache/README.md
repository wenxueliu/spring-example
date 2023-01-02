

## 使用方法

1、连续多次访问 http://localhost:8081/cache-example/get?id=1

第一次调用打印`查询用户【id】= 1`，之后不再打印。

2、访问 http://localhost:8081/cache-example/delete?id=1

3、访问 http://localhost:8081/cache-example/get?id=1

再次打印`查询用户【id】= 1`