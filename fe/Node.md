# Node.js 學習

## 簡介
- 特化的JavaScript運行環境

## 運行
```sh
$ node test.js
```

## 模塊使用
- 取得模塊：`require([module])`

### 0. 常用模塊
Module | Description
-|-
`http` | http服務器
`fs` | 文件讀寫操作
`events` | 事件監聽器操作(註冊、註銷、觸發)
`zlib` | 文件壓縮操作

### 1. http 模塊
- http相關操作

#### `http.createServer(function(request, response))`
- 創造http服務器
```js
var http = require('http');

http.createServer(function (request, response) {
  
  response.writeHead(200, {'Content-Type': 'text/plain'});
  response.end('Hello World\n');

}).listen(8888);

console.log('Server running at http://127.0.0.1:8888/');
```

### 2. fs 模塊
- 文件讀寫相關操作

#### `fs.readFileSync('<file_name>')`
- 同步讀取文件，失敗拋出異常(不推薦)
```js
var fs = require('fs');
var data = fs.readFileSync('input.txt');
console.log('read input.txt:');
console.log(data.toString());
```

#### `fs.readFile('<file_name>', function(err, data))`
- 異步讀取文件，失敗信息作為第一個參數
```js
var fs = require('fs')
fs.readFile('input.txt', function(err, data) {
  if(err) {
    console.log(err)
    return
  }
  console.log(data.toString())
})
console.log('running end')
```

### 3. events 模塊

#### `EventEmitter` 對象
```js
var events = require('events');
var eventEmitter = new events.EventEmitter();
```

#### `EventEmitter.emit('<event_name>', '<parameter>', ...)`
- 觸發事件
```js
eventEmitter.emit('connect')
```

#### `EventEmitter.on('<event_name>', function())` `EventEmiiter.addListener('<event_name>', function())`
- 註冊事件監聽器
```js
var connectHandler = function connetc() {
  console.log('connect success');
  eventEmitter.emit('data_received')
}
eventEmitter.on('connect', connectHandler)
eventEmitter.on('data_received', function() {
  console.log('data received success')
})
```

#### `EventEmitter.once('<event_name>', function())`
- 註冊一次性事件監聽器(觸發一次後注銷)
```js
eventEmitter.once('hello', function() {
  console.log('hello')
})
eventEmitter.emit('hello')
eventEmitter.emit('hello')  // 無事件觸發
```

#### `EventEmitter.removeListener('<event_name>', <callback>)`
- 移除監聽器
```js
var callback = function() {
  console.log('hello ' + count++)
}
emitter.on('hello', callback)
emitter.emit('hello')
emitter.removeListener('hello', callback)
emitter.emit('hello')  // 無事件觸發
```

#### `EventEmitter.removeAllListeners([event])`
- 移除所有監聽器(指定事件則移除該事件所有監聽器)
```js
emitter.removeAllListeners('hello')
emitter.removeAllListeners()
```

#### `EventEmitter.setMaxListeners(<n>)`
- 限制事件最大監聽器數目(默認為10)
```js
emitter.setMaxListeners(10)
```

#### `EventEmitter.listeners('<event>')`
- 返回事件的監聽器列表
```js
var list = emitter.listeners('hello3')
```

#### `EventEmitter.listenerCount('<event>')`
- 返回事件監聽器數目
```js
var count = emitter.listenerCount('hello')
```

#### `error` 事件
- 遇到異常觸發 `error` 事件，未處理則拋出異常，終止程序
```js
emitter.on('error', function() {
  console.log('something goes wrong')
})
emitter.emit('error')  // something goes wrong
emitter.removeAllListener('error')
emitter.emit('error')  // 拋出異常並終止程序
```

### 4. zlib 模塊

#### `zlib.createGzip()`
- 建立壓縮文件管道
```js
fs.createReadStream('input.txt')
  .pipe(zlib.createGzip())
  .pipe(fs.createWriteStream('input.txt.gz'))
```

### 5. url 模塊

#### `url.parse(string).query` | `url.parse(string).pathname`
- 獲得url中路由部分

### 6. queryString 模塊

#### `querystring.parse(queryString)["foo"]`

### 6. util 模塊

#### `util.inherits(constructor, superConstructor)`
- 對象間原型繼承

## 特殊對象

### 0. Global 全局對象
- 訪問全局屬性，不直接訪問global對象

#### 常用屬性
Attribute | Description
-|-
`__filename` | 當前執行腳本路徑
`__dirname` | 當前執行腳本所在目錄
`console` | 標準輸出流對象
`process` | 描述進程狀態對象

