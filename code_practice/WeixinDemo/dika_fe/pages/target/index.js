// pages/target/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    targetList: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    // console.log('target onload')
    const app = getApp()
    const targetList = app.globalData.targetList
    this.setData({targetList})
    // console.log(app)
    // console.log(this.targetList)
  },

  createTarget: function(e) {
    wx.navigateTo({
      url: 'target-create/target-create',
    })
  },

  editTarget: function (e) {
    const x = e.detail.x
    // console.log(x)
    const index = e.currentTarget.dataset['index']

    if(x < 335) {
      this.edit(index)
    } else {
      this.mark(index)
    }
  },

  edit: function (index) {
    // console.log('edit')
    const app = getApp()
    app.globalData.editTarget = this.data.targetList[index]
    wx.navigateTo({
      url: 'target-edit/target-edit',
      success: function(res) {
        // console.log('navigate success')
      },
      fail: function(res) {
        // console.log('navegate fail')
        // console.log(res)
      } 
    })
  },

  mark: function (index) {
    // console.log('mark')
    const targetList = this.data.targetList
    targetList[index].marked = !targetList[index].marked
    this.setData({targetList})
  }
  // /**
  //  * 生命周期函数--监听页面初次渲染完成
  //  */
  // onReady: function () {

  // },

  // /**
  //  * 生命周期函数--监听页面显示
  //  */
  // onShow: function () {

  // },

  // /**
  //  * 生命周期函数--监听页面隐藏
  //  */
  // onHide: function () {

  // },

  // /**
  //  * 生命周期函数--监听页面卸载
  //  */
  // onUnload: function () {

  // },

  // /**
  //  * 页面相关事件处理函数--监听用户下拉动作
  //  */
  // onPullDownRefresh: function () {

  // },

  // /**
  //  * 页面上拉触底事件的处理函数
  //  */
  // onReachBottom: function () {

  // },

  // /**
  //  * 用户点击右上角分享
  //  */
  // onShareAppMessage: function () {

  // },


})