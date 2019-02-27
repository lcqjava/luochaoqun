## 编译.java文件
javac CpuProblem.java

## 执行.class文件
java mnt.test.cpu.CpuProblem

## top
查看最消耗cpu的进程id

## 导出进程线程栈
jstack pid >> cpuStack.log

## 查看进程中cpu消耗过高的线程
top -p PID -H

## 将线程ID转换成16进制,会得到一数字
printf "%0x\n" 线程id

## 在日志中搜索线程id，可以看到具体的类信息

