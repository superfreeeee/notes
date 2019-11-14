# Spring Demo 

## demo1
- springboot 項目環境 + 簡單controller分發請求(使用 `RestFul API` 風格)
- springboot 默認端口 `8080`

## demo2
- 跨域請求配置範例 `/config/WebMvcConfig`
- 使用 `application.yaml` 上下文配置信息
- 使用 `/constant/response/SimpleResponse` 測試返回信息轉JSON格式
- Controller - Service(面對接口) 實現基礎業務分層

## demo3
- 整合 `mybatis` (xml配置文件, 注解配置, yaml環境配置, dao層mapper注入)
- 進入mysql控制台，執行 `source [demo3_init.sql的路徑全名]` 初始化mysql數據庫

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

#### Models
- SimpleResponse
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

## demo4
- 整合 `Swagger`，透過 `http://localhost:8999/swagger-ui.html` 訪問自動化生成文檔(就不用像demo3傻逼一樣需要手工維護一個文檔，同時能作為接口簡單測試方法，使用 `Postman` 能做更細部的測試)

## demo5
- 透過 `Spring Data Redis` 整合 `Redis`

## demo6
- 整合 `Spring Security`

## demo5
- 整合 `Redis`，使用三種手段 `Spring Data Redis`、`Spring Cache`、`Jedis or others`




