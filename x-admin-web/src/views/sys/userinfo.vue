<template>
  <div>
    <el-card style=" margin-block: center; " shadow="hover">
      <el-form label-width="80px" size="small">

        <el-form-item label="用户名" style="margin-top: 30px">
          <el-input v-model="userList.username" disabled autocomplete="off" style="width: 400px"></el-input>
        </el-form-item>
        <el-form-item label="昵称">
          <el-input v-model="userList.nickname" autocomplete="off" style="width: 400px"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-input v-model="userList.sex" placeholder="性别" style="width: 400px">
          </el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="userList.email" autocomplete="on" style="width: 400px"></el-input>
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="userList.phone" autocomplete="off" style="width: 400px"></el-input>
        </el-form-item>
        <el-form-item style="margin-left: 150px; ">
          <el-button type="primary" @click="return1">返回主页</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>
 
<script>
import userApi from '@/api/userInfo'
import { getToken } from '@/utils/auth'
import userManage from '@/api/userManage'
export default {
  name: "Person",
  data() {
    //电话号码校验
    const checkPhone = (rule, value, callback) => {
      if(!this.validatePhone.test(value)) {
        return callback(new Error('手机号格式不合法'));
      }
      callback()
    }
    return {
      token: getToken(),
      userList:[],
      options: [{
        value: '男',
        label: '男'
      }, {
        value: '女',
        label: '女'
      }],
      value: '',
      rules: {
        phone: [
          {required: true, message: '请输入手机号', trigger: 'blur'}
        ],
      },
      rulesPhone: {
        phone: [
          { validator: checkPhone, trigger: 'blur' }
        ],
      },
      interval: null,
      validatePhone: /^1[3,4,5,6,7,8,9][0-9]{9}$/
    }
  },
 
  created() {
    this.getUserInfo();
  },
  methods: {
    load() {
      const username = this.name
      if (!username) {
        this.$message.error("当前无法获取用户信息!")
        return
      }
    },
    getUserInfo(){
      userApi.getUserInfo(this.token).then(res => {
        this.userList =  res.data;
      })
    },
    save() {
      userManage.getUserByName(userList.name).then(res => {
        this.userList = res.data;
      })
      this.request.put("/user", this.userList).then(res => {
        if (res.data) {
          this.$message.success("保存成功")
          this.created()
 
          this.$emit('refreshUser')
        } else {
          this.$message.error("保存失败")
        }
      })
    },
    return1() {
      this.$router.push("/")
    }
  },
  // mounted(){
  //   this.$http.get("user/info").then((resp)=>{
  //     if(resp.code == 20000){
  //       this.form == resp.data;
  //     }
  //   })
  // }
}
</script>
 
<style scoped>
.el-card {
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>