# spring

#### 创建一个Maven工程

选择quickstart

Google `maven repository`，查找`spring context`，然后将找到的dependency：

```
    <!-- https://mvnrepository.com/artifact/org.springframework/spring-context -->
	<dependency>
	    <groupId>org.springframework</groupId>
	    <artifactId>spring-context</artifactId>
	    <version>4.1.9.RELEASE</version>
	</dependency>
```

插入到pom.xml文件中：

```
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.gary</groupId>
  <artifactId>SpringAnno</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <packaging>jar</packaging>

  <name>SpringAnno</name>
  <url>http://maven.apache.org</url>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  </properties>

  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
      <scope>test</scope>
    </dependency>
    
    <!-- https://mvnrepository.com/artifact/org.springframework/spring-context -->
	<dependency>
	    <groupId>org.springframework</groupId>
	    <artifactId>spring-context</artifactId>
	    <version>4.1.9.RELEASE</version>
	</dependency>
    
  </dependencies>
</project>
```

保存之后，maven自动会将依赖包从远程的仓库中下载到本地仓库(一般在C:\Users\Administrator\\.m2\repository)中。



1. XML configuration

2. annotation configuration

   直接在类的前一行加上@Component, 然后再xml文件中的beans标签加入一行<context:component-scan base-package="com.gary.SpringTest">\</context:component-scan>

   之后调用getBean的时候，使用首字符非大写的类名就可以了。

   如：

   ```
   @Component
   public class Bike implements Vehicle {
   	public void drive() {
   		System.out.println("drive bike");
   	}
   }
   ```

   调用的时候：

   ```
   //当你创建这个context的对象的时候，会从spring.xml文件中载入所有的配置信息
       	ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
       	
           Vehicle obj = (Vehicle)context.getBean("bike");
           obj.drive();
   ```

   其中XML文件：

   ```
   <beans xmlns="http://www.springframework.org/schema/beans"
   	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
   	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
   		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd"
   	xmlns:context="http://www.springframework.org/schema/context">
   
   	<context:component-scan base-package="com.gary.SpringTest"></context:component-scan>
   	
   </beans>
   ```

   

3. Java configuration

   见SpringAnno工程

### Bean Property

比如类里面有成员变量的时候，可以通过在XML里面设置property标签来初始化。

<bean id="tyre" class="com.gary.SpringTest.Tyre">

​	<property name="成员变量名" value="成员变量值"></property>

</bean>

**使用property标签的时候，实际上是调用了该成员变量的setter函数(getXXX).**

-->这叫做setter injection。

当然，也可以不用setter来初始化，而使用构造函数来进行初始化，这叫做Constructor Injection。

增加构造函数：

```
	public Tyre(String brand) {
		super();
		this.brand = brand;
	}
```

XML里面改成：

```
<bean id="tyre" class="com.gary.SpringTest.Tyre">
		<constructor-arg value="MRF"></constructor-arg>
</bean>
```

其中value="MRF"，该值会被填写到构造函数的参数中。



### Autowired annotation(自动装配注解)

比如说：

```
@Component
public class Car implements Vehicle {
	
	@Autowired
	private Tyre t;
	
	public void drive() {
		System.out.println("car " + t);
	}
}
```

其中Car这个里面有一个私有的对象Tyre，使用了自动装配注解则会在这个类被实例化的时候也实例化这个对象，具体方法根据声明就是找到这个类，然后使用XML里面定义的property标签进行初始化。

###AOP（Aspect Oriented Programming）

1. Write Aspect
2. Configure where the aspects apply

