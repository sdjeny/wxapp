// pages/ship_detail/ship_detail.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    stations: ['北海', '涠洲岛'],
    details: null,
    welcomMsg: 'loading...'
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log('onload:' + JSON.stringify(options));
    if (false) {
      var dets = JSON.parse('[{"id":"1271243462179074050","code":"BW01","lineId":"19","lineName":"\u5317\u6d77-\u6da0\u6d32","plannedDepartureDate":"2020-07-17","plannedDepartureTime":"08:30","departureHarbourId":"16","departureHarbourName":"\u5317\u6d77","startPortName":"\u5317\u6d77\u56fd\u9645\u5ba2\u8fd0\u6e2f\u7801\u5934","arrivalHarbourId":"17","arrivalHarbourName":"\u6da0\u6d32","endPortName":"\u6da0\u6d32\u5c9b\u897f\u89d2\u7801\u5934","shipId":"1072399049077907458","shipNo":"GK100","shipName":"\u5317\u6e3816","shipTypeName":"\u9ad8\u901f\u8239","plannedArrivalDate":"2020-07-17","plannedArrivalTime":"09:50","run_time":"\u7ea61\u5c0f\u65f620\u5206","min_price":150,"sale_num":571,"list_type":"ship","typeName":"\u5e38\u89c4","cabinList":[{"cabinId":"170759034442281671","cabinName":"\u4e00\u5c42\u666e\u901a\u8231A\u533a","statusName":"\u6b63\u5e38","typeName":"\u5e38\u89c4","availableSeatCount":153,"remainSeatCount":153,"soldSeatCount":6,"reservedSeatCount":0,"cabinClassKey":"3","statusKey":"0","fullTicketPrice":150,"intReserveOrderId":0,"totalAvailableSeatCount":159},{"cabinId":"170759034442281672","cabinName":"\u4e00\u5c42\u666e\u901a\u8231B\u533a","statusName":"\u6b63\u5e38","typeName":"\u5e38\u89c4","availableSeatCount":43,"remainSeatCount":43,"soldSeatCount":0,"reservedSeatCount":180,"cabinClassKey":"3","statusKey":"0","fullTicketPrice":150,"intReserveOrderId":0,"totalAvailableSeatCount":223},{"cabinId":"170759034442281675","cabinName":"\u4e09\u5c42\u5546\u52a1\u8231D\u533a","statusName":"\u6b63\u5e38","typeName":"\u5e38\u89c4","availableSeatCount":53,"remainSeatCount":53,"soldSeatCount":2,"reservedSeatCount":0,"cabinClassKey":"2","statusKey":"0","fullTicketPrice":180,"intReserveOrderId":0,"totalAvailableSeatCount":55},{"cabinId":"170759034442281673","cabinName":"\u4e09\u5c42\u666e\u901a\u8231E\u533a","statusName":"\u6b63\u5e38","typeName":"\u5e38\u89c4","availableSeatCount":243,"remainSeatCount":243,"soldSeatCount":17,"reservedSeatCount":0,"cabinClassKey":"3","statusKey":"0","fullTicketPrice":150,"intReserveOrderId":0,"totalAvailableSeatCount":260},{"cabinId":"170759034442281674","cabinName":"\u4e8c\u5c42\u5546\u52a1\u8231C\u533a","statusName":"\u6b63\u5e38","typeName":"\u5e38\u89c4","availableSeatCount":79,"remainSeatCount":79,"soldSeatCount":0,"reservedSeatCount":0,"cabinClassKey":"2","statusKey":"0","fullTicketPrice":180,"intReserveOrderId":0,"totalAvailableSeatCount":79},{"cabinId":"170759034442281676","cabinName":"\u7279\u7b49\u8231-\u8d35\u5bbe\u623f","statusName":"\u6b63\u5e38","typeName":"\u5e38\u89c4","availableSeatCount":0,"remainSeatCount":0,"soldSeatCount":0,"reservedSeatCount":10,"cabinClassKey":"1","statusKey":"0","fullTicketPrice":240,"intReserveOrderId":0,"totalAvailableSeatCount":10}]},{"id":"1271243489022619650","code":"BW02","lineId":"19","lineName":"\u5317\u6d77-\u6da0\u6d32","plannedDepartureDate":"2020-07-17","plannedDepartureTime":"10:00","departureHarbourId":"16","departureHarbourName":"\u5317\u6d77","startPortName":"\u5317\u6d77\u56fd\u9645\u5ba2\u8fd0\u6e2f\u7801\u5934","arrivalHarbourId":"17","arrivalHarbourName":"\u6da0\u6d32","endPortName":"\u6da0\u6d32\u5c9b\u897f\u89d2\u7801\u5934","shipId":"1072392650415497217","shipNo":"GK98","shipName":"\u5317\u6e3815","shipTypeName":"\u8c6a\u534e\u5ba2\u8239","plannedArrivalDate":"2020-07-17","plannedArrivalTime":"11:40","run_time":"\u7ea61\u5c0f\u65f640\u5206","min_price":150,"sale_num":160,"list_type":"ship","typeName":"\u5e38\u89c4","cabinList":[{"cabinId":"170759034442282954","cabinName":"\u4e09\u5c42\u8d35\u5bbe\u5ba4","statusName":"\u6b63\u5e38","typeName":"\u5e38\u89c4","availableSeatCount":0,"remainSeatCount":0,"soldSeatCount":0,"reservedSeatCount":0,"cabinClassKey":"11","statusKey":"0","fullTicketPrice":240,"intReserveOrderId":0,"totalAvailableSeatCount":0},{"cabinId":"170759034442282955","cabinName":"\u4e3b\u5ba2\u8231\u4e00\u5c42","statusName":"\u6b63\u5e38","typeName":"\u5e38\u89c4","availableSeatCount":89,"remainSeatCount":89,"soldSeatCount":42,"reservedSeatCount":128,"cabinClassKey":"12","statusKey":"0","fullTicketPrice":150,"intReserveOrderId":0,"totalAvailableSeatCount":259},{"cabinId":"170759034442282952","cabinName":"\u4e8c\u5c42\u8d35\u5bbe\u5ba4","statusName":"\u6b63\u5e38","typeName":"\u5e38\u89c4","availableSeatCount":12,"remainSeatCount":12,"soldSeatCount":0,"reservedSeatCount":12,"cabinClassKey":"11","statusKey":"0","fullTicketPrice":240,"intReserveOrderId":0,"totalAvailableSeatCount":24},{"cabinId":"170759034442282953","cabinName":"\u7532\u677f\u4e8c\u5c42\u5ba2\u8231","statusName":"\u6b63\u5e38","typeName":"\u5e38\u89c4","availableSeatCount":59,"remainSeatCount":59,"soldSeatCount":19,"reservedSeatCount":0,"cabinClassKey":"10001","statusKey":"0","fullTicketPrice":180,"intReserveOrderId":0,"totalAvailableSeatCount":78}]},{"id":"1271243538641235969","code":"BW03","lineId":"19","lineName":"\u5317\u6d77-\u6da0\u6d32","plannedDepartureDate":"2020-07-17","plannedDepartureTime":"15:20","departureHarbourId":"16","departureHarbourName":"\u5317\u6d77","startPortName":"\u5317\u6d77\u56fd\u9645\u5ba2\u8fd0\u6e2f\u7801\u5934","arrivalHarbourId":"17","arrivalHarbourName":"\u6da0\u6d32","endPortName":"\u6da0\u6d32\u5c9b\u897f\u89d2\u7801\u5934","shipId":"1072399049077907458","shipNo":"GK100","shipName":"\u5317\u6e3816","shipTypeName":"\u9ad8\u901f\u8239","plannedArrivalDate":"2020-07-17","plannedArrivalTime":"16:40","run_time":"\u7ea61\u5c0f\u65f620\u5206","min_price":150,"sale_num":540,"list_type":"ship","typeName":"\u5e38\u89c4","cabinList":[{"cabinId":"170759034442285817","cabinName":"\u4e00\u5c42\u666e\u901a\u8231A\u533a","statusName":"\u6b63\u5e38","typeName":"\u5e38\u89c4","availableSeatCount":150,"remainSeatCount":150,"soldSeatCount":9,"reservedSeatCount":0,"cabinClassKey":"3","statusKey":"0","fullTicketPrice":150,"intReserveOrderId":0,"totalAvailableSeatCount":159},{"cabinId":"170759034442285818","cabinName":"\u4e00\u5c42\u666e\u901a\u8231B\u533a","statusName":"\u6b63\u5e38","typeName":"\u5e38\u89c4","availableSeatCount":21,"remainSeatCount":21,"soldSeatCount":22,"reservedSeatCount":180,"cabinClassKey":"3","statusKey":"0","fullTicketPrice":150,"intReserveOrderId":0,"totalAvailableSeatCount":223},{"cabinId":"170759034442285821","cabinName":"\u4e09\u5c42\u5546\u52a1\u8231D\u533a","statusName":"\u6b63\u5e38","typeName":"\u5e38\u89c4","availableSeatCount":49,"remainSeatCount":49,"soldSeatCount":6,"reservedSeatCount":0,"cabinClassKey":"2","statusKey":"0","fullTicketPrice":180,"intReserveOrderId":0,"totalAvailableSeatCount":55},{"cabinId":"170759034442285819","cabinName":"\u4e09\u5c42\u666e\u901a\u8231E\u533a","statusName":"\u6b63\u5e38","typeName":"\u5e38\u89c4","availableSeatCount":252,"remainSeatCount":252,"soldSeatCount":8,"reservedSeatCount":0,"cabinClassKey":"3","statusKey":"0","fullTicketPrice":150,"intReserveOrderId":0,"totalAvailableSeatCount":260},{"cabinId":"170759034442285820","cabinName":"\u4e8c\u5c42\u5546\u52a1\u8231C\u533a","statusName":"\u6b63\u5e38","typeName":"\u5e38\u89c4","availableSeatCount":68,"remainSeatCount":68,"soldSeatCount":11,"reservedSeatCount":0,"cabinClassKey":"2","statusKey":"0","fullTicketPrice":180,"intReserveOrderId":0,"totalAvailableSeatCount":79},{"cabinId":"170759034442285822","cabinName":"\u7279\u7b49\u8231-\u8d35\u5bbe\u623f","statusName":"\u6b63\u5e38","typeName":"\u5e38\u89c4","availableSeatCount":0,"remainSeatCount":0,"soldSeatCount":0,"reservedSeatCount":10,"cabinClassKey":"1","statusKey":"0","fullTicketPrice":240,"intReserveOrderId":0,"totalAvailableSeatCount":10}]}]');
      // dets.forEach(element => { });
      for (let index = 0; index < dets.length; index++) {
        const element = dets[index];
        element.hidden = true;
        element.index = index;
      }
      this.setData({
        init: false,
        details: dets
      })
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
        complete: res => {}
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