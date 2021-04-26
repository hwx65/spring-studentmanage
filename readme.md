# Spring Student Mange Application — 作业三



## 要求

将前一次作业的学生管理应用改为REST风格



## 实现

参考《Building REST services with Spring 》和 spring-hateoas-examples 项目对之前的学生管理系统进行修改：

1. 修改pom.xml，添加 hateoas、lombok等依赖：

   ```xml
   <dependency>
       <groupId>org.projectlombok</groupId>
       <artifactId>lombok</artifactId>
   </dependency>
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-hateoas</artifactId>
   </dependency>
   ```

2. 修改 StudentController.java，转为REST风格，比如查询所有学生的方法变为：

   ```java
   @GetMapping("/students")
   	ResponseEntity<CollectionModel<EntityModel<Student>>> findAll() {
   		List<EntityModel<Student>> students = StreamSupport
               	.stream(repository.findAll().spliterator(), false)
   				.map(student -> EntityModel.of(student, //
   						linkTo(methodOn(StudentController.class).findOne(student.getId())).withSelfRel(), //
   						linkTo(methodOn(StudentController.class).findAll()).withRel("students"))) //
   				.collect(Collectors.toList());
   		return ResponseEntity.ok( //
   				CollectionModel.of(students, //
   						linkTo(methodOn(StudentController.class).findAll()).withSelfRel()));
   	}
   ```

3. 修改 data.sql，只保留10条数据，方便查询和修改





## 结果

通过PostMan访问，以 json 的形式放回操作的结果

#### 增加

在 Body 中以 JSON 的格式写入新增学生的信息，然后以 Post 方式访问 localhost:8080/students/，增加成功后返回学生信息，状态码为201。

#### 删除

以 DELETE 方式访问 localhost:8080/students/{id}，{id} 替换为学生的 id，成功则返回信息“Successfully detele”，状态码为 200，如果无法删除则返回信息“No studundet have id: {id}”，状态码为 400

#### 修改

在 Body 中以 JSON 的格式写入学生修改之后的信息，然后以 Put 方式访问 localhost:8080/students/{id} ，成功则返回状态码 200；

#### 查询

以 GET 方式访问 localhost:8080/students/，查询所有学生信息，访问 localhost:8080/students/{id} 查询单个学生信息，获得结果均以 JSON 格式返回，状态码为 200





## 参考

https://spring.io/guides/tutorials/rest/

https://github.com/spring-projects/spring-hateoas-examples

https://www.bilibili.com/video/BV1GE411G7hu

