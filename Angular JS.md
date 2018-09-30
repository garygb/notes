#Angular JS

安装环境angular cli：

```shell
npm install -g @angular/cli 
```

我们安装所有的javaScript包都可以使用`npm install`来进行安装。

这个叫做"scoped" package name, These names are of format @group/package.

如果不加`-g`参数，我们仅仅将angular安装在当前的目录下。加上了`-g`意味着安装angular globally。这样我就能在任何的目录下运行angular CLI。

通过：

```
ng -v
```

来查看是否正确安装。

使用：

```
ng new <project-name>
```

来创建新的工程。

创建好之后，使用VSCode来打开，注意到package.json中存放了当前项目依赖的所有third party dependencies。

使用：

```shell
ng serve
```

来创建一个服务器，并部署当前的angular程序，执行成功后就可以在打开`localhost:4200`来查看这个工程了！

### Component

Component is a combination of a view and backing logic.（每个component都可以自解释，他拥有了所有需要的东西）

每个component都由一个.html文件，一个.css文件，两个个.ts文件组成。

一个view由静态的html和动态的typescript文件组成。

使用以下命令来自动生成新的component：

```
ng generate component hello-world
```

这里创建了一个hello-world的component:

![](/img/2.gif)

![](/img/1.gif)

