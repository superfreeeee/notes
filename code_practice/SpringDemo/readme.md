# Spring Demo 

## demo1
- springboot 項目環境 + 簡單controller分發請求(使用 `RestFul API` 風格)
- springboot 默認端口 `8080`

## demo2
- 跨域請求配置範例 `/config/WebMvcConfig`
- 使用 `application.yaml` 配置信息
- 使用 `/constant/response/SimpleResponse` 測試返回信息轉JSON格式
- Controller - Service(面對接口) 實現基礎業務分層

## demo3
- 進入mysql控制台，執行 `source [demo3_init.sql的路徑全名]` 初始化mysql數據庫
- 整合 `mybatis` (xml配置文件, 注解配置, yaml環境配置, dao層mapper注入)

### mysql表信息
```sql
-- tables
+------------------------+
| Tables_in_spring_demo3 |
+------------------------+
| user                   |
+------------------------+

-- `user` table
+----------+-------------+------+-----+---------+----------------+
| Field    | Type        | Null | Key | Default | Extra          |
+----------+-------------+------+-----+---------+----------------+
| id       | int(10)     | NO   | PRI | NULL    | auto_increment |
| username | varchar(50) | NO   |     | NULL    |                |
| password | varchar(50) | NO   |     | NULL    |                |
+----------+-------------+------+-----+---------+----------------+
```

### demo3 提供測試接口信息
- 請求接口：主機ip + path

Method | Path | Request Body | Description | Response Body data
-|-|-|-|-
GET | /user/ |  | 返回一般字符串 | String
GET | /user/login |  | 返回一般字符串 | String
POST | /user/register | UserParam | 用戶註冊 | String
POST | /user/login | UserParam | 用戶登入 | UserEntity

#### Forms
- Response Body
```json
{
  "code": [response_status],
  "data": [data]
}
response_status: 200成功，403失敗，2500已捕獲錯誤
```
- UserParam
```json
{
  "username": string,
  "password": string
}
```
- UserEntity
```json
{
  "id": number,
  "username": string,
  "password": string
}
```