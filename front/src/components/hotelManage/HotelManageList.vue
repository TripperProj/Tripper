<template>
  <div class="hotel-manage">
    <div class="hotel-manage-header">
      <div>
        <h2>{{ this.managerID }} 님 환영합니다.</h2>
        <span>총 예약건수 : {{ this.totalReservation }}</span>
        <div><button @click="hotelCreate">호텔 추가</button> |</div>
      </div>
    </div>
    <div class="hotel-manage-body">
      <ul class="hotel-manage-list">
        <div class="hotel-manage-list-head">
          <div class="index">순번</div>
          <div class="hotel-name">호텔이름</div>
          <div class="reservation">예약건수</div>
          <div class="vacant-room">잔역객실</div>
        </div>
        <li
          class="hotel-manage-item"
          v-bind:key="idx"
          v-for="(hotel, idx) in hotelList"
          :hotelId="hotel.id"
        >
          <div class="index">
            {{ idx + 1 }}
          </div>
          <div class="hotel-name">{{ hotel.name }}</div>
          <div class="reservation">{{ hotel.reservation }}</div>
          <div class="vacant-room">{{ hotel.vacantRoom }}</div>
          <button class="room-manage" @click="this.hotelManagement">
            호텔 관리
          </button>
          <button
            class="reservation-manage"
            @click="this.reservationManagement"
          >
            예약 관리
          </button>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import {} from "@/api/hotelManage";

export default {
  data() {
    return {
      managerID: this.$store.getters.getMemId,
      hotelList: [
        { name: "호텔1", reservation: "5", vacantRoom: 5 },
        { name: "호텔2", reservation: "14", vacantRoom: 5 },
        { name: "호텔3", reservation: "12", vacantRoom: 5 },
      ],
    };
  },
  methods: {
    loadHotelList() {},
    adminCheck() {
      if (!this.isAdmin) {
        alert("관리자가 아닙니다. 메인페이지로 이동합니다");
        this.$router.push("/main");
      }
    },
    hotelCreate() {
      this.$router.push("/hotel/manage/create");
    },
    hotelManagement() {
      this.$router.push(`/hotel/manage/${id}`);
    },
    reservationManagement() {
      this.$router.push(`/hotel/reservation/${id}`);
    },
  },
  computed: {
    isAdmin: function () {
      return this.$store.getters.getRole === "ROLE_MANAGER";
    },
    totalReservation: function () {
      let sum = 0;
      this.hotelList.forEach((item) => {
        sum += parseFloat(item.reservation);
      });
      return sum;
    },
  },
  created() {
    this.adminCheck();
    this.loadHotelList();
  },
};
</script>

<style scoped></style>
