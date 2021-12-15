<template>
  <div class="container">
    <div class="tripList" v-for="(trip, index) in trips" :key="index">
      <div class="border">
        <div class="context">
          <h3>{{ trip.title }}</h3>
          <div class="memo">{{ trip.memo }}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data: () => ({
    trips: [
      { title: "우정여행 제주도", memo: "3년만에 떠나는 우정여행" },
      { title: "부산", memo: "한번도 못가본거 실화냐?" },
      { title: "울산", memo: "회 파나,,?" },
    ],
  }),
};
</script>

<style lang="scss" scoped>
.container {
  width: 100%;
  height: fit-content;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
  overflow-x: none;
}
.tripList {
  margin: 20px 0px 20px 30px;
  border-radius: 25px;
  width: 340px;
  height: 250px;
  background: #7dbeff;
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1;
  .border {
    width: 300px;
    height: 210px;
    border-radius: 20px;
    border: 4px solid #fff;
    background: #dae8e6;
    padding: 10px 10px;
    text-align: center;
    //background-image: url("");
    background-size: cover;
  }
  &:hover {
    cursor: pointer;
    box-shadow: 1px 1px 0 rgb(0, 0, 0, 0.3);
    position: relative;
    top: 1px;
    background: #f99ec0;
  }
}
</style>
