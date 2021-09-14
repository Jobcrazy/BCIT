<template>
  <div class="hello">
    <!--<router-link to="/convert">进制转换</router-link>-->
    <router-link to="/complement">原码补码</router-link>
    <router-link to="/float">浮点数</router-link>
    <router-link to="/division">除法</router-link>
    <router-view />
  </div>
</template>

<script>
export default {
  name: "Home",
  data() {
    return {};
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
a {
  color: #42b983;
}
</style>
