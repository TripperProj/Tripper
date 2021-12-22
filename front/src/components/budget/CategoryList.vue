<template>
  <div class="container">
    <div class="berth category">
      <div>
        <i class="icon fas fa-bed"></i>
        숙소
      </div>
      <i class="down fas fa-angle-down"></i>
    </div>
    <div class="meal category">
      <div>
        <i class="icon fas fa-utensils"></i>
        식비
      </div>
      <i class="down fas fa-angle-down"></i>
    </div>
    <div class="traffic category">
      <div>
        <i class="icon fas fa-bus"></i>
        교통
      </div>
      <i class="down fas fa-angle-down"></i>
    </div>
    <div class="experience category">
      <div>
        <i class="icon fab fa-gratipay"></i>
        체험
      </div>
      <i class="down fas fa-angle-down"></i>
    </div>
    <div class="etc category">
      <div>
        <i class="icon fas fa-plus"></i>
        기타
      </div>
      <i class="down fas fa-angle-down"></i>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style lang="scss" scoped>
.container {
  width: 97%;
  .category {
    i {
      margin-right: 20px;
    }
    display: flex;
    align-items: center;
    justify-content: space-between;
    margin: 0 auto 20px auto;
    font-size: 1.5rem;
    color: #3c4856;
    width: 97%;
    background: #eee;
    min-height: 70px;
    border-radius: 20px;
    padding: 0 20px;
    z-index: 2;
    .down {
      text-align: right;
      width: 20px;
    }
    &:hover {
      cursor: pointer;
    }
  }
}
</style>
