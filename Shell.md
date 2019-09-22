# Shell學習筆記
Shell腳本副檔名：`.sh`
## 變量

### 基本操作
```sh
#!：指定執行的解釋器  
echo：向窗口輸出文本  
$var or ${var}：使用變量  
readonly var：定義只讀變量  
unset var：刪除變量
```

### 字符串
```sh
${#string}：獲取字符串長度   # ${#"123"} = 3
${string:1:4}：提取子字符串  # ${"string":1:4} = trin
`expr index ${string} char`
```

### 數組
```sh
array=(value1 value2 value3 ...)：定義  # 空格隔開
${array[n]}：讀下標為n的值
${array[@]} or ${array[*]}：讀所有的值
${#array[@]} or ${#array[*]}：數組長度
```

### 注釋
```sh
# 單行註釋
:<<EOF  #EOF可用任何符合代替
12345
EOF
```

## 參數傳遞
```sh
$0：執行檔名
$n：第n個參數
$#：參數個數
$*：所有參數合併為單一字符串，空格隔開
$@：同上
$$：當前進程ID
$!：最後進程ID
$-：當前選項
$?：結束狀態，0為正常
```

## 表達式（運算符）

### 算術運算符
```sh
`expr $a + $b`：加法運算
`expr $a - $b`：減法運算
`expr $a \* $b`：乘法運算，*需轉義
`expr $a / $b`：除法運算
`expr $a % $b`：取模運算
# mac下用$((expression))) 取代`expr expression`，無需轉義
```

### 關系運算符
```sh
`expr $a -eq $b`：==
`expr $a -ne $b`：!=
`expr $a -gt $b`：>
`expr $a -lt $b`：<
`expr $a -ge $b`：>=
`expr $a -le $b`：<=
```

### 布爾運算符（位運算？）
```sh
!：取反
-o：或運算
-a：與運算
```

### 邏輯運算符
```sh
&&：AND
||：OR
```

### 