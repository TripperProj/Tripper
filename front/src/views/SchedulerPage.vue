<template>
  <div class="container">
    <h2 id="title">내 여행</h2>
    <div class="createBtn">
      <div class="border">여행 생성</div>
    </div>
    <TripList />
    <CreateTrip class="createTrip" />
  </div>
</template>

<script>
import TripList from "@/components/scheduler/TripList.vue";
import CreateTrip from "@/components/scheduler/CreateTrip.vue";
export default {
  components: {
    TripList,
    CreateTrip,
  },
};
</script>

<style lang="scss" scoped>
.container {
  width: 100%;
  overflow: hidden;
  #title {
    text-align: center;
  }
  .createBtn {
    width: 120px;
    height: 60px;
    display: flex;
    position: relative;
    right: 20px;
    bottom: 10px;
    justify-content: center;
    align-items: center;
    background: #7dbeff;
    border-radius: 10px;
    color: #fff;
    float: right;
    z-index: 1;
    &:hover {
      cursor: pointer;
      box-shadow: 1px 1px 0 rgb(0, 0, 0, 0.3);
      position: relative;
      bottom: 9px;
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
}
.createTrip {
  // position: absolute;
  left: 0;
  right: 0;
  top: 100px;
  margin: 0 auto;
}
</style>
