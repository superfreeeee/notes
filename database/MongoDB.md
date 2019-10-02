# MongoDB
- 分佈式文件存儲數據庫

## 與SQL對照
SQL | MongoDB | description
---|----|---
database | database | 数据库
table | collection | 数据库表---集合
row | document | 数据记录行---文档
column | field | 数据字段---域
index | index | 索引
table joins | - | 表连接,MongoDB不支持
primary key | primary key | 主键,MongoDB自动将_id字段设置为主键

## commands
command | description
-|-
show dbs | 顯示數據庫列表
db | 當前數據庫對象或集合
use [db] | 連接到數據庫

## 元數據
namespace | description
-|-
dbname.system.namespaces | 列出所有名字空间
dbname.system.indexes | 列出所有索引
dbname.system.profile | 包含数据库概要(profile)信息
dbname.system.users | 列出所有可访问数据库的用户
dbname.local.sources | 包含复制对端（slave）的服务器信息和状态

## Notice

### 保留數據庫
name | description
-|-
admin | 相當於root權限數據庫
local | 不被複製，用於存儲本地單台服務器的任意集合
config | 保存分片信息