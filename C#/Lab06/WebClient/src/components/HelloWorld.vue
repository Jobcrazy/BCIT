<template>
  <el-form
    ref="form"
    :model="form"
    label-width="80px"
    v-loading="loading"
    :rules="rules"
    :hide-required-asterisk="true"
  >
    <el-row>
      <el-col :span="6">&nbsp;</el-col>
      <el-col :span="12">
        <el-form-item prop="src">
          <el-input
            placeholder="Input the Temperature Here"
            v-model.number="form.src"
            class="input-with-select"
          >
            <el-select
              v-model="form.mode"
              slot="prepend"
              placeholder="Please choose"
            >
              <el-option label="Celsius to Fahrenheit" value="1"></el-option>
              <el-option label="Kelvin to Fahrenheit" value="2"></el-option>
              <el-option label="Fahrenheit to Celsius" value="3"></el-option>
              <el-option label="Celsius to Kelvin" value="4"></el-option>
              <el-option label="Kelvin to Celsius" value="5"></el-option>
              <el-option label="Fahrenheit to Kelvin" value="6"></el-option>
            </el-select>
            <el-button slot="append" @click="submitForm('form')"
              >Convert</el-button
            >
          </el-input>
        </el-form-item>
        <el-alert :title="result" type="success" :closable="false" v-if="result != null" />
      </el-col>
      <el-col :span="6"> </el-col>
    </el-row>
  </el-form>
</template>

<script>
export default {
  name: "HelloWorld",
  data() {
    return {
      loading: false,
      result: null,
      form: {
        src: "",
        mode: "1",
      },
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.convert();
          } else {
            return false;
          }
        });
      },
      rules: {
        src: [{ required: true, message: "Please fill out the form", trigger: "blur" }],
      },
    };
  },
  methods: {
    async convert() {
      this.loading = true;

      try {
        let result = await this.$axios({
          method: "GET",
          headers: { "content-type": "application/json" },
          url: "/api/convert",
          params: {
            mode: this.form.mode,
            src: this.form.src,
          },
        });

        this.loading = false;
        console.log(result);
        this.result = "Convertion Result: " + result.data.data;
      } catch (error) {
        this.loading = false;
      }
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
.el-select .el-input {
  width: 200px;
}
.input-with-select .el-input-group__prepend {
  background-color: #fff;
}

.el-form-item__content {
  margin-left: 0 !important;
}
</style>
