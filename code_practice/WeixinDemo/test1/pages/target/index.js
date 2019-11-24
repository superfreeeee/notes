// pages/target/index.js
const targetList = [
  {id: 0, title: 'target 0', isMarked: false},
  {id: 1, title: 'target 1', isMarked: false},
  {id: 2, title: 'target 2', isMarked: true},
  {id: 3, title: 'target 3', isMarked: false},
  {id: 4, title: 'target 4', isMarked: false},
  {id: 5, title: 'target 5', isMarked: true}
]

Page({

  /**
   * 页面的初始数据
   */
  data: {
    targetList
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    // wx.setNavigationBarTitle({
    //   title: '目标',
    // })
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

  },
  mark: function(e) {
    const id = Number(e.currentTarget.id.split(' ')[1])
    const target = this.getTargetById(id)
    target.isMarked = !target.isMarked
    this.setData({targetList: this.data.targetList})
  },
  edit: function(e) {
    const x = e.detail.x - 315
    if(x > 0) {
      this.mark(e)
      return
    }
    const id = Number(e.currentTarget.id.split(' ')[1])
    const target = this.getTargetById(id)
    const app = getApp()
    app.globalData.editTargetId = target && target.id
    wx.navigateTo({ url: 'target-edit/target-edit'})
  },
  getTargetById(id) {
    for (let target of this.data.targetList) {
      if (target.id === id) {
        return target
      }
    }
  }
})