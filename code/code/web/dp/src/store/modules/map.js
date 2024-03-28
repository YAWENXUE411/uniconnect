const state = {
    activeMap: null
}
const getters = {
    getMap: state => {
        return state.activeMap;
    }
}

const actions = {}

const mutations = {
    setMap(state, map) {
        state.activeMap = map;
    }
}

export default {
    namespace: true,
    state,
    getters,
    actions,
    mutations
}