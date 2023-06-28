<template>
  <div>
    <img :src="currentUserAvatar"/>
    <el-empty description="主页正在开发中，敬请期待……"></el-empty>
  </div>
</template>

<script>
export default {
  data() {
    return {
      currentUserAvatar: localStorage.getItem('currentUserAvatar')
    }
  }
}
</script>

<style>
</style>