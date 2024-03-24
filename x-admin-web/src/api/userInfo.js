import request from '@/utils/request'


export default{
  
  getUserInfo(token){
    return request({
      url: '/user/info',
      method: 'get',
      params: {token}
    });
  }
}
