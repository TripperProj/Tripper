<template>
  <div class="container">
    <div class="border">
      <div id="cancel"><i class="fas fa-times"></i></div>
      <div class="menu">
        <div id="deposit" @click="stat_1">입금</div>
        <div id="withdraw" @click="stat_0">지출</div>
      </div>
      <div>
        <input id="title" placeholder="제목을 입력하세요" type="text" />
        <input
          v-model="deposit_money"
          type="text"
          placeholder="금액을 입력하세요"
          @input="(e) => (deposit_money = changeNum(e.target.value))"
        /><span class="one">\</span>
      </div>
      <label>카테고리</label>
      <div class="deposit_container">
        <div class="category bed">
          <div class="icon fas fa-bed"></div>
          <div class="label">숙소</div>
        </div>
        <div class="category meal">
          <div class="icon fas fa-utensils"></div>
          <div class="label">식사</div>
        </div>
        <div class="category traffic">
          <div class="icon fas fa-bus"></div>
          <div class="label">교통</div>
        </div>
        <div class="category play">
          <div class="icon fab fa-gratipay"></div>
          <div class="label">체험</div>
        </div>
        <div class="category etc">
          <div class="icon fas fa-plus"></div>
          <div class="label">기타</div>
        </div>
      </div>
      <div id="okay">확인</div>
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
  height: 450px;
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

    #cancel {
      position: inherit;
      float: right;
      &:hover {
        cursor: pointer;
      }
    }
    .menu {
      display: flex;
      width: 88%;
      margin: 5px auto;
      div {
        display: flex;
        width: 6rem;
        height: 3rem;
        margin-right: 10px;
        background: #ec8060;
        border-radius: 20px;
        align-items: center;
        justify-content: center;
        font-size: 1.1rem;
        font-weight: 700;
        color: #fff;
        &:hover {
          cursor: pointer;
          position: relative;
          bottom: 1px;
        }
      }
      #deposit {
        background: #00b49d;
      }
    }
    .one {
      font-size: 1.5rem;
      margin-left: 10px;
      position: relative;
      bottom: 58px;
      left: 410px;
      opacity: 0.7;
    }
    .deposit_container {
      display: flex;
      justify-content: space-around;
      align-items: center;
      width: 84%;
      padding: 0 20px;
      height: 100px;
      bottom: 15px;
      border-radius: 25px;
      position: relative;
      margin: 0 auto;
      background-color: #eee;
      opacity: 0.5;
      .category:hover {
        cursor: pointer;
        position: relative;
        bottom: 1px;
      }
      .icon {
        opacity: 1;
        font-size: 25px;
        text-align: center;
        border: 1x solid grey;
        padding: 10px;
      }
      .label {
        text-align: center;
        font-size: 14px;
      }
    }
    #okay {
      display: flex;
      width: 80%;
      height: 50px;
      border-radius: 30px;
      padding: 0 30px;
      font-size: 20px;
      font-weight: 700;
      align-items: center;
      justify-content: center;
      background: #eee;
      color: #7dbeff;
      margin: 0 auto;
      &:hover {
        cursor: pointer;
      }
    }
  }
}
input {
  display: block;
  width: 80%;
  margin: 20px auto;
  background-color: #eee;
  opacity: 0.5;
  color: #3c4856;
  border: 1px solid #fff;
  border-radius: 30px;
  padding: 0px 30px;
  border-bottom: 2px solid #eee;
  height: 40px;
  color: #000;
  font-size: 20px;
  &:focus {
    outline: none;
  }
}
label {
  position: relative;
  bottom: 25px;
  left: 30px;
  color: #fff;
  font-size: 1.2rem;
}
i {
  background-color: white;
  border-radius: 50%;
  border: 1x solid grey;
  padding: 10px;
  position: relative;
  bottom: 1px;
}
</style>
