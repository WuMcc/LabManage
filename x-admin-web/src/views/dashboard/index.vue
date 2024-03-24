<template>
  <dev class="dashboard-container">
    <el-dialog v-model="dialogVisible">
      <span>弹窗</span>
    </el-dialog>
    <div class="dashboard-text">{{ name }}欢迎您使用实验室管理系统！</div>
    </dev>
</template>

<script>
import { mapGetters } from 'vuex'
import {MessageBox} from 'element-ui'
export default {
  name: 'Dashboard',
  computed: {
    ...mapGetters([
      'username'
    ])
  },
  data(){
    return{
      dialogVisible: true,
    }
  },
  mounted(){
    MessageBox.alert('公告：用户可以预约状态为“未使用”的实验室或设备！').then(() => {
      this.dialogVisible = false;
    })
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
