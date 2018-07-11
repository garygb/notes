###一. Linux命令行

使用Ctrl+Alt+T进入Terminal。

1. **ls [option] \[file\]**

   - ls 显示当前目录中的文件和文件夹
   - ls ~ 同上
   - ls /home/programmingknowledge/ 绝对路径下的文件
   - ls Documments/ 相对路径下的文件
   - ls .. 上一层
   - ls ../.. 上两层
   - ls -l 显示详细信息
   - ls -a 显示所有文件（包括隐藏文件，隐藏文件以.开头）
   - ls -al 组合ls -l 和 ls -a
   - ls -lS 根据文件的大小排序（组合上l是因为这样看着明确）
   - ls Documents/*.html 显示Documents文件夹中所有的.html文件
   - ls Documents/\*.\* 显示Documents文件夹中所有文件
   - ls -lS > output.txt 将结果放到output.txt中
   - ls  -d */ 显示所有的文件夹
   - ls -R 递归显示当年目录下所有的文件和文件夹

2. **man**用于查看工具的用户手册

   如： man ls

3. **clear** 清屏（可向上滚动看之前的）

4. **pwd** print working directory

5. **cd dir**

   - cd / 进入root文件夹
   - cd ~ 进入home文件夹
   - cd .. 进入父文件夹
   - cd /home/programmingknowledge/ 绝对路径
   - cd Documents/ 相对路径
   - cd My\ Books 使用\加空格的方法来进入中间有空格的文件夹
     - 或者 cd "My Books",  cd 'My Books'

6. **cat options file1 file2 ..**

   - 作用有三，一是显示txt文件的内容，二是将多个txt文件的内容组合起来，三是创建新的txt文件。


   - cat 进入echo模式，结束用Ctrl+D表示这是文件尾
   - cat list1.txt 显示出list1.txt的文本
   - cat list1.txt list2.txt 显示出list1.txt的文本，并接着显示list2.txt的文本
   - cat -b list1.txt 显示出list1.txt的文本，并在每一个**非空行**之前加上行号
   - cat -n list1.txt 显示出list1.txt的文本，并在每一个行之前加上行号
   - cat -s list1.txt 显示出list1.txt的文本，有多个空行则只显示一个
   - cat -E list1.txt 显示出list1.txt的文本，在每行结尾显示$

7. **重定向**

   - cat > test.txt 把在terminal中输入的内容重定向输入到test.txt中（注意这样的如果原来test.txt中有内容会被清除）
   - cat >> text.txt 向原有的文本文件内容后添加内容
   - cat list1.txt list2.txt > out.txt 将list1.txt和list2.txt中的内容拼接起来写入到一个新的文件out.txt中
   - cat list1.txt >> list2.txt 将list1.txt中的内容添加到list2.txt中

8. **mkdir 创建文件夹**

   - mkdir image 创建文件夹image
   - mkdir image/pics 在子目录image下创建文件夹pics（但是如果当时没有创建文件夹image，则不能直接创建pics）
   - mkdir -p image/pics 在没有创建父文件夹image的情况下直接创建pics（-p等于--parent）
   - mkdir -p names/{Tom,Tony,Gary} 创建一些子文件夹（注意中间不要有空格）

9. **rmdir -option dir** 删除文件夹

   - mkdir -p a/b/c/d/e

     rmdir a/b/c/d/e  （删除了文件夹e）

     rmdir -p a/b/c/d/e （删除了以及所有删除后变为空的父文件夹，如果删除之后发现里面还是非空，那么会提示删除失败，具体可以用-pv看）

     rmdir -pv a/b/c/d/e （相比于-p，会打印出所有删除的信息--verbose）

10. **rm -option fd** 删除文件和文件夹

  - rm -rv a/b/ 递归删除b以及b下的所有文件和文件夹
  - rm a.txt 删除一个文件

11. **cp options source destination** 拷贝文件和文件夹

    - cp file1.txt file2.txt  将一个文件的内容拷贝到另一个文件中（如果file2.txt不存在就创建一个，file2.txt如果原来里面有什么内容会被清除）
    - cp  file1.txt dir1/ 将file1.txt拷贝到dir1目录下（文件名还是叫file1.txt）
    - cp file1.txt file2.txt dir2/ 拷贝多个文件
      - 如果dir2里面已经有一个文件叫做file1.txt 那么拷贝会overwrite掉这个原先的文件，解决方法是使用 -i 的选项（代表interactive），在每次将要覆盖掉原来的文件的时候都会询问。(y - overwrite, n - not overweite)
    - cp ../file1.txt ../file2.txt . 将上一层目录中的file1.txt和file2.txt拷贝到当前目录下(. 表示当前目录)
    - cp -r dir1 dir3 将文件夹dir1已经里面的所有文件和文件夹拷贝到dir3中（没有就新建，将dir1中的子文件和文件夹拷贝到dir3中，如果存在则直接将文件夹dir1（包含所有子文件和文件夹）拷贝到dir3中）

