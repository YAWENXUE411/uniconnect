<template>
    <div class="header">
        <div class="logo">
            <div class="logo-image"></div>
        </div>
        <div class="login-operate">
            <span @click="operate('login')" class="login" v-if="!user">login</span>
            <span style="margin-left: 50px" @click="operate('Sign Up')" class="register" v-if="!user">Sign Up</span>
            <span v-if="user">{{ user.email }}</span>
        </div>
    </div>
</template>
<script>
export default {
  name: "Header",
  data() {
    return {
      operateType: ""
    };
  },
  computed: {
    user() {
      let user = this.$store.getters.getUser;
      console.log(user)
      return user;
    }
  },
  mounted() {},
  methods: {
    operate(name) {
      this.$emit("click", name);
    }
  }
};
</script>
<style lang="scss" scoped>
.header {
  position: absolute;
  right: 10px;
  top: 10px;
  left: 10px;
  height: 200px;
  display: flex;
  justify-content: space-between;
  .logo {
    height: 100%;
    .logo-image {
      background: url("~@/assets/images/logo.png");
      background-size: 100% 100%;
      background-repeat: no-repeat;
      height: 100%;
      width: 600px;
    }
  }
  .login-operate {
    color: black;
    font-size: 32px;
    line-height: 200px;
    position: absolute;
    right: 100px;

    .login {
      cursor: pointer;
    }
    .register {
      cursor: pointer;
    }
  }
}
</style>


