<template>
  <div>
    <div class="hotel-info">
      <div class="name">
        {{ this.hotel.name }}
      </div>
      <div class="hotel">
        
      </div>
    </div>
    <div class="hotel-reservation-container">
      <div class="reservation-form">
        <form @submit.prevent="">
          <input type="text" />
          <input type="text" />
          <input type="text" />
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import { hotelInfo } from "@/api/hotel";
export default {
  data() {
    return {
      hotelId: "",
      hotel: {},
      reservation: {},
    };
  },
  created() {
    this.loadHotel();
  },
  methods: {
    async loadHotel() {
      const { data } = await hotelInfo(this.hotelId);
      this.hotel = data.hotel;
    },
  },
};
</script>

<style></style>
