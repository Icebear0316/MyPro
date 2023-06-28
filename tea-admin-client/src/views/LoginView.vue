<template>
  <div>
    <div style="background: #fff; width: 600px; margin: 60px auto; padding: 20px 50px;">
      <!-- 标题文字 -->
      <h1 style="text-align: center; margin: 30px auto;">管理员登录</h1>
      <!-- 登录表单 -->
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="ruleForm.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="ruleForm.password"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 表单
      ruleForm: {
        username: 'root',
        password: '123456'
      },
      // 表单规则
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 4, max: 15, message: '长度在 4 到 15 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 4, max: 15, message: '长度在 4 到 15 个字符', trigger: 'blur'}
        ]
      }
    };
  },
  methods: {
    // 提交表单
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let url = 'http://localhost:9080/account/users/login';
          console.log('url = ' + url);
          let formData = this.qs.stringify(this.ruleForm);
          console.log('formData = ' + formData);

          this.axios.post(url, formData).then((response) => {
            let jsonResult = response.data;
            if (jsonResult.state == 20000) {
              this.$message({
                message: '登录成功！',
                type: 'success'
              });
              let loginResult = jsonResult.data;
              localStorage.setItem('localJwt', loginResult.token);
              localStorage.setItem('currentUserId', loginResult.id);
              localStorage.setItem('currentUserName', loginResult.username);
              localStorage.setItem('currentUserAvatar', loginResult.avatar);
              this.$router.push('/');
            } else if (jsonResult.state == 40100) {
              this.$message.error(jsonResult.message);
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
body {
  background: #2c3e50;
}
</style>