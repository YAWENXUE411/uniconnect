<template>
    <div class="shop">
        <Map />
        <ShopInfo v-if="shopItem" :shop="shopItem" @close="closeShopItem" />
        <AddShop v-if="newShop" :shop="newShop" @lnglat-change="lnglatChange" @cancel="cancelNewShop" @confirm="confirmNewShop" />
        <div class="operates">
            <el-button @click="addShop">Add Store</el-button>
        </div>
        <div class="go-back iconfont icon-fanhui" :title="'go back'" @click="goBack"></div>
    </div>
</template>
<script>
import Map from "@/components/Map";
import ShopInfo from "./components/ShopInfo";
import AddShop from "./components/AddShop";
import dpUrl from "@/assets/images/dp.png";
import dpSelectedUrl from "@/assets/images/dp-selected.png";
const dpIcon = new BMapGL.Icon(dpUrl, new BMapGL.Size(24, 24), {
  imageSize: new BMapGL.Size(24, 24)
});
const dpSelectedIcon = new BMapGL.Icon(dpSelectedUrl, new BMapGL.Size(24, 24), {
  imageSize: new BMapGL.Size(24, 24)
});
export default {
  name: "Shop",
  components: {
    Map,
    ShopInfo,
    AddShop
  },
  data() {
    return {
      shopList: [],
      markers: [],
      selectedMarker: null,
      newMarker: null,
      shopItem: null,
      mouseTool: null,
      newShopVisible: false,
      newShop: null,
      clickHandler: null,
      defaultCursor: null
    };
  },
  computed: {
    map() {
      return this.$store.getters.getMap;
    },
    user() {
      return this.$store.getters.getUser;
    }
  },
  watch: {
    map: {
      handler(_map) {
        if (_map) {
          this.defaultCursor = _map.getDefaultCursor();
          this.getShopList();
        }
      },
      immediate: true
    }
  },
  mounted() {},
  beforeDestroy() {
    this.removeShopMarkers();
  },
  methods: {
    goBack() {
      this.$router.go(-1);
    },
    closeShopItem() {
      if (this.selectedMarker) {
        this.selectedMarker.setIcon(dpIcon);
        this.selectedMarker = null;
      }
      this.shopItem = null;
    },
    addShop() {
      if (!this.user) {
        this.$message.error("Please login first!");
        return;
      }
      this.closeNewCacheShop();
      if (!this.clickHandler) {
        this.clickHandler = this.map.addEventListener("click", this.mapClick);
        this.map.setDefaultCursor("crosshair");
        this.clickHandler = true;
      }
    },
    mapClick(e) {
      let lnglat = e.latlng;
      const point = new window.BMapGL.Point(lnglat.lng, lnglat.lat);
      this.newMarker = new BMapGL.Marker(point, {
        icon: dpSelectedIcon
      });
      this.map.addOverlay(this.newMarker);
      this.newShop = {
        name: "",
        lng: lnglat.lng,
        lat: lnglat.lat,
        msg: ""
      };
      if (this.clickHandler) {
        this.map.setDefaultCursor(this.defaultCursor);
        this.map.removeEventListener("click", this.mapClick);
        this.clickHandler = null;
      }
    },
    closeNewCacheShop() {
      if (this.newMarker) {
        this.newShop = null;
        this.map.removeOverlay(this.newMarker);
        this.newMarker = null;
      }
    },
    cancelNewShop() {
      this.closeNewCacheShop();
    },
    confirmNewShop() {
      this.closeNewCacheShop();
      this.getShopList();
    },
    lnglatChange(lnglat) {
      if (this.newMarker) {
        let point = new BMapGL.Point(lnglat[0], lnglat[1]);
        this.newMarker.setPosition(point);
      }
    },
    getShopList() {
      this.shopList = [];
      // let params = {
      //   pageNo: 1,
      //   pageSize: 20000000,
      //   param: {
      //     id: "",
      //     keyword: ""
      //   }
      // };
      // this.$axios.post(SERVICE_URL + "shop/pageList", params).then(res => {
      this.$axios.get(SERVICE_URL + "shop/points").then(res => {
        if (res.data.code === 0) {
          let data = res.data.data;
          if (!res.data.data) {
            return;
          }
          data.forEach(item => {
            this.shopList.push({
              shopId: item.id,
              name: item.name,
              discript: item.desc,
              lng: item.lng,
              lat: item.lat,
              evaluates: [],
              score: item.score
            });
          });
          this.shopList.forEach(item => {
            let marker = this.markers.find(_item => {
              return _item.extData.data.shopId === item.shopId;
            });
            if (!marker) {
              let marker = new BMapGL.Marker(
                new BMapGL.Point(item.lng, item.lat)
              );
              marker.setIcon(dpIcon);
              marker.extData = {
                data: item
              };
              marker.on("click", this.markerClick);
              this.map.addOverlay(marker);
              this.markers.push(marker);
            }
          });
        }
      });
    },
    removeShopMarkers() {
      this.markers.forEach(marker => {
        this.map.removeOverlay(marker);
      });
      this.markers = [];
    },
    markerClick(e) {
      if (this.selectedMarker) {
        this.selectedMarker.setIcon(dpIcon);
        this.selectedMarker = null;
      }
      this.selectedMarker = e.target;
      this.selectedMarker.setIcon(dpSelectedIcon);
      this.map.panTo(e.target.getPosition());
      this.shopItem = e.target.extData.data;
    }
  }
};
</script>
<style lang="scss" scoped>
.shop {
  width: 100%;
  height: 100%;
  position: absolute;
  .operates {
    position: absolute;
    top: 10px;
    right: 10px;
  }
  .go-back {
    position: absolute;
    top: 10px;
    left: 10px;
    cursor: pointer;
  }
}
</style>


