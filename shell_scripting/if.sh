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
