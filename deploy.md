# 上传 win -> linux 
1. dos窗口进入jar包所在路径 
D:\my project\blog\blog-web\target
2. 执行命令
scp blog-web-1.0-SNAPSHOT.jar root@47.112.114.47:\usr\app\blog\

scp D:\my project\blog\blog-web\target\scp blog-web-1.0-SNAPSHOT.jar root@47.112.114.47:\usr\app\blog\
3. 输入密码
TanQinyao790


######################
脚本提示
/usr/app/blog/./blog.sh blog-web-1.0-SNAPSHOT.jar 

启动
/usr/app/blog/./blog.sh blog-web-1.0-SNAPSHOT.jar start

查看启动状态
/usr/app/blog/./blog.sh blog-web-1.0-SNAPSHOT.jar status

查看日志 tail -f 
tail -f /usr/app/blog/logs/【日志文件名】
