<template>
  <div>
    <!-- 外层容器 -->
    <el-container>
      <!-- 上半区域：顶栏 -->
      <el-header class="layout-header">
        <!-- 顶栏右侧的当前用户信息及下拉菜单 -->
        <div class="login-user">
          <span class="welcome">欢迎回来，<b>{{currentUserName}}</b>！</span>
          <el-dropdown @command="handleCommand">
            <el-avatar size="medium" :src="currentUserAvatar"></el-avatar>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item icon="el-icon-plus" command="openEditInfoDialog">修改资料</el-dropdown-item>
              <el-dropdown-item icon="el-icon-plus" command="openEditAvatarDialog">修改头像</el-dropdown-item>
              <el-dropdown-item icon="el-icon-plus" command="openEditPasswordDialog">修改密码</el-dropdown-item>
              <el-dropdown-item icon="el-icon-plus" divided command="openLogoutConfirm">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
        <!-- 顶栏的标题文字 -->
        <h1>学茶商城后台管理平台</h1>
      </el-header>
      <!-- 下半区域 -->
      <el-container class="layout-body">
        <!-- 下半区域的左侧边栏 -->
        <el-aside class="layout-aside">
          <el-menu
              router
              :default-active="$router.currentRoute.path"
              class="el-menu-vertical-demo"
              background-color="#222"
              text-color="#fff"
              active-text-color="#fff">
            <!-- 主页 -->
            <el-menu-item index="/admin/index">
              <i class="el-icon-s-home"></i>
              <span>主页</span>
            </el-menu-item>
            <!-- 临时菜单 -->
            <el-submenu index="temp">
              <template slot="title">
                <i class="el-icon-warning"></i>
                <span>临时菜单</span>
              </template>
              <el-menu-item index="/admin/temp/tag/type/add-new">
                <i class="el-icon-circle-plus"></i>
                <span slot="title">新增标签类别</span>
              </el-menu-item>
              <el-menu-item index="/admin/temp/tag/type/list">
                <i class="el-icon-circle-plus"></i>
                <span slot="title">标签类别列表</span>
              </el-menu-item>
              <el-menu-item index="/admin/temp/tag/add-new">
                <i class="el-icon-circle-plus"></i>
                <span slot="title">新增标签</span>
              </el-menu-item>
              <el-menu-item index="/admin/temp/tag/list">
                <i class="el-icon-circle-plus"></i>
                <span slot="title">标签列表</span>
              </el-menu-item>
              <el-menu-item index="/admin/temp/category/add-new">
                <i class="el-icon-circle-plus"></i>
                <span slot="title">新增类别</span>
              </el-menu-item>
              <el-menu-item index="/admin/temp/category/list">
                <i class="el-icon-circle-plus"></i>
                <span slot="title">类别列表</span>
              </el-menu-item>
              <el-menu-item index="/admin/temp/user/add-new">
                <i class="el-icon-circle-plus"></i>
                <span slot="title">新增用户</span>
              </el-menu-item>
              <el-menu-item index="/admin/temp/user/list">
                <i class="el-icon-circle-plus"></i>
                <span slot="title">用户列表</span>
              </el-menu-item>
            </el-submenu>
          </el-menu>
        </el-aside>
        <!-- 下半区域的右侧主体 -->
        <el-main class="layout-main">
          <!-- 由其它视图组件来显示 -->
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<style>
.layout-header {
  background: #1684b0;
}

.layout-header .login-user {
  float: right;
  margin-top: 12px;
  display: flex;
}

.layout-header .login-user .welcome {
  line-height: 36px;
  color: #fff;
  margin-right: 10px;
}

.layout-header h1 {
  color: #fff;
  line-height: 60px;
}

.layout-body {
  position: absolute;
  top: 60px;
  bottom: 0;
  left: 0;
  right: 0;
}

.layout-aside {
  background: #222;
}

.layout-aside .el-menu {
  border: 0;
}

.layout-aside i {
  color: #fff !important;
}

.layout-main {
  background: #fff;
}

.el-menu-item.is-active {
  background: #2d3c4d !important;
}
</style>

<script>
export default {
  data() {
    return {
      currentUserName: '',
      currentUserAvatar: ''
    }
  },
  methods: {
    handleCommand(command) {
      if (command == 'openEditInfoDialog') {
        this.openEditInfoDialog();
      } else if (command == 'openEditAvatarDialog') {
        this.openEditAvatarDialog();
      } else if (command == 'openEditPasswordDialog') {
        this.openEditPasswordDialog();
      } else if (command == 'openLogoutConfirm') {
        this.openLogoutConfirm();
      }
    },
    openEditInfoDialog() {
      console.log('准备弹出修改当前用户资料的对话框');
    },
    openEditAvatarDialog() {
      console.log('准备弹出修改当前用户头像的对话框');
    },
    openEditPasswordDialog() {
      console.log('准备弹出修改当前用户密码的对话框');
    },
    openLogoutConfirm() {
      let message = '您确定退出登录吗？';
      this.$confirm(message, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        localStorage.removeItem('localJwt');
        this.$router.push('/login');
      }).catch(() => {
      });
    },
    loadCurrentUserInfo() {
      this.currentUserName = localStorage.getItem('currentUserName');
      this.currentUserAvatar = localStorage.getItem('currentUserAvatar');
    }
  },
  mounted() {
    this.loadCurrentUserInfo();
  }
}
</script>