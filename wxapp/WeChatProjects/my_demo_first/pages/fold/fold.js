// pages/fold/fold.js
Page({

  data: {
    hello: 'abc',
    isShow: true,
    //这是个图片地址,大致长这样“>”，略
    entry: ''
  },

  changeToggle() {
    var that = this;
    that.setData({
      isShow: !that.data.isShow
    })
  },

  onLoad: function (opts) {
    let self = this;
    wx.request({
      // url: 'https://sdjen.free.idcfengye.com/wxapp/hello',
      url: 'http://sdjen.vicp.net/wxapp/hello',
      data: {},
      header: {
        'content-type': 'json'
      },
      success: function (res) {
        console.log(res);
        self.setData({
          hello: res.data
        });
      },
      complete: res => {

      }
    })
  }

})