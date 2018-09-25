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

   在beans中加入bean元素，其中有两个属性，一个是name(id)，这是你在调用：

   ```
   context.getBean("xxx");
   ```

   中输入的名字。

   另一个属性是class，为你根据bean的名字产生的类全名。如下：

   ```
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:context="http://www.springframework.org/schema/context"
          xmlns:aop="http://www.springframework.org/schema/aop"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
              http://www.springframework.org/schema/beans/spring-beans-2.5.xsd
              http://www.springframework.org/schema/context
              http://www.springframework.org/schema/context/spring-context-2.5.xsd
              http://www.springframework.org/schema/aop            
              http://www.springframework.org/schema/aop/spring-aop-3.1.xsd  
              "><!-- 要添加最后2行 -->
              
   	<bean name="triangle" class="com.gary.springaop.model.Triangle">
   		<property name="name" value="Triangle name"></property>
   	</bean>
   	
   	<bean name="circle" class="com.gary.springaop.model.Circle">
   		<property name="name" value="Circle name"></property>
   	</bean>
   	
   	<bean name="shapeService" class="com.gary.springaop.service.ShapeService" autowire="byName">
   	</bean>
   </beans>
   ```

   

2. annotation configuration

   直接在类的前一行加上@Component, 然后再xml文件中的beans标签加入一行

   ```
   <context:component-scan base-package="com.gary.SpringTest"></context:component-scan>
   ```

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
   
   	<context:component-scan base-package="com.gary.SpringTest">		</context:component-scan>
   	
   </beans>
   ```

   

3. Java configuration

   见SpringAnno工程

   ```
   //create a class who will provide me the configuration
   ApplicationContext factory = new AnnotationConfigApplicationContext(AppConfig.class);
   
   //只有在配置类里面提供有返回这个类的方法之后，才能正常使用getBean方法
   Samsung s7 = factory.getBean(Samsung.class);
   s7.config();
   ```

   在AppConfig类里面：

   ```
   //用来specify this is a configuration file(class)
   @Configuration
   //Scan for the qualified Beans
   @ComponentScan(basePackages="com.gary.SpringAnno")
   public class AppConfig {
   
   	// 1. 使用bean tag来生产对象
   	
   //	@Bean
   //	public Samsung getPhone() {
   //		return new Samsung();
   //	}
   //	
   //	//检查方法的返回值类型，发现是需要的，就“生产”一个出来
   //	@Bean
   //	public MobileProcessor getProcessor() {
   //		return new SnapDragon();
   //	}
   	
   	// 2. 不使用Bean tag来生产对象
   	
   }
   ```

   ```
   public interface MobileProcessor {
   	void process();
   }
   ```

   有两个实现了这个接口的处理器：

   ```
   @Component
   //使用primary注解可以在多个类符合的情况下先选择这个类生产
   @Primary
   public class MediaTek implements MobileProcessor {
   
   	public void process() {
   		System.out.println("World's 2'nd best CPU.");
   		
   	}
   
   }
   ```

   ```
   //注意到直接使用@Component的话，id默认是snapDragon（也就是把首字母小写了）
   //也可以显式地指定id
   @Component("mySnapDragon")
   public class SnapDragon implements MobileProcessor {
   
   	public void process() {
   		System.out.println("World's best CPU.");
   	}
   
   }
   ```

   Samsung类里面需要处理器的依赖：

   ```
   //若想让这个对象默认成为一个Bean，做法是在这个类之前加上一个 @Component 的标签
   //然后在配置类里面加上一个extra annotation，@ComponentScan(basePackages="你需要查找Bean的完整包名，多个包的话使用逗号隔开")
   @Component
   public class Samsung {
   	
   	//如果没有的话，会自动让工厂生产出来
   	//如果有多个类符合的话，就会出现confusion，如这里的MediaTek和SnapDragon都实现了MobileProcessor类，工厂不知道造哪个出来
   	@Autowired
   	//当然，也可以使用Qualifier来显式指定需要装配的对象
   	@Qualifier("mySnapDragon")
   	MobileProcessor cpu;
   	
   	public MobileProcessor getCpu() {
   		return cpu;
   	}
   
   	public void setCpu(MobileProcessor cpu) {
   		this.cpu = cpu;
   	}
   
   	public void config() {
   		System.out.println("Octa Core, 4GB RAM");
   		cpu.process();
   	}
   }
   ```

   

### Bean Property

比如类里面有成员变量的时候，可以通过在XML里面设置property标签来初始化。

```
<bean id="tyre" class="com.gary.SpringTest.Tyre">
	<property name="成员变量名" value="成员变量值"></property>
