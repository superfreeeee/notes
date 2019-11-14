# Redis 

## 特點
1. 高性能(key-value)數據庫(NoSQL特性)
2. `list`, `set`, `zset`, `hash`數句結構存儲
3. master-slave模式數據備份
4. 所有操作皆具原子性

# redis客戶端啟動相關
- redis服務啟動
```sh
# 一般啟動
$ redis-server

# 配置啟動(後台運行)
$ redis-server etc/redis.conf
```
- redis客戶端啟動
```sh
$ redis-cli
```
- `PING` 檢測啟動
```sh
$ redis-cli
redis 127.0.0.1:6379>
redis 127.0.0.1:6379> PING

PONG
```
- 遠程啟動
```sh
$ redis-cli -h 127.0.0.1 -p 6379 -a "mypass"
redis 127.0.0.1:6379>
redis 127.0.0.1:6379> PING

PONG
```
# 數據操作相關

## 1. Key 鍵
- 基本格式
```sh
> [command] [key_name]
```
- 實例
```sh
redis 127.0.0.1:6379> SET runoobkey redis
OK
redis 127.0.0.1:6379> DEL runoobkey
(integer) 1
```

### Key 命令
```sh
1	DEL key
# 该命令用于在 key 存在时删除 key。
2	DUMP key
# 序列化给定 key ，并返回被序列化的值。
3	EXISTS key
# 检查给定 key 是否存在。
4	EXPIRE key seconds
# 为给定 key 设置过期时间，以秒计。
5	EXPIREAT key timestamp
# EXPIREAT 的作用和 EXPIRE 类似，都用于为 key 设置过期时间。 不同在于 EXPIREAT 命令接受的时间参数是 UNIX 时间戳(unix timestamp)。
6	PEXPIRE key milliseconds
# 设置 key 的过期时间以毫秒计。
7	PEXPIREAT key milliseconds-timestamp
# 设置 key 过期时间的时间戳(unix timestamp) 以毫秒计
8	KEYS pattern
# 查找所有符合给定模式( pattern)的 key 。
9	MOVE key db
# 将当前数据库的 key 移动到给定的数据库 db 当中。
10	PERSIST key
# 移除 key 的过期时间，key 将持久保持。
11	PTTL key
# 以毫秒为单位返回 key 的剩余的过期时间。
12	TTL key
# 以秒为单位，返回给定 key 的剩余生存时间(TTL, time to live)。
13	RANDOMKEY
# 从当前数据库中随机返回一个 key 。
14	RENAME key newkey
# 修改 key 的名称
15	RENAMENX key newkey
# 仅当 newkey 不存在时，将 key 改名为 newkey 。
16	TYPE key
# 返回 key 所储存的值的类型。
```

## 2. String 字符串
- 基本格式
```sh
> [command] [key_name]
```
- 實例
```sh
redis 127.0.0.1:6379> SET runoobkey redis
OK
redis 127.0.0.1:6379> GET runoobkey
"redis"
```

