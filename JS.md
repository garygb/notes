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



### JS语法

## Unit 1 Introduction to JavaScript

## Unit 2 Variables and Types

### Primitive Types

- Number --> double-precision 64-bit format IEEE 754 values（在JS里面没有Integers）

- String --> sequences of Unicode characters(16-bit) (在JS里面没有character type！ A character is just a string of length 1)

- Boolean

- undefined --> 当一个变量被声明(编译器为一个变量申请了空间)但是没有被定义(z这个变量被赋了值)，这个阶段，在JavaScript里面此变量会被赋予一个特殊的值：undefined, JavaScript为这个值赋予了一个特殊的类型叫做Undefine，这个类型里面只有undefined一个取值。

- null -->这也是一个单独的类型，里面只有一个取值为null。

  ```
  var a;
  console.log(a);  // undefined
  a = null;
  console.log(a);  // null
  ```

- **Difference between undefined and null**

  > 一个类比： 当你填写一个表单，如果某个位置是空的，你怎么知道让别人知道你是没有填（忘记）还是这个内容不使用于你？--> 使用N/A来实现。
  >
  > 这里的null就类似于表单中的N/A。
  >
  > 当一个变量的值是undefined，这代表这个变量的值还没被输入；
  >
  > 当你想赋一个empty，你需要显式地赋一个null值。
  >
  > 当别人看到了null,就知道这个值不是你忘了赋，而是就是empty。

- ES6里面还有一种类型叫做Symbol，类似于C++和Java中的枚举类型，是一组常量的集合，变量可以取这组集合里面的其中一个值.(以上所介绍的适用于ES5)

#### 总结

1. 当你声明一个变量的时候，并没有一个特别的类型信息绑定在这个变量上。

2. 相同的变量可以被赋上不同的type的值。

3. 声明变量的时候，没有作用域信息（如Java中的public等）。

4. 类型信息可以被询问出来

   使用typeof operator：

   ```
   typeof <value>
   typeof <variable>
   ```

   ```
   var a;
   console.log(typeof a); // undefined
   a = 10;
   console.log(typeof a); // number
   a = "hello";
   console.log(typeof a); // string
   a = null;
   console.log(typeof a); // object (should be null, it's a bug made by the language creator)
   a = true;
   console.log(typeof a); // boolean
   ```

### 类型转换（Type coercion）

```
// concatenation with String values
123+"4" = "1234" // 将数字123强制转换为字符串"123"
```

js希望做成一个易于使用的语言，其解释器会基于一些假设，自动cover一些可能是错误的地方。

```
var a = 10;
var b = 10;
var c = "10";
if (a == b) { // true
    console.log("equal");
}
if (a == c) { // true
    console.log("equal");
}
```

当你想要比较两个值的时候，如果两个值的类型不同，则如果其中一个值可以被自动类型转换为另一个值的类型，则先进行自动类型转换，然后再比较转换后的值是否相同。

这实际上会对开发者产生迷惑的作用，但是语言已经推出了，考虑到兼容以前的程序，又不能修改之前的语法功能，只好新设计了一个新的运算符："===",这个运算符提供了你期望的比较功能，不会做type conversion，不同类型的值在进行比较的时候会直接返回FALSE。

```javascript
var a = 10;
var b = 10;
var c = "10";
if (a === b) { // true
    console.log("equal");
}
if (a === c) { // false
    console.log("equal");
}
```

在JavaScript里面，每一个值都有其对应的Boolean值。

- number --> 非0为true
- string -->非空为true
- undefined, null -->均为FALSE

```javascript
var a = 10;
var b = 0;
var c = "10";
var d = "";
var e = undefined;
var f = null;
if (a) { // true
    console.log("a is true");
} 
if (b) { // false
    console.log("b is true");
}
if (c) { // true
    console.log("c is true");
}
if (d) { // false
    console.log("d is true");
}
if (e) { // false
    console.log("e is true");
}
if (f) { // false
    console.log("f is true");
}
```

总结

- JavaScript在类型上很灵活
- 每种类型都有其对应的Boolean类型
- 总是使用===来进行判等(both value and type checks)

## Unit 3 Objects

JavaScript is a object-oriented language, but it's not class-based!

JavaScript objects are of free form, they are not bound to particular class!

We can created object in line in JS.(By using {} )

**由于JavaScript中并不是class-based的，因此你可以在任何时间内向里面添加或者删除你想要的任何东西。**在JavaScript里面，没有预先定义的模板和结构（例如Java里面的类）

### 可以动态地向对象中加入属性

```javascript
var myObj = {};
myObj.prop = "Hello";
console.log(myObj); //Object { prop: "Hello" }
console.log("The myObj property is " + myObj.prop); // The myObj property is Hello(取值还是一样)
```

与模板不同，你可以把JavaScript的对象看成是一个Map（property : value）. Key is property and value is value. 

### 直接初始化属性和值

```javascript
var myObj = {
	"prop" : "Hello",
	"prop2" : 123,
	"prop3" : false
}
```

###没有访问限制符

JavaScript中还有一个特点是和Java或者C++不同的，就是它没有访问限制符。你没有权利看来设置他们的访问权限，所有的属性都是可以被访问的。

###访问不存在的属性

还有一点就是当你访问了一个对象的不存在的属性，C++或Java的编译器会替你检查这个属性是否属于这个类，但是在JavaScript中，由于其不是编译执行的，你没有这个level的protection，当你访问了这个对象的未定义的property的时候，它不会回复你error，只是告诉你这个property是undefined的。

```javascript
console.log("Accessing property that does not exist " + myObj.prop5); // undefined
```

### 总结：JavaScript Objects

- Free-form - not bound to a class
- Object literal notation to create objects
- Object properties can be accessed directly
- New properties can be added on objects directly
- Objects can have methods

###Dot and bracket notations

```javascript
console.log("Accessing using dot notation: " + myObj.prop);
console.log("Accessing using square bracket notation: " + myObj["prop"]);//注意这里属性名字需要加上"".
```

以上两者输出都是相同的。

### The [] notation

- Use [] notation when:

  - Property name is a reserved word / invalid identifier（但是尽量不要使用不合法的标识符在属性名中）

    ```javascript
    var myObj = {
    	"prop" : "Hello",
    	"prop2" : 123,
    	"prop3" : false,
      "1" : "one"
    }
    
    // 属性的名字是一个不合法的标识符（必须以字母或者下划线开头）
    console.log("Accessing using dot notation: " + myObj.1);
    // 但是使用[]就可以正常访问
    console.log("Accessing using square bracket notation: " + myObj["1"]);
    
    /*
    Exception: SyntaxError: missing ) after argument list
    @Scratchpad/1:16
    */
    ```

  - Property name starts with a number(见上述)

  - Property name is dynamic

    ```javascript
    // 假设propertyName是需要用户输入的字符串，此时使用.来获得这个属性的值就不方便了，此时可以使用[]
    var propertyName = "prop2";
    console.log(myObj[propertyName]); // 输出123 
    ```

-  当你使用[] 的时候，它减小了engine优化的可能性，当使用. 的时候，engine事先知道我要访问对象里面的哪个属性，因此可以做一些优化。但是尽量还是使用dot notation.

- Dot and [] notation can be interchanged.
