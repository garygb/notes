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



TODO： TS basics  tutorial 7