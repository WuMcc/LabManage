<template>
    <div>
        <!-- 搜索栏 -->
        <el-card id="search" style="display: flex; ">
            <el-row>
                <el-col :span="24">
                    <el-input v-model="searchModel.lid" placeholder="实验室ID" clearable></el-input>
                    <el-input v-model="searchModel.lname" placeholder="实验室名称" clearable></el-input>
                    <el-button @click="getLabList" type="primary" round icon="el-icon-search">查询</el-button>
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
                <el-table-column label="预约" width="140">

                    <template slot-scope="scope">
                        <el-button @click="openEditUI(scope.row.lid)" type="primary" icon="el-icon-edit" size="mini"
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
            <el-form :model="labForm" ref="labFormRef1" :rules="rules">
                <el-form-item label="实验室名称" prop="lname" :label-width="formLabelWidth">
                    <el-input v-model="labForm.lname" disabled autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="实验室ID" prop="lid" :label-width="formLabelWidth">
                    <el-input v-model="labForm.lid" disabled autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="预约该实验室" :label-width="formLabelWidth">
                    <el-switch v-model="labForm.status" :active-value="0" :inactive-value="1"
                        >
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
import {MessageBox} from "element-ui";
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
  mounted(){
    MessageBox.alert('预约实验室后两小时会自动释放！！！请及时使用！！').then(() => {
      this.dialogVisible = false;
    })
  },
    methods: {
        updateLab() {
            this.dialogFormVisible1 = true;
            // 触发表单验证
            this.$refs.labFormRef1.validate((valid) => {
                if (valid) {
                    // 提交请求给后台
                    labApi.updateLabs(this.labForm).then(response => {
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
            this.title = "如预约错误，点击取消按钮重新选择预约！";
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
