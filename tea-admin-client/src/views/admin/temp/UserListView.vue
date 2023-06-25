<template>
  <div>
    <!-- 顶部面包屑标识与导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right" style="font-size: 16px;">
      <el-breadcrumb-item :to="{ path: '/' }">
        <i class="el-icon-s-promotion"></i> 后台管理
      </el-breadcrumb-item>
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
    </el-breadcrumb>
    <el-divider></el-divider>

    <!-- 操作区域 -->
    <div style="margin: 0 0 20px 0;">
      <el-button type="primary" size="medium" @click="$router.push('/admin/temp/user/add-new')">添加用户
      </el-button>
    </div>

    <!-- 数据表格 -->
    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="id" label="ID" width="60" align="center"></el-table-column>
      <el-table-column label="头像" width="60" align="center">
        <template slot-scope="scope">
          <el-avatar :size="30" :src="scope.row.avatar"></el-avatar>
        </template>
      </el-table-column>
      <el-table-column prop="username" label="用户名" width="120" header-align="center"
                       :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="nickname" label="昵称" width="100" header-align="center"
                       :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="phone" label="手机号码" width="120" align="center"
                       :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="email" label="电子邮箱" width="150" header-align="center"
                       :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="description" label="简介" header-align="center"
                       :show-overflow-tooltip="true"></el-table-column>
      <el-table-column label="是否启用" width="120" align="center">
        <template slot-scope="scope">
          <el-switch
              @change="toggleEnable(scope.row)"
              v-model="scope.row.enable"
              :active-value="1"
              :inactive-value="0"
              active-color="#13ce66"
              inactive-color="#999">
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="100" align="center">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" circle size="mini"
                     @click="openEditDialog(scope.row)"></el-button>
          <el-button type="danger" icon="el-icon-delete" circle size="mini"
                     @click="openDeleteConfirm(scope.row)"></el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页控件 -->
    <div style="margin: 10px 0; text-align: right;">
      <el-pagination
          @current-change="changePage"
          :hide-on-single-page="true"
          :page-size="pageSize"
          :current-page="currentPage"
          layout="total, prev, pager, next"
          :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 表格数据
      tableData: [],
      // 分页相关数据
      currentPage: this.$router.currentRoute.query.page ? parseInt(this.$router.currentRoute.query.page) : 1,
      pageSize: 20,
      total: 0,
    }
  },
  methods: {
    // 打开编辑对话框
    openEditDialog(user) {
      let title = '提示';
      let message = '您正在尝试编辑【' + user.id + '-' + user.username + '】的用户，抱歉，此功能尚未实现……';
      this.$alert(message, title, {
        confirmButtonText: '确定'
      });
    },
    // 切换启用状态
    toggleEnable(user) {
      let enableText = ['禁用', '启用'];
      let url = 'http://localhost:9080/account/users/' + user.id;
      let enable = user.enable;
      if (enable == 1) {
        url += '/enable';
      } else {
        url += '/disable';
      }
      console.log('url = ' + url);

      this.axios.post(url).then((response) => {
        let jsonResult = response.data;
        if (jsonResult.state == 20000) {
          this.$message({
            message: '将【' + user.username + '】的启用状态修改为【' + enableText[enable] + '】成功！',
            type: 'success'
          });
          this.loadUserList();
        } else if (jsonResult.state == 40400) {
          this.$alert(jsonResult.message, '操作失败', {
            confirmButtonText: '确定',
            callback: action => {
              this.loadUserList();
            }
          });
        } else {
          this.$alert(jsonResult.message, '操作失败', {
            confirmButtonText: '确定',
            callback: action => {
            }
          });
        }
      }).catch(err => {
        let message = '服务器响应错误！';
        this.$alert(message, '操作失败', {
          confirmButtonText: '确定',
          callback: action => {
          }
        });
      });
    },
    // 打开删除确认框
    openDeleteConfirm(user) {
      let message = '此操作将永久删除【' + user.username + '】用户，是否继续？';
      this.$confirm(message, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.handleDelete(user);
      }).catch(() => {
      });
    },
    // 处理删除
    handleDelete(user) {
      let url = 'http://localhost:9080/account/users/' + user.id + '/delete';
      console.log('url = ' + url);

      this.axios.post(url).then((response) => {
        let jsonResult = response.data;
        if (jsonResult.state == 20000) {
          this.$message({
            message: '删除【' + user.username + '】用户成功！',
            type: 'success'
          });
          this.loadUserList();
        } else if (jsonResult.state == 40400) {
          this.$alert(jsonResult.message, '操作失败', {
            confirmButtonText: '确定',
            callback: action => {
              this.loadUserList();
            }
          });
        } else {
          this.$alert(jsonResult.message, '操作失败', {
            confirmButtonText: '确定',
            callback: action => {
            }
          });
        }
      }).catch(err => {
        let message = '服务器响应错误！';
        this.$alert(message, '操作失败', {
          confirmButtonText: '确定',
          callback: action => {
          }
        });
      });
    },
    // 切换分页
    changePage(page) {
      this.$router.replace('?page=' + page);
      this.loadUserList();
    },
    // 加载用户列表
    loadUserList() {
      let page = this.$router.currentRoute.query.page;
      if (!page) {
        page = 1;
      }

      let url = 'http://localhost:9080/account/users?page=' + page;
      console.log('url = ' + url);

      this.axios.get(url).then((response) => {
        let jsonResult = response.data;
        if (jsonResult.state == 20000) {
          this.tableData = jsonResult.data.list;
          this.currentPage = jsonResult.data.currentPage;
          this.pageSize = jsonResult.data.pageSize;
          this.total = jsonResult.data.total;
        }
      }).catch(err => {
        let message = '服务器响应错误！';
        this.$alert(message, '操作失败', {
          confirmButtonText: '确定',
          callback: action => {
          }
        });
      });
    }
  },
  mounted() {
    this.loadUserList();
  }
}
</script>

<style scoped>

</style>