<template>
  <div>
    <!-- 搜索栏 -->
    <el-card id="search" style="display: flex; ">
      <el-row>
        <el-col :span="24">
          <el-input v-model="searchModel.lid" placeholder="实验室ID" clearable></el-input>
          <el-input v-model="searchModel.lname" placeholder="实验室名称" clearable></el-input>
          <el-button @click="getLabList" type="primary" round icon="el-icon-search">查询</el-button>
          <el-button @click="addLab" type="primary" icon="el-icon-plus" circle></el-button>
        </el-col>
      </el-row>
    </el-card>

    <!-- 结果列表 -->
    <el-card>
      <el-table :data="labList" stripe style="width: 100%">
        <el-table-column label="#" width="60">
          <template slot-scope="scope">
            <!-- (pageNo-1) * pageSize + index + 1 -->
            {{
          (searchModel.pageNo - 1) * searchModel.pageSize + scope.$index + 1
        }}
          </template>
        </el-table-column>
        <el-table-column prop="lid" label="实验室ID" width="150">
        </el-table-column>
        <el-table-column prop="lname" label="实验室名称" width="150">
        </el-table-column>
        <el-table-column prop="status" label="实验室状态" width="140">

          <template slot-scope="scope">
            <el-tag v-if="scope.row.status == 1">未使用</el-tag>
            <el-tag v-else type="danger">使用中</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="140">

          <template slot-scope="scope">
            <el-button @click="openEditUI(scope.row.lid)" type="primary" icon="el-icon-edit" size="mini"
              circle></el-button>
            <el-button @click="deleteLab(scope.row)" type="danger" icon="el-icon-delete" size="mini"
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
    <el-dialog @close="clearForm" :title="title" :visible.sync="dialogFormVisible">
      <el-form :model="labForm" ref="labFormRef" :rules="rules">
        <el-form-item label="实验室名称" prop="lname" :label-width="formLabelWidth">
          <el-input v-model="labForm.lname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="实验室ID" prop="lid" :label-width="formLabelWidth">
          <el-input v-model="labForm.lid" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="实验室状态" :label-width="formLabelWidth">
          <el-switch v-model="labForm.status" :active-value="1" :inactive-value="0">
          </el-switch>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addLab">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 用户信息编辑对话框 -->
    <el-dialog @close="clearForm1" :title="title" :visible.sync="dialogFormVisible1">
      <el-form :model="labForm" ref="labFormRef1" :rules="rules">
        <el-form-item label="实验室名称" prop="lname" :label-width="formLabelWidth">
          <el-input v-model="labForm.lname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="实验室ID" prop="lid" :label-width="formLabelWidth">
          <el-input v-model="labForm.lid" disabled autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="实验室状态" :label-width="formLabelWidth">
          <el-switch v-model="labForm.status" :active-value="1" :inactive-value="0">
          </el-switch>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible1 = false">取 消</el-button>
        <el-button type="primary" @click="updateLab">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import labApi from "@/api/labManage";
import Axios from "axios";
export default {
  data() {
    return {
      formLabelWidth: "130px",
      labForm: {},
      dialogFormVisible: false,
      dialogFormVisible1: false,
      title: "",
      total: 0,
      searchModel: {
        pageNo: 1,
        pageSize: 5,
      },
      labList: [],
      rules: {
        lname: [
          { required: true, message: "请输入实验室名称", trigger: "blur" },
          {
            min: 3,
            max: 10,
            message: "长度在 3 到 10 个字符",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    deleteLab(lab) {
      this.$confirm(`您确认删除该实验室 ${lab.lname} ?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        labApi.deleteLabById(lab.lid).then(response => {
          this.$message({
            type: 'success',
            message: response.message
          });
          this.getLabList();
        });

      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    addLab() {
      this.title = "新增实验室";
      this.dialogFormVisible = true;
      // 触发表单验证
      this.$refs.labFormRef.validate((valid) => {
        if (valid) {
            // 提交请求给后台
          labApi.addLab(this.labForm).then(response => {
            // 成功提示
            this.$message({
              message: response.message,
              type: 'success'
            });
            // 关闭对话框
            this.dialogFormVisible = false;
            // 刷新表格
            this.getLabList();
          }); 
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    updateLab() {
      this.dialogFormVisible1 = true;
      // 触发表单验证
      this.$refs.labFormRef1.validate((valid) => {
        if (valid) {
            // 提交请求给后台
          labApi.updateLab(this.labForm).then(response => {
            // 成功提示
            this.$message({
              message: response.message,
              type: 'success'
            });
            // 关闭对话框
            this.dialogFormVisible1 = false;
            // 刷新表格
            this.getLabList();
          }); 
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    clearForm() {
      this.labForm = {};
      this.$refs.labFormRef.clearValidate();
    },
    clearForm1() {
      this.labForm = {};
      this.$refs.labFormRef1.clearValidate();
    },
    openEditUI(id) {
        this.title = "修改实验室";
        // 根据id查询用户数据
        labApi.getLabById(id).then(response => {
          this.labForm = response.data;
        });
      
      this.dialogFormVisible1 = true;
    },
    handleSizeChange(pageSize) {
      this.searchModel.pageSize = pageSize;
      this.getLabList();
    },
    handleCurrentChange(pageNo) {
      this.searchModel.pageNo = pageNo;
      this.getLabList();
    },
    getLabList() {
      labApi.getLabList(this.searchModel).then((response) => {
        this.labList = response.data.rows;
        this.total = response.data.total;
      });
    },
  },
  created() {
    this.getLabList();
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