<template>
  <div class="home">
    <Header @click="showDialog" />
    <div class="home-content">
      <Transition name="slide-fade-left">
        <div class="map-part" v-if="show">
          <div class="title">
            <div>Map navigation</div>
          </div>
          <div class="map-btn" @click="goto('shop')" style="cursor:pointer"></div>
        </div>
      </Transition>
      <Transition name="slide-fade-right">
        <div class="study-part" v-if="show">
          <div class="title">
            <div>Life and learning</div>
          </div>
          <div class="study-btn" @click="goto('table')" style="cursor:pointer"></div>
        </div>  
      </Transition>
    </div>
    <Login v-if="dialogTitle === 'login'" :title="dialogTitle" :visible="dialogVisible" @close="handleClose" />
    <Register v-if="dialogTitle === 'Sign Up'" :title="dialogTitle" :visible="dialogVisible" @close="handleClose" />
  </div>
</template>

<script>
import Header from "@/components/Header";
import Login from "@/components/Login";
import Register from "@/components/Register";
export default {
  name: "Home",
  components: {
    Header,
    Login,
    Register
  },
  data() {
    return {
      dialogTitle: "",
      dialogVisible: false,
      user: null,
      errorMsg: null,
      show: false
    };
  },
  mounted() {
    setTimeout(() => {
      this.show = true;
    }, 100);
  },
  methods: {
    handleClose() {
      this.dialogVisible = false;
    },
    showDialog(title) {
      this.dialogTitle = title;
      this.dialogVisible = true;
    },
    goto(name) {
      this.$router.push({
        name: name
      });
    }
  }
};
</script>

<style lang="scss" scoped>
.slide-fade-left-enter-active {
  transition: all 0.8s ease;
}

.slide-fade-left-enter {
  transform: translateX(-100px);
  opacity: 0;
}
.slide-fade-right-enter-active {
  transition: all 0.8s ease;
}

.slide-fade-right-enter {
  transform: translateX(100px);
  opacity: 0;
}
.home {
  width: 100%;
  height: 100%;
  background-size: 100% 100%;
  background-repeat: no-repeat;
  .home-content {
    position: absolute;
    top: 200px;
    left: 10px;
    right: 10px;
    bottom: 10px;
    width: calc(100% - 20px);
    height: calc(100% - 210px);
    display: flex;
    justify-content: center;
    .map-part {
      width: 500px;
      height: calc(100% - 160px);
      margin: 80px 100px;
      .title {
        font-size: 24px;
        color: #000;
        text-align: center;
        height: 44px;
        border: 1px solid lightgray;
        padding: 2px;
        div {
          height: 40px;
          line-height: 40px;
          border: 1px solid lightgray;
        }
      }
      .map-btn {
        margin-top: 10px;
        background: url("~@/assets/images/map-bg.png");
        background-size: 100% 100%;
        background-repeat: no-repeat;
        height: calc(100% - 40px);
      }
    }
    .study-part {
      width: 500px;
      margin: 80px 100px;
      height: calc(100% - 160px);
      .title {
        font-size: 24px;
        color: #000;
        text-align: center;
        height: 44px;
        border: 1px solid lightgray;
        padding: 2px;
        div {
          height: 40px;
          line-height: 40px;
          border: 1px solid lightgray;
        }
      }
      .study-btn {
        margin-top: 10px;
        background: url("~@/assets/images/study-bg.png");
        background-size: 100% 100%;
        background-repeat: no-repeat;
        height: calc(100% - 40px);
      }
    }
  }
}
</style>
