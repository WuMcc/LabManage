<template>
  <div>
    <!-- 搜索栏 -->
    <el-card id="search" style="display: flex; ">
      <el-row>
        <el-col :span="24">
          <el-input v-model="searchModel.did" placeholder="设备ID" clearable></el-input>
          <el-input v-model="searchModel.dname" placeholder="设备名称" clearable></el-input>
          <el-button @click="getDeviceList" type="primary" round icon="el-icon-search">查询</el-button>
        </el-col>
      </el-row>
    </el-card>

    <!-- 结果列表 -->
    <el-card>
      <el-table :data="deviceList" stripe style="width: 100%">
        <el-table-column label="#" width="60">
          <template slot-scope="scope">
            <!-- (pageNo-1) * pageSize + index + 1 -->
            {{
          (searchModel.pageNo - 1) * searchModel.pageSize + scope.$index + 1
        }}
          </template>
        </el-table-column>
        <el-table-column prop="did" label="设备ID" width="150">
        </el-table-column>
        <el-table-column prop="dname" label="设备名称" width="150">
        </el-table-column>
        <el-table-column prop="lid" label="所属实验室编号" width="150">
        </el-table-column>
        <el-table-column prop="status" label="设备状态" width="140">

          <template slot-scope="scope">
            <el-tag v-if="scope.row.status == 1">未使用</el-tag>
            <el-tag v-else type="danger">使用中</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="140">

          <template slot-scope="scope">
            <el-button @click="openEditUI(scope.row.did)" type="primary" icon="el-icon-edit" size="mini"
              circle></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- 分页组件 -->
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
      :current-page="searchModel.pageNo" :page-sizes="[5, 10, 20, 50]" :page-size="searchModel.pageSize"
      layout="total, sizes, prev, pager, next, jumper" :total="total">
    </el-pagination>


    <!-- 用户信息编辑对话框 -->
    <el-dialog @close="clearForm1" :title="title" :visible.sync="dialogFormVisible1">
      <el-form :model="deviceForm" ref="deviceFormRef1" :rules="rules">
        <el-form-item label="设备名称" prop="dname" :label-width="formLabelWidth">
          <el-input v-model="deviceForm.dname" disabled autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="设备ID" prop="did" :label-width="formLabelWidth">
          <el-input v-model="deviceForm.did" disabled autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="所属实验室ID" prop="lid" :label-width="formLabelWidth">
          <el-input v-model="deviceForm.lid" disabled autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="预约该设备" :label-width="formLabelWidth">
          <el-switch v-model="deviceForm.status" :active-value="0" :inactive-value="1"
          >
          </el-switch>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible1 = false">取 消</el-button>
        <el-button type="primary" @click="updateDevice">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import deviceApi from "@/api/deviceManage";
import Axios from "axios";
import {MessageBox} from "element-ui";
export default {
  data() {
    return {
      formLabelWidth: "130px",
      deviceForm: {},
      dialogFormVisible: false,
      dialogFormVisible1: false,
      title: "",
      total: 0,
      searchModel: {
        pageNo: 1,
        pageSize: 5,
      },
      deviceList: [],
      rules: {
        dname: [
          { required: true, message: "请输入设备名称", trigger: "blur" },
          {
            min: 1,
            max: 10,
            message: "长度在 1 到 10 个字符",
            trigger: "blur",
          },
        ],
      },
    };
  },
  mounted(){
    MessageBox.alert('预约设备后两小时会自动释放！！！请及时使用！！').then(() => {
      this.dialogVisible = false;
    })
  },
  methods: {
    deleteDevice(device) {
      this.$confirm(`您确认删除该设备 ${device.dname} ?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deviceApi.deleteDeviceById(device.did).then(response => {
          this.$message({
            type: 'success',
            message: response.message
          });
          this.getDeviceList();
        });

      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    updateDevice() {
      this.dialogFormVisible1 = true;
      // 触发表单验证
      this.$refs.deviceFormRef1.validate((valid) => {
        if (valid) {
            // 提交请求给后台
          deviceApi.updateDevices(this.deviceForm).then(response => {
            // 成功提示
            this.$message({
              message: response.message,
              type: 'success'
            });
            // 关闭对话框
            this.dialogFormVisible1 = false;
            // 刷新表格
            this.getDeviceList();
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    clearForm() {
      this.deviceForm = {};
      this.$refs.deviceFormRef.clearValidate();
    },
    clearForm1() {
      this.deviceForm = {};
      this.$refs.deviceFormRef1.clearValidate();
    },
    openEditUI(id) {
        this.title = "如预约错误，点击取消按钮重新选择预约！";
        // 根据id查询用户数据
        deviceApi.getDeviceById(id).then(response => {
          this.deviceForm = response.data;
        });

      this.dialogFormVisible1 = true;
    },
    handleSizeChange(pageSize) {
      this.searchModel.pageSize = pageSize;
      this.getDeviceList();
    },
    handleCurrentChange(pageNo) {
      this.searchModel.pageNo = pageNo;
      this.getDeviceList();
    },
    getDeviceList() {
      deviceApi.getDeviceList(this.searchModel).then((response) => {
        this.deviceList = response.data.rows;
        this.total = response.data.total;
      });
    },
  },
  created() {
    this.getDeviceList();
  },
};
</script>

<style>
#search .el-input {
  width: 200px;
  margin-right: 10px;
}

.el-dialog .el-input {
  width: 85%;
}
</style>
