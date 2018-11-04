# TypeScript

##Unit 1: Introduction and Setting Up

编码环境：

1. Visual Studio Code

2. Node.js (TypeScript Compiler是一个js程序，而且编译好的js代码也需要运行在node.js上)

3. TypeScript Compiler

   使用以下命令来安装typeScript Compiler package: （npm是node.js package manager, -g说明这个compiler可以在全局使用）

   ```
   npm install typescript -g
   ```

编译typescript脚本：

```shell
tsc <typescript filename>
```

运行js脚本：

```
node <javascript filename>
```

##Unit 2: Basic Features and Types

### Type declarations

```typescript
var a: number; // a is a variable of type number
var b: boolean;
var c: string;
a = 10;
a = true; //编译器会报错 Type 'true' is not assignable to type 'number'
b = true;
c = 'hello';
```

注意typescript的类型定义是后缀定义，这与Java中的前缀定义还是有区别的。

`undefined`和`null`都是基本数据类型，其类型中只包含了一个可能的元素。因此这两种类型我们一般不需要声明其类型。

### 定义数组类型

```typescript
var myArr: number[];  // array of type number
myArr = []; // ok
myArr = [1,2,3,4]; // ok
myArr = [1,true]; // compliation error!
```

但是使用了类型定义的数组之后，有时候我们仍然要使用可以放各种类型元素的数组，这个时候我们就需要使用**tuple**：

```typescript
var myArr: [number, boolean]; // for tuple declarations, the data types are specified like elements inside the  []
myArr = [1, true]; // ok
myArr = [100, false]; // ok
myArr = [2]; // Error! The array must have the same size as you declared!
```

###类型擦除和错误行为

当你的JS代码在编译后，所有的类型信息都会消失，因为这只是TypeScript为了保证你在写代码的时候不反类型错误的一个保证。

当你在TypeScript中出现了错误的时候，Typescript编译器还是会编译生成相应的JS代码。

**The TypeScript compiler flags errors during development time for the developers, to alert the developers making sure that they know what they are doing, and do compilation anyway if the thing it's compiling is a valid JavaScript. **

(如以上的代码，在编译之后还是一个合法的JavaScript脚本，只是在`myArr = [2];`违背了之前声明的元组规范，这依然能够被正确编译)

**The traditional compiler stops the compliaton process, because the intent is to make sure that the compiled code is error free.**

### Typing with functions

```ts
function myAdd(a: number, b: number) {
    return a + b;
}
var sum = myAdd("foo", 1); // 会提示error
var sum = myAdd(1,2,3); // error: expected 2 arguments, but got 3
var sum2 = myAdd(1); // error: expected 2 arguments, but got 1
var sum3 = myAdd(1, 2); // OK
```

The number of arguments to function in JS do not have to match the function signature. You can pass in more(ignore) of less(filled with `undefined`) arguments that what's declared in the function.

###Optional and Default Arguments

TypeScript在默认的情况下会强制要求传入参数的个数要和函数声明时候一致。

如果在参数名的结尾加上了`?`这个符号，说明这个参数是可选的。

可选的参数必须放在函数声明的末尾。

可选参数的个数不唯一，可以有多个。

``` ts
function myAdd(a:, b: number, c?: number) {
    ...
}
myAdd(1,2); // OK
myAdd(1,2,3); // OK
```

使用以下方式给可选的参数赋默认值：

```js
function myAdd(a: number, b: number, c = 0) { // c is the default value
    return a + b + c;
}
var sum: number = myAdd(1,2);
console.log(sum); // 3
console.log(myAdd(1, 2, 3)); // 6
```

```ts
function myAdd(a: number, b: number, c:number = 0, d?: number) { // c is the default value
    console.log(d); // undefined
    return a + b + c;
}
```

### Specifying Function Return Type

```js
function myAdd(a: number, b: number, c:number = 0, d?: number): number { // c is the default value
    console.log(d); // undefined
    return a + b + c;
}
```

###Implicit Typing

```ts
var a = 10;
a = true; // error: Type 'true' is not assignable to type 'number'.
```

函数的case：

```ts
function greet(): string { // 不显式写出返回的类型, 类型依然能正确推导出来
    return "Good Morning";
}
var greeting = greet(); // implicit typed as String
```

甚至当你不声明函数的返回值，由于返回的明显是一个字符串类型的变量，因此将返回值赋给greeting的时候，编译器也知道greeting是一个string。

但是当年将变量的声明和变量的赋值拆分为两行的时候，会发现他的类型不再是String了，而是Any：

```ts
var greeting;
greeting = greet(); // implicit typed as Any
greeting = 10; // OK!!
```

只有在一个变量的声明和赋值(初始化)在同一行的时候，implicit typing才有效。

###Any and Union Types

在TS里面，我们也希望有一些变量想JS里面那样灵活，可以容纳各种类型，对于这种变量，我们可以将他们声明为`Any`类型：

```ts
var b: any;
b = 1; // OK
b = "hello"; // OK

var c: number | boolean;  // union type
c = 1;
c = true;
c = "e"; // error
```

Any告诉编译器，不要做类型检查，我可以将任何类型赋给b。

Union type可以让编译器loosen the type checking。只要是`number`类型或者`boolean`类型的都可以接受。

## Unit 3: Classes and Interfaces



```ts
class Person {
    firstName; // declare the member variable
    lastName: string; // add type constraint
}

var aPerson: Person = new Person(); // declare the var of type Person(注意这里如果不直接声明是属于Person类型的，那么也会被implicitly typed as Person)
aPerson.firstName = "Huang";
aPerson.lastName = "Guobin";
console.log(aPerson.firstName);
console.log(aPerson);
```

编译，查看生成的JavaScript代码：

``` ts
var Person = /** @class */ (function () {
    function Person() {
    }
    return Person;
}());
var aPerson = new Person(); 
aPerson.firstName = "Huang";
aPerson.lastName = "Guobin";
console.log(aPerson.firstName);
console.log(aPerson);
```



```
class Person {
    firstName; // declare the member variable
    lastName: string; // add type constraint

    // Works every time you call a "new Person()" (Initialization)
    constructor() {
        this.firstName = " ";
        this.lastName = " ";
    }

    // constructor(firstName: string, lastName: string) {
    //     this.firstName = firstName;
    //     this.lastName = lastName;
    // }

    getFullName() {
        return this.firstName + " " + this.lastName; 
    }
}

var aPerson: Person = new Person(); // declare the var of type Person(注意这里如果不直接声明是属于Person类型的，那么也会被implicitly typed as Person)
aPerson.firstName = "Huang";
aPerson.lastName = "Guobin";
console.log(aPerson.firstName);
console.log(aPerson.getFullName());
console.log(aPerson);
```

注意：TS不允许有多个构造函数存在。



《tutorial 14》