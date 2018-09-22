# Bootstrap

点击来选择隐藏和显示文字：

```
<div class="container">
		<div class="row">
			<!-- 在lg的页面下，使用3个col，在md的屏幕下，使用3个col -->
			<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
				<a href="#col1Content" data-toggle="collapse">
					<h4>Column 1</h4>
				</a>
				<div id="col1Content" class="collapse in">
					Compile Bootstrap with your own asset pipeline by downloading our source Sass, JavaScript, and documentation files. This option requires some additional tooling:
				</div>
				
			</div>
			
			<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
				<a href="#col2Content" data-toggle="collapse">
					<h4>Column 2</h4>
				</a>
				<div id="col2Content" class="collapse">
					Compile Bootstrap with your own asset pipeline by downloading our source Sass, JavaScript, and documentation files. This option requires some additional tooling:
				</div>
				
			</div>

			<!-- 作用只是用来对齐 -->
			<div class="clearfix visible-small"></div>

			<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
				<a href="#col3Content" data-toggle="collapse">
					<h4>Column 3</h4>
				</a>
				<div id="col3Content" class="collapse">
					Compile Bootstrap with your own asset pipeline by downloading our source Sass, JavaScript, and documentation files. This option requires some additional tooling:
				</div>
				
			</div>

			<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
				<a href="#col4Content" data-toggle="collapse">
					<h4>Column 4</h4>
				</a>
				<div id="col4Content" class="collapse in">
					Compile Bootstrap with your own asset pipeline by downloading our source Sass, JavaScript, and documentation files. This option requires some additional tooling:
				</div>
				
			</div>

		</div>
	</div>
```

根据页面大小选择显示特定的元素

```
	<div class="well hidden-sm hidden-md hidden-lg">
		<p>Screen &lt; 768px</p>
	</div>

	<div class="well hidden-xs hidden-md hidden-lg">
		<p>Screen &gt;= 768px and &lt; 992px</p>
	</div>

	<div class="well hidden-sm hidden-xs hidden-lg">
		<p>Screen &gt;= 992px and &lt; 1200px</p>
	</div>

	<div class="well hidden-sm hidden-xs hidden-md">
		<p>Screen &gt;= 1200px</p>
	</div>
```

- IVS是华为的智能视频监控系统，硬件部分主要由前端IPC（摄像头）和后端VCN（主要负责信息的存储，处理，容灾备份，以及与eSDK交互），SDK主要由C++编写，用来和VCN交互，并向上提供API（桌面客户端调用），eSDK Server则是为了方便其他团体二次开发（如设计前端网页，手机应用等），在原有eSDK的基础上包装了一层，并向上提供RESTful风格的web服务（相比起常见的web后端缺少了数据存取层，而是直接调用SDK接口来访问数据），整个系统以微服务形式部署在边缘云Docker镜像中。
- 我在这个项目中负责的部分主要是RESTful API的开发，调用demo的编写，对于SDK新增接口编写JNA调用测试，日志整改，故障排查和排查工具编写。整个项目使用Spring Boot开发，项目中涉及到RESTful API, SOAP（老的web服务风格），log4j日志系统，登录密码的安全传输，回调函数等，学习了常用的设计模式：单例模式，工厂模式。常见的测试框架如Mockito，在调用SDK代码的时候使用了JNA，处理了很多数据类型的映射问题，锻炼了自己阅读接口文档，通过阅读源码和定位问题的能力。了解了一个大型程序整体的架构，学习了真实项目的代码。