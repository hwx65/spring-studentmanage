# Spring Student Mange Application



* 数据库中预先存储了大约3000条数据，压力测试任务为查询所有名字B开头的学生
* atOnceUsers = 100
* 配置为：2个或者4个节点，有cache或者无cache，共四种，其中每个节点有0.5个cpu
* 每种配置下运行gatling五次，取每次运行结果中的`mean response time`，最后再对这5次的结果取平均

|        | 2个节点，无cache | 2个节点，有cache | 4个节点，无cache | 4个节点，有cache |
| ------ | ---------------- | ---------------- | ---------------- | ---------------- |
| 第一次 | 7390             | 1999             | 5779             | 1668             |
| 第二次 | 6465             | 1997             | 6472             | 1504             |
| 第三次 | 6656             | 1981             | 5029             | 1640             |
| 第四次 | 6350             | 2061             | 4943             | 2036             |
| 第五次 | 6378             | 1980             | 5345             | 2021             |
| 平均   | 6647.8           | 2003.6           | 5513.6           | 1584             |



**结论**：使用cache可以在很大程度上提高系统性能。







### 部分实验代码：

**haproxy.cfg**

```cfg
defaults
    mode tcp
frontend lb-app-pi
    bind *:8080
    default_backend servers
backend servers
    balance roundrobin
    server server1 localhost:8081
    server server2 localhost:8082
    server server3 localhost:8083
    server server4 localhost:8084
```



**GatlingTestSimulation.scala**

```scala
package gatlingtest

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class GatlingTestSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:8080")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

     val scn = scenario("Student Manage test")
    .exec(
      http("search request")
        .get("/students?stuname=B")
    )
    setUp(scn.inject(atOnceUsers(200)).protocols(httpProtocol))
}

```



