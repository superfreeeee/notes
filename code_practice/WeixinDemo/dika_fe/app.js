//app.js
App({
  globalData: {
    userInfo: {
      username: 'superfree'
    },
    targetList: new Array(10).fill(0).map((item, index) => ({
      id: index, 
      icon: 'text', 
      title: ('target ' + index), 
      description: ('content ' + index), 
      marked: (index % 3 == 0) 
    })),
    // targetList: [
    //   {
    //     id: 0,
    //     icon: "camera",
    //     title: "target title 1",
    //     description: "target content 1",
    //     marked: false
    //   },
    //   {
    //     id: 1,
    //     icon: "baseball",
    //     title: "target title 2",
    //     description: "target content 2",
    //     marked: true
    //   },
    //   {
    //     id: 2,
    //     icon: "baseball",
    //     title: "target title 2",
    //     description: "target content 2",
    //     marked: true
    //   },
    //   {
    //     id: 3,
    //     icon: "baseball",
    //     title: "target title 2",
    //     description: "target content 2",
    //     marked: true
    //   },
    //   {
    //     id: 4,
    //     icon: "baseball",
    //     title: "target title 2",
    //     description: "target content 2",
    //     marked: true
    //   },
    //   {
    //     id: 5,
    //     icon: "baseball",
    //     title: "target title 2",
    //     description: "target content 2",
    //     marked: true
    //   },
    //   {
    //     id: 6,
    //     icon: "basketball",
    //     title: "target title 3",
    //     description: "target content 3",
    //     marked: false
    //   },
    //   {
    //     id: 7,
    //     icon: "basketball",
    //     title: "target title 3",
    //     description: "target content 3",
    //     marked: false
    //   },
    //   {
    //     id: 8,
    //     icon: "basketball",
    //     title: "target title 3",
    //     description: "target content 3",
    //     marked: false
    //   }
    // ],
    editTarget: {}
  }
  // onLaunch: function () {
  //   // 展示本地存储能力
  //   var logs = wx.getStorageSync('logs') || []
  //   logs.unshift(Date.now())
  //   wx.setStorageSync('logs', logs)

  //   // 登录
  //   wx.login({
  //     success: res => {
  //       // 发送 res.code 到后台换取 openId, sessionKey, unionId
  //     }
  //   })
  //   // 获取用户信息
  //   wx.getSetting({
  //     success: res => {
  //       if (res.authSetting['scope.userInfo']) {
  //         // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
  //         wx.getUserInfo({
  //           success: res => {
  //             // 可以将 res 发送给后台解码出 unionId
  //             this.globalData.userInfo = res.userInfo

  //             // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
  //             // 所以此处加入 callback 以防止这种情况
  //             if (this.userInfoReadyCallback) {
  //               this.userInfoReadyCallback(res)
  //             }
  //           }
  //         })
  //       }
  //     }
  //   })
  // }
})