### String 命令
```sh
1	SET key value
# 设置指定 key 的值
2	GET key
# 获取指定 key 的值。
3	GETRANGE key start end
# 返回 key 中字符串值的子字符
4	GETSET key value
# 将给定 key 的值设为 value ，并返回 key 的旧值(old value)。
5	GETBIT key offset
# 对 key 所储存的字符串值，获取指定偏移量上的位(bit)。
6	MGET key1 [key2..]
# 获取所有(一个或多个)给定 key 的值。
7	SETBIT key offset value
# 对 key 所储存的字符串值，设置或清除指定偏移量上的位(bit)。
8	SETEX key seconds value
# 将值 value 关联到 key ，并将 key 的过期时间设为 seconds (以秒为单位)。
9	SETNX key value
# 只有在 key 不存在时设置 key 的值。
10	SETRANGE key offset value
# 用 value 参数覆写给定 key 所储存的字符串值，从偏移量 offset 开始。
11	STRLEN key
# 返回 key 所储存的字符串值的长度。
12	MSET key value [key value ...]
# 同时设置一个或多个 key-value 对。
13	MSETNX key value [key value ...]
# 同时设置一个或多个 key-value 对，当且仅当所有给定 key 都不存在。
14	PSETEX key milliseconds value
# 这个命令和 SETEX 命令相似，但它以毫秒为单位设置 key 的生存时间，而不是像 SETEX 命令那样，以秒为单位。
15	INCR key
# 将 key 中储存的数字值增一。
16	INCRBY key increment
# 将 key 所储存的值加上给定的增量值（increment） 。
17	INCRBYFLOAT key increment
# 将 key 所储存的值加上给定的浮点增量值（increment） 。
18	DECR key
# 将 key 中储存的数字值减一。
19	DECRBY key decrement
# key 所储存的值减去给定的减量值（decrement） 。
20	APPEND key value
# 如果 key 已经存在并且是一个字符串， APPEND 命令将指定的 value 追加到该 key 原来值（value）的末尾。
```

## 3. Hash 哈希
- 特點
1. 字符串鍵值對，適合存儲對象
2. 可存2^31-1個鍵值對

- 實例
```sh
127.0.0.1:6379>  HMSET runoobkey name "redis tutorial" description "redis basic commands for caching" likes 20 visitors 23000
OK
127.0.0.1:6379>  HGETALL runoobkey
1) "name"
2) "redis tutorial"
3) "description"
4) "redis basic commands for caching"
5) "likes"
6) "20"
7) "visitors"
8) "23000"
```

### Hash命令
```sh
1	HDEL key field1 [field2]
# 删除一个或多个哈希表字段
2	HEXISTS key field
# 查看哈希表 key 中，指定的字段是否存在。
3	HGET key field
# 获取存储在哈希表中指定字段的值。
4	HGETALL key
# 获取在哈希表中指定 key 的所有字段和值
5	HINCRBY key field increment
# 为哈希表 key 中的指定字段的整数值加上增量 increment 。
6	HINCRBYFLOAT key field increment
# 为哈希表 key 中的指定字段的浮点数值加上增量 increment 。
7	HKEYS key
# 获取所有哈希表中的字段
8	HLEN key
# 获取哈希表中字段的数量
9	HMGET key field1 [field2]
# 获取所有给定字段的值
10	HMSET key field1 value1 [field2 value2 ]
# 同时将多个 field-value (域-值)对设置到哈希表 key 中。
11	HSET key field value
# 将哈希表 key 中的字段 field 的值设为 value 。
12	HSETNX key field value
# 只有在字段 field 不存在时，设置哈希表字段的值。
13	HVALS key
# 获取哈希表中所有值
14	HSCAN key cursor [MATCH pattern] [COUNT count]
# 迭代哈希表中的键值对。
```

## 4. List 列表
- 特點
1. 字符串列表
2. 可存2^32-1個元素

- 實例
```sh
redis 127.0.0.1:6379> LPUSH runoobkey redis
(integer) 1
redis 127.0.0.1:6379> LPUSH runoobkey mongodb
(integer) 2
redis 127.0.0.1:6379> LPUSH runoobkey mysql
(integer) 3
redis 127.0.0.1:6379> LRANGE runoobkey 0 10

1) "mysql"
2) "mongodb"
3) "redis"
```

