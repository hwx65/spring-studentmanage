# Spring Student Mange Application — 作业四



## 要求

使用spring batch技术读取课程群中excel文件内容进行必要的处理转换，再存入到你所开发的学生管理系统的数据库中，让你的学生管理系统可对这些数据进行进一步的（增删改查）管理



## 实现

### reader

* 参考【1】和【2】构造一个可以读 xlsx 类型文件的 reader():

  ```java
  public ItemStreamReader<Student> reader() {
  		PoiItemReader<Student> reader = new PoiItemReader<Student>();
  		reader.setResource(new ClassPathResource("./测试数据.xlsx"));
  		reader.setRowMapper(new RowMapperImpl());
  		reader.setLinesToSkip(1);
  
  		return reader;
  }
  ```

### processor

* 构造一个只打印学生信息，不做其他任何事情的processor：

  ```java
  public Student process(final Student student) throws Exception {
  		log.info("Converting (" + student + ") into (" + student + ")");
  		return student;
  }
  ```

### writer

* 构造一个将处理后的学生信息写入数据库的write():

  ```java
  public JdbcBatchItemWriter<Student> writer(DataSource dataSource) {
  		return new JdbcBatchItemWriterBuilder<Student>()
  				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
  				.sql("INSERT INTO students (id, name, gender, birthday, phonenumber, academy) VALUES (:id, :name, :gender, :birthday, :phonenumber, :academy)")
  				.dataSource(dataSource).build();
  }
  ```



## 结果

在本次实验中，因为文件中的数据缺少性别和出生日期等信息，所以我在表格中，对这两个信息进行了随机补充，最后访问 http://localhost:8080/students/ 可以看到如下结果：

![](D:\spring\spring-studentmanage\students.png)





## 参考

【1】[Spring: Creating a Batch Service](https://spring.io/guides/gs/batch-processing/)

【2】[Spring Batch Tutorial: Reading Information From an Excel File](https://www.petrikainulainen.net/programming/spring-framework/spring-batch-tutorial-reading-information-from-an-excel-file/)



