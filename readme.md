# Spring Student Mange Application




## 运行

指令

```
./mvnw spring-boot:run
```



如果出现报错，则根据提示执行

```
mvn spring-javaformat:apply
```



访问：http://localhost:8080/



## 使用介绍

1. 初始界面，点击`FIND STUDENTS`进入功能界面

   ![image-20210325133515717](Readme.pic\image-20210325133515717.png)

2. 功能界面，可以增加或者根据学生姓名查找

   ![image-20210325134316225](Readme.pic\image-20210325134316225.png)

3. 输入姓名，查找学生

   * 如果查询值为空，则进入所有学生列表页面，点击学生姓名可进入该学生信息页面

     ![image-20210325140959492](Readme.pic\image-20210325140959492.png)

   * 如果查询结果有多个，则进入查询结果列表

   * 如果结果只有一个，则进入学生信息页面，可以进行修改或者删除

     ![image-20210325135419921](Readme.pic\image-20210325135419921.png)

   4. 增加学生界面，填写信息之后点击`Add Student`进入学生信息界面

      ![image-20210325141335001](Readme.pic\image-20210325141335001.png)



## 参考

https://www.codeproject.com/Articles/36847/Three-Layer-Architecture-in-C-NET-2

https://github.com/sa-spring/spring-petclinic

