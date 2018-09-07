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

