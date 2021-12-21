<template>
  <div class="hotel-manage">
    <div class="hotel-info">
      <h2>{{ hotel.title }}}</h2>
    </div>
    <div class="room-list">
      <div class="room" v-for="(room, idx) in rooms" v-bind:key="idx">
        <div></div>
      </div>
    </div>
    <button>객실 추가</button>
  </div>
</template>

<script>
import {} from "@/api/hotelManage";
import { hotelInfo } from "@/api/hotel";

export default {
  data() {
    return {
      hotel: null,
    };
  },
  props: {
    hotelId: {
      type: String,
      require: true,
    },
  },
  methods: {
    hotelInfo() {
      const { data } = hotelInfo(this.hotelID);
      this.hotel = data;
    },
    created() {
      this.hotelInfo();
    },
  },
};
</script>

<style></style>
