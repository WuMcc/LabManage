import request from '@/utils/request'
import serveStatic from 'serve-static';

export default{
  getDeviceList(searchModel){
    return request({
      url: '/sys/device/list',
      method: 'get',
      params:{
        dname: searchModel.dname,
        did: searchModel.did,
      }
    });
  },
  addDevice(device){
    return request({
      url: '/sys/device',
      method: 'post',
      data: device
    });
  },
  updateDevice(device){
    return request({
      url: '/sys/device/set',
      method: 'put',
      data: device
    });
  },
  updateDevices(device){
    return request({
      url: '/sys/device/borrow',
      method: 'put',
      data: device
    });
  },
  // saveDevice(device){
  //   if(device.did == null && device.did == undefined){
  //     return this.addDevice(device);
  //   }
  //   return this.updateDevice(device);
  // },
  getDeviceById(id){
    return request({
      url: `/sys/device/${id}`,
      method: 'get'
    });
  },
  deleteDeviceById(id){
    return request({
      url: `/sys/device/${id}`,
      method: 'delete'
    });
  },
}
