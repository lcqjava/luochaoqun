## dubbo协议
 - dubbo:缺省协议，长链接NIO实现，适用于消费者远多于提供者，每个请求数据量很小。
         传文件、视频不适合。
 - rmi:阻塞短连接，使用jdk标准化序列方式。
       适用传入传出参数大小混合，消费者和提供者个数差不多。
 - hessian:基于HTTP,参数数据包比较大
 - thrift:
 - webservice:
 - memcached:
 - redis:
 - rest: