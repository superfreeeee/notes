# HTTP 協議
- 默認端口號: 80
## 特點
- 基於TCP/IP的短連接
- 簡單，縮減響應服務器規模
- 靈活，可自由傳輸數據對象的標記類型 
- 請求-響應服務模式
- 無狀態，各請求獨立不相關

## 工作流程
1. 客戶端與服務器建立連接，TCP三次握手
2. 客戶端請求 Request
3. 服務器響應 Response
4. 客戶端與服務器斷開鏈接，TCP四次揮手

## HTTP報文

### Request 請求報文
- 組成：Request-Line + headers + blank-line + Request-Body
```sql
+-----------------+-------+--------------------------+-------+------------------------+---------+---------+
| Method(請求方法) | SPACE | Request_URL(統一資源標示符) | SPACE | HTTP_Version(HTTP版本) | CR(回車) | LF(換行) | Request-Line 請求行
+-----------------+-------+--------------------------+-------+------------------------+---------+---------+
| 頭部字段名 | : | value(值) | CR | LF | headers 請求頭1
+----------+---+-----------+----+----+
|                 ...                |      ...
+----------+---+-----------+----+----+
| 頭部字段名 | : | value(值) | CR | LF | headers 請求頭n
+----------+---+-----------+----+----+
| CR | LF | Blank-Line 空行
+----+----+
|   ...   | Request-Body 請求體
+---------+
```
- 示例
```http
POST /search HTTP/1.1  
Accept: image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/vnd.ms-excel, application/vnd.ms-powerpoint, 
application/msword, application/x-silverlight, application/x-shockwave-flash, */*  
Referer: http://www.google.cn/  
Accept-Language: zh-cn  
Accept-Encoding: gzip, deflate  
User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 2.0.50727; TheWorld)  
Host: www.google.cn 
Connection: Keep-Alive  
Cookie: PREF=ID=80a06da87be9ae3c:U=f7167333e2c3b714:NW=1:TM=1261551909:LM=1261551917:S=ybYcq2wpfefs4V9g; 
NID=31=ojj8d-IygaEtSxLgaJmqSjVhCspkviJrB6omjamNrSm8lZhKy_yMfO2M4QMRKcH1g0iQv9u-2hfBW7bUFwVh7pGaRUb0RnHcJU37y-
FxlRugatx63JLv7CWMD6UB_O_r

hl=zh-CN&source=hp&q=domety 
```

#### Method 請求方法
Type | Use
-|-
GET | 請求信息
HEAD | 請求讀取URL頭部
POST | 發送數據
PUT | 在指定URL下儲存文檔
DELETE | 刪除URL所標示文檔
CONNECT | 用於代理服務器
OPTION | 請求選項信息
TRACE | 進行環迴測試請求報文
PATCH | ？

- `GET`、`HEAD`、`POST`、`PUT`、`DELETE`、`CONNECT`、`OPTIONS`、`TRACE`、`PATCH`

#### Request-Header 請求頭域
Type | Header | Description
-|-|-
Cache | If-Modified-Since | 儲存上一次響應報文的 `Last-Modified`，與服務器最後文件修改時間比較，ex:`If-Modified-Since:Thu, 09 Feb 2012 09:07:57 GMT`
. | If-None-Match | 儲存上一次響應報文的 `ETag`，比較服務器驗證資源，ex:`If-None-Match:"03f2b33c0bfcc1:0"`
. | Cache-Control | 指定報文緩存機制`Public`任意緩存, `Private`私有緩存, `no-cache`不緩存
. | Pragma | 同`Cache-Control`，防止頁面被緩存，只有`no-cache`
Client | Accept | 客戶端可接收類型，格式：`*/*`，ex: `text/html`  
. | Accept-Encoding | 客戶端可接收編碼方法，可壓縮方式
. | Accept-Language | 客戶端可接收語言
. | User-Agent | 客戶端使用的操作系統和瀏覽器的名稱和版本，
. | Accept-Charset | 客戶端可接收字符集，`gb2312`, `utf-8`
Cookie/Login | Cookie | 發送客戶端Cookie給服務器，可用來返回`Seesion id`
Entity | Content-Length | 指明請求體長度，字節？
. | Content-Type | 指明請求體格式類型，ex:`Content-Type:application/x-www-form-urlencoded`
Miscellaneous | Referer | Request上下文信息
Transport | Connection | `keep-alive`保留TCP數據連接，`close`表示請求完畢後關閉連接
. | Host | 指定服務器的 `IP` 與 `端口號`，通常由URL提取
Others | Authorization | 存放授權信息

### Response 響應報文
- 組成：Request-Line + headers + blank-line + Request-Body
```sql
+-----------------------+-------+---------------------+-------+----------------+---------+---------+
| HTTP_Version(HTTP版本) | SPACE | Status-Code(狀態碼)) | SPACE | Reason-Phrase | CR(回車) | LF(換行) | Status-Line 狀態行
+-----------------------+-------+---------------------+-------+----------------+---------+---------+
| 頭部字段名 | : | value(值) | CR | LF | headers 響應頭1
+----------+---+-----------+----+----+
|                 ...                |      ...
+----------+---+-----------+----+----+
| 頭部字段名 | : | value(值) | CR | LF | headers 響應頭n
+----------+---+-----------+----+----+
| CR | LF | Blank-Line 空行
+----+----+
|   ...   | Response-Body 響應體
+---------+
```
- 示例
```http
HTTP/1.1 200 OK
Date: Mon, 23 May 2005 22:38:34 GMT
Content-Type: text/html; charset=UTF-8
Content-Encoding: UTF-8
Content-Length: 138
Last-Modified: Wed, 08 Jan 2003 23:11:55 GMT
Server: Apache/1.3.3.7 (Unix) (Red-Hat/Linux)
ETag: "3f80f-1b6-3e1cb03b"
Accept-Ranges: bytes
Connection: close

<html>
<head>
  <title>An Example Page</title>
</head>
<body>
  Hello World, this is a very simple HTML document.
</body>
</html>
```

