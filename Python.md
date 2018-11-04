# Python

###强制类型转换

```python
x = 3.5
y = int(3.5) # Java 中的强制类型转换为(int) 3.5
```

### 除法

```python
x = 3 / 4 
y = 3 // 4   # same as Java division
print("x=", x, ", y=", y, sep="") # x=0.75, y=0
```

### print

```python
# 有逗号隔开的，则Python会自动在输出的时候加上空格，如果不想要空格，则可以自己指定分隔符
print("hello", "world", sep = "")  
# Python在默认的情况下会在末尾加上换行符号，如果不想换行，可以自己指定结尾(end)的符号
print("hello", end="")  
print("World")
```

### input

```python
p = input()
q = input("Enter a number: ") # input with prompt
r = float(input("Enter a number: ")) # cast it to a float number
print(p, q, r)
```

### 字符串长度

```python
s = input()
print("Length = ", len(s)) 
```

###获取子字符串

```python
s = "hello" 
print("Substring 1:", s[1:]) # 从index为1开始，至字符串末尾
print("Substring 2:", s[1:3]) # 从index为1开始，至index为3之前(不包括index为3)
print("Substring 3:", s[-3:]) # 输出后3个字符
print("Substring 4:", s[-3:-1]) # 输出index在-3（倒数第三个）以后，并不包括-1(最后一个)
```

输出的结果为：

```
Substring 1: ello
Substring 2: el
Substring 3: llo
Substring 4: ll
```

###判断子字符串是否存在

```python
s = "hello"
print("Is string 'el' in s?", ("el" in s)) # True
```

###List

对于Java中：

```Java
List list = new ArrayList();
list.add("House");

```



```python
arr = [] # Python中的List可以插入任意的对象（没有规定一定要统一类型）
```



```python
# 3行4列
a = np.zeros((3,4))
b = np.ones((1,2))
c = np.empty((3,4)) # 非常接近于0的数字
# 从10到34，步长为2, reshape是更改矩阵的形状
d = np.arange(10,34,2).reshape((3,4))
# 从0到12
e = np.arange(12).reshape((3,4))
# 定义了1到10之间有5段
f = np.linspace(1,10,5)
```



```
c = a * b
c_dot = np.dot(a, b)
```

