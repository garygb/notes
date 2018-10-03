# Shell Scripting

###1. Introduction

### 2. Using Variables and Comments

```shell
#! /bin/bash
# this is a comment
echo "hello world." #this is also a comment

# two type of  variable(在变量名之前加上$)
# 1. system variable -- created and maintained by Linux OS (UPPER CASE)
# 2. user defined variable -- created and maintained by user (defined in lower cases)

echo Our shell name is $BASH #bash shell name
echo Our shell version is $BASH_VERSION
echo Our name directory is $HOME
echo Our current working directory is $PWD

#define user defined variable
#WRONG: name = Mark (Do not have space!)
name=Mark
#Wrong: (Do not name the var starting with number)
#10val=10
#use the variable
echo The name is $name.
echo value is $10val
```

###3. Read User Input

```shell
#! /bin/bash

echo "Enter names : "
read name1 name2 name3  # your input(from terminal) will assigned to this variable
echo "Entered names : $name1, $name2, $name3."

#允许你在提示的同一行输入input
read -p 'username: ' user_var
#让你静默地输入内容（如密码）
read -sp 'password: ' pass_var

# echo with no string (用于换行)
echo

echo username is $user_var.
echo password is $pass_var.

#将一个数组读入names变量
echo "Enter names:"
read -a names
echo "Names: ${names[0]} ${names[1]} ${names[2]}" 

#不指定read的变量的时候，就会读入默认的变量中
read 
echo "Read into default var REPLY $REPLY."
```

### 4. Passing Arguments to a Bash-Script

```shell
#! /bin/bash

# $0是传入的第0个参数，也就是脚本名
# $1 $2 ... 分别代表传入的第i个参数
# 调用的时候： ./arguments.sh Tony Marry Gary
# 此时 $0=./arguments.sh $1=Tony $2=Marry $3=Gary
echo $0 $1 $2 $3 ' > $1 $2 $3'

#args表示的是一个数组
# 将输入的参数保存为一个数组
args=("$@")

# 通过index来访问数组中的成员
echo ${args[0]} ${args[1]} ${args[2]}

# 直接输出整个数组($@ is a default var for array)
echo $@

# 输出传入脚本的参数的个数($# is a default var for # of arguments)
echo $#
```

### 5. If Statement(if then, if then else, if elif else)

```shell
#! /bin/bash

count=10
word=abc
# 注意这里[]需要与其他的符号有空格隔开
#if [ $count -eq 10 ]
# 或者用以下的方式
#if [ $count > 9 ]
#if (($count > 9))

# 在shell中，== 和 = 的意义是相同的(对于String)
#if [ $word = "abc" ]
#if [ $word != "abcdef" ]
# 当你使用string的 > < 符号的时候，需要使用[[]]
if [[ $word > "acb" ]]
then
	echo "condition 1 is true"
# 可以使用elif then 来增加判断
elif [ $word = 'abc' ]
then
	echo "condition 2 is true"
else
	echo "condition is false"
fi

#if [condition]
#then
#    statement
#fi
```

### 6. File test operators

```shell
#! /bin/bash

# \c 是用来使光标处在echo结束后的同一行
# 使用-e使得echo可以解释 \c (used to enable the interpretation of backslash)
echo -e "Enter the name of the file: \c"

read file_name

# -e 文件是否存在
# -f 文件是否是regular file
# -d 文件是否是directory

# character special file(文本文件-b) VS block special file(二进制文件-c)
# -s 文件是否不为空
# -r 文件是否具有读权限 -w 写 -x 执行
if [ -s $file_name ]
then
	echo "$file_name not empty."
else
	echo "$file_name empty."
fi
```

### 7. How to append output to the end of text file

 