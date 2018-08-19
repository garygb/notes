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
