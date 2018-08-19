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
