<template>
  <div>
    <p>
      输入数值: <input type="text" v-model="original_number" /> <br />
      原始进制: <input type="number" v-model="original_base" /><br />
      目标进制: <input type="number" v-model="target_base" /><br />
    </p>
    <p>结果: {{ result_number }}</p>
  </div>
</template>

<script>
export default {
  name: "Convert",
  data() {
    return {
      original_base: 10,
      target_base: 2,
      original_number: 0,
      result_number: 0,
    };
  },
  watch: {
    original_base() {
      this.refresh();
    },
    target_base() {
      this.refresh();
    },
    original_number() {
      this.refresh();
    },
    result_number() {
      this.refresh();
    },
  },
  methods: {
    refresh() {
      if (10 === this.original_base) {
        this.decToOther();
      }
    },
    decToOther() {
      // 取得整数部分
      number = this.original_number.split(".");      
      let intPart = number[0];
      let floatPart = number[1];
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
