# Servlet & JSP

### 调用Servlet

在html中，我们向服务器请求一个表单：

```
<form action="add" method="post">
	Enter 1st number: <input type="text" name="num1"></br>
	Enter 2nd number: <input type="text" name="num2"></br>
	<input type="submit" name="提交">
</form>
```

当你点击submit这个按钮的时候，以上填写的内容就会被传输到服务端。

可以通过：

```
int i = Integer.parseInt(req.getParameter("num1"));
```

来获得填写的值。

在服务器端，我们有一个对应的Servlet endpoint来处理这个请求：

当一个类继承了HTTPServlet之后，这个类便成为了一个Servlet：

```
public class AddServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		int i = Interger.parseInt(req.getParameter("num1"));
		int j = Interger.parseInt(req.getParameter("num2"));

		int k = i+j;
		PrintWriter out = res.getWriter();
		out.println("Result is: " + k);
	}
}
```

这里面HttpServletRequest和HttpServletResponse都是Tomcat为我们自动生成的并传入的，不需要我们来创建。

我们可以在web.xml（deployment descriptor）里面配置类和url的映射关系，以下是/show这个URL对应到com.gary.hsqldbms.ShowServlet这个类的映射：

```
  <servlet>
  	<servlet-name>abc</servlet-name>
  	<servlet-class>com.gary.hsqldbms.AddServlet</servlet-class>
  </servlet>
  
  <servlet-mapping>
  	<servlet-name>abc</servlet-name>
  	<url-pattern>/add</url-pattern>
  </servlet-mapping>
```

我们可以写一个service方法，这个会处理各种请求。

当然我们可以用doGet和doPost，这些只能处理单一类型的请求。

在HttpServlet类的实现里面，service函数也是通过检查request参数中的method来调用具体的方法。

### 从Servlet调用Servlet

1. #### 使用Request Dispatcher进行forword（c->s1->s2->c）

   ```
   RequestDispatcher rd = request.getRequestDispatcher("sq");
   rd.forward(request, response);
   ```

   其中，"/sq"为跳转到的URI。

   #### 使用将k添加到请求头中进行传参

   将k添加到request的属性中：

   ```
   request.setAttribute("k", k);
   RequestDispatcher rd = request.getRequestDispatcher("sq");
   rd.forward(request, response);
   ```

   然后在/sq对应的Servlet中取出这个属性：

   ```
   int k = (int)request.getAttribute("k");
   ```

   之后就可以对这个属性做一些处理了。

2. #### 使用SendRedirect让客户端重新请求(c->s1->c->s2->c)

   #### 1）并使用URL rewriting带上第一个Servlet处理的结果

   ```
   	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
   		int i = Integer.parseInt(request.getParameter("num1"));
   		int j = Integer.parseInt(request.getParameter("num2"));
   		
   		int k = i + j;
   		
   		response.sendRedirect("sq?k="+k);
   }
   ```

   第二个Servlet：

   ```
   int k = Integer.parseInt(request.getParameter("k"));
   ```

   getParameter用来解析URL中的参数k。

   我们可以看到，一开始使用的URL为：

   ```
   http://localhost:8080/DemoApp/index.html
   ```

   当点击“提交”按钮后，URL变成了：

   ```
   http://localhost:8080/DemoApp/sq?k=5
   ```

   #### 2）并使用Session Management来传参数

   session object是由Tomcat来维护的，使用request.getSession来get hold of the session.

   ```
   	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
   		int i = Integer.parseInt(request.getParameter("num1"));
   		int j = Integer.parseInt(request.getParameter("num2"));
   		
   		int k = i + j;
   		
   		HttpSession session = request.getSession();
   		session.setAttribute("k", k);
   		
   		response.sendRedirect("sq");
   }
   ```

   第二个Servlet使用：

   ```
   HttpSession session = request.getSession();
   session.getAttribute("k");
   ```

   来获得这个k。

   注意：getParameter是通过容器的实现来取得通过类似post，get等方式传入的数据；

   而request.setAttribute()和getAttribute()只是在web容器内部流转，仅仅是请求处理阶段 。

### ServletContext

在web.xml里面声明context-param，这是一个name-value pair：

```
  <context-param>
  	<param-name>name</param-name>
  	<param-value>Gary</param-value>
  </context-param>
```

在使用的时候，通过获得Tomcat实例化的ServletContext对象，调用getInitParameter方法就可以获得对应的值：

