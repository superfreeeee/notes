# MongoDB：分佈式文件存儲數據庫

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
{"site":"www.baidu.com"},
{"site":"www.google.com","name":"Google"},
{"site":"www.runoob.com","name":"菜鸟教程","num":5}

```

## 基本操作(使用js腳本)
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
```sh
$ db.sang_collect.insert({name:"三国演义",author:{name:"罗贯中",age:99}});
```

### ObjectId
- 默認 `_id` 鍵
- 12字節, 24個十六進制數 `[時間戳(8)][機器碼(6)][進程id(4)][計數器(6)]`

### 其他
- 二進制數據
- js代碼

## 深入操作詳解

### 文檔替換
- 替換前
```json
{
  "_id" : ObjectId("59f005402844ff254a1b68f6"),
  "name" : "三国演义",
  "authorName" : "罗贯中",
  "authorGender" : "男",
  "authorAge" : 99.0
}
```
- 文檔替換操作
```js
$ var book=db.sang_collect.findOne({name:"三国演义"})
$ book.author={name:book.authorName,gender:book.authorGender,age:book.authorAge}
$ delete book.author
$ delete book.authorName
$ delete book.authorGender
$ delete book.authorAge
$ db.sang_collect.update({name:"三国演义"},book)
```
- 替換後
```json
{
  "_id" : ObjectId("59f005402844ff254a1b68f6"),
  "name" : "三国演义",
  "author" : {
    "name" : "罗贯中",
    "gender" : "男",
    "age" : 99.0
  }
}
```

### 全部修改
- update默認只修改第一個匹配數據
```json
{ "_id" : ObjectId("59f00d4a2844ff254a1b68f7"), "x" : 1 }
{ "_id" : ObjectId("59f00d4a2844ff254a1b68f8"), "x" : 1 }
{ "_id" : ObjectId("59f00d4a2844ff254a1b68f9"), "x" : 1 }
{ "_id" : ObjectId("59f00d4a2844ff254a1b68fa"), "x" : 2 }
```
```js
$ db.sang_collect.update({x:1},{x:99})
```
```json
{ "_id" : ObjectId("59f00d4a2844ff254a1b68f7"), "x" : 99 }
{ "_id" : ObjectId("59f00d4a2844ff254a1b68f8"), "x" : 1 }
{ "_id" : ObjectId("59f00d4a2844ff254a1b68f9"), "x" : 1 }
{ "_id" : ObjectId("59f00d4a2844ff254a1b68fa"), "x" : 2 }
```

- 替換全部數據
```js
$ db.sang_collect.update({x:1},{$set:{x:99}},false,true)
// 參數二表示set修改器，參數三表示未找到時是否作為新數據插入，參數四表示是否改動所有匹配文檔
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