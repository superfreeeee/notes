# Canvas 圖形


# SVG 圖形
- 副檔名 `.svg`，以XML描述的矢量圖形

## 嵌入HTML方法
1. `<embed>`
```xml
<!-- Adobe SVG Viewer 推荐 -->
<embed src="rect.svg" width="300" height="100" type="image/svg+xml" pluginspage="http://www.adobe.com/svg/viewer/install/" />
<!-- pluginspage 属性指向下载插件的 URL。 -->
```
2. `<object>`
```xml
<!-- object為HTML 4標準 -->
<object data="rect.svg" width="300" height="100" 
type="image/svg+xml"
codebase="http://www.adobe.com/svg/viewer/install/" />
<!-- codebase 属性指向下载插件的 URL -->
```
3. `<iframe>`
```xml
<iframe src="rect.svg" width="300" height="100"></iframe>
```
4. 直接引入
```html
<html xmlns:svg="http://www.w3.org/2000/svg">
<body>

<p>This is an HTML paragraph</p>

<svg:svg width="300" height="100" version="1.1" >
<svg:circle cx="100" cy="50" r="40" stroke="black"
stroke-width="2" fill="red" />
</svg:svg>

</body>
</html>
```
```xml
<svg xmlns="http://www.w3.org/2000/svg" version="1.1">
  <circle cx="100" cy="50" r="40" stroke="black" stroke-width="2" fill="red" />
</svg>
```

## 預定義圖形
shape | tag
-|-
矩形 | `<rect>`
圓形 | `<circle>`
橢圓 | `<ellipse>`
線 | `<line>`
折線 | `<polyline>`
多邊形 | `<polygon>`
路徑 | `<path>`

### `<rect>` 矩形
```xml
<rect 
  width="300" 
  height="100"
  style="fill:rgb(0,0,255); stroke-width:1; stroke:red"
/>

<rect 
  x="200" y="200" 
  width="250" height="250" 
  style="fill:blue; 
    stroke:pink; 
    stroke-width:5; 
    fill-opacity:0.1; 
    stroke-opacity:0.5"
/>

<rect 
  x="20" y="20" 
  width="250" height="250"
  style="fill:blue;
    stroke:pink;
    stroke-width:5;
    opacity:0.9"
/>

<rect 
  x="20" y="20" 
  rx="20" ry="20" 
  width="250" height="100" 
  style="fill:red;
    stroke:black;
    stroke-width:5;
    opacity:0.5"
/>
```
Properties | Description
-|-
x, y | 左上角座標(偏移量)
rx, ry | 圓角偏移量
width, height | 寬度 & 高度
style | ---包裝下列屬性---
fill | 填充顏色
fill-opacity | 填充透明度
stroke | 線條顏色(邊框顏色)
stroke-width | 線條寬度(邊框寬度)
stroke-opacity | 線條透明度
opacity | 整個元素透明度

### `<circle>` 圓形
```xml
<circle 
  cx="90" cy="390" r="40" 
  stroke="black"
  stroke-width="2" 
  fill="red"
/>
<circle
  r="100"
  fill="black"
/>
```
Properties | Description
-|-
cx, cy | 圓心座標，默認(0, 0)
r | 半徑長
stroke | 同rect
stroke-width | 同rect
fill | 同rect

### `<ellipse>` 橢圓
```xml
<ellipse 
  cx="300" cy="430" 
  rx="120" ry="80"
  style="fill:rgb(200,100,50);
    stroke:rgb(0,0,100);
    stroke-width:2"
/>

<ellipse 
  cx="670" cy="430" 
  rx="220" ry="30"
  style="fill:purple"
/>

<ellipse 
  cx="650" cy="400" 
  rx="190" ry="20"
  style="fill:lime"
/>

<ellipse 
  cx="640" cy="375" 
  rx="170" ry="15"
  style="fill:yellow"
/>

<ellipse 
  cx="1100" cy="400" 
  rx="220" ry="30"
  style="fill:yellow"
/>

<ellipse 
  cx="1080" cy="400" 
  rx="190" ry="20"
  style="fill:white"
/>
<!-- 等於有不同半徑的圓形 -->
```
Properties | Description
-|-
cx, cy | 同circle
rx, ry | 水平, 垂直半徑

### `<line>` 線條
```xml
<line 
  x1="50" y1="50" 
  x2="300" y2="300"
  style="stroke:rgb(99,99,99);
    stroke-width:5"
/>
```
Properties | Description
-|-
x1, y1 | 起始座標
x2, y2 | 結束座標

### `<polygon>` 多邊形(>=3) 
```xml
<polygon points="220,100 300,210 170,250"
  style="fill:#cccccc;
    stroke:#000000;
    stroke-width:1"/>

<polygon points="50,50 100,50 50,200 100,100"
  style="fill:yellow;
  stroke:blue;
  stroke-width:5"
/>

<polygon points="500,50 500,500 1000,500 1000,50"
  fill="black"
  stroke="yellow"
  stroke-width="10"
  fill-opacity="0.5"
/>
```
Properties | Description
-|-
points | 各點座標 `"x1,y1 x2,y2 x3,y3 ..."`

### `<polyline>` 折線
```xml
<polyline points="0,0 0,20 20,20 20,40 40,40 40,60"
  style="fill:white;
    stroke:red;
    stroke-width:2"
/>

<polyline points="50,50 100,50 200,100 200,300 100, 200"
  fill="white"
  stroke="green"
  stroke-width="4"
/>
<!-- 等於不閉合的多邊形 -->
```
Properties | Description
-|-
points | 各點座標 `"x1,y1 x2,y2 x3,y3 ..."`

### `<path>` 路徑 
```xml

```
Properties | Description
-|-
d | 指令行 `"[Command1]x1 y1 [Command2]x2 y2 [C3]x3 y3 ..."`

Commands | Full Name | Use
-|-
M | moveTo
L | lineTo
H | horizontal lineTo
V | vertical lineTo
C | curveTo
S | smooth curveTo
Q | quadratic Belzier curve
T | smooth quadratic Belzier curveto
A | elliptical Arc
Z | closepath