#### Status-code 響應狀態碼
Type | Meaning | Description
-|-|-
1xx | 信息 | 收到請求，繼續處理
2xx | 成功 | 請求已被接收、理解、接受
3xx | 重定向 | 採取進一步措施完成請求
4xx | 客戶端錯誤 | 請求錯誤
5xx | 服務器錯誤 | 服務器無法完成請求

#### Response-Header 響應頭域
Type | Header | Description
-|-|-
Cache | Date | 生成響應的時間與日期，ex:`Date:Sat, 11 Feb 2012 11:35:14 GMT`
. | Expires | 指定過期時間內使用緩存，ex:`Date:Tue, 08 Feb 2022 11:35:14 GMT`
Cookie/Login | P3P | 用於跨域設置Cookie，ex:`P3P:CP=CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NEV OTC NOI DSP COR`
. | Set-Cookie | 寫入Cookie給客戶端，ex:`Set-Cookie:src=4c31523a; path=/; domain=.acookie.taobao.com`
Entity | ETag | 配合請求報文中的 `If-None-Match`，ex:`ETag:"03f2b33c0bfcc1:0"`
. | Last-Modified | 配合請求報文中的 `If-Modified-Since`，ex:`Last-Modified:Sat, 11 Feb 2012 11:35:14 GMT`
. | Content-Type | 服務器響應對象類型與字符集，ex:`Content-Type:text/html; charset=utf-8`
. | Content-Length | 響應體長度，十進制字節數
. | Content-Encoding | 響應體編碼和壓縮方法，ex:`Content-Encoding:gzip`
. | Content-Language | 響應對象語言，ex:`Content-Language:en-us`
Miscellaneous | Server | 指明服務器軟件信息，ex:`Server:Microsoft-IIS/7.5`
. | X-AspNet-Version | ASP.NET版本，ex:`X-AspNet-Version:4.0.30319`
. | X-Powered-By | 網站開發技術，ex:`X-Powered-By:ASP.NET`
Transport | Connection | 與請求頭的Connection相同
Location | Location | 指明重定向的新地址URL


## Session 與 Cookie
- HTTP為無狀態，Cookie在客戶端保持狀態，Session在服務端保持狀態，且Seesion透過Cookie保存標示

### Seesion
- 服務器響應將 `SeesionID` 保存於 `Set-Cookie` 響應頭域
- 服務器檢查 `Cookie` 請求頭域中 `SeesionID` 是否存在或過期

#### 客戶端 Session 存儲結構
Web瀏覽器 | SessionID
-|-
A程序 | xxxxxx
B程序 | yyyyyy
C程序 | zzzzzz

#### 服務器 Session 存儲結構
SessionID唯一標識 | Information | .
-|-|-
xxxxxx | Key1 | Value1
. | Key2 | Value2
. | Key3 | Value3
yyyyyy | can be `Null`
zzzzzz | Key1 | Value1
. | Key2 | Value2
... |

### Cookie
- 使用 Cookie 保存 SessionID
- 禁用 Cookie 時透過URL重寫附加 SessionID (路徑附加信息、查詢字符串)

## HTTP 認證
- Basic Authentication
- Digest Authentication
- WSSE(WS-Security) HTTP Authentication
- token Authentication
- OAuth1.0 Authentication
- OAuth2.0 Authentication
- Kerberos
- NTLM
- Hawk Authentication
- AWS Signature
- HTTPS

### HTTP Basic Authentication
- 工作流程：
1. 客戶端請求->：客戶端發送請求
2. 服務器響應<-：返回 `401:Unauthorized` + `(Header) WWW-Authenticate: Basic realm="My realm"(安全域字符串)`
3. 客戶端請求->：附帶帳號密碼信息，`(Header) Authorization: Basic xxxxxxxxxxxxx`

- 缺陷
1. 明文傳輸，需配合HTTPS
2. 未提供對代理和中間節點的防護措施
3. 假冒服務器容易騙過認證，誘導用戶輸入帳號密碼

### HTTP Digest Authentication 摘要認證
- 工作流程：
1. 客戶端請求->：客戶端發送請求
2. 服務器響應<-：返回 `Digest challenge` (摘要盤問) `401:Unauthorized` + `(Header) WWW-Authenticate: Digest realm="Restricted area"(安全域字符串),qop="auth",nonce="123456",opaqe="123456"`
3. 客戶端請求->：附帶帳號密碼信息，`(Header) Authorization: Basic xxxxxxxxxxxxx`

#### Digest Challenge 參數
Key | Necessary | Meaning
-|-|-
realm (領域) | NOT NULL | 
nonce (現時) | NOT NULL | 
stale |  | 
opaque (不透明體) | NOT NULL | 
algorithm (算法) |  | 
qop (保護質量) | NOT NULL | 

### OAuth2.0 Authentication

# HTTPS 協議
- 默認端口號: 443
