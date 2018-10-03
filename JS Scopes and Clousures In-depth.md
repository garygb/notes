# JS Scopes and Clousures In-depth

##Unit 1 Understanding Scopes

JS 仅支持Function Scoping，不支持Block Scoping.

```js
var name = "Gary";
if (name == "Gary") {
    var department = "Engineering";
}
console.log(name); // Gary
console.log(department); // Engineering， 因为JavaScript中不存在Block Scoping
```

但是如果将一个对象定义在函数内部，则在外部无法访问这个对象：

```js
var name = "Gary";
function allocateDepartment() {
    // 注意这里name是定义在外部的，定义在Outter sccope的对象可以在inner scope中使用
    // (如果在inner scope中找不到这个对象的话)
    if (name == "Gary") { 
    	var department = "Engineering";    
    }
}
console.log(name); // Gary
console.log(department); // Undefined, JavaScript中支持Function Scoping
```

### IIFE(Immediately invoked function expression)

在js中，我们要尽量避免使用全局变量，因为当一个page载入很多的js文件的时候，这些文件很有可能是属于不同人写的，你不知道别的人会不会和你声明一样的全局变量。

```js
var a = 1; // Will pollute the global namespace
var b = 2;
console.log(a + b);
```

更好的做法是将全局变量转化为局部变量，一个很好的做法就是将这些代码全部放到一个函数中去：

```js
function myFun() {
    var a = 1; // local variable
    var b = 2;
    console.log(a + b);
}

myFun(); // invoke the function
```

但是这样又引入了新的全局标志符myFun，这也有可能和别人声明的函数出现冲突。

以下使用匿名函数的方式，并保证在函数声明的时候就自动调用这个函数：

```js
(function () {
    var a = 1; 
    var b = 2;
    console.log(a + b);
})();  // IIFE
```

###Read and Write Operation

当你未声明就使用一个变量的时候，写操作是可以的（JS会替你创建这个变量在global scope中），但是读操作是不行的（执行会报错）。

```js
a = 1; //OK
console.log(b); // error
a = b; // error, read of var b is wrong
```

### The Window Object

当你创建了一个global variable，你就相当于创建了一个window这个全局变量的一个属性。（全局函数也是类似，其也是window变量的一个属性）

这个window变量是和你所载入的页面对应的。

```js
var abc = 1;
abc // 直接访问
window.abc // 通过window对象访问
```

注意这个window全局变量名随浏览器的不同会不同。在其他运行环境，如node，这个变量叫做global.



##Unit 2 Compilation and Interpretation

2 phase：

1. compilcation phase: Look at the declaration, and register the variable.(Book-keeping stuff)
2. interpretation phase: Look at the scope chain that was created during the compilcation step, In order to  identify the what variables to use.

注意: 当你在当前作用域中找不到你使用的object的时候，会找one-level-up的作用域，直到找到记录了这个对象的作用域或者抵达global scope。

![](/img/5.gif)

如上图，c这个变量在myFn中并未定义，于是解释器从上一层的scope也就是global scope中查找这个变量，但是很遗憾，在global scope中也没有找到这个变量，于是由于这个变量是一个写操作，解释器就在**全局作用域**中创建了这个变量。

注意由于一个page中可能会有很多的js文件，因此这样的行为可能会带来很大的危害，所以要记得一定要在使用某个变量前声明他。

如下：

![](/img/4.gif)

以上由于一直找到global scope也没找到c这个变量的声明，于是便throw an error。

在以下的代码中，`console.log(c);`输出的是undefined：

![](/img/6.gif)

原因就在于complication step和interpretation step是相互独立开的。因此在scope chain中查询b的时候，实际上是早到了inner function 的scope中的b（这个b是在compile step中被加入的），而且很遗憾，这个时候b还并未被赋值。

以下的代码也是同样的道理：

```js
console.log(a); // undefined (Not an acccess to a undeclared variable)
var a = 10;
```

### Hoisting

**Almost as if when the interpreter runs, the variable declarations are hoisted to the top.**(这是由于JS的2 phase step导致的)

这对function也是一样:

```js
myFn1(); // calling before the function is declared (Works!)

function myFn1() {
    ...
}
```

这样的做法对recursion很重要：

```jsx
function fnA() {
    fnB(); // OK, because the declaration(compilation) step always go first.
}

function fnB() {
    fnA();
}
```

如果没有hositing，那么无论是哪个函数定义在前，都将无法正常访问。

注意hositing对于function declaration有效，但是对于function expression无效：

```js
fnA(); // execute undefined var

var fnA = function() { // assigment is in the interpretation step
    ...
}
```

### Strict Mode

在实际写代码的时候，由于JS的语法带来了很多的问题：

```js
var myName = "";

myname = "Gary"; // 由于写错了variable的名字，导致了创建了一个新的全局变量，而myName也没有正确被赋值
```

在首行加入`"use strict";`配置为strict mode:

```js
"use strict"; // Runs the whole thing in strict mode
var myName = "";

myname = "Gary"; // 在strict mode中，解释器会阻止对未声明的变量赋值
/*
Exception: ReferenceError: assignment to undeclared variable myname
@Scratchpad/1:4:1
*/
```

但是以上的做法会导致一个page中的所有js文件都被配置为strict mode， 将strict mode的声明放到函数体内部：

```js
(function () {
    "use strict";
    var myName = "";
    myname = "Gary";
})();
```

这样, strict mode will only apply to this function.(其他的一些类库不会受到strict mode的影响)



## Unit 3 Closures

当一个函数被创建的时候，它还会记住创建这个函数时候的所有scope information。

如下：

```js
var a = 10;

function outer() {
    var b = 20;
    
    var innner = function() {
        console.log(a);
        console.log(b);
    } 
    return inner; // 返回这个内部创建的函数对象
}

var innerFn = outer();
innerFn(); // 在外部调用这个函数对象(照样会正常输出10和20)
```

在外部调用这个函数对象innerFn的时候，照样会正常输出a和b的值，尽管这时候已经离开的b的作用域。**因为当一个函数被创建的时候，它还会记住创建这个函数时候的所有scope information。**(即使这个返回的对象交给了另外一个文件，那个文件里面甚至没有这个全局的a,函数也能正常执行)

The function has the snapshot of the scope chain. It knows each variable it needs to point to.（记录下了所有这些需要变量的位置）