// pages/ship_detail/ship_detail.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    stations: ['北海', '涠洲岛'],
    details: null,
    welcomMsg: '',
    nextdate: null,
    predate: null,
    date:'',
  },

  predate: function (e) {},

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log('onload:' + JSON.stringify(options));
    wx.showLoading({
      title: 'loading...',
    });
    if (true) {
      let self = this;
      var u = 'https://www.laiu8.cn/ship/index?prevDate=' + options.date + '&departPort=' + (0 == options.from ? 16 : 17) + '&arrivalPort=' + (0 == options.to ? 16 : 17);
      console.log(u);
      wx.request({
        url: u,
        data: {},
        header: {
          'content-type': 'json'
        },
        success: res => {
          var tmp = res.data;
          var fs = "lineList: JSON.parse('";
          var es = "code: Number";
          tmp = tmp.substring(tmp.indexOf(fs) + fs.length, tmp.indexOf(es)).trim();
          tmp = tmp.substring(0, tmp.length - 3);
          var dets = JSON.parse(tmp);
          // console.log('success:' + tmp);
          for (let index = 0; index < dets.length; index++) {
            const element = dets[index];
            element.hidden = true;
            element.index = index;
          }
          this.setData({
            welcomMsg: null,
            details: dets,
            date:options.date,
          });
        },
        fail: res =>
          self.setData({
            welcomMsg: JSON.stringify(res),
            date:options.date,
          }),
        complete: res =>
          wx.hideLoading({
            success: (res) => {

            },
          })
      });
    } else {
      let self = this;
      var u = app.myurl + '/wxapp/wzcpw/ship_search/' + options.from + '/' + options.to + '/' + options.date + '/3';
      console.log(u);
      wx.request({
        url: u,
        data: {},
        header: {
          'content-type': 'json'
        },
        success: function (res) {
          var dets = res.data;
          for (let index = 0; index < dets.length; index++) {
            const element = dets[index];
            element.hidden = true;
            element.index = index;
          }
          self.setData({
            welcomMsg: null,
            details: dets
          })
        },
        fail: function (res) {
          console.log('fail' + JSON.stringify(res));
          self.setData({
            welcomMsg: JSON.stringify(res)
          })
        },
        complete: res => {
          wx.hideLoading({
            success: (res) => {
              console.log(res);
            },
          })
        }
      });
    }
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
  isOpen: function (e) {
    var that = this;
    var idx = e.currentTarget.dataset.index;
    // console.log("idx:" + idx);
    var dets = that.data.details;
    // console.log(dets);
    for (let i = 0; i < dets.length; i++) {
      if (idx == i) {
        dets[i].hidden = !dets[i].hidden;
      } else {
        dets[i].hidden = true;
      }
    }
    this.setData({
      details: dets
    });
    return true;
  }
})