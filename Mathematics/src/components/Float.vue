<template>
  <div>
    <p>
      浮点数转二进制(10位: 1 - 4 - 5)<br /><br />
      数值: <input type="text" v-model="original_number" /><br /><br />
      原始码: {{ original_bits }}<br />
      符号位: {{ signal_bit }}<br />
      指数位: {{ exponent_bits }}<br />
      小数位: {{ mantissa_bits }}<br /><br />
    </p>

    <p>
      二进制转浮点数(10位: 1 - 4 - 5)<br /><br />
      符号位: <input type="text" v-model="original_signature" maxlength="1" /><br />
      指数位: <input
        type="text"
        v-model="original_exponent"
        maxlength="4"
      /><br />
      小数位: <input type="text" v-model="original_mantissa" maxlength="5" />
      <br /><br />
      十进制: {{ float_decimal }}<br />
      指数值: {{ float_power }}<br />
      原始码: {{ float_original }}<br />      
    </p>
  </div>
</template>

<script>
export default {
  name: "Float",
  data() {
    return {
      original_number: 0,
      original_bits: "0.0",
      signal_bit: 0,
      exponent_bits: "0111",
      mantissa_bits: "00000",
      width_limit: null,
      original_signature: "0",
      original_exponent: "0000",
      original_mantissa: "00000",
      float_power: 0,
      float_original: "0.0",
      float_decimal: "0.0",
    };
  },
  watch: {
    original_number() {
      this.refresh();
    },
    original_signature() {
      this.refresh_float();
    },
    original_exponent() {
      this.refresh_float();
    },
    original_mantissa() {
      this.refresh_float();
    },
  },
  methods: {
    refresh() {
      if (isNaN(parseInt(this.original_number))) {
        return;
      }

      //此位宽上能表示的最大值
      let max_number = 252;
      let min_number = -0.0009765625;

      let bNegtive = false;
      //判断是否负数
      if (0 > this.original_number) {
        bNegtive = true;
        this.signal_bit = 1;
      } else {
        this.signal_bit = 0;
      }

      let original_abs_value = Math.abs(this.original_number);

      //取整数部分，转换为二进制
      let int_part = Math.floor(original_abs_value);
      let int_bits = this.int_to_binary(int_part);

      //取小数部分，转换为二进制，最大位数为小数点后10位
      let mant_part = original_abs_value - int_part;
      let mant_bit = this.mant_to_binary(mant_part, 10);

      //合并成原始码
      let sign_str = this.signal_bit == 1 ? "-" : "";
      this.original_bits = sign_str + String(int_bits) + "." + mant_bit;

      //判断范围是否超限
      if (min_number > original_abs_value || max_number < original_abs_value) {
        this.signal_bit = this.exponent_bits = this.mantissa_bits = "Overflow";
        return;
      }

      //开始转换为计算机二进制表达
      //把原始码转换成科学计数法
      let abs_original_bits = this.original_bits.replace(/\-/g, "");
      let power_number = this.float_to_science(abs_original_bits, false);
      let science_inotation = this.float_to_science(abs_original_bits, true);

      //如果指数小于-6，则直接把指数归零
      if (power_number < -6) {
        this.exponent_bits = "00000";

        //取出从第八位开始的所有数字
        let mant_right = abs_original_bits.substring(
          8,
          abs_original_bits.length
        );

        //向右侧补齐5个零
        this.mantissa_bits = this.pad(mant_right, 5, false);
      } else {
        //指数加上7，再换为2进制，得到指数的二进制表达
        this.exponent_bits = this.pad(
          this.int_to_binary(power_number + 7),
          4,
          true
        );

        //把科学计数法的表达去掉头两个字符，然后少于指定位数的话，往屁股后填充0
        let mant_right = science_inotation.substr(
          2,
          science_inotation.length - 1
        );

        //长度太长的话，截取其中五位即可
        if (mant_right.length > 5) {
          this.mantissa_bits = mant_right.substr(0, 5);
        } else if (mant_right.length < 5) {
          this.mantissa_bits = this.pad(mant_right, 5, false);
        } else {
          this.mantissa_bits = mant_right;
        }
      }
    },
    int_to_binary(sourceNumber) {
      let quotient = Math.floor(sourceNumber / 2);
      let remainder = Math.floor(sourceNumber % 2);
      if (0 == quotient) {
        return remainder;
      }
      return remainder + 10 * this.int_to_binary(quotient);
    },
    mant_to_binary(sourceMantissa, max_level) {
      let temp_result = sourceMantissa * 2;
      let remainder = temp_result - Math.floor(temp_result); //.toFixed(10); //加tofix解决浮点数精度问题
      let result = "";
      if (1 <= temp_result) {
        result = "1";
      } else {
        result = "0";
      }

      if (0 == remainder || 0 == max_level) {
        return result;
      }
      return result + this.mant_to_binary(remainder, max_level - 1);
    },
    float_to_science(float_number, bReplace) {
      if (float_number == 0) {
        if (bReplace) {
          return "0.0";
        }
        return 0;
      }
      let index_of_point = float_number.indexOf(".");
      if (false == bReplace) {
        //返回要变成2的几次方
        if (float_number <= 1) {
          let index_of_one = float_number.indexOf("1");
          return index_of_point - index_of_one;
        } else {
          return index_of_point - 1;
        }
      }

      if (1 == index_of_point && 1 <= float_number) {
        return float_number;
      }

      // 对于小数点不在索引1上的
      if (1 <= float_number) {
        // 先提取第一位数
        let left = float_number.substr(0, 1);
        let right = float_number.substr(1, float_number.length) + "0";
        //去掉右边的小数点
        right = right.replace(/\./g, "");

        return left + "." + right;
      }

      let index_of_one = float_number.indexOf("1");
      // 先提取第一位数
      let left = float_number.substring(index_of_one, index_of_one + 1);
      let right =
        float_number.substring(index_of_one + 1, float_number.length) + "0";
      return left + "." + right;
    },
    pad(strNumber, width, bLeft) {
      let pad = "";
      strNumber = String(strNumber);
      if (strNumber.length < width) {
        for (let index = 0; index < width - strNumber.length; index++) {
          pad += "0";
        }
        if (bLeft) {
          return pad + strNumber;
        }
        return strNumber + pad;
      } else {
        return strNumber;
      }
    },
    remove_none_binary(binary_number) {
      //去掉非二进制
      let reg = /[0-1]{1}/g;
      let list = binary_number.match(reg);
      if (list == null) {
        return "";
      }
      return binary_number.match(reg).join("");
    },
    binary_to_int(binary_number) {
      let sum = 0;
      for (let n = 0; n < binary_number.length; n++) {
        sum +=
          parseInt(binary_number[n]) *
          Math.pow(2, binary_number.length - n - 1);
      }
      return sum;
    },
    binary_mantissa_to_float(mantissa) {
      let sum = 0.0;
      for (let n = 0; n < mantissa.length; n++) {
        if (mantissa[n] == "1") {
          sum = sum + 1 / Math.pow(2, n + 1);
        }
      }
      return sum;
    },
    refresh_float() {
      this.float_power = "0";
      this.float_original = "0.0";

      //去掉所有二进制值里的非二进制内容
      this.original_signature = this.remove_none_binary(
        this.original_signature
      );
      this.original_exponent = this.remove_none_binary(this.original_exponent);
      this.original_mantissa = this.remove_none_binary(this.original_mantissa);

      //如果三个数字有任何一个长度不符合要求，则直接返回，并把结果设置为Overflow
      if (
        this.original_signature.length != 1 ||
        this.original_exponent.length != 4 ||
        this.original_mantissa.length != 5
      ) {
        this.float_decimal = "Invalid Number";
        return;
      }

      //处理符号位
      this.float_decimal = this.original_signature == 0 ? "+" : "-";

      //先处理特殊情况
      if (this.original_exponent == "1111") {
        if (this.original_mantissa == "00000") {
          this.float_decimal += "INF";
        } else {
          this.float_decimal = "NaN";
        }
        return;
      } else if (
        this.original_exponent == "0000" &&
        this.original_mantissa == "00000"
      ) {
        this.float_decimal += "0";
        return;
      }

      let left_part = "";
      let right_part = "";

      //处理指数位全零的情况
      if (this.original_exponent == "0000") {
        //小数位前面补齐6个0，再加上“0.”
        left_part = 0;
        right_part = "000000" + this.original_mantissa;

        this.float_power = "< -6";
      } else {
        let power = this.binary_to_int(this.original_exponent) - 7;
        this.float_power = power;

        if (power < 0) {
          left_part = 0;
          //小数点左移
          right_part = "1" + this.original_mantissa;
          for (let n = 0; n < Math.abs(power) - 1; n++) {
            right_part = "0" + right_part;
          }
        } else if (power > 0) {
          left_part = 1;
          right_part = this.original_mantissa + "0000000000000000";
          //小数点左移
          for (let n = 0; n < Math.abs(power); n++) {
            left_part = left_part + right_part.charAt(0);
            right_part = right_part.substring(1, right_part.length);
          }
        } else {
          //指数就是0
          left_part = 1;
          right_part = this.original_mantissa;
        }
      }

      this.float_original = left_part + "." + right_part;

      //把二进制小数转换成十进制
      left_part = this.binary_to_int(left_part);
      right_part = this.binary_mantissa_to_float(right_part);
      this.float_decimal =
        this.float_decimal + (left_part + right_part);
    },
  },
};
</script>

<style scoped>
</style>