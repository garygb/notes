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

### 对象中可以内嵌别的对象

如下：

```js
var myObj = {
	"prop" : "Hello",
	"prop2" : 123,
	"prop3" : false,
	"innerObj" : {"innnerProp" : "This is a inner proprty value."}
}
```

可以使用：

```js
myObj.innerObj.innerProp；
// or
myObj.innnerObj["innnerProp"]；
```

来访问它。

### JavaScript的对象机制类似于Java，是变量存放着对象的内存地址

```js
var myObj1 = {
  "myProp" : "hello"
}
var myObj2 = myObj1; //让myObj2"指向"与对象myObj1相同的地址

myObj2.myProp = "modified";

console.log(myObj1.myProp);  // 打印出modified
```

###判等

```js
var myObj1 = {
  "myProp" : "hello"
}
var myObj2 = myObj1;

myObj2.myProp = "modified";
myObj3 = {
   "myProp" : "hello"
}
console.log(myObj1 === myObj2); // true, 指向了同一个对象
console.log(myObj1 === myObj3); // false, 指向了不同对象，尽管值相等还是false
```

### Undefined vs. Null for Objects

```js
var person = {
    'firstName': 'Huang',
    'lastName' : 'Guobin'
}
```

当你使用：

```js
person.middleName; // undefined
```

当你想要显式地设置你没有某个属性的时候：

```js
var person = {
    'firstName': 'Huang',
    'middleName' : null;
    'lastName' : 'Guobin'
}
```

此时调用：

```js
person.middleName; // null
```

这样显式地告诉了调用者你不是没有设置这个属性，而是你本身就没有middle name.

### 删除属性

第一种办法（错误的方法）

```js
var person = {
    'firstName': 'Huang',
    'lastName' : 'Guobin',
    'age' : 20
}
...
person.age = undefined; 
...
console.log(person.age); // undefined
```

以上的做法看起来好像和直接删除了age属性是一样的，但是当你查看person对象的时候就会发现：

```js
Object { firstName: "Huang", lastName: "Guobin", age: undefined }
```

打印出的对象里面依然有age这个属性，只是其值设置为了undefined,并没有直接把他删除掉。

更好的办法是：

```js
delete person.age;
...
person.age; // undefined, 输出person也会发现age属性消失了
```

### 数组

```js
var arr = [1, 2, 3]; // defined in line
arr[0]; // access the element
arr[3]; //当你访问out of bound的数组元素的时候，会返回 undefined
```

类似于对象，数组也可以动态加入和删除元素。

```js
arr[3] = "hello";
```

事实是，所有的JavaScript数组本质上是一个JavaScript对象。只不过数组可能有一些特殊的属性在里面。

查看这个数组的内容：

```js
(4) […]
0: 1
1: 2
2: 3
3: "hello"
length: 4
__proto__: Array []
```

可以看见，数组就是一个对象，这个对象的每个属性名字就是数组的index，值就是数组在那个index的value。相比起对象，数组中自动加入了一个属性:length.

可以使用访问对象的方式来访问数组：

```js
arr["0"]; // 与arr[0]等价，由于属性名是数字，因此不能使用.来访问
```

但为什么使用`arr[0]`可以访问？

原因是解释器将数字0自动类型转换为了字符串。（实际上对于对象，也可以发生这种转换）

如下：

```
var obj = {"0": 1};
...
obj[0]; // 返回1 
```

访问数组的长度：

```js
arr.length;
```

使用一个新的变量指向这个数组：

```js
var arr2 = arr;
```

你可以随意添加index：

```
arr[10] = 1;
```

查看数组的结构：

```js
(11) […]
0: 1
1: 2
2: 3
10: 1
length: 11
__proto__: Array []
```

可以看到数组的长度属性记录的不是数组中实际元素的个数，而是数组最后一个元素的索引加1.

你可以设置非数字的属性：

```
arr["foo"] = "abc";
```

查看数组结构：

```js
(11) […]
0: 1
1: 2
2: 3
10: 1
foo: "abc"
length: 11
__proto__: Array []
```

长度还是不变（最大的索引决定）。

### 包裹对象(Wrapper Objects)

我们知道string是一个基本类型，但是我们可以使用`s.length`来获得字符串的长度，实际上发生的事情是：string这个基本类型会被自动转换为对应的等价对象，然后那个对象里面有个属性是length。

但当你随后查看`typeof s`的时候，你会发现还是一个string类型，这是因为等价对象是临时创建的，并不会将这个对象赋值给s, 当我们使用完了length之后，这个对象立即就被消除了。

四种有包裹对象的基本类型：String, boolean, number, symbol.

### 函数

Functions are also Objects.

js的函数参数是很flexible的，你如果提供的少了，他会把未提供的默认为undefined(注意在C++和Java中，这是无法通过编译的)，如果提供的多了，JavaScript会直接忽略掉这些多出来的参数。

