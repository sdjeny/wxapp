// pages/ticket/ticket.js
var util = require('../../utils/util.js');
const app = getApp();
Page({

  data: {
    pickerHidden: true,
    chosen: '',
    array: ['A地', 'B地'],
    from: 0,
    to: 1,
    date: util.formatDate(new Date(), '-'), //'2016-09-01',
    customItem: '全部'
  },

  onLoad: function (e) {
    if ('A' == e.from) {
      this.setData({
        from: 0,
        to: 1
      })
    } else {
      this.setData({
        from: 1,
        to: 0
      })
    }
  },

  goto_detail: function (e) {

  },

  pickerConfirm(e) {
    this.setData({
      pickerHidden: true
    })
    this.setData({
      chosen: e.detail.value
    })
  },

  pickerCancel() {
    this.setData({
      pickerHidden: true
    })
  },

  pickerShow() {
    this.setData({
      pickerHidden: false
    })
  },

  formSubmit(e) {
    let self = this;
    console.log('form发生了submit事件，携带数据为：', e.detail.value);
    wx.showToast({
      title: self.data.array[self.data.from] + "-" + self.data.array[self.data.from] + " at " + self.data.date,
      icon: 'success',
      duration: 2000
    });

    wx.navigateTo({
      url: '/pages/ship_detail/ship_detail?from=' + self.data.from + '&to=' + self.data.to + '&date=' + self.data.date
    })
    /*
        wx.requestSubscribeMessage({
          tmplIds: ['iRJV_pdxGQRFzV_UyK9xOcRlNA3Eybeqtcm0SUlXbvI'],
          success(res) {
            wx.login({
              success: res => {
                // 发送 res.code 到后台换取 openId, sessionKey, unionId        
                var code = res.code; //返回code
                var appId = 'wx1864668d1e6e7bba';

                wx.request({
                  url: 'http://sdjen.vicp.net/wxapp/subscribe_send/' + appId + '/' + code + '/iRJV_pdxGQRFzV_UyK9xOcRlNA3Eybeqtcm0SUlXbvI/3',
                  data: {},
                  header: {
                    'content-type': 'json'
                  },
                  success: function (res) {
                    console.log(res);
                  },
                  complete: res => {

                  }
                })
              }
            })
            console.log('openid为' + app.globalData.openid);
          }
        })
    */
  },

  formReset(e) {
    console.log('form发生了reset事件，携带数据为：', e.detail.value)
    this.setData({
      chosen: ''
    })
  },

  bindFromPickerChange: function (e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      from: e.detail.value
    })
  },

  bindToPickerChange: function (e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      to: e.detail.value
    })
  },

  bindPickerChange: function (e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      index: e.detail.value
    })
  },
  bindMultiPickerChange: function (e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      multiIndex: e.detail.value
    })
  },
  bindMultiPickerColumnChange: function (e) {
    console.log('修改的列为', e.detail.column, '，值为', e.detail.value);
    var data = {
      multiArray: this.data.multiArray,
      multiIndex: this.data.multiIndex
    };
    data.multiIndex[e.detail.column] = e.detail.value;
    switch (e.detail.column) {
      case 0:
        switch (data.multiIndex[0]) {
          case 0:
            data.multiArray[1] = ['扁性动物', '线形动物', '环节动物', '软体动物', '节肢动物'];
            data.multiArray[2] = ['猪肉绦虫', '吸血虫'];
            break;
          case 1:
            data.multiArray[1] = ['鱼', '两栖动物', '爬行动物'];
            data.multiArray[2] = ['鲫鱼', '带鱼'];
            break;
        }
        data.multiIndex[1] = 0;
        data.multiIndex[2] = 0;
        break;
      case 1:
        switch (data.multiIndex[0]) {
          case 0:
            switch (data.multiIndex[1]) {
              case 0:
                data.multiArray[2] = ['猪肉绦虫', '吸血虫'];
                break;
              case 1:
                data.multiArray[2] = ['蛔虫'];
                break;
              case 2:
                data.multiArray[2] = ['蚂蚁', '蚂蟥'];
                break;
              case 3:
                data.multiArray[2] = ['河蚌', '蜗牛', '蛞蝓'];
                break;
              case 4:
                data.multiArray[2] = ['昆虫', '甲壳动物', '蛛形动物', '多足动物'];
                break;
            }
            break;
          case 1:
            switch (data.multiIndex[1]) {
              case 0:
                data.multiArray[2] = ['鲫鱼', '带鱼'];
                break;
              case 1:
                data.multiArray[2] = ['青蛙', '娃娃鱼'];
                break;
              case 2:
                data.multiArray[2] = ['蜥蜴', '龟', '壁虎'];
                break;
            }
            break;
        }
        data.multiIndex[2] = 0;
        break;
    }
    console.log(data.multiIndex);
    this.setData(data);
  },
  bindDateChange: function (e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      date: e.detail.value
    })
  },
  bindTimeChange: function (e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      time: e.detail.value
    })
  },
  bindRegionChange: function (e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      region: e.detail.value
    })
  }
})