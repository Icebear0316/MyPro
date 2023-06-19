<template>
  <div>
    <!-- 顶部的面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right" style="font-size: 16px;">
      <el-breadcrumb-item :to="{ path: '/' }">主页</el-breadcrumb-item>
      <el-breadcrumb-item>内容管理</el-breadcrumb-item>
      <el-breadcrumb-item>标签管理</el-breadcrumb-item>
      <el-breadcrumb-item>新增标签类别</el-breadcrumb-item>
    </el-breadcrumb>
    <el-divider></el-divider>

    <!-- 表单 -->
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
      <el-form-item label="标签类别名称" prop="name">
        <el-input v-model="ruleForm.name"></el-input>
      </el-form-item>
      <el-form-item label="排序序号" prop="sort">
        <el-input v-model="ruleForm.sort"></el-input>
      </el-form-item>
      <el-form-item label="是否启用">
        <el-switch
            v-model="ruleForm.enable"
            :active-value="1"
            :inactive-value="0"
            active-color="#13ce66"
            inactive-color="#ccc">
        </el-switch>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 表单
      ruleForm: {
        name: '测试标签名称X',
        sort: '99',
        enable: 1
      },
      // 表单规则
      rules: {
        name: [
          {required: true, message: '请输入标签类别名称', trigger: 'blur'},
          {pattern: /^[a-zA-Z\u4e00-\u9fa5]{2,10}$/, message: '标签类别必须是2~10长度的字符组成，且不允许使用标点符号', trigger: 'blur'}
        ],
        sort: [
          {required: true, message: '请输入排序序号', trigger: 'blur'},
          {pattern: /^(\d{1}|[1-9]{1}[0-9]{1})$/, message: '排序序号必须是0~99之间的值', trigger: 'blur'}
        ]
      }
    };
  },
  methods: {
    // 提交表单
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let url = 'http://localhost:9080/content/tags/type/add-new';
          console.log('url = ' + url);
          let formData = this.qs.stringify(this.ruleForm);
          console.log('formData = ' + formData);

          this.axios.post(url, formData).then((response) => {
            let jsonResult = response.data;
            if (jsonResult.state == 20000) {
              this.$message({
                message: '新增标签类别成功！',
                type: 'success'
              });
              this.resetForm(formName);
            } else {
              let title = '操作失败';
              this.$alert(jsonResult.message, title, {
                confirmButtonText: '确定',
                callback: action => {
                }
              });
            }
          });
        }
      });
    },
    // 重置表单
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>

<style>
</style>