```js
function sayHello(name, timeOfDay) {
    console.log("Hello " + name + 
               ", Time of day is " + timeofDay);
}

sayHello("Gary"); // Hello Gary, Time of day is undefined
sayHello("Gary", "afternoon", "ignored"); // won't be an error
```

因此，函数重载是无法在JS中实现的（同名的函数有不同的参数，你调用的参数类型和个数决定了真正执行的函数是哪个）。

### 返回值

由于是弱类型的，所以没有返回值类型在函数的声明里面。

我们可以直接使用return;

```js
function sayHello() {
    console.log("Hello");
    return;
}
var result = sayHello(); // 此时并没有返回值赋给result，因此result为undefined
```

### 函数表达式

在JavaScript中，function is first class object.

``` js
var f = function fun() {  // This is called a function expression
    console.log("hello");
}
f(); //找到这个变量绑定的函数，然后执行
```

由于我们完全没有用过fun这个名字，我们将这个函数赋给了一个变量，之后我们就通过这个变量名来引用了，于是我们可以将原来的函数改写为：

```js
var f = function () {  // This is called a function expression
    console.log("hello");
}
```

这叫做你们函数表达式(Anonymous Function Expression).

此时当你对f赋予别的值的时候(e.g. `f = 1; f(); //error`)，原来这个函数表达式的信息就消失了。

###Functions as arguments

```js
var executor = function (fn) {
    fn();
}

executor(f); // passing the function var as argument
```

### this关键字

在Java中，一个类里面有成员变量和成员函数，在JS里面，函数和变量都可以是property。

在函数成员中，直接使用变量名来访问属性是不够健壮的(fragile code)，如下情况：

```js
var person = {
    "firstName": "Huang",
    "lastName": "Guobin",
    "getFullName": function() {
        return person.firstName + " " + person.lastName;
    }
}

var person2 = person;
person = {};
console.log(person2.getFullName()); // 这会输出"undefined undefined"
```

可以看到实际上变量就是一个指向内存某个区域的指针，在这里我将原来的person交给了新的变量来管理，并将原来的person指向了新的内容，此时在调用getFullName，可以发现由于函数内部我硬编码了firstName和lastName这两个属性绑定的对象，因此我在修改了这些属性所属的对象之后，函数就不工作了。

因此我需要使用this这个关键字, 它代表了这个函数成员所属的对象，即使在将内容交给不同的对象后，这个函数依然能执行：

```js 
var person = {
    "firstName": "Huang",
    "lastName": "Guobin",
    "getFullName": function() {
        return this.firstName + " " + this.lastName;
    }
}

var person2 = person;
person = {};
console.log(person2.getFullName()); // 这会输出"Huang Guobin"
```

###Implicit Arguments

每个JavaScript的函数都有一个默认的参数arguments.(另一个implicit argument是this)

这个arguments**类似**一个array，保存了所有的入参的值：

（注意：传入的这个arguments并不是一个array，它实际上是一个Object，除了索引外，有些可以对array使用的方法不能使用到arguments上）

``` js
var add = function(a, b) {
    var i, sum=0;
    for (i = 0; i < arguments.length; i++) {
        sum += arguments[i]; // 可以像数组一样使用这个参数
    }
   	return sum;
}

console.log(add(1,2,3,4,5)); // return 15
```

###Summary: JavaScript Functions

1. Functions can be written in literal form
2. A function is a "value" that can be assiged to a variable
3. Can be called by passing in arguments
4. Functions are objects!
5. Flexible argument count
6. No function overloading
7. Default arguments
8. The **arguments** argument
9. Function Declaration vs. Function Expression vs. Anonymous Function Expression
10. Functions as object property

## Unit 5 Wrapping up

### Array提供的方法

1. push(xxx) 从最后插入
2. pop() 弹出最后一个元素
3. shift() 取出第一个元素 
4. unshift(xx) 将这个元素插入到数组的第一个位置

### Array的forEach方法

在调用forEach方法的时候，当你传入一个函数变量，forEach会自动为你传入一些参数：

1. item: 这个数组遍历到的元素
2. index: 这个元素的index
3. array: 这个数组本身

``` js
myArray.forEach(function(item, index, array) {
   console.log(item, index，array);
});
```

如下是一个调用的例子：

``` js 
var myArray = [10, 20, "hello"];
var myFunction = function(item) {
  console.log(item);
};

myArray.forEach(myFunction);
```

### 常用的内置对象

1. Math对象： 提供了一些数学方法的调用
2. Date对象：提供了一些关于时间的方法

### Next Steps

- Scopes and clousures
- Objects and Prototypes
- Asynchronous JavaScript - Callbacks and Promises
- Client side frameworks
- Server side frameworks