import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/views/Home'
import Shop from '@/views/Shop'
import TableList from '@/views/TableList'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/shop',
      name: 'shop',
      component: Shop
    },
    {
      path: '/table',
      name: 'table',
      component: TableList
    }
  ]
})
