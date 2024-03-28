const state = {
    user: null
}
const getters = {
    getUser: state => {
        return state.user;
    }
}

const actions = {}

const mutations = {
    setUser(state, user) {
        state.user = user;
    }
}

export default {
    namespace: true,
    state,
    getters,
    actions,
    mutations
}