### List命令
```sh
1	BLPOP key1 [key2 ] timeout
# 移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
2	BRPOP key1 [key2 ] timeout
# 移出并获取列表的最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
3	BRPOPLPUSH source destination timeout
# 从列表中弹出一个值，将弹出的元素插入到另外一个列表中并返回它； 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
4	LINDEX key index
# 通过索引获取列表中的元素
5	LINSERT key BEFORE|AFTER pivot value
# 在列表的元素前或者后插入元素
6	LLEN key
# 获取列表长度
7	LPOP key
# 移出并获取列表的第一个元素
8	LPUSH key value1 [value2]
# 将一个或多个值插入到列表头部
9	LPUSHX key value
# 将一个值插入到已存在的列表头部
10	LRANGE key start stop
# 获取列表指定范围内的元素
11	LREM key count value
# 移除列表元素
12	LSET key index value
# 通过索引设置列表元素的值
13	LTRIM key start stop
# 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。
14	RPOP key
# 移除列表的最后一个元素，返回值为移除的元素。
15	RPOPLPUSH source destination
# 移除列表的最后一个元素，并将该元素添加到另一个列表并返回
16	RPUSH key value1 [value2]
# 在列表中添加一个或多个值
17	RPUSHX key value
# 为已存在的列表添加值
```

## 5. Set 集合
- 特點
1. String的無序集合，無重複成員
2. 可存2^32-1個成員

- 實例
```sh
redis 127.0.0.1:6379> SADD runoobkey redis
(integer) 1
redis 127.0.0.1:6379> SADD runoobkey mongodb
(integer) 1
redis 127.0.0.1:6379> SADD runoobkey mysql
(integer) 1
redis 127.0.0.1:6379> SADD runoobkey mysql
(integer) 0
redis 127.0.0.1:6379> SMEMBERS runoobkey

1) "mysql"
2) "mongodb"
3) "redis"
```

### Set 命令
```sh
1	SADD key member1 [member2]
# 向集合添加一个或多个成员
2	SCARD key
# 获取集合的成员数
3	SDIFF key1 [key2]
# 返回给定所有集合的差集
4	SDIFFSTORE destination key1 [key2]
# 返回给定所有集合的差集并存储在 destination 中
5	SINTER key1 [key2]
# 返回给定所有集合的交集
6	SINTERSTORE destination key1 [key2]
# 返回给定所有集合的交集并存储在 destination 中
7	SISMEMBER key member
# 判断 member 元素是否是集合 key 的成员
8	SMEMBERS key
# 返回集合中的所有成员
9	SMOVE source destination member
# 将 member 元素从 source 集合移动到 destination 集合
10	SPOP key
# 移除并返回集合中的一个随机元素
11	SRANDMEMBER key [count]
# 返回集合中一个或多个随机数
12	SREM key member1 [member2]
# 移除集合中一个或多个成员
13	SUNION key1 [key2]
# 返回所有给定集合的并集
14	SUNIONSTORE destination key1 [key2]
# 所有给定集合的并集存储在 destination 集合中
15	SSCAN key cursor [MATCH pattern] [COUNT count]
# 迭代集合中的元素
```

## 6. Sorted Set 有序集和
- 特點
1. 同一般Set
2. 每個分量包含一個double型分數(score)用於排序(score可重複)
3. 最大成員數2^32-1

- 實例
```sh
redis 127.0.0.1:6379> ZADD runoobkey 1 redis
(integer) 1
redis 127.0.0.1:6379> ZADD runoobkey 2 mongodb
(integer) 1
redis 127.0.0.1:6379> ZADD runoobkey 3 mysql
(integer) 1
redis 127.0.0.1:6379> ZADD runoobkey 3 mysql
(integer) 0
redis 127.0.0.1:6379> ZADD runoobkey 4 mysql
(integer) 0
redis 127.0.0.1:6379> ZRANGE runoobkey 0 10 WITHSCORES

1) "redis"
2) "1"
3) "mongodb"
4) "2"
5) "mysql"
6) "4"
```

