// pages/fold2/fold2.js
//https://blog.csdn.net/qq_35770969/article/details/81477145
Page({

  /**
   * 页面的初始数据
   */
  data: {
    memberList: [{
        iamges: "/assets/logo_aiqiyi2x.png",
        cont: "爱奇艺影视会员",
        discount: "7.5折",
        hidden: true,
        id: "0",
        invalidActivty: [{
            price: "2.98元",
            oldPrice: "3元",
            validType: "周卡",
            validTime: '7天有效'
          },
          {
            price: "18.98元",
            oldPrice: "25元",
            validType: "月卡",
            validTime: '30天有效'
          },
        ]
      },
      {
        iamges: "/assets/logo_tengxun2x.png",
        cont: "腾讯视频会员",
        discount: "7折",
        hidden: true,
        id: "1",
        invalidActivty: [{
            price: "2.98元",
            oldPrice: "3元",
            validType: "周卡",
            validTime: '7天有效'
          },
          {
            price: "18.98元",
            oldPrice: "25元",
            validType: "月卡",
            validTime: '30天有效'
          },
        ]
      },
      {
        iamges: "/assets/logo_youku2x.png",
        cont: "优酷视频黄金会员",
        discount: "8折",
        hidden: true,
        id: "2",
        invalidActivty: [{
            price: "2.98元",
            oldPrice: "3元",
            validType: "周卡",
            validTime: '7天有效'
          },
          {
            price: "18.98元",
            oldPrice: "25元",
            validType: "月卡",
            validTime: '30天有效'
          },
        ]
      },
      {
        iamges: "/assets/logo_sohu2x.png",
        cont: "搜狐视频黄金会员",
        discount: "8折",
        hidden: true,
        id: "3",
        invalidActivty: [{
            price: "2.98元",
            oldPrice: "3元",
            validType: "周卡",
            validTime: '7天有效'
          },
          {
            price: "18.98元",
            oldPrice: "25元",
            validType: "月卡",
            validTime: '30天有效'
          },
        ]
      },
    ]
  },
  isOpen: function (e) {
    var that = this;
    var idx = e.currentTarget.dataset.index;
    console.log(idx);
    var ls = that.data.memberList;
    console.log(ls);
    // ls.forEach(element => element.hidden = true);
    // ls[idx].hidden = !ls[idx].hidden;
    for (let i = 0; i < ls.length; i++) {
      if (idx == i) {
        ls[i].hidden = !ls[i].hidden;
      } else {
        ls[i].hidden = true;
      }
      console.log(i + " VS " + idx+" h:"+ ls[i].hidden);
    }
    console.log(ls);
    this.setData({
      memberList: ls
    });
    return true;
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

  }
})