12. **mv options source destination** 移动文件

    - mv file1.txt file2.txt 相当于改名
    - mv file2.txt dir1/ 移动到dir1文件夹下（如果dir1下已经有file2.txt那么就会overwrite它，防止重写可以使用 -i 进入interactive模式）
    - mv dir1 dir2 将文件夹dir1及其内部所有文件移动到dir目录下（注意这里不用 -r）

13. **less** 用来打开一个很长的文本文件

    - less big.txt 使用箭头上下键来调整页面，使用空格键向后翻页，使用B键向前翻页，使用大写的G跳到文末，使用小写的g跳到文首。
    - 使用‘/’加你要查找的内容来从前向后查找  如: /book (使用n跳转到下一个)
    - 使用‘?’加你要查找的内容来从前向后查找  如: ：?book (使用n跳转到下一个)

14. **touch** 创建一个新的空文件/ 改变现有文件和文件夹的时间戳

    - touch file1.txt 如果file1.txt不存在，那么就创建了一个新的文件，如果file1.txt已经存在，那么就会更新这个文件的时间戳

15. **nano** 一个编辑器，可以创建编辑文本

16. **sudo** super user do, 获得管理员权限

    - e.g.  sudo apt-get install gcc
    - 使用sudo -s 或者 su 进入特权模式 (使用exit退出)

17. **top** 显示进程CPU占用比，内存使用率等

    - i键可以过滤掉所有idle的进程
    - s键可以设置刷新时间
    - k键可以kill进程，输入pid，按两次回车就行
    - q键退出

18. **kill -flags pid**

    - pidof unity-control-center  --> 返回8514(pid是动态变化的)

      kill 8514  --> kill掉这个进程

      kill -KILL 8514 --> 强制结束这个进程

      kill -9 8514 -->与上面一样，强制结束进程，一般用在普通的kill无法关掉进程的情况下

    - ps -ux 可以找到本user的所有运行的进程，在这个里面你可以找到你想结束的进程

    - ps -aux 可以看到所有的user的进程

    - ps -u root 可以看到root的所有进程（不一定要root，可以是各个合法的用户名）

    - ps -C gnome-terminal 可以显示所有gnome-terminal进程的instance

19. **echo** 

    - echo hello world --> 返回hello world

    - echo "hello world" --> 返回 hello world

    - var="Mark" --> 定义一个变量var，并赋值为"Mark"

      echo $var --> 返回var变量的值Mark（只在当前terminal的session下有效）

    - x=10

      echo "The value of x is $x." --> 返回 The value of x is 10.

    - echo -e 'some \text' -->加上-e代表打开了escape mode，那么\t就会被解读为tab符，输出 some     ext

20. chmod 改变权限

    - chmod o+x file1.txt 为file1.txt 的other使用者增加可执行这个权限
    - o-other g-group u-user/owner of the file
    - \+ -> 增加权限 - ->减少权限
    - r -> read w -> write x ->executable
    - -rw-rw-rwx  -->第一个代表是文件(-)还是文件夹(d) 后面依次是user的权限，group的权限，others 的权限（每三个一组）
    - 执行 chmod ug=rw -->那么user和group的权限变为了rw-
    - chmod a-rwx（取消了所有人的所有权限）
    - chmod u+rw,g+w,o+x 1.txt

21. 改变文件夹的权限

    - 取消了write权限的文件夹将在该文件夹中无法创建文件
    - 取消了read权限，可以进入该文件夹，但是没法使用ls
    - 取消了文件夹的执行权限，那么你就没法cd 进入这个文件夹了

22. 权限的八进制表示

    - chmod 755 file1.txt --> u-7 g-5 o-5 其中7-111(rwx) 5-101(r-x)  

23. bash脚本入门

    - 开头 #!/bin/bash
    - 之后可以写一系列的command

24. which & whatis

    - which bash -->返回bash二进制文件的绝对路径
    - whatis ls    --> 返回ls的short description

