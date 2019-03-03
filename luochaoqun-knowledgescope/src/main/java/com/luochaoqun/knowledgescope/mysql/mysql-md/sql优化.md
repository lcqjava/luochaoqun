## sql性能优化

### 优化目标

1. 减少IO次数，数据库操作中90%时间都是io占用了

2. 减少CPU计算，函数尽量在代码中处理

----

### 优化方法

1. 分析复制sql，explain select * from ,分析执行计划

2. 定长类型和变长类型

3. 只需要一条记录用limit 1,找到了就直接返回了

4. 看情况使用exists和in，因为执行计划是in会限制性in括号里的，而exists会以外表作为驱动。in适用于外表大而内表小，exists适合外表小而内表大的情况。

5. 使用join代替子查询，因为子查询需要建立临时表，然后在这个临时表的基础上查询，查询完毕撤销这些临时表。而join不需要建立临时表，性能会更快。

6. 使用union 代替or，用union all 代替 union，union会涉及到排序增加cpu的运算加大资源消耗

7. 尽量早过滤，分页查询先过滤好分页数据

8. 避免类型转换，会导致索引无法使用情况

9. 避免在where后面字段null值判断，会导致放弃索引而进行权标扫描

10. 避免使用!=,>,<,会放弃使用索引索引而进行全表扫描

11. 对连续的数值使用between

12. 避免在where自居进行表达式操作

13. 避免where子句进行函数操作，如：

    select * from tb_a where substring(name,1,2) = 'luo'

    替换为

    select * from tb_a where name like 'luo%'

14. 尽量使用数字型字段而非字符型

15. 使用varchar代替char

    