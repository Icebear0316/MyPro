import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: HomeView,
    redirect: '/admin/index',
    children: [
      {
        path: '/admin/index',
        component: () => import('../views/admin/AdminIndexView.vue')
      },
      {
        path: '/admin/temp/tag/type/add-new',
        component: () => import('../views/admin/temp/TagTypeAddNewView.vue')
      },
      {
        path: '/admin/temp/tag/type/list',
        component: () => import('../views/admin/temp/TagTypeListView.vue')
      },
      {
        path: '/admin/temp/tag/add-new',
        component: () => import('../views/admin/temp/TagAddNewView.vue')
      },
      {
        path: '/admin/temp/tag/list',
        component: () => import('../views/admin/temp/TagListView.vue')
      }
    ]
  },
  {
    path: '/login',
    component: () => import('../views/LoginView.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