```
public class MyServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();

		// ServletContext is instantiated by Tomcat
		// Using getServletContext() to get the object
		ServletContext ctx = getServletContext();
		// Give the value of the name
		String str = ctx.getInitParameter("name");
		out.println(str);
	}
}
```

### ServletConfig

注意，ServletContext会被所有的Servlet所共享。

当你想要对不同的Servlet有不同的上下文的时候，使用ServletConfig

ServletConfig只在某个特定的Servlet里面起作用，如下，我声明了一个只在qq这个Servlet里面有效的init-param：

```
  <servlet>
  	<servlet-name>qq</servlet-name>
  	<servlet-class>com.gary.demoApp.MyServlet</servlet-class>
  	<init-param>
  		<param-name>name</param-name>
  		<param-value>Marry</param-value>
  	</init-param>
  </servlet>
```

在Servlet中使用的时候：

```
// ServletConfig is instantiated by Tomcat
// Using getServletConfig() to get the object
ServletConfig cg = getServletConfig();
// Give the value of the name
String str = cg.getInitParameter("name");
out.println(str);
```

###Servlet Annotation Configuration

使用@WebServlet来注释这个Servlet，这样就无需在web.xml里面配<servlet>和<urlmapping>了。

```
@WebServlet("/add")
public class AddServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		···
	}
	
}
```

### 使用JSP

使用Servlet，为了绘制出复杂的html页面，我们需要产生大量的out.println("xxx");这样的语句。

把html放在Java代码中是比较麻烦的，更好的办法是反过来，把Java代码放进html里，这就产生了JSP。

在表单提交的tag里面，直接声明action的值为具体的jsp文件名：

```
<form action="add.jsp" method="post">
	Enter 1st number: <input type="text" name="num1"></br>
	Enter 2nd number: <input type="text" name="num2"></br>
	<input type="submit" name="提交">
</form>
```

对应的add.jsp文件：

```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="red">
		
		<%
		int i = Integer.parseInt(request.getParameter("num1"));
		int j = Integer.parseInt(request.getParameter("num2"));
		
		int k = i + j;
		out.println("Output: " + k);
		%>
</body>
</html>
```

注意：request，response，out都是JSP内置的对象，无需特别声明。（这些叫做implicit object in JSP）

使用<% %>来隔开Java代码和HTML代码。<% %>里面的Java代码叫做scriptlet。

实际上每个JSP都会被转换成对应的Servlet来执行：

1. hello.jsp对应的Servlet类名为hello_jsp。
2. 所有写在scriptlet里面的代码都会被映射到对应的Servlet里面的service方法里。

   

###Builtin Object in JSP

1. request (HttpServletRequest)

2. response (HttpServletRequest)

3. pageContext (PageContext)

   ```
   // 作用域仅在这个page中
   pageContext.setAttribute("name", "Gary");
   
   // 如果想将这个属性在整个session中均有效
   pageContext.setAttribute("name", "Gary", PageContext.SESSION_SCOPE);
   ```

   

4. out(JspWriter) ~ PrintWriter object

5. session (HttpSession)

6. application (ServletContext)

7. config (ServletConfig)

   可以看到我们之前需要获得ServletConfig需要以下代码：

   ```
   // ServletConfig is instantiated by Tomcat
   // Using getServletConfig() to get the object
   ServletConfig cg = getServletConfig();
   // Give the value of the name
   String str = cg.getInitParameter("name");
   out.println(str);
   ```

   在JSP里，只要直接使用：

   ```
   config.getInitParameter("name");
   ```

   

### JSP里的异常处理

在会抛出异常的页面里，指明出现异常的时候需要跳转到哪个页面：

```
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="error.jsp" %>
```

在error.jsp里面，设置isErrorPage为True：

```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
```

这样就拥有了一个异常对象（只在ErrorPage里面有效），使用这个异常对象就可以打印出特定的信息：

```
<%= exception.getMessage() %>
```



### Login&Logout

登录的时候使用：

```
session.setAttribute("uname","Gary");
```

来设置session。

使用session来保持需要登录的页面确实是用户登录过的。

```
if (session.getAttribute("uname") == null) {
    response.sendRedirect("login.jsp");
}
```

登出的时候，使用：

```
session.removeAttribute("uname");
response.sendRedirect("login.jsp"); // redirect to login page
```

但是这样有一个安全漏洞，当你完成logout的时候，如果你点击后退按钮，它会退到之前登录的鉴权后的页面去。