#### 全局函數
1. `setTimeout(cb, ms)`
- ms秒後執行cb函數
- 返回此計時器句柄

2. `clearTimeout(t)`
- 清除計時器t

3. `setInterval(cb, ms)`
- 每ms秒執行一次cb
- 返回計時器句柄

#### console 標準輸出流對象
Function | Description
-|-
`console.log([data][, args...])` | 輸出data，或照c的printf格式
`console.info([data][, args...])` | 藍色提醒標示
`console.error([data][, args...])` | 紅色錯誤標示
`console.warn([data][, args...])` | 黃色警告標示
`console.trace()` | 當前調用棧
`console.time(label)` | 計時器開始
`console.timeEnd(label)` | 計時器結束，返回`label: xxxms`

#### process 描述近程狀態對象
Event | Emit
-|-
`exit` | 退出進程時觸發
`beforeExit` | 事件循環為空時觸發
`uncaughtExcpetion` | 異常事件冒泡到事件循環時觸發
`Signal` | 收到信號時觸發

Attribute | Description
-|-
`stdout` | 標準輸出流
`stderr` | 標準異常流
`stdin` | 標準輸入流
`argv` | 命令行腳本參數，第一個為node命令，第二個為執行腳本路徑，其餘為參數
`execPath` | 返回當前執行腳本的絕對路徑(node命令路徑)
`execArgv` | 執行腳本命令中node和文件間的參數
`env` | 當前shell的環境變量
`exitCode` | 進程退出碼
`version` | node的版本
`versions` | node和依賴的版本
`config` | 
`pid` | 當前進程號
`title` | 
`arch` | 當前CPU架構(arm, ia32, x64)
`platform` | 運行平台(linux, win32)

Function | Description
-|-
abort() | 这将导致 node 触发 abort 事件。会让 node 退出并生成一个核心文件。
chdir(directory) | 改变当前工作进程的目录，如果操作失败抛出异常。
cwd() | 返回当前进程的工作目录
exit([code]) | 使用指定的 code 结束进程。如果忽略，将会使用 code 0。
getgid() | 获取进程的群组标识（参见 getgid(2)）。获取到得时群组的数字 id，而不是名字。注意：这个函数仅在 POSIX 平台上可用(例如，非Windows 和 Android)。
setgid(id) | 设置进程的群组标识（参见 setgid(2)）。可以接收数字 ID 或者群组名。如果指定了群组名，会阻塞等待解析为数字 ID 。注意：这个函数仅在 POSIX 平台上可用(例如，非Windows 和 Android)。
getuid() | 获取进程的用户标识(参见 getuid(2))。这是数字的用户 id，不是用户名。注意：这个函数仅在 POSIX 平台上可用(例如，非Windows 和 Android)。
setuid(id) | 设置进程的用户标识（参见setuid(2)）。接收数字 ID或字符串名字。果指定了群组名，会阻塞等待解析为数字 ID 。注意：这个函数仅在 POSIX 平台上可用(例如，非Windows 和 Android)。
getgroups() | 返回进程的群组 iD 数组。POSIX 系统没有保证一定有，但是 node.js 保证有。注意：这个函数仅在 POSIX 平台上可用(例如，非Windows 和 Android)。
setgroups(groups) | 设置进程的群组 ID。这是授权操作，所以你需要有 root 权限，或者有 CAP_SETGID 能力。注意：这个函数仅在 POSIX 平台上可用(例如，非Windows 和 Android)。
initgroups(user, extra_group) | 读取 /etc/group ，并初始化群组访问列表，使用成员所在的所有群组。这是授权操作，所以你需要有 root 权限，或者有 CAP_SETGID 能力。注意：这个函数仅在 POSIX 平台上可用(例如，非Windows 和 Android)。
kill(pid[, signal]) | 发送信号给进程. pid 是进程id，并且 signal 是发送的信号的字符串描述。信号名是字符串，比如 'SIGINT' 或 'SIGHUP'。如果忽略，信号会是 'SIGTERM'。
memoryUsage() | 返回一个对象，描述了 Node 进程所用的内存状况，单位为字节。
nextTick(callback) | 一旦当前事件循环结束，调用回调函数。
umask([mask]) | 设置或读取进程文件的掩码。子进程从父进程继承掩码。如果mask 参数有效，返回旧的掩码。否则，返回当前掩码。
uptime() | 返回 Node 已经运行的秒数。
hrtime() | 返回当前进程的高分辨时间，形式为 [seconds, nanoseconds]数组。它是相对于过去的任意事件。该值与日期无关，因此不受时钟漂移的影响。主要用途是可以通过精确的时间间隔，来衡量程序的性能。你可以将之前的结果传递给当前的 process.hrtime() ，会返回两者间的时间差，用来基准和测量时间间隔。

