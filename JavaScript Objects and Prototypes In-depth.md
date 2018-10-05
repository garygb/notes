# JavaScript Objects and Prototypes In-depth

##Unit 1 Creating Objects

**Approach 1:**

```js
var emp1 = {};
emp1.firstName = "Michael";
emp1.lastName = "Scott";
emp1.gender = "M";
emp1.designation = "Regional Manager";

// OR
var emp1 = {
    "firstName": "Michael",
    "lastName": "Scott",
    "gender": "M",
    "designation": "Regional Manager"
};
```

但是使用上述的方法很容易漏掉初始化某些属性。而且当创建的对象很多的时候，每个对象都要手动去这样创建也很麻烦，效率很低，代码重复高。

**Approach 2:**

```js
function createEmployeeObject(firstName, lastName, gender, designation) {
    var newObject = {}; // initialize a empty object
    newObject.firstName = firstName;
    newObject.lastName = lastName;
    newObject.gender = gender;
    newObject.designation = designation;
    return newObject;
}
var emp3 = createEmployeeObject("Jim", "Halpert", "M", "Sales Representation");
```

使用第二种方法来创建对象在js中很常见，JS为这种方法创建了一种快捷方式：使用consructor functions来创建对象。

观察可以注意到，`var newObject = {};`和`return newObject;`这两行对于所有的创建对象的函数都是共同的。

### JavaScript Constructors

调用一个函数有两种方式：

1. Constructor mode
2. Regular mode

**Call function in construct mode:**

```js
var emp3 = new Employee("Jim", "Halpert", "M", "Sales Representation");
```

在函数之前加上new，等于告诉解释器这个函数是一个constructor function。

1. 省掉了公共的部分（2行）
2. 变量名是标准的this
3. 遵循构造器的规约（函数首字母大写）

``` js
// The function name indicate that it's a constructor
function Employee(firstName, lastName, gender, designation) {
    // var this = {};
    this.firstName = firstName;
    this.lastName = lastName;
    this.gender = gender;
    this.designation = designation;
    // return this;
}
```

In other class-based languages, the convention is to name classes with initial-case. In JavaScript, constructor functions are named that way.

**使用new关键字来调用普通的函数也是可行的**，解释器补充进去的两句话，前一句并没有起到实际作用，后一句并不会执行：

```js 
function createEmployeeObject(firstName, lastName, gender, designation) {
   	// var this = {};
    var newObject = {}; // initialize a empty object
    newObject.firstName = firstName;
    newObject.lastName = lastName;
    newObject.gender = gender;
    newObject.designation = designation;
    return newObject;
    // return this;
}
var emp3 = new createEmployeeObject("Jim", "Halpert", "M", "Sales Representation");
```

**使用普通的模式来调用构造函数是不可行的**，并没有新的对象被创建出来了，返回值是undefined：

```js
// The function name indicate that it's a constructor
function Employee(firstName, lastName, gender, designation) {
    // var this = {};
    // 此时Employee函数中的this指向的是gloabal object.
    this.firstName = firstName;
    this.lastName = lastName;
    this.gender = gender;
    this.designation = designation;
    // return this;
}
// 由于Employee函数并没有返回任何值，因此emp3变量中的值为undefined
var emp3 = Employee("Jim", "Halpert", "M", "Sales Representation"); 
```

####Always make sure which mode to call which function.



##Unit 2 Understanding the `this` function

在JavaScript中，调用函数有四种方式：

```js 
function foo() {
    console.log("hello");
    console.log(this);
}
foo(); // Method #1 (Simple function object)

var obj = {"prop": "This is the object itself!"};
obj.foo = function() {
    console.log("hello");
    console.log(this);
}
obj.foo(); // Method #2 (Referring the function as a property of a object)

new foo(); // Method #3 (In constructor mode)

...        // Method #4
```

在函数调用的时候，本来就有两个隐式的参数：`this`, `arguments`.

`this`为何值取决于使用哪种方式来调用这个函数：

####Method #1

`this`的值是global object。global object的值却取决于runtime environment。如当你将这个JS脚本运行在浏览器中的时候，那么`this`的值是`window`。当你运行在node.js环境中的时候，`this`的值是`global`。

####Method #2

当你调用一个函数in the context of an object, `this`的值就是那个对象。如上面method 2的`this`就是`obj`的引用。

####Method #3

当你使用construcotr mode调用一个函数的时候，`this`指向的是解释器帮你制造的新对象。

调用`new foo()`的时候，`this`打印出的是新创建的什么属性也没有的一个新的对象。



|  No  |                        Method                        |      This Reference      |
| :--: | :--------------------------------------------------: | :----------------------: |
|  1   |        Calling standalone functions directly         |    The global object     |
|  2   | Calling functions as property of an object reference |   The object reference   |
|  3   |   Calling standalone functions using `new` keyword   | The newly created object |
|  4   |                                                      |                          |

以下是一个使用this的实例：

以下有两个不同的函数，`Bicycle`函数需要在constructor mode下被调用；`inflateTire`是需要作为一个对象的函数属性被调用。这两个函数的类型不同，因此函数中使用的`this`也不相同。尽管其中一个函数存在于另一个函数之中。

``` js
// Function meant to be called in contructor mode.
function Bicycle(cadence, speed, gear, tirePressure) {
    // 这里的this是解释器新创建的对象
    this.cadence = cadence;
    this.speed = speed;
    this.gear = gear;
    this.tirePressure = tirePressure;
    this.inflateTires = function() {
        // 注意当我需要调用这个函数的时候，是作为对象中的一个属性来调用的，那么此时this指向的是这对象本身
        this.tirePressure += 3;
    }
}

// Calling the function in construtor mode
var bicycle1 = new Bicycle(50, 20, 4, 25);
bicycle1.inflateTires(); // tirePressure变为了28
```

