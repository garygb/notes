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
