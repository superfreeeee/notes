# REST 風格

## RESTful六大原則
1. C/S架構：數據存儲在Server，Client使用，提升Client可移植性
2. 無狀態：無需保存客戶端狀態，缺點是重複發送Client信息造成數據傳輸冗余
3. 統一接口：面對接口編程，隱藏實現細節，前後端解偶
4. 統一數據格式：使用Http請求中MIME Type確定具體格式(POST, PUT, PATCH通常只返回狀態碼)
5. 系統分層：客戶端面對接口，隱藏代理，考慮安全策略
6. 可緩存：適當緩存管理避免客戶端使用舊數據或髒數據重複響應
7. 按需編碼、訂製代碼（可選）：後端提供動態腳本而不必依賴前端靜態資源

## 四級服務

### Level 0: Swamp of POX
- 面向前台
```json
// add user
{
  "addUser": {
    "username": "superfree"
  }
}

// response
{
  "userId": "123456"
}

// query grade
{
  "queryGrade": {
    "userId": "123456",
    "course": "Math"
  }
}

// response
{
  "grade": "100"
}

// delete user
{
  "deleteUser": {
    "userId": "123456"
  }
}
```

### Level 1: Resources
- 面向資源
```json
// add user
// url: /user
{
  "addUser": {
    "username": "superfree"
  }
}

// response
{
  "userId": "123456"
}

// query grade
// url: /course
{
  "queryGrade": {
    "userId": "123456",
    "course": "Math"
  }
}

// response
{
  "grade": "100"
}

// delete user
// url: /user
{
  "deleteUser": {
    "userId": "123456"
  }
}
```

### Level 2: HTTP Verbs
- 打上標簽(HTTP方法)
```json
// add user
// Method: POST
// url: /user
{
  "addUser": {
    "username": "superfree"
  }
}

// response
{
  "userId": "123456"
}

// query grade
// Method: GET
// url: /course?userId=123456&course=Math
//  or
// url: /course
{
  "queryGrade": {
    "userId": "123456",
    "course": "Math"
  }
}
```

### Level 3: Hypermedia Controls
- 完美服務
```json
// add user
// Method: POST
// url: /user
{
  "addUser": {
    "username": "superfree"
  }
}

// response
{
  "userId": "123456",
  "options": [
    {
      "rel": "cancel", // relationship 可選操作，cancel -> DELETE
      "url": "/user/123456"
    } 
  ]
}
```

### Level 總結
- 第一級：強調HTTP為應用層協議，應面對資源請求(path應表明請求資源，使用名詞，而不是表明動作的動詞)，同時避免path與method不對應
- 第二級：使用HTTP方法對應curl操作，理解curl式Web服務
- 第三集：HATEOAS，使用超媒體作為應用狀態引擎，將後續操作url寫入response中
```json
GET https://api.example.com/profile

{
  "name": "Steve",
  "picture": {
    "large": "https://somecdn.com/pictures/1200x1200.png",
    "medium": "https://somecdn.com/pictures/100x100.png",
    "small": "https://somecdn.com/pictures/10x10.png"
  }
}
```

## 統一返回數據格式：JSON
```json
{
  "code": number, // http狀態碼
  "status": String, // 狀態字符串
  "message": String, // 狀態為fail, error時提示信息
  "data": Object // 響應body
}
```


