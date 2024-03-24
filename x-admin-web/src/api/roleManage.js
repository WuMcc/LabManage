import request from '@/utils/request'

export default{
  getRoleList(searchModel){
    return request({
      url: '/sys/role/list',
      method: 'get',
      params:{
        pageNo: searchModel.pageNo,
        pageSize: searchModel.pageSize,
        roleName: searchModel.roleName
      }
    });
  },
  addRole(role){
    return request({
      url: '/sys/role',
      method: 'post',
      data: role
    });
  },
  updateRole(role){
    return request({
      url: '/sys/role',
      method: 'put',
      data: role
    });
  },
  saveRole(role){
    if(role.roleId == null && role.roleId == undefined){
      return this.addRole(role);
    }
    return this.updateRole(role);
  },
  getRoleById(id){
    return request({
      url: `/sys/role/${id}`,
      method: 'get'
    });
  },
  deleteRoleById(id){
    return request({
      url: `/sys/role/${id}`,
      method: 'delete'
    });
  },
  getAllRoleList(){
    return request({
      url: '/sys/role/all',
      method: 'get'
    });
  },
}