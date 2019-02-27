# nginx

## 配置解析

1.proxy_set_header Host $host

说明：查看日志发现会输出当前nginx服务器的ip，$host是当前nginx服务器

2.proxy_set_header Host $proxy_host

说明：$proxy_host 需要转发的服务器

3.proxy_set_header X-Real-IP $remote_addr

说明：$remote_add为客户端的真实ip


4.proxy_set_header Host \$host:\$proxy_port

说明：打印日志会有显示nginx监听的端口


5.proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for

说明：如果是一台nginx效果一样，如果有多台nginx，$remote_addr是上一台nginx的ip

