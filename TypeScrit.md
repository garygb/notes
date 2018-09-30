# TypeScrit

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

