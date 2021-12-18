<template>
  <div class="container">
    <div class="preview">
      <h3>총 예산 {{ budget }} 원에서</h3>
      <h2>{{ total }} 원 남았습니다!</h2>
      <span id="today">오늘의 지출 금액 {{ today }} 원</span>
      <div class="createBtn" @click="pageLink">
        <div class="border">예산 설정</div>
      </div>
    </div>
    <UpdateBudget id="updateBudget" />
  </div>
</template>

<script>
import UpdateBudget from "./UpdateBudget.vue";
export default {
  components: {
    UpdateBudget,
  },
  data: () => ({
    budget: 1000000,
    total: 443530,
    today: 243300,
  }),
  created() {
    this.total = this.comma(this.total);
    this.budget = this.comma(this.budget);
    this.today = this.comma(this.today);
  },
  methods: {
    //화폐 콤마 찍기
    comma(num) {
      var len, point, str;

      num = num + "";
      point = num.length % 3;
      len = num.length;

      str = num.substring(0, point);
      while (point < len) {
        if (str != "") str += ",";
        str += num.substring(point, point + 3);
        point += 3;
      }
      return str;
    },
  },
};
</script>

<style lang="scss" scoped>
.container {
  width: 80%;
  margin: 50px auto;
  #today {
    color: #a0acbd;
    font-size: 16px;
  }
}
.createBtn {
  width: 120px;
  height: 60px;
  display: flex;
  position: absolute;
  right: 60px;
  top: 130px;
  justify-content: center;
  align-items: center;
  background: #7dbeff;
  border-radius: 10px;
  color: #fff;
  float: right;
  z-index: 1;
  &:hover {
    cursor: pointer;
    background: #f99ec0;
    box-shadow: 1px 1px 0 rgb(0, 0, 0, 0.3);
    position: absolute;
    bottom: 1px;
  }
  .border {
    border-radius: 7px;
    width: 80px;
    border: 3px solid #fff;
    padding: 10px 10px;
    font-weight: 700;
    text-align: center;
  }
}
</style>
