#使用Mockito进行测试

在我们的测试中，我们可能会遇到自己写的类的方法调用别的类提供的服务的情况，这些方法可能是比如说云服务商、或者其他类库所提供的，这些方法的测试是由别的组织来负责的，并不属于我们的责任，此时我们希望在测试的时候，仅仅测试自己写的程序的正确性， 至于调用的他人写的库或者服务，他们的功能我们仅仅通过硬编码输入输出来做一个Stub（Mock service）来提供。

以下是一个实际的例子：

我们有一个类Calculator，里面调用了其他人写的一个服务（假设是某个云服务商），CalculatorService是一个接口，里面的实现都由第三方来实现。

其中就有一个add方法，功能是将两个int数相加。

我们实现的perform函数的功能是调用add方法，然后乘以2.

在构造我们的计算器的时候，我们需要提供这个云服务的对象。

    package com.gary.DemoJunit;
    
    public class Calculator {
    
    	CalculatorService service = null;
    	
    	public Calculator(CalculatorService service) {
    		this.service = service;
    	}
    	
    	public int perform(int i, int j) {
    		return service.add(i, j) * 2;
    	}
    }

接口如下：

    package com.gary.DemoJunit;
    
    public interface CalculatorService {
    
    	int add(int i, int j);
    }

我们创建一个JUnit类TestCalculator测试我们的Calculator：

创建之前，确保pom.xml里面已经有了junit依赖（看下你要的版本，实际上只要创建maven工程，就会有这个依赖，但是版本可能不太合适，我们可以自己到mvnrepository网上拷贝）：

    	<!-- https://mvnrepository.com/artifact/junit/junit -->
    	<dependency>
    	    <groupId>junit</groupId>
    	    <artifactId>junit</artifactId>
    	    <version>4.11</version>
    	    <scope>test</scope>
    	</dependency>

TestCalculator.java:

    package com.gary.DemoJunit;
    
    import static org.junit.Assert.assertEquals;
    import static org.mockito.Mockito.verify;
    import static org.mockito.Mockito.when;
    
    import org.junit.Before;
    import org.junit.Rule;
    import org.junit.Test;
    import org.mockito.Mock;
    import org.mockito.junit.MockitoJUnit;
    import org.mockito.junit.MockitoRule;
    
    public class TestCalculator {
    
    	Calculator c = null;
    	
    	// STUB
    	// creating a fake service
    //	CalculatorService service = new CalculatorService() {
    //		
    //		public int add(int i, int j) {
    //			return 0;
    //		}
    //	};
    	// 使用mockito来替我们生成这个fake service
    	// 实际上这个可能是一个cloud service (我们想要测试的是Calculator，而不是这个service,至于这个service，谁提供谁来测试)
    //	CalculatorService service = mock(CalculatorService.class);
    	@Mock
    	CalculatorService service;
    	
    	@Rule
    	public MockitoRule rule = MockitoJUnit.rule();
    	
    	// this will be called before test
    	@Before
    	public void setUp() {
    		// provide the fake service for testing
    		c = new Calculator(service);
    	}
    	
    	@Test
    	public void testPerform() {
    		
    		// we test perform, not testing add(which is a service)
    		// hard code the input and output of add()
    		when(service.add(2, 3)).thenReturn(5);
    		// test the perform method（第一个参数检查的是是否返回值为10）
    		assertEquals(10, c.perform(2, 3));
    		// to verify that your are actually call the mockito service
    		verify(service).add(2, 3);
    	}
    	
    	// @after --- will be called after test
    	// like release some resource...
    }
    

注意到在使用JUnit的时候，有三个注解:

    @Before // this will be called before test
    @Test
    @After  // this will be called after test

@Test是我们的测试用的方法。

我们希望生成这个服务，然后让我们的计算器去调用这个服务，但是我们不希望我们的计算器去真正的调用这个服务（因为在现实生活中，这个服务可能是云服务商提供的，调用的开销过大，而且他们也应该为我们做好了测试的工作）：

这时候我们就可以使用mockito来替我们生成这个fake service：

    CalculatorService service = mock(CalculatorService.class);

注意这里之所以可以这样使用mock方法，是因为我们在之前导入了Mockito里面的所有静态成员函数和静态成员变量：

    import static org.mockito.Mockito.*;

类似的用法还有：

    System.out.println("hello");

我们知道哦out是System类的一个静态成员变量，System类属全称为ava.lang.System。如果我们想直接使用这个out，我们就需要导入这个静态成员变量：

    import static java.lang.System.out; // 精确到成员函数或者成员变量名

当然也可以直接用注解来装配Mock service：

    // 使用注解来装配Mock service
    @Mock
    CalculatorService service;
    // 声明是在JUnit里面使用
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

以下就是我们的硬编码输入输出的过程，伪造一个service，其中when是org.mockito.Mockito类里面的一个静态方法：

    // we test perform, not testing add(which is a service)
    // hard code the input and output of add()
    when(service.add(2, 3)).thenReturn(5);

assertEquals测试我们的Calculator类的perform方法是否返回了正确的返回值：

运行这个程序的时候就能看到结果。

verify的功能是验证这个服务的add方法是否真的被调用到了，如果没有，测试也会报错：

Wanted but not invoked.

    // test the perform method（第一个参数检查的是是否返回值为10）
    assertEquals(10, c.perform(2, 3));
    // to verify that your are actually call the mockito service
    verify(service).add(2, 3);


