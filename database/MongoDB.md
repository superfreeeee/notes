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

## 與RDBMS對應術語
RDBMS | MongoDB
-|-
數據庫(database) | 數據庫
表格(table) | 集合
行(row) | 文檔
列(column, field) | 字段
表聯合 | 嵌入字段
主健(primary key) | 主鍵(自帶 `_id` 作為主鍵)

### 文檔 Document(行、單筆數據)
```json
{"column_name": "value", "column_name": "value"}
```

### 集合 (文檔組、table)
```json
{"site":"www.baidu.com"}
{"site":"www.google.com","name":"Google"}
{"site":"www.runoob.com","name":"菜鸟教程","num":5}
```

## commands
```sh
# 顯示數據庫列表
show dbs
# 當前數據庫對象或集合
db
# 連接到數據庫
use [db]
```

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