# Webpack 靜態模塊打包器

## 基本概念
- entry: 入口模塊
- output: 打包後文件目標位置
- loader: 解析非js文件，轉為webpack可使用模塊
- plugins: 打包優化和壓縮、定義環境變量
- mode: 編譯模式選擇

### Entry points 入口起點

#### 單入口寫法 `entry: string|Array<string>`
- 簡寫
```js
const config = {
  entry: './path/to/my/entry/file.js'
};

module.exports = config;
```
- 原貌
```js
const config = {
  entry: {
    main: './path/to/my/entry/file.js'
  }
};
```

#### 多入口寫法 `entry: {[entryChunkName: string]: string|Array<string>}`
- 用法
```js
const config = {
  entry: {
    app: './src/app.js',
    vendors: './src/vendors.js'
  }
};
```

#### 使用場景
- 單頁面應用：應用入口app + 第三方庫vendor
- 多頁面應用：通常一個html使用一個入口起點

### Output 編譯目標位置
- 只存在一個輸出位置

#### 最簡配置 `output: {filename: string, path: string}`
- filename: 輸出文件名
- path: 輸出目錄的絕對路徑
```js
const config = {
  output: {
    filename: 'bundle.js',
    path: '/home/proj/public/assets'
  }
};

module.exports = config;
```

#### 多入口起點
- 使用佔位符：`[name]` 為chunk名稱
```js
{
  entry: {
    app: './src/app.js',
    search: './src/search.js'
  },
  output: {
    filename: '[name].js',
    path: __dirname + '/dist'
  }
}

// 写入到硬盘：./dist/app.js, ./dist/search.js
```

### Mode 編譯模式 `mode: string`
- 分為 `development` 和 `production`

Mode | `process.env.NODE_ENV` 值 | Plugins 插件啟用
-|-|-
development | `'development'` | `NamedChunksPlugin`, `NamedModulesPlugin`
production | `'production'`， | `FlagDependencyUsagePlugin`, `FlagIncludedChunksPlugin`, `ModuleConcatenationPlugin`, `NoEmitOnErrorsPlugin`, `OccurrenceOrderPlugin`, `SideEffectsFlagPlugin`, `UglifyJsPlugin`

### Loader 非js文件解析
- 用法 `module: {rules: Array<{test: string, use: string}>`
- 須先install相應模塊，`npm install css-loader`, `npm install ts-loader`
```js
module.exports = {
  module: {
    rules: [
      { test: /\.css$/, use: 'css-loader' },
      { test: /\.ts$/, use: 'ts-loader' }
    ]
  }
};
```

### Plugins 插件
- 解決loader無法解決的其他事

#### Plugins 模塊接口
```js
// ConsoleLogOnBuildWebpackPlugin.js
const pluginName = 'ConsoleLogOnBuildWebpackPlugin';

class ConsoleLogOnBuildWebpackPlugin {
  apply(compiler) {
    compiler.hooks.run.tap(pluginName, compilation => {
      console.log("webpack 构建过程开始！");
    });
  }
}
```

#### 用法(可帶Option，需new一個實例) `plugins: Array<new xxxPlugin(option)>`
```js
const HtmlWebpackPlugin = require('html-webpack-plugin'); //通过 npm 安装
const webpack = require('webpack'); //访问内置的插件
const path = require('path');

const config = {
  entry: './path/to/my/entry/file.js',
  output: {
    filename: 'my-first-webpack.bundle.js',
    path: path.resolve(__dirname, 'dist')
  },
  module: {
    rules: [
      {
        test: /\.(js|jsx)$/,
        use: 'babel-loader'
      }
    ]
  },
  plugins: [
    new webpack.optimize.UglifyJsPlugin(),
    new HtmlWebpackPlugin({template: './src/index.html'})
  ]
};

module.exports = config;
```

## 配置

### 基本配置
```js
// webpack.config.js
module.exports = {
  mode: 'development',
  entry: './foo.js',
  output: {
    path: path.resolve(__dirname, 'dist'),
    filename: 'foo.bundle.js'
  }
}
```

## 模塊

### 模塊解析
- 絕對路徑
- 相對路徑
- 模塊路徑：從 `resolve.modules` 中給定目錄下查找，默認為 `node-modules`

### 基本模塊類型支持
- CoffeeScript
- TypeScript
- ESNext (Babel)
- Sass
- Less
- Stylus

##
