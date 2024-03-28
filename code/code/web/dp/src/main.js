import Vue from 'vue'
import App from './App'
import router from './router'
import store from "./store/index"
// import { initResize } from '@/utils/resize.js'
import ElementUI from "element-ui"
import axios from 'axios'
import "element-ui/lib/theme-chalk/index.css"
import "@/assets/styles/iconfont/iconfont.css"


// initResize()
Vue.config.productionTip = false
Vue.prototype.$axios = axios

Vue.use(ElementUI)
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
