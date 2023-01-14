

## 使用方法

1、连续多次访问 http://localhost:8081/cache-example/get?id=1

每次调用打印`查询用户【id】= 1`


2、连续多次访问 http://localhost:8081/cache-example/get?id=2

第一次调用打印`查询用户【id】= 2`，之后不再打印。

3、访问 http://localhost:8081/cache-example/delete?id=2

4、访问 http://localhost:8081/cache-example/get?id=2

再次打印`查询用户【id】= 2`

5、重复 3、4 步骤