### Sorted Set 命令
```sh
1	ZADD key score1 member1 [score2 member2]
# 向有序集合添加一个或多个成员，或者更新已存在成员的分数
2	ZCARD key
# 获取有序集合的成员数
3	ZCOUNT key min max
# 计算在有序集合中指定区间分数的成员数
4	ZINCRBY key increment member
# 有序集合中对指定成员的分数加上增量 increment
5	ZINTERSTORE destination numkeys key [key ...]
# 计算给定的一个或多个有序集的交集并将结果集存储在新的有序集合 key 中
6	ZLEXCOUNT key min max
# 在有序集合中计算指定字典区间内成员数量
7	ZRANGE key start stop [WITHSCORES]
# 通过索引区间返回有序集合指定区间内的成员
8	ZRANGEBYLEX key min max [LIMIT offset count]
# 通过字典区间返回有序集合的成员
9	ZRANGEBYSCORE key min max [WITHSCORES] [LIMIT]
# 通过分数返回有序集合指定区间内的成员
10	ZRANK key member
# 返回有序集合中指定成员的索引
11	ZREM key member [member ...]
# 移除有序集合中的一个或多个成员
12	ZREMRANGEBYLEX key min max
# 移除有序集合中给定的字典区间的所有成员
13	ZREMRANGEBYRANK key start stop
# 移除有序集合中给定的排名区间的所有成员
14	ZREMRANGEBYSCORE key min max
# 移除有序集合中给定的分数区间的所有成员
15	ZREVRANGE key start stop [WITHSCORES]
# 返回有序集中指定区间内的成员，通过索引，分数从高到低
16	ZREVRANGEBYSCORE key max min [WITHSCORES]
# 返回有序集中指定分数区间内的成员，分数从高到低排序
17	ZREVRANK key member
# 返回有序集合中指定成员的排名，有序集成员按分数值递减(从大到小)排序
18	ZSCORE key member
# 返回有序集中，成员的分数值
19	ZUNIONSTORE destination numkeys key [key ...]
# 计算给定的一个或多个有序集的并集，并存储在新的 key 中
20	ZSCAN key cursor [MATCH pattern] [COUNT count]
# 迭代有序集合中的元素（包括元素成员和元素分值）
```

## 7. HyperLogLog
- 基數
最小無重複元素集合

- 特點
1. 基數統計算法，所需空間小而固定
2. 根據輸入計算基數而不存儲輸入元素本身

- 實例
```sh
redis 127.0.0.1:6379> PFADD runoobkey "redis"
1) (integer) 1
redis 127.0.0.1:6379> PFADD runoobkey "mongodb"
1) (integer) 1
redis 127.0.0.1:6379> PFADD runoobkey "mysql"
1) (integer) 1
redis 127.0.0.1:6379> PFCOUNT runoobkey
(integer) 3
```

### HyperLogLog 命令
```sh
1	PFADD key element [element ...]
# 添加指定元素到 HyperLogLog 中。
2	PFCOUNT key [key ...]
# 返回给定 HyperLogLog 的基数估算值。
3	PFMERGE destkey sourcekey [sourcekey ...]
# 将多个 HyperLogLog 合并为一个 HyperLogLog
```

# 服務相關

## 1. Publish/Subscribe 發布/訂閱
- 訂閱
```sh
redis 127.0.0.1:6379> SUBSCRIBE redisChat

Reading messages... (press Ctrl-C to quit)
1) "subscribe"
2) "redisChat"
3) (integer) 1
```
- 發布
```sh
redis 127.0.0.1:6379> PUBLISH redisChat "Redis is a great caching technique"
(integer) 1
redis 127.0.0.1:6379> PUBLISH redisChat "Learn redis by runoob.com"
(integer) 1
# 订阅者的客户端会显示如下消息
1) "message"
2) "redisChat"
3) "Redis is a great caching technique"
1) "message"
2) "redisChat"
3) "Learn redis by runoob.com"
```

### Publish/Subscibe 命令
```sh
1	PSUBSCRIBE pattern [pattern ...]
# 订阅一个或多个符合给定模式的频道。
2	PUBSUB subcommand [argument [argument ...]]
# 查看订阅与发布系统状态。
3	PUBLISH channel message
# 将信息发送到指定的频道。
4	PUNSUBSCRIBE [pattern [pattern ...]]
# 退订所有给定模式的频道。
5	SUBSCRIBE channel [channel ...]
# 订阅给定的一个或多个频道的信息。
6	UNSUBSCRIBE [channel [channel ...]]
# 指退订给定的频道。
```

