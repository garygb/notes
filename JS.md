# JS

存放css有两种方式：

1. 使用<style>tag
2. 使用link

同理，存放js也有两种方式

1. 使用<script> tag

2. 使用link

   

myList.pop()弹出数组中的最后一个元素

myList.shift() 弹出数组中的第一个元素



```
document.getElementById('col1Content');
var collapse = document.getElementsByClassName('collapse');
```



```
document.querySelector('.done');
```

修改<p>元素中的html：

```
var firstPTag = document.querySelector('p');
firstPTag.innerHTML = "New Paragraph <strong>Content</strong>";
```



```
li.className = li.className + " special";
// 去掉also-done
li.className = li.className.replace("also-done","");
```



确定浏览器是否支持某个js函数，到caniuse.com上查找。



href="#"的时候，就相当于重新加载了当前的界面。



sublime text 使用ctrl+D来选中文件中所有的同名的字符串（用来一次性修改）。