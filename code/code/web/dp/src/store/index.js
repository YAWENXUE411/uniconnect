import Vue from "vue";
import Vuex from "vuex";
import user from "./modules/user"
import map from "./modules/map"

Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
        user,
        map
    },
    state: {},
    mutations: {},
    actions: {}
})