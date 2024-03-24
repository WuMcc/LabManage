import request from '@/utils/request'

export default{
  getAllMenu(){
    return request({
      url: 'sys/menu',
      method: 'get'
    });
  }
}