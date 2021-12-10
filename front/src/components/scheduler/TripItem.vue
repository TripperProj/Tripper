<template>
  <div class="tripItem">
    <div class="border">
      <div class="context">
        <h3>{{ title }}</h3>
        <div class="memo">{{ memo }}</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data: () => ({
    title: "우정여행 제주도",
    memo: "존나 두륵두근 ",
  }),
};
</script>

<style lang="scss" scoped>
.tripItem {
  border-radius: 25px;
  width: 300px;
  height: 200px;
  background: #7dbeff;
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1;
  .border {
    width: 260px;
    height: 160px;
    border-radius: 20px;
    border: 4px solid #fff;
    background: #dae8e6;
    padding: 10px 10px;
    text-align: center;
    background-image: url("C:/Tripper/front/src/assets/jeju.jpg");
    background-size: cover;
  }
  &:hover {
    cursor: pointer;
    box-shadow: 1px 1px 0 rgb(0, 0, 0, 0.3);
    position: relative;
    top: 1px;
  }
}
</style>
