<template>
  <div class="list-contents">
    <div class="list-wrapper">
      <div class="list">
        <div class="list-item">
          <img v-bind:src="hotel.photo" alt="" />
          <div class="">
            <span> {{ hotel.Name }}</span> <br />
            <span>{{ hotel.minPrice }}</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      hotel: {
        photo:
          "https://search.pstatic.net/common/?src=https://media.datahc.com/HP242234631.jpg&type=f134_134",
        Name: "뭐시기 호텔",
        minPrice: 210000,
      },
    };
  },
};
</script>

<style></style>
