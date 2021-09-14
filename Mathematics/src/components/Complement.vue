<template>
  <div>
    <p>
      十进制转二进制<br />
      数值: <input type="text" v-model="original_number" /> <br />
      位宽: <input type="number" v-model="binary_wdith" /> <br />
      原码: {{ original_code }}<br />
      取反(u): {{ bitewise_code }}<br />
      取反(i): {{ ubitewise_code }}<br />
      补码: {{ complement_code }}<br />偏移值: 2<sup>{{ binary_wdith - 1 }}</sup
      >-<input type="number" v-model="k" />: {{ offset_binary }}<br /><br />
    </p>
    <p>
      二进制转十进制<br />
      二进制数: <input type="number" v-model="binary_number" /> <br />
      补码原值: {{ complement_decimal }}<br />
      偏移2<sup>{{ binary_to_decimal_width }}</sup
      >-<input type="number" v-model="k_decimal" />的原值: {{ offset_decimal
      }}<br />
    </p>
  </div>
</template>

<script>
export default {
  name: "Complement",
  data() {
    return {
      original_number: 0,
      binary_wdith: 8,
      original_code: "00000000",
      bitewise_code: "11111111",
      ubitewise_code: "_1111111",
      complement_code: "00000000",
      k: 0,
      offset_binary: "10000000",
      binary_number: "00",
      complement_decimal: 0,
      binary_to_decimal_width: 1,
      k_decimal: 0,
      offset_decimal: -2,
    };
  },
  watch: {
    original_number() {
      if (this.original_number === "" || this.original_number === "-") {
        return;
      }
      this.original_number = parseInt(this.original_number);
      if (isNaN(this.original_number)) {
        this.original_number = 0;
      }
      this.refresh();
    },
    binary_wdith() {
      this.refresh();
    },
    k() {
      if (isNaN(parseInt(this.k))) {
        return;
      }
      this.refresh();
    },
    binary_number() {
      this.refresh();
    },
    k_decimal() {
      this.refresh();
    },
  },
  methods: {
    refresh() {
      if (isNaN(parseInt(this.original_number))) {
        return;
      }
      let bits = this.binary_wdith; //例子：8位最大表达: 2的7次方
      //let umax = (max = Math.pow(2, bits));
      //let max = Math.pow(2, bits - 1) - 1; //例子：8位最大表达: 2的7次方减去1
      //let min = 0 - Math.pow(2, bits); //例子：8位最小表达: 负(2的7次方)

      if (0 <= this.original_number) {
        // 大于0直接转换成二进制
        var result = "" + this.convertToBinary(this.original_number);
        let pad = "";
        if (result.length <= this.binary_wdith) {
          this.complement_code = this.original_code = this.pad(
            result,
            this.binary_wdith
          );

          //取反
          this.bitewise_code = this.convertToBitWise(this.original_code);
          this.ubitewise_code = "_" + this.bitewise_code.substring(1);

          //去掉原码和补码已经达到指定位数的
          if (result.length >= this.binary_wdith) {
            this.original_code = this.complement_code = "Overflow";
          }
        } else {
          this.ubitewise_code = this.bitewise_code = this.complement_code = this.original_code =
            "Overflow";
        }
      } else {
        //如果是负数，拿到绝对值，最高位置1，得到原码
        let absNumber = Math.abs(this.original_number);
        var result = "" + this.convertToBinary(absNumber);
        if (result.length <= this.binary_wdith) {
          //去掉原码和补码已经达到指定位数的
          if (result.length == this.binary_wdith) {
            this.original_code = "Overflow";
          } else {
            result = this.pad(result, this.binary_wdith);
            this.original_code = "1" + result.substring(1);
          }          

          //取反
          this.bitewise_code = this.convertToBitWise(result);
          this.ubitewise_code = "_" + this.bitewise_code.substring(1);

          //无符号的取反加个1
          //this.binaryAdd(ubitewise_code, "1");
          this.complement_code =
            "1" + this.binaryAdd(this.bitewise_code.substring(1), "1");
        } else {
          this.ubitewise_code = this.bitewise_code = this.complement_code = this.original_code =
            "Overflow";
        }
      }

      //计算偏移值
      //console.log(parseInt(this.original_number) + parseInt(this.k) + Math.pow(2, bits - 1))
      if (isNaN(parseInt(this.k))) {
        return;
      }
      let temp =
        parseInt(this.original_number) +
        (Math.pow(2, bits - 1) - parseInt(this.k));
      if (temp < 0) {
        this.offset_binary = "Overflow";
        return;
      }
      temp = this.convertToBinary(
        parseInt(this.original_number) +
          (Math.pow(2, bits - 1) - parseInt(this.k))
      );
      this.offset_binary = "" + temp;
      this.offset_binary = this.pad(this.offset_binary, this.binary_wdith);
      if (this.offset_binary.length > this.binary_wdith) {
        this.offset_binary = "Overflow";
      }

      //二进制转10进制
      if (this.binary_number.length < 2) {
        this.offset_decimal = this.complement_decimal = "Overflow";
        return;
      }

      //去掉无关的字符
      let reg = /[0-1]{1}/g;
      this.binary_number = this.binary_number.match(reg).join("");

      //二进制转换成对应的十进制整数
      if (this.binary_number.substring(0, 1) === "0") {
        //原来的数是正数，直接进行转换
        this.complement_decimal = this.convertToDecimal(this.binary_number);
      } else {
        //原来的数字是负数，先减一，再去符号，再取反，再加上负号
        let number = this.binary_number.substring(1);
        let difference = this.binarySub(number, this.pad("1", number.length));
        let binary_abs = this.convertToBitWise(difference);
        let abs = this.convertToDecimal(binary_abs);
        this.complement_decimal = "-" + abs;
      }

      //偏移二进制转换为整数，先减掉2的n次方，再加上k
      this.binary_to_decimal_width = this.binary_number.length - 1;
      let org_uint = this.convertToDecimal(this.binary_number);
      org_uint -= Math.pow(2, this.binary_to_decimal_width);
      this.offset_decimal = org_uint + parseInt(this.k_decimal);
    },
    convertToBinary(sourceNumber) {
      let quotient = Math.floor(sourceNumber / 2);
      let remainder = Math.floor(sourceNumber % 2);
      if (0 == quotient) {
        return remainder;
      }
      return remainder + 10 * this.convertToBinary(quotient);
    },
    convertToBitWise(sourceNumber) {
      sourceNumber = sourceNumber.replace(/0/g, "2");
      sourceNumber = sourceNumber.replace(/1/g, "0");
      sourceNumber = sourceNumber.replace(/2/g, "1");
      return sourceNumber;
    },
    pad(strNumber, width) {
      let pad = "";
      if (strNumber.length < width) {
        for (let index = 0; index < width - strNumber.length; index++) {
          pad += "0";
        }
        return pad + strNumber;
      } else {
        return strNumber;
      }
    },
    binaryAdd(num1, num2) {
      let nLenNum1 = num1.length;
      let nLenNum2 = num2.length;

      var nLenLonger = 0;
      if (nLenNum1 > nLenNum2) {
        nLenLonger = nLenNum1;
        num2 = this.pad(num2, nLenNum1);
      } else {
        nLenLonger = nLenNum2;
        num1 = this.pad(num1, nLenNum2);
      }

      //进位标志
      var bAdd = false;

      //申请一段内存用于存放结果，内存长度比大的那个数还大一位
      var list = [];

      //已经加的位数
      var nTotalAdded = 0;

      //从个位开始循环加
      var nIndex = 1;
      while (nTotalAdded < nLenLonger) {
        let sum = bAdd ? 1 : 0;
        bAdd = false; //清空进位标志
        let a = num1.substring(nLenLonger - nIndex, nLenLonger - nIndex + 1);
        let b = num2.substring(nLenLonger - nIndex, nLenLonger - nIndex + 1);
        sum = sum + parseInt(a) + parseInt(b);
        if (sum >= 2) {
          sum -= 2;
          bAdd = true;
        }

        list.push(sum);
        nTotalAdded++;
        ++nIndex;
      }
      return list.reverse().join("");
    },
    binarySub(num1, num2) {
      let bFlag = false;
      let list = [];
      let length = num1.length;
      for (let n = 1; n <= num1.length; n++) {
        let current_num1 = num1[length - n];
        if (bFlag) {
          bFlag = false;
          if (1 == current_num1) {
            current_num1 = 0;
          } else {
            current_num1 = 2;
            bFlag = true;
          }
        }
        let diffence = parseInt(current_num1) - parseInt(num2[length - n]);
        if (diffence == -1) {
          diffence = 1;
          bFlag = true;
        }
        list.push(diffence);
      }
      return list.reverse().join("");
    },
    convertToDecimal(binary_number) {
      let sum = 0;
      for (let n = 0; n < binary_number.length; n++) {
        sum +=
          parseInt(binary_number[n]) *
          Math.pow(2, binary_number.length - n - 1);
      }
      return sum;
    },
  },
};
</script>

<style scoped>
</style>
