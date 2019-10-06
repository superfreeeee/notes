# SQL基礎

## 基本語句

### 1. SELECT 選擇操作（查）
```sql
-- 選取所有列
SELECT * FROM [table]

-- 選取特定列
SELECT [columns] FROM [table]
```

#### SELECT DISTINCT
```sql
-- 選取唯一值
SELECT DISTINCT [columns] FROM [table]
```

### 2. WHERE 子句
```sql
... WHERE [condition]
condition := [column] [operation] [value]

-- 作 select 子句
SELECT * FROM [table] WHERE [column] [operation] [value]
```

#### operation
operation | description
-|-
= | 等於
<>, != | 不等於
> | 大於
< | 小於
>= | 大於等於
<= | 小於等於
BETWEEN | 某範圍之間
LIKE | 某種搜索模式

#### value
- 文本值
```sql
SELECT * FROM Persons WHERE FirstName='Bush'
```
- 數值
```sql
SELECT * FROM Persons WHERE Year>1965
```

### 3. AND, OR 運算符
```sql
-- 結合 where 子句條件
-- AND
SELECT * FROM Persons WHERE FirstName='Thomas' AND LastName='Carter'

-- OR
SELECT * FROM Persons WHERE firstname='Thomas' OR lastname='Carter'

-- combination 使用圓括號構造複雜表達式
SELECT * FROM Persons WHERE (FirstName='Thomas' OR FirstName='William')AND LastName='Carter'
```

### 4. ORDER BY 子句
```sql
-- 默認升序排列
... ORDER BY [column] (DESC)?

-- 作 select 子句
SELECT Company, OrderNumber FROM Orders ORDER BY Company
-- 二、三級條件
SELECT Company, OrderNumber FROM Orders ORDER BY Company, OrderNumber
-- 降序
SELECT Company, OrderNumber FROM Orders ORDER BY Company DESC,OrderNumber ASC
```

### 5. INSERT INTO 插入操作（增）
```sql
-- 一般插入
INSERT INTO [table] VALUES ([values], [value2], ...)
-- 插入特定列
INSERT INTO [table] ([column1], [column2], ...) VALUES ([values], [value2], ...)
```

### 6. UPDATE 更新操作（改）
```sql
-- 更新列
UPDATE [table] SET [new_values] (WHERE...)?
new_value := [column] = [value]
```

### 7. DELETE 刪除操作（刪）
```sql
-- 刪除行
DELETE FROM [table] (WHERE...)?
-- 刪除所有行（表結構、屬性、索引不變）
DELETE FROM [table]
DELETE * FROM [table]
```

---
## 進階語法

### 1. TOP (LIMIT)（用於 SELECT ）
```sql
-- 限制搜索行數
SELECT TOP [count] [columns] FROM [table]
count := [number] | [percentage]
number := [digit]
percentage := [digit] PERCENT

-- MySQL 用法
SELECT [columns] FROM [table] LIMIT [count]

-- Oracle 語法
SELECT [columns] FROM [table] WHERE ROWNUM <= [count]
```

### 2. LIKE （用於 WHERE 子句）
```sql
-- 指定 WHERE 子句搜索模式
SELECT [columns] FROM [table] WHERE [condition]
condition := [column] LIKE [pattern]

-- 使用 % 通配符
SELECT * FROM Persons WHERE City LIKE 'N%'  -- 匹配 Nxxx
SELECT * FROM Persons WHERE City LIKE '%g'  -- 匹配 xxxg
SELECT * FROM Persons WHERE City LIKE '%lon%'  -- 匹配 xxxlonxxx

-- 使用 NOT 關鍵字
SELECT * FROM Persons WHERE City NOT LIKE '%lon%' -- 匹配不含 xxxlonxxx
```

#### 通配符（用於 pattern ）
sign | match
-|-
% | 零到多個字符
_ | 一個字符
[chars] | chars中任一字符
[^chars], [!chars] | 不含chars中任一字符

### 3. IN （用於 WHERE 子句）
```sql
-- 於 WHERE 規定多個值
SELECT [columns] FROM [table] WHERE [column] IN (value1,value2,...)

-- 實例
SELECT * FROM Persons WHERE LastName IN ('Adams','Carter')
```

### 4. BETWEEN 操作符（用於 WHERE 子句）
```sql
-- 指定數據範圍
SELECT [columns] FROM [table] WHERE [column] BETWEEN [value1] AND [value2]

-- 實例
SELECT * FROM Persons WHERE LastName BETWEEN 'Adams' AND 'Carter'

-- 使用 NOT 關鍵字
SELECT * FROM Persons WHERE LastName NOT BETWEEN 'Adams' AND 'Carter'
```

