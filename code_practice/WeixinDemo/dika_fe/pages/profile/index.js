// pages/profile/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo: {},
    options: [
      {
        title: '我的好友',
        url: 'profile-friends/profile-friends'
      },
      {
        title: '礼物商店',
        url: 'profile-giftshop/profile-giftshop'
      },
      {
        title: '设置',
        url: 'profile-setting/profile-setting'
      },
      {
        title: '分享',
        url: 'profile-share/profile-share'
      }
    ]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    const app = getApp()
    const userInfo = app.globalData.userInfo
    this.setData({userInfo})
  },

  viewProfileDetail: function(e) {
    const x = e.detail.x
    const nav = {}
    if(x <= 95) {
      nav.url = 'profile-detail/profile-detail-avatar/profile-detail-avatar'
    } else {
      nav.url = 'profile-detail/profile-detail'
    }
    wx.navigateTo(nav)
  },

  viewDetail: function(e) {
    console.log('view detail')
    const url = e.currentTarget.dataset['url']
    wx.navigateTo({
      url,
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})