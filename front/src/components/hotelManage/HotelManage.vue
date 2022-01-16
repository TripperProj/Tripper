<template>
  <div class="hotel-manage-wrapper">
    <div class="hotel-manage">
      <div class="hotel-info">
        <h2>{{ hotel.title }}</h2>
        <h4>주소 : {{ hotel.address }}</h4>
        <h4>총 방수 : {{ hotel.rooms.totalRooms }}</h4>
        <button class="hotel-update">호텔 수정</button>
      </div>
      <div class="room-list-title">
        <h3>객실 리스트</h3>
      </div>
      <table class="room-list">
        <thead class="list-header">
          <tr>
            <th scope="col" class="index">순서</th>
            <th scope="col" class="title">객실 이름</th>
            <th scope="col" class="room-num">객실 호수</th>
            <th scope="col" class="price">1박 비용</th>
            <th scope="col" class="capacity">기준 인원</th>
            <th scope="col" class="max-capacity">최대 인원</th>
            <th scope="col">예약</th>
          </tr>
        </thead>
        <tbody class="list-body">
          <tr class="room" v-for="(room, idx) in hotel.rooms.rooms" :key="idx">
            <th scope="row">{{ idx + 1 }}</th>
            <td>{{ room.name }}</td>
            <td>{{ room.roomNum }}</td>
            <td>{{ room.price }}</td>
            <td>{{ room.capacity }}</td>
            <td>{{ room.maxCapacity }}</td>
            <td><button @click="roomReservation">예약확인</button></td>
          </tr>
        </tbody>
      </table>
      <div>{{ hotelId }}</div>
      <button>객실 추가</button>
    </div>
    <ReservationList
      v-if="modalToggle"
      :typ="this.typ"
      :id="this.checkRoomId"
    ></ReservationList>
  </div>
</template>

<script>
import {} from "@/api/hotelManage";
import { hotelInfo } from "@/api/hotel";
import ReservationList from "@/components/hotelManage/ReservationList.vue";

export default {
  data() {
    return {
      hotel: {
        title: "호텔 1",
        address: "남구로 어딘가",
        rooms: {
          totalRooms: 2,
          rooms: [
            {
              id: "26",
              name: "방1",
              roomTypeId: "10",
              roomNum: "101",
              price: 20000,
              capacity: 2,
              maxCapacity: 4,
            },
            {
              id: "26",
              name: "방2",
              roomTypeId: "10",
              roomNum: "102",
              price: 20000,
              capacity: 2,
              maxCapacity: 3,
            },
          ],
        },
      },
      typ: "room",
      checkRoomId: "",
      modalToggle: false,
    };
  },
  props: {
    hotelId: {
      type: String,
      require: true,
    },
  },
  created() {},
  methods: {
    hotelInfo() {
      const { data } = hotelInfo(this.hotelID);
      this.hotel = data;
    },
    roomReservation(id) {
      this.checkRoomId = id;
      this.modalToggle = true;
    },
  },
  components: {
    ReservationList,
  },
};
</script>

<style scoped lang="scss">
.hotel-manage-wrapper {
  max-width: 90%;
  background: white;
  -webkit-box-shadow: 0 20px 20px rgba(0, 0, 0, 0.08);
  box-shadow: 0 20px 20px rgba(0, 0, 0, 0.08);
  border-radius: 20px;
  padding: 5px 15px;
  width: 80%;
  margin: 40px auto;
}
.hotel-manage {
  align-items: center;
  justify-content: center;
  .hotel-info {
    margin: 5px 10px;
    padding: 5px 0px;
    border-top-right-radius: 15px;
  }
}
.room-list {
  width: 100%;
  .list-header {
  }
  .list-body {
    text-align: center;
    .room {
    }
  }
}
</style>
