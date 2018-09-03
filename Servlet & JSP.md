# Servlet & JSP

### 调用Servlet

在html中，我们向服务器请求一个表单：

```
<form action=add method="post">
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

我们可以在web.xml里面配置类和url的映射关系，以下是/show这个URL对应到com.gary.hsqldbms.ShowServlet这个类的映射：

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

1. #### 使用Request Dispatcher进行forword

   ```
   RequestDispatcher rd = request.getRequestDispatcher("sq");
   rd.forward(request, response);
   ```

   其中，"/sq"为跳转到的URI。

2. #### 使用Session Management进行传参

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