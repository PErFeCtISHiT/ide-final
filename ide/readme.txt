BFIDE工作流程
user：
newuser：新建用户，在fileAdmin中保存其用户名和密码
login：登陆，会在file菜单的open菜单里更新可open的文件
logout 登出，会在file菜单的open菜单里更新可open的文件
delete 删除，在管理员（账号admin，密码pis）模式下可以查看所有用户并删除用户，登陆一次管理员模式可以看到可删除的用户

未登录状态下不可新建文件及编辑代码，未新建文件或打开文件状态下不可编辑代码

file-new：新建文件，生成用户名_文件名.bf或者.ook文件
file-open：打开该用户的某个文件
file-save：将text中的内容保存到文件中
file-run：将text区存到文件里再取出来执行
file-delete：删除某个文件

mode-bf：bf模式
mode-ook：ook模式

version-i：返回第i个版本的代码

用整体代换实现redo和undo

bf语言的输入实例
io：字符
loop：1233，出现重复字符
if：若干字符加回车
add：字符+字符空格
mult：字符*字符空格