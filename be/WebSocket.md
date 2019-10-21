# WebSocket 原理與實現

## Comet: 基於HTTP長連接的“服務器推”技術
- 需求：服務器實時更新信息到客戶端
- 可行方案：
1. 基於客戶端套接口：Flash XMLSocket, Java Applet
2. 基於HTTP長連接：使用 `AJAX` 異步通信技術配合 `iframe` 實現

### 實現一：基於AJAX的`長輪詢`(long-polling)方式
1. `服務器會阻塞請求`直到有數據傳遞或是超時才返回
2. 客戶端處理完信息後`重新發送請求`建立連接(長期佔用)
3. 服務器在斷開期間保存數據，連接後一次發送
4. 無須安裝插件
5. 透過`XMLHttpRequest`的`readystate 4`時回調函數實現重新連接

### 實現二：基於`iframe`及`htmlfile`的流(streaming)方式
1. 服務器不直接返回數據，而是返回對客戶端的函數調用
2. 配合`ActiveX`的`htmlfile`文檔流

## Spring MVC WebSocket實現