25. useradd 用来创建user

    - sudo useradd gary -m -s /bin/bash -g users -c "my comment"（-m 为该用户创建一个默认的home目录， -s  /bin/bash 允许该用户使用shell， -g users 设置该用户所属的默认group为users，-c "xxx" 为该用户加上comment "xxx"）

26. userdel 用来删除user

    - sudo userdel gary 删除用户的用户名，密码，但不会删除用户的home directory （员工离职，但是不希望删除他所做的东西）
    - sudo userdel -r gary 删除用户的用户名，密码，home目录

27. groups

    - groups 列出你自己这个用户所在的所有组
    - cat /etc/group 列出所有的组
    - sudo groupadd Java 加入一个组，叫做Java
    - sudo groupdel Java 删除一个组，叫做Java
    - sudo gpasswd -a username groupname  (-a add user, -d delete user 向某个组里面插入/删除用户)

28. .bashrc文件

    - .bashrc文件是在打开新的terminal时候执行的一个脚本文件
    - 修改(定制) .bashrc 文件可以设置环境变量(如在安装好Java的时候要设置Java home目录)，修改默认的打开terminal的方式
    - 如： 加入 echo "hello world" 则会在每次打开命令行的时候都会打印出hello world
    - 加入 alias ls='ls -l' 每次在命令行中输入ls都会显示long list

29. stat filename用来查看文件的属性

    - stat test.c 可以看见文件的大小，权限，最近访问时间，最近修改时间……

30. df 用来查看文件系统的disk usage(disk free)

    - df -h 显示human readable的格式

31. du filename 用来看这个文件占用了多少磁盘空间（文件的大小）

    - du -sh dir1 （h用来以human readable的格式显示，s表示看summary，不加s看到的是该文件夹以及所有子文件夹的大小）
    - 使用du -h看到的是 . 文件的大小（当前文件夹）

32. free 可以看到该操作系统下可使用的内存，swap memory

    - -b (in bytes) -k(in KB) -m(in MB) -g(in GB) -h(in human readable format)

33. watch 用来周期性地执行命令/脚本

    - watch free -h 可见每两秒（默认是两秒）会刷新一次内存的使用情况（使用ctrl+C来结束）
    - watch -n 0.5 free -m 使用-n来显式指定周期（这里是0.5sec）

34. head/tail 用来显示文件的前十行/尾十行（一般用在log文件上）

    - head -n3 dmesg 看dmesg的前3行（n可以不加）
    - tail -f dmesg 可以看到dmesg的后10行，而且不会退出，也就是如果dmesg文件中的日志项持续增加的话，则会动态显示该日志当前的最后10行记录
    - head dmesg kern.log 可以同时显示多个文件的内容

35. find 用来搜索一个文件夹下的文件

    - find /home/ -name test.sh  在/home/文件夹下查找名字叫test.sh的文件（*.sh 查找所有的shell脚本 hello.\* 查找所有的hello为文件名的文件）
    - 如果有些文件夹需要访问权限，就在find 之前加上sudo
    - find /home/magiclab/ -mtime -2 两天之前到现在创建的里面找（不带-的，直接用2的话说明是恰好两天之前创建的）

36. 使用Ctrl+R来搜索之前输入过的指令

    - history

37. apt-get （apt: advanced package tool，适用于基于Debian的系统，基于red hat的系统如centos使用yum）

    - sudo apt-get update 将本地的包和remote的包进行同步（更新）

    - sudo apt-get install php5 安装php5，安装完之后可以看一下php5 -v，如果出现了版本信息，说明安装成功

    - sudo apt-get remove php5 卸载php5

    - 如果希望卸载的时候把配置文件也删除，使用： sudo apt-get remove --purge php5 

    - 因为在安装php5的时候，把有依赖关系的软件也安装了，那么在卸载掉php5之后，如果希望也卸载掉那些不再需要用到的软件，使用：

      sudo apt-get autoremove

38. wc test.txt

    统计test.txt中的行数，word数，character数

    wc -c test.txt --> 仅仅统计byte(character)数量

    -l 行数 

    -w word数

    -L 最长的行的字节数

39. cal 显示日历

    cal \<year> 显示该年的日历

    cal 2 2014 显示2014年2月的月历

    cal -3 显示上一个月 当月 以及下一个月

40. date 显示当前系统时间

    使用date -s "11/20/2003 12:48:00" 设置系统时间

    date "+%d%h%y" 显示特定格式的时间，具体用法百度或者看man page
