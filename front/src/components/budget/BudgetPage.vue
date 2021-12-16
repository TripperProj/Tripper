<template>
  <div class="container">
    <h3>총 예산</h3>
    <h2>{{ total }} 원 남았습니다!</h2>
    <div class="createBtn" @click="pageLink">
      <div class="border">예산 설정</div>
    </div>
  </div>
</template>

<script>
export default {
  data: () => ({
    total: 2443530,
  }),
  created() {
    this.comma(this.total);
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
      this.total = str;
    },
  },
};
</script>

<style lang="scss" scoped>
.container {
  width: 80%;
  margin: 50px auto;
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
