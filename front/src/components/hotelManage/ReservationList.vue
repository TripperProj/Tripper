<template>
  <div class="reservation-list-wrapper">
    <div class="info">
      <h2></h2>
    </div>
    <table class="reservation-list">
      <thead class="table-header">
        <tr>
          <th>순번</th>
          <th>예약자 아이디</th>
        </tr>
      </thead>
      <tbody class="table-body">
        <tr>
          <th></th>
          <td></td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import { roomReservation, hotelReservation } from "@/api/hotelManage";
export default {
  data() {
    return {
      reservations: [],
    };
  },
  props: {
    typ: {},
    id: {},
  },
  created() {
    this.checkReservation();
  },
  methods: {
    async checkReservation() {
      const { data } =
        this.typ === "hotel"
          ? await hotelReservation(this.props.id)
          : await roomReservation(this.props.id);
      for (let i = 0; i < data.reservationTotal; i++) {
        const reservation = {
          adultNum: data.reservationDtos[i].adultNum,
          childNum: data.reservationDtos[i].childNum,
          checkin: data.reservationDtos[i].checkin,
          checkout: data.reservationDtos[i].checkout,
          memid: data.reservationDtos[i].memid,
          status: data.reservationDtos[i].status,
        };
        this.reservations.push(reservation);
      }
    },
  },
};
</script>

<style></style>
