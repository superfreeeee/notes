# Git指令

## 基本操作
```sh
# 建立倉庫
$ git init

# 信息設置
$ git config --list
$ git config --global user.name "[user name]"
$ git config --global user.email [your email]

# 提交相關
$ git clone
$ git add [files]
$ git commit -m '[message]'
$ git push

## 狀態查看
$ git status

# 遠程地址操作
$ git remote origin set-url [url]
$ git remote add origin [url]
$ git remote rm origin
```

## 建立本地倉庫
- 倉庫初始化
```
$ git init
```

## 信息設置
- 查看個人信息
```
$ git config --list
```

- 設置用戶名和郵箱
```
$ git config --global user.name "[your name]"
$ git config --global user.email superfreeeee@gmail.com
```

## 提交相關

- 克隆远程仓库 
```
$ git clone [url]
```

- 添加修改
```sh
1. 添加指定文件
$ git add [files]

2. 添加所有修改
$ git add .
```

- 提交到本地倉庫
```
$ git commit -m ['message']
```

- 上傳遠程倉庫
```
$ git push
```

## 狀態查看
- 添加和提交狀況查詢
```
$ git status
```

## 遠程地址

- 修改遠程地址
```
1. 修改地址
$ git remote origin set-url [url]

2. 刪除後添加
$ git remote rm origin
$ git remote add origin [url]
```



