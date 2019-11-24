// pages/target/target.js
const targetList = [
  {targetId: 0, title: 'title 0', content: 'content 1', percent: "40", isMarked: true},
  {targetId: 1, title: 'title 1', content: 'content 2', percent: "60", isMarked: true},
  {targetId: 2, title: 'title 2', content: 'content 3', percent: "20", isMarked: false},
  {targetId: 3, title: 'title 3', content: 'content 1', percent: "40", isMarked: true},
  {targetId: 4, title: 'title 4', content: 'content 2', percent: "60", isMarked: true},
  {targetId: 5, title: 'title 5', content: 'content 3', percent: "20", isMarked: false},
  {targetId: 6, title: 'title 6', content: 'content 1', percent: "40", isMarked: true},
  {targetId: 7, title: 'title 7', content: 'content 2', percent: "60", isMarked: true},
  {targetId: 8, title: 'title 8', content: 'content 3', percent: "20", isMarked: false},
  {targetId: 9, title: 'title 9', content: 'content 1', percent: "40", isMarked: true},
  {targetId: 10, title: 'title 10', content: 'content 2', percent: "60", isMarked: true},
  {targetId: 11, title: 'title 11', content: 'content 3', percent: "20", isMarked: false},
]

Page({

  /**
   * 页面的初始数据
   */
  data: {
    array: new Array(20).fill(0),
    targetList: targetList
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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
  markTarget: function(e) {
    console.log(e)
    const query = wx.createSelectorQuery()
    query.selectAll('.target-mark').boundingClientRect()
    query.selectViewport().scrollOffset()
    query.exec(function(res){
      console.log(res[0])
      console.log(res[1])
    })
    const index = (e.currentTarget.offsetTop - 1)/73
    // console.log(index)
    // const targetList = this.data.targetList
    // targetList[index].isMarked = !targetList[index].isMarked
    // this.setData({
    //   targetList: targetList
    // }) 
  }
})