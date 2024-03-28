<template>
    <div class="base-map" id="container"></div>
</template>

<script>
export default {
    name: 'Map',
    data() {
        return {}
    },
    created() {},
    mounted() {
        this.initMap()
    },
    beforeDestroy() {},
    methods: {
        initMap() {
            const map = new BMapGL.Map('container')
            const point = new BMapGL.Point(MAP_CENTER[0], MAP_CENTER[1])
            map.centerAndZoom(point, MAP_ZOOM)
            map.enableScrollWheelZoom(true)
            this.$store.commit("setMap", map)
        }
    }
}
</script>

<style lang="scss" scoped>
.base-map {
    width: 100%;
    height: 100%;
    position: absolute;
    margin: 0;
    overflow: hidden;
    z-index: 0;
}
</style>