## 2. 事務
- 特點
1. 可一次執行多個命令
2. 批量操作在EXEC前被放入隊列緩存
3. 單條命令失敗不影響其餘命令執行(單條命令具備原子性，事務則否)
4. 事務處理過程不接收客戶端提交的命令

- stage 階段
1. 開始事務
2. 命令入隊
3. 執行事務

- 實例
```sh
redis 127.0.0.1:6379> MULTI
OK
redis 127.0.0.1:6379> SET book-name "Mastering C++ in 21 days"
QUEUED
redis 127.0.0.1:6379> GET book-name
QUEUED
redis 127.0.0.1:6379> SADD tag "C++" "Programming" "Mastering Series"
QUEUED
redis 127.0.0.1:6379> SMEMBERS tag
QUEUED
redis 127.0.0.1:6379> EXEC
1) OK
2) "Mastering C++ in 21 days"
3) (integer) 3
4) 1) "Mastering Series"
   2) "C++"
   3) "Programming"
```

### 事務命令
```sh
1	DISCARD
# 取消事务，放弃执行事务块内的所有命令。
2	EXEC
# 执行所有事务块内的命令。
3	MULTI
# 标记一个事务块的开始。
4	UNWATCH
# 取消 WATCH 命令对所有 key 的监视。
5	WATCH key [key ...]
# 监视一个(或多个) key ，如果在事务执行之前这个(或这些) key 被其他命令所改动，那么事务将被打断。
```

## 3. Script 腳本
- 基本格式
```sh
redis 127.0.0.1:6379> EVAL script numkeys key [key ...] arg [arg ...]
```

- 實例
```sh
redis 127.0.0.1:6379> EVAL "return {KEYS[1],KEYS[2],ARGV[1],ARGV[2]}" 2 key1 key2 first second

1) "key1"
2) "key2"
3) "first"
4) "second"
```

### Script 命令
```sh
1	EVAL script numkeys key [key ...] arg [arg ...]
# 执行 Lua 脚本。
2	EVALSHA sha1 numkeys key [key ...] arg [arg ...]
# 执行 Lua 脚本。
3	SCRIPT EXISTS script [script ...]
# 查看指定的脚本是否已经被保存在缓存当中。
4	SCRIPT FLUSH
# 从脚本缓存中移除所有脚本。
5	SCRIPT KILL
# 杀死当前正在运行的 Lua 脚本。
6	SCRIPT LOAD script
# 将脚本 script 添加到脚本缓存中，但并不立即执行这个脚本。
```

## 4. 連接
- 實例
```sh
redis 127.0.0.1:6379> AUTH "password"
OK
redis 127.0.0.1:6379> PING
PONG
```

### 連接命令
```sh
1	AUTH password
# 验证密码是否正确
2	ECHO message
# 打印字符串
3	PING
# 查看服务是否运行
4	QUIT
# 关闭当前连接
5	SELECT index
# 切换到指定的数据库
```

