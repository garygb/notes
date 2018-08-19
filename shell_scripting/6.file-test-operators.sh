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
