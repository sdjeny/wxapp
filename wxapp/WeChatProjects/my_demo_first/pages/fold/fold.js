// pages/fold/fold.js
Page({

  data: {
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

})