<template>
  <div class="container">
    <div class="createBtn" @click="pageLink">
      <div class="border">예산 설정</div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style lang="scss" scoped>
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
