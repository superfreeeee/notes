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

## commands(使用js腳本)
```sh
# 顯示數據庫列表
$ show dbs
# 當前數據庫對象或集合
$ db
# 連接到數據庫
$ use [db]

# 顯示數據庫
$ show dbs

# 插入數據(增)
$ db.[collection].insert({[key]: [value]})
$ db.[collection].insertMany([{[key1]: [value1]}, {}[key2]: [value2], {[key3]: [value3]}])

# 查詢數據(查)
$ db.[colleciton].findOne()
$ db.[colleciton].find()

# 修改數據(改)
$ db.[collection].update({sample}, {target})  # sample表示要更改數據, target表示更新後數據

# 刪除操作(刪)
$ db.[collection].remove({sample})
```

## 基本數據格式
- 以BSON作為文檔數據存儲和網路傳輸格式

### Number 數字
- 默認為64位浮點數
```sh
$ db.sang_collec.insert({x: 3.1415926})
$ db.sang_collec.insert({x: 3})
```
- 整數使用 `NumberInt` 和 `NumberLong`
```sh
$ db.sang_collec.insert({x: NumberInt(10)})
$ db.sang_collec.insert({x: NumberLong(12)})
```

### String 字符串
- 直接存儲
```sh
$ db.sang_collec.insert({x: "hello world"})
```

### 正則表達式(同js)
- 主要用於查找(find)
```
$ db.sang_collec.find({x:/^(hello)(.[a-zA-Z0-9])+/i})
```

### Array 數組
```sh
$ db.sang_collec.insert({x:[1,2,3,4,new Date()]})
```

### Date 日期
```sh
$ db.sang_collec.insert({x:new Date()})
```

### Inner document 內嵌文檔

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