### 4. Alias (AS)
```sql
-- 指定別名
-- 指定表的別名
SELECT [column] FROM [table] AS [alias_name]
-- 指定列的別名
SELECT [column] AS [alias_name] FROM [table]

-- 表別名實例（簡化搜索關鍵字）
SELECT po.OrderID, p.LastName, p.FirstName FROM Persons AS p, Product_Orders AS po WHERE p.LastName='Adams' AND p.FirstName='John'

-- 列別名實例
SELECT LastName AS Family, FirstName AS Name FROM Persons
```

### 5. JOIN



### 100. CREATE
- create database
```sql
-- 創建數據庫
CREATE DATABASE [db_name]

-- 實例
CREATE DATABASE my_db
```
- create table
```sql
-- 創建表
CREATE TABLE [table_name] (
  [column_name] [description],
  [column_name] [description],
  ...  
)
description := [data_type] [constraints]
```

#### Constraints 約束
type | description
-|-
NOT NULL | 不接受NULL值
UNIQUE | 保證唯一性（單個表可擁有多個UNIQUE）
PRIMARY KEY | 主鍵（附帶NOT NULL, UNIQUE，每個表應有且只有一個主鍵）
FOREIGN KEY | 
CHECK | 
DEFAULT | 

- NOT NULL
```sql
CREATE TABLE Persons (
  Id_P int NOT NULL,
  LastName varchar(255) NOT NULL,
  FirstName varchar(255),
  Address varchar(255),
  City varchar(255)
)
```
- UNIQUE
```sql
-- 創建表時定義 UNIQUE 約束
-- for MySQL
CREATE TABLE Persons (
  Id_P int NOT NULL,
  LastName varchar(255) NOT NULL,
  FirstName varchar(255),
  Address varchar(255),
  City varchar(255),
  UNIQUE (Id_P)
)

-- for SQL Server / Oracle / MS Access
CREATE TABLE Persons (
  Id_P int NOT NULL UNIQUE,
  LastName varchar(255) NOT NULL,
  FirstName varchar(255),
  Address varchar(255),
  City varchar(255)
)

-- 命名且定義多個列的 UNIQUE 約束
CREATE TABLE Persons (
  Id_P int NOT NULL,
  LastName varchar(255) NOT NULL,
  FirstName varchar(255),
  Address varchar(255),
  City varchar(255),
  CONSTRAINT uc_PersonID UNIQUE (Id_P,LastName)
)

-- 表創建後修改添加 UNIQUE 約束
-- for MySQL / SQL Server / Oracle / MS Access
ALTER TABLE Persons ADD UNIQUE (Id_P)
ALTER TABLE Persons ADD CONSTRAINT uc_PersonID UNIQUE (Id_P,LastName)

-- 撤銷 UNIQUE 約束
-- for MySQL
ALTER TABLE Persons DROP INDEX uc_PersonID

-- for SQL Server / Oracle / MS Access
ALTER TABLE Persons DROP CONSTRAINT uc_PersonID
```
- PRIMARY KEY
```sql
-- 創建表時定義 PRIMARY KEY 約束
-- for MySQL
CREATE TABLE Persons (
  Id_P int NOT NULL,
  LastName varchar(255) NOT NULL,
  FirstName varchar(255),
  Address varchar(255),
  City varchar(255),
  PRIMARY KEY (Id_P)
)

-- for SQL Server / Oracle / MS Access
CREATE TABLE Persons (
  Id_P int NOT NULL PRIMARY KEY,
  LastName varchar(255) NOT NULL,
  FirstName varchar(255),
  Address varchar(255),
  City varchar(255)
)

-- 命名並定義多列 PRIMARY KEY 約束
-- for MySQL / SQL Server / Oracle / MS Access
CREATE TABLE Persons (
  Id_P int NOT NULL,
  LastName varchar(255) NOT NULL,
  FirstName varchar(255),
  Address varchar(255),
  City varchar(255),
  CONSTRAINT pk_PersonID PRIMARY KEY (Id_P,LastName)
)

-- 表創建後修改 PRIMARY KEY 約束
-- for MySQL / SQL Server / Oracle / MS Access
ALTER TABLE Persons ADD PRIMARY KEY (Id_P)
ALTER TABLE Persons ADD CONSTRAINT pk_PersonID PRIMARY KEY (Id_P,LastName)

-- 撤銷 PRIMARY KEY 約束
-- for MySQL
ALTER TABLE Persons DROP PRIMARY KEY

-- for SQL Server / Oracle / MS Access
ALTER TABLE Persons DROP CONSTRAINT pk_PersonID
```
- FOREIGN KEY
```sql

```
- CHECK
```sql

```
- DEFAULT
```sql

```