## 5. Server 服務器
- 實例
```sh
redis 127.0.0.1:6379> INFO

# Server
redis_version:2.8.13
redis_git_sha1:00000000
redis_git_dirty:0
redis_build_id:c2238b38b1edb0e2
redis_mode:standalone
os:Linux 3.5.0-48-generic x86_64
arch_bits:64
multiplexing_api:epoll
gcc_version:4.7.2
process_id:3856
run_id:0e61abd297771de3fe812a3c21027732ac9f41fe
tcp_port:6379
uptime_in_seconds:11554
uptime_in_days:0
hz:10
lru_clock:16651447
config_file:

# Clients
connected_clients:1
client-longest_output_list:0
client-biggest_input_buf:0
blocked_clients:0

# Memory
used_memory:589016
used_memory_human:575.21K
used_memory_rss:2461696
used_memory_peak:667312
used_memory_peak_human:651.67K
used_memory_lua:33792
mem_fragmentation_ratio:4.18
mem_allocator:jemalloc-3.6.0

# Persistence
loading:0
rdb_changes_since_last_save:3
rdb_bgsave_in_progress:0
rdb_last_save_time:1409158561
rdb_last_bgsave_status:ok
rdb_last_bgsave_time_sec:0
rdb_current_bgsave_time_sec:-1
aof_enabled:0
aof_rewrite_in_progress:0
aof_rewrite_scheduled:0
aof_last_rewrite_time_sec:-1
aof_current_rewrite_time_sec:-1
aof_last_bgrewrite_status:ok
aof_last_write_status:ok

# Stats
total_connections_received:24
total_commands_processed:294
instantaneous_ops_per_sec:0
rejected_connections:0
sync_full:0
sync_partial_ok:0
sync_partial_err:0
expired_keys:0
evicted_keys:0
keyspace_hits:41
keyspace_misses:82
pubsub_channels:0
pubsub_patterns:0
latest_fork_usec:264

# Replication
role:master
connected_slaves:0
master_repl_offset:0
repl_backlog_active:0
repl_backlog_size:1048576
repl_backlog_first_byte_offset:0
repl_backlog_histlen:0

# CPU
used_cpu_sys:10.49
used_cpu_user:4.96
used_cpu_sys_children:0.00
used_cpu_user_children:0.01

# Keyspace
db0:keys=94,expires=1,avg_ttl=41638810
db1:keys=1,expires=0,avg_ttl=0
db3:keys=1,expires=0,avg_ttl=0
```

### Server 命令
```sh
1	BGREWRITEAOF
# 异步执行一个 AOF（AppendOnly File） 文件重写操作
2	BGSAVE
# 在后台异步保存当前数据库的数据到磁盘
3	CLIENT KILL [ip:port] [ID client-id]
# 关闭客户端连接
4	CLIENT LIST
# 获取连接到服务器的客户端连接列表
5	CLIENT GETNAME
# 获取连接的名称
6	CLIENT PAUSE timeout
# 在指定时间内终止运行来自客户端的命令
7	CLIENT SETNAME connection-name
# 设置当前连接的名称
8	CLUSTER SLOTS
# 获取集群节点的映射数组
9	COMMAND
# 获取 Redis 命令详情数组
10	COMMAND COUNT
# 获取 Redis 命令总数
11	COMMAND GETKEYS
# 获取给定命令的所有键
12	TIME
# 返回当前服务器时间
13	COMMAND INFO command-name [command-name ...]
# 获取指定 Redis 命令描述的数组
14	CONFIG GET parameter
# 获取指定配置参数的值
15	CONFIG REWRITE
# 对启动 Redis 服务器时所指定的 redis.conf 配置文件进行改写
16	CONFIG SET parameter value
# 修改 redis 配置参数，无需重启
17	CONFIG RESETSTAT
# 重置 INFO 命令中的某些统计数据
18	DBSIZE
# 返回当前数据库的 key 的数量
19	DEBUG OBJECT key
# 获取 key 的调试信息
20	DEBUG SEGFAULT
# 让 Redis 服务崩溃
21	FLUSHALL
# 删除所有数据库的所有key
22	FLUSHDB
# 删除当前数据库的所有key
23	INFO [section]
# 获取 Redis 服务器的各种信息和统计数值
24	LASTSAVE
# 返回最近一次 Redis 成功将数据保存到磁盘上的时间，以 UNIX 时间戳格式表示
25	MONITOR
# 实时打印出 Redis 服务器接收到的命令，调试用
26	ROLE
# 返回主从实例所属的角色
27	SAVE
# 同步保存数据到硬盘
28	SHUTDOWN [NOSAVE] [SAVE]
# 异步保存数据到硬盘，并关闭服务器
29	SLAVEOF host port
# 将当前服务器转变为指定服务器的从属服务器(slave server)
30	SLOWLOG subcommand [argument]
# 管理 redis 的慢日志
31	SYNC
# 用于复制功能(replication)的内部命令
```





