# Git 教程

1. **git init** 

   在当前的文件夹里面初始化一个repository

2. **git status**

   看当前的状态

3. **git add \<file\>**

   track the file

   git add .  --> .代表stage everything

   git add *.html --> stage所有的html文件

   若有一些类型的文件不想被stage，如日志文件，那么需要创建一个.gitignore文件(touch .gitignore)，之后用编辑器在里面加入 *.log 这样在stage的时候就会自动忽略掉所有.log文件。

4. git commit

   会进入到一个类似vim的编辑器里面，你可以在里面输入commit message

   另一种方法： git commit -m 'xxxx'

   使用: git commit -a -m 'xxxx' 可以跳过staging step（所有**已经被track的文件**都会被自动staged，并commit）

5. git log

   列出过去提交的日志信息，以及细节的信息如Author,commit的时间，commit notes。

6. git branch MyBranch 

   创建了一个branch，叫做MyBranch

7. git checkout MyBranch

   切换到新创建的branch

   在MyBranch中的commit不会影响到master branch的内容

   (不信切换改点东西并commit，然后切换到master看看)

   ```
   $ git checkout -b dev
   Switched to a new branch 'dev'
   ```

   git checkout命令加上-b参数表示创建并切换，相当于以下两条命令：

   ```
   $ git branch dev
   $ git checkout dev
   Switched to branch 'dev'
   ```

   因为创建、合并和删除分支非常快，所以Git鼓励你使用分支完成某个任务，合并后再删掉分支，这和直接在master分支上工作效果是一样的，但过程更安全。 

8. 合并branch

   先确保你在destination branch(master)，然后输入git merge \<source branch\> 如：git merge MyBranch ，这样，就能把source branch merge到destination branch了。

   当master branch 和 MyBranch都在MyBranch创建之后做了修改，想要合并的时候就会出现conflict。

   ```
     MyBranch
   <<<<<<< HEAD
     This is MyBranch.
   =======
     This is master branch.
   >>>>>>> master
   ```

   <<\<HEAD 以及之下的到===之前内容是该branch里面新增的，===之后到>>>>master的内容是source branch（master）里面增加的。

   删掉这些行，并留下自己想要的（source branch里面改动的和当前branch里改动的）。

9. git stash的用法

   保存做到一半的工作（还不想commit，但是需要转到另外一个branch上干活）

   详细用法见https://git-scm.com/book/zh/v1/Git-%E5%B7%A5%E5%85%B7-%E5%82%A8%E8%97%8F%EF%BC%88Stashing%EF%BC%89

10. git remote

  查看现在存在的远程repositories（代码仓库）

11. git clone https://github.com/garygb/tusk.git

    用来将远程代码仓库克隆到本地（任何人的都可以）

    下到本地后，cd tusk进入tusk，然后git remote 就可以看到有origin,这是https://github.com/garygb/tusk.git的别称。

12. git fetch origin

    将会把服务器上所有自从你上次clone或者fetch后改变的文件都download下来，fetch会把数据下载到local repository但是不会进行merge

    git pull origin

    将会自动fetch并merge the changes from remote branch into your current branch.

13. git push origin master

    会将该本地仓库的内容同步到名叫origin的remote repository，放到master branch中。

14. git remote add \<alias\> \<url>

    例如：git remote add MyRepo https://github.com/garygb/tusk.git

    在本地添加remote repository

15. git reset --hard HEAD^ 回退到上一版本 HEAD^^ 上两个版本 HEAD~100 上100个版本

16. git log 查看提交历史

17. git reflog 查看查看命令历史，用以确定HEAD跳到哪个版本

18. git reset --hard 1094a 

19. git diff readme.txt  查看没有stage的修改

20. 用git diff HEAD -- readme.txt命令可以查看工作区（unstaged）和版本库里面最新版本的区别 

21. git checkout -- file可以丢弃工作区的修改 

22. 命令git reset HEAD <file>可以把暂存区的修改撤销掉（unstage），重新放回工作区 