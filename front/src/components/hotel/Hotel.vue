<template>
  <li>
    <div class="hotel-item" v-bind:key="idx" v-for="(hotel, idx) in hotels">
      <div class="hotel-img-slot">
        <img class="hotel-img" v-bind:src="hotel.url" />
      </div>
      <div class="hotel-item-header">
        <h4 class="hotel-title">{{ hotel.title }}</h4>
      </div>
      <div class="hotel-item-body">
        <span class="hotel-content">{{ hotel.content }}</span>
      </div>
      <div class="hotel-item-footer">
        <span class="hote-location">위치</span>
      </div>
    </div>
  </li>
</template>

<script>
export default {
  data() {
    return {
      hotels: [
        {
          title: "호텔1",
          content: "호텔임",
          url: require("@/assets/hotel1.jpg"),
        },
        {
          title: "호텔2",
          content: "아무튼 호텔임",
          url: require("@/assets/hotel2.jpg"),
        },
        {
          title: "호텔3",
          content: "어쩃든 호텔임",
          url: require("@/assets/hotel3.jpg"),
        },
        {
          title: "호텔4",
          content: "무조건 호텔임",
          url: require("@/assets/hotel4.jpg"),
        },
        {
          title: "호텔5",
          content: "호텔 일수도?",
          url: require("@/assets/jeju.jpg"),
        },
      ],
    };
  },
};
</script>

<style scoped>
li {
  list-style: none;
}
</style>
