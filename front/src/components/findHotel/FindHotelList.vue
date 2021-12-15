<template>
  <div class="list-contents">
    <div class="list-wrapper">
      <div class="list">
        <div class="list-item">
          <img v-bind:src="hotel.photo" alt="" />
          <div class="">
            <span> {{ hotel.Name }}</span> <br />
            <span>{{ hotel.minPrice }}</span>
            <button>호텔 찜하기</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { dibsHotel } from "@/api/hotel.js";

export default {
  data() {
    return {
      hotel: {
        photo:
          "https://search.pstatic.net/common/?src=https://media.datahc.com/HP242234631.jpg&type=f134_134",
        Name: "뭐시기 호텔",
        minPrice: 210000,
      },
      user: {
        userId: "1111",
      },
    };
  },
  methods: {
    async hotelDibs() {
      const hotelUserData = {
        hotelName: this.hotel.Name,
        userId: this.user.userId,
      };
      try {
        const { data } = await dibsHotel(hotelUserData);
        console.log(data);
      } catch (error) {
        console.log(error);
      }
    },
  },
};
</script>

<style></style>
