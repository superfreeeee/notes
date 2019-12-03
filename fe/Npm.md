# Npm 前端包管理器
- Node Package Module ?

## Commands
```sh
# 初始化 package.json
$ npm init
$ npm init --yes  # 跳過客製化 cli
$ npm init -y  # --yes 的縮寫
$ npm init --scope=username  # 創建作用域包

# 修改 npm 設定
$ npm set init.<attr> <default>  # 修改初始化默認值

# 安裝依賴包
$ npm install <module_name>
$ npm install  # 添加 package.json 中所有依賴
$ npm install <module_name> --save  # 安裝並更新到 dependendies
$ npm install <module_name> --save-dev  # 更新到 devDependendies
$ npm install -g <module_name>  # 安裝全局包

# 卸載依賴包
$ npm uninstall <module_name>
$ npm uninstall --save <module_name>  # 卸載並更新 package.json
$ npm uninstall -g <module_name>  # 卸載全局包

# 更新依賴包版本
$ npm update
$ npm update -g <module_name>  # 更新全局包
# 檢查依賴包版本過時
$ npm outdated  

# 創建用戶
$ npm adduser
$ npm login  # 登入用戶
$ npm whoami  # 檢查登入信息

# 發布包
$ npm publish
$ npm publish --access=public  # 發布作用域包
```

## package.json

### 初始化
```json
{
  "name": "npm",
  "version": "1.0.0",
  "description": "",
  "main": "index.js",
  "scripts": {
    "test": "echo \"Error: no test specified\" && exit 1"
  },
  "keywords": [],
  "author": "",
  "license": "ISC"
}
```

### 屬性詳解
Attribute | Description | Restrict
-|-|-
name | 包名稱 | all lowercase, no space, allow dash or underscore
version | 包版本號 | 從 `"1.0.0"` 開始
description | 包描述 | "" or 包含readme.md中首段內容
main |  | 默認 `index.js`
dependencies | 包依賴 | 用於生產環境
devDependencies | 包依賴 | 用於開發環境

```json
{
  "name": "npm",
  "version": "1.0.0",
  "description": "",
  "main": "index.js",
  "scripts": {
    "test": "echo \"Error: no test specified\" && exit 1"
  },
  "keywords": [],
  "author": "",
  "license": "ISC"
}
```