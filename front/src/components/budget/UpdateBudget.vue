<template>
  <div class="container">
    <div class="border">
      <div class="menu">
        <div id="deposit" @click="stat_1">입금</div>
        <div id="withdraw" @click="stat_0">지출</div>
      </div>
      <div>
        <input
          v-model="deposit_money"
          type="text"
          dir="rtl"
          @input="(e) => (deposit_money = changeNum(e.target.value))"
        /><span class="one">\</span>
      </div>
      <div class="deposit_container" v-if="stat">ss</div>
    </div>
  </div>
</template>

<script>
export default {
  data: () => ({
    stat: 1,
    deposit_money: "",
  }),
  watch: {
    deposit_money() {},
  },
  methods: {
    changeNum: function (value) {
      return (value = this.comma(this.uncomma(value)));
    },
    comma(str) {
      str = String(str);
      return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, "$1,");
    },
    uncomma(str) {
      str = String(str);
      return str.replace(/[^\d]+/g, "");
    },
    stat_1() {
      this.stat = 1;
    },
    stat_0() {
      this.stat = 0;
    },
  },
  filters: {
    inputNumberFormat(val) {
      return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
};
</script>

<style lang="scss" scoped>
.container {
  height: 500px;
  background: #7dbeff;
  border-radius: 20px;
  padding: 10px;
  border: 4px solid #e6f4f1;
  .border {
    height: 94%;
    margin: auto auto;
    padding: 10px;
    border-radius: 10px;
    border: 4px solid #fff;
    .menu {
      display: flex;
      div {
        display: flex;
        width: 5rem;
        height: 3rem;
        margin-right: 10px;
        background: #3c4856;
        border-radius: 10px;
        align-items: center;
        justify-content: center;
        font-size: 1.1rem;
        font-weight: 700;
        color: #fff;
      }
      #deposit {
        background: #ec8060;
      }
    }
    .deposit_container {
      .one {
        font-size: 1.5rem;
        margin-left: 10px;
      }
    }
  }
}
input {
  background-color: transparent;
  border: 0px solid;
  border-bottom: 2px solid #3c4856;
  height: 50px;
  width: 150px;
  color: #000;
  font-size: 20px;
  &:focus {
    outline: none;
  }
}
</style>
