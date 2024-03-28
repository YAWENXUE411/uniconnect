<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App',
  mounted() {
    let user = JSON.parse(sessionStorage.getItem("user"));
    if (user) {
      this.$store.commit("setUser", user);
    }
  }
}
</script>

<style>
/* @import "./styles/iconfont.css"; */
html, body, #app{ height: 100%; margin: 0; padding: 0;}
nav {
  padding: 0px;
}
body {
  /* background: rgb(141 215 164 / 0.5); */
  font-size: 14px;
}
</style>
