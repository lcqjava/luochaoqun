介绍：每个线程会创建一个副本的变量。像connection、session中有应用，如session实例，很多地方会用到，并且需要用到session实例需要在方法中传递，而ThreadLocal在本线程获取即可。

## 场景:
-- 每个线程需要自己单独的实例
-- 实例需要在多个方法中共享，但不希望被多线程共享

## 主要方法：
-- get():获取当前线程下的threadLocalMap变量，如果为空则调用initThreadlocal方法创建一个，如果不为空则获取entry(threadLocalMap.get(this)),entry不为空则获取value，如果为空则调用initThreadLocal方法