### 1. Buffer 緩衝區對象
- 基本使用
```js
const buf = Buffer.from('hello world123456', 'ascii')

console.log(buf.toString())
console.log(buf.toString('hex'))
console.log(buf.toString('base64'))
```

#### Buffer所支持的編碼
Encoding | Description
-|-
ASCII | 支持7位ASCII，設置去除高位能顯著提升性能
utf8 | 多字節Unicode
utf16le | 2 or 4字節，小字節序Unicode編碼
ucs2 | utf16le別名
base64 | Base64
latin1 | 把Buffer變成一字節編碼串
binary | latin1別名
hex | 每個字符變成兩位十六進制數

#### create Buffer 創建緩衝區
1. `Buffer.alloc(size[, fill[, encoding]])`  
    - 返回一个指定大小的 Buffer 实例，如果没有设置 fill，则默认填满 0
2. `Buffer.allocUnsafe(size)` `Buffer.allocUnsafeSlow(size)`
    - 返回一个指定大小的 Buffer 实例，但是它不会被初始化，所以它可能包含敏感的数据
3. `Buffer.from(array)`
    - 返回一个被 array 的值初始化的新的 Buffer 实例（传入的 array 的元素只能是数字，不然就会自动被 0 覆盖）
4. `Buffer.from(arrayBuffer[, byteOffset[, length]])`
    - 返回一个新建的与给定的 ArrayBuffer 共享同一内存的 Buffer。
5. `Buffer.from(buffer)`
    - 复制传入的 Buffer 实例的数据，并返回一个新的 Buffer 实例
6. `Buffer.from(string[, encoding])`
    - 返回一个被 string 的值初始化的新的 Buffer 实例

#### write Buffer 寫入緩衝區
`buf.write(string[, offset[, length]][, encoding])`
- 返回實際寫入大小，空間不足則只寫入部分字符串
- string為欲寫入字符串，offset為寫入位置索引，length寫入字節數，encoding字符串編碼(默認utf8)

#### read Buffer 讀取緩衝區
1. `buf.toString([encoding[, start[, end]]])`
    - encoding解碼編碼，start起始位置，end結束位置
2. `buf.toJSON`
    - 返回JSON對象，形如 `{"type":"Buffer", "data":[1,2,3,4,5]}`

#### concat Buffer 合併緩衝區
`Buffer.concat(list[, totalLength])`
- list為用於合併的緩衝區對象列表，totalLength指定合併後緩衝區長度

#### compare Buffer 比較緩衝區
`buf.compare(otherBuffer)`
- 返回-1, 0, 1分別代表buf <, =, > otherBuffer

#### copy Buffer 拷貝緩衝區
`buf.copy(targetBuffer[, targetStart[, sourceStart[, sourceEnd]]])`
- targetBuffer欲拷貝對象

#### splice Buffer 切片緩衝區(剪裁)
`buf.slice([start[, end]])`

#### length 緩衝區長度
`buf.length`

### 2. Stream 流接口
- 抽象接口，分為四類：
1. `Readable` 可读操作
2. `Writable` 可写操作
3. `Duplex` 可读可写操作
4. `Transform` 操作被写入数据，然后读出结果

- 所有對象都是EventEmitter實例：常用事件
1. `data` 当有数据可读时触发
2. `end` 没有更多的数据可读时触发
3. `error` 在接收和写入过程中发生错误时触发
4. `finish` 所有数据已被写入到底层系统时触发

#### `readStream` 讀取流
- 可透過`fs.createReadStream('<file>')`創建讀取流
- `readStream.setEncoding('<encoding>')`: 設定讀取編碼
- `data event`: 有可讀數據時觸發
- `end event`: 讀取完畢時觸發
- `error event`: 出錯時觸發

#### `writeStream` 寫入流
- 可透過`fs.createWriteStream('<file>')`創建寫入流
- `writeStream.write(data, encoding)`: 寫入數據
- `writeStream.end()`: 標記結尾
- `finish event`: 寫入完畢時觸發
- `error event`: 出錯時觸發

#### `pipe` 管道流
- 建立從readStream到writeStream的管道流(讀取並寫入)
- 使用：`readStream.pipe(writeStream)`

#### `link` 鏈式流
- 鏈式連接多管道進行寫入操作






