<template>
  <div>
    <p>
      被除数: <input type="number" v-model="first_number" /> <br />
      除数: <input type="number" v-model="second_number" /><br />
      商: {{ quotient }} -- 余: {{ remainder }} <br />
    </p>
  </div>
</template>

<script>
export default {
  name: "Division",
  data() {
    return {
      first_number: 1,
      second_number: 2,
      quotient: 0,
      remainder: 1,
    };
  },
  watch: {
    first_number() {
      this.refresh();
    },
    second_number() {
      this.refresh();
    },
  },
  methods: {
    refresh() {
      if (
        isNaN(this.first_number) ||
        isNaN(this.second_number) ||
        this.second_number == 0
      ) {
        this.quotient = this.remainder = NaN;
        return;
      }

      this.first_number = parseInt(this.first_number);
      this.second_number = parseInt(this.second_number);

      this.quotient = parseInt(
        parseInt(this.first_number) / parseInt(this.second_number)
      );
      this.remainder =
        parseInt(this.first_number) % parseInt(this.second_number);
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
