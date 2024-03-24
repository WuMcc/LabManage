import request from '@/utils/request'
import serveStatic from 'serve-static';

export default{
  getLabList(searchModel){
    return request({
      url: '/sys/lab/list',
      method: 'get',
      params:{
        lname: searchModel.lname,
        lid: searchModel.lid,
      }
    });
  },
  addLab(lab){
    return request({
      url: '/sys/lab',
      method: 'post',
      data: lab
    });
  },
  updateLab(lab){
    return request({
      url: '/sys/lab/set',
      method: 'put',
      data: lab
    });
  },
  updateLabs(lab){
    return request({
      url: '/sys/lab/borrow',
      method: 'put',
      data: lab
    });
  },
  // saveLab(lab){
  //   if(lab.lid != null && lab.lid != undefined){
  //     return this.addLab(lab);
  //   }
  //   return this.updateLab(lab);
  // },
  getLabById(id){
    return request({
      url: `/sys/lab/${id}`,
      method: 'get'
    });
  },
  deleteLabById(id){
    return request({
      url: `/sys/lab/${id}`,
      method: 'delete'
    });
  },
}
