function observe(obj) {
  if(!obj || typeof obj !== 'object') {
    return
  }
  for(let key in obj) {
    let value = obj[key]
    if(typeof value === 'object') {
      observe(value)
    }
    Object.defineProperty(data,key,{    //定义对象
      configurable:true,    //可删除，原本的对象就能删除
      enumerable:true,    //可遍历，原本的对象就能遍历
      get:function(){
          console.log('这是假的')    //调用属性时，会调用 get 方法，所以调用属性可以在 get 内部做手脚
          //return val    //这里注释掉了，实际调用属性就是把值 return 出去
      },
      set:function(newVal){
          console.log('我不给你设置。。。')    //设置属性时，会调用 set 方法，所以设置属性可以在 set 内部做手脚
          //val = newVal    //这里注释掉了，实际设置属性就是这样写的。
      }
    })
  }
}

let obj = {}
let num = 10

observe(obj)
observe(num)