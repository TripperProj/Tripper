<template>
  <div class="hotel-create">
    <form @submit.prevent>
      <div class="hotel-title">
        <label for="title"> 호텔 이름 </label>
        <input type="text" placeholder="호텔 이름을 입력해주세요" />
      </div>
      <div class="address">
        <div class="postcode">
          주소를 입력해주세요.
          <VueDaumPostcode @complete="addressSearch"></VueDaumPostcode>
        </div>
        <div class="map"></div>
      </div>
    </form>
  </div>
</template>

<script>
import { createHotel } from "@/api/hotelManage";
import { VueDaumPostcode } from "vue-daum-postcode";

export default {
  data() {
    return {
      titie: "",
      address: "",
    };
  },
  components: {
    VueDaumPostcode,
  },
  methods: {
    async submitForm() {
      const hotelInfo = {
        title: this.titie,
        adress: this.adress,
      };
      return await createHotel(hotelInfo);
    },
    addressSearch(data) {
      this.address = data.roadAddress;
    },
  },
};
</script>

<style scoped>
.postcode {
  width: 400px;
}
</style>