</bean>
```

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

加上@Aspect注解，这个类就变成了一个Aspect。

这个类的方法变成了advice。你可以将这个advice应用于处在spring容器的任何其他方法前后。

```
@Aspect
public class LoggingAspect {

	@Before("execution(public String getName())")
	public void LoggingAdvice() {
		System.out.println("Advice run. Get Method called.");
	}
}
```

@Before("execution(public String getName())")说明了只要看到有public String getName()这样的标签的方法，在它执行前我会执行我的advice。（并没有声明是哪个类里面的getName方法，因此不管调用的是哪个JavaBean，只要签名正确，都会执行）

若需要仅仅在Circle的getName之前使用这个advice，则需要在方法名前加上类的全名，如下：

```
@Before("execution(public String com.gary.springaop.model.Circle.getName())")
```

在spring.xml中：

1. 将这个类配置到bean container中

```
<bean id="loggingAspect" class="com.gary.springaop.aspect.LoggingAspect" />
```

2. 告诉spring我们已经做好了AOP的准备

   （Hey Spring，我们要使用AOP了，因此如果你看到了@Aspect注解的话，要保证所有的advice都正确配置）

```
<aop:aspectj-autoproxy></aop:aspectj-autoproxy>
```

### Aspect apply to multiple points

将advice应用到所有的getter上面：

```
@Before("execution(public String get*())") // apply to all getters that return a string
```

```
@Before("execution(public * get*())") // apply to all getters(a method that starts with get, must be public, have no argument)
```

```
@Before("execution(* get*())") // apply to all getters(a method that starts with get, have no argument)
```

```
@Before("execution(* get*(..))") // for any match of zero or more arguments
@Before("execution(* get*(*))") // for any match of one or more arguments
@Before("execution(* get*())")  // for any match of zero arguments
```

```
@Before("execution(* com.gary.springaop.model.*.getName())") //com.gary.springaop.model包下的所有getter方法
```

### Pointcut（multiple advice methods apply to one pointcut）

使用pointcut可以保证你指输入expression一次，然后可以在多个地方使用这个expression。

因为allGetters()这个方法会在每次出现"* get*()"这个pattern方法前执行，而在它Z执行之前，有两个advice会被执行。

```java
@Aspect
public class LoggingAspect {
	
	@Before("allGetters()")
	public void LoggingAdvice() {
		System.out.println("Advice run. Get Method called.");
	}
	
	@Before("allGetters()")
	public void secondAdvice() {
		System.out.println("Second advice executed.");
	}
	
	@Pointcut("execution(* get*())")
	public void allGetters() {} // dummy method
	
}	
```

我想让某个aspect应用于类里面的所有方法，可以使用within，如下，第一行和第二行是等价的，但是第一行可读性更好：

```java
	@Pointcut("within(com.gary.springaop.model.Circle)")
//	等价于：@Pointcut("execution(* * com.gary.springaop.model.Circle.*(..))")
	public void allCircleMethods() {}
```

我想让pointcut apply to all the methods in com.gary.springaop.model这个包中：

```
@Pointcut("within(com.gary.springaop.model.*)")
```

如果model这个包里面还有子包的话：

```
@Pointcut("within(com.gary.springaop.model..*)")
```

这里的..类似于方法参数里面的..,意味着这个类可以是model根目录下的类，也可以是子包中的类。

注意：

**within后面的参数是类名，而execute后面的参数是方法名。**

args后面跟的参数是arguments that you want your methods to have for which you want to apply your pointcut.

```
@Pointcut("args(Circle)")
```

将advice应用在所有同时满足allGetters()和allCircleMethods()的地方：

```
@Before("allGetters() && allCircleMethods()")
```

