<template>
  <div class="hotel-create">
    <form class="hotel-create-form" @submit.prevent>
      <div class="hotel-title">
        <label for="title"> 호텔 이름 </label>
        <input type="text" placeholder="호텔 이름을 입력해주세요" />
      </div>
      <div class="address">
        <input type="text" placeholder="주소" v-model="address" />
        <button class="modal-button" @click="showModal = true">
          주소 입력
        </button>
      </div>
      <div class="address-modal" v-if="showModal" @close="showModal = false">
        <div class="modal-wrapper">
          <div class="modal-contaniner">
            <div class="modal-header"></div>
            <div class="modal-body">
              <VueDaumPostcode @complete="addressSearch"></VueDaumPostcode>
            </div>
            <div class="modal-footer"></div>
          </div>
        </div>
      </div>
      <div class="img-files">
        <input
          type="file"
          class="hotel-img-input"
          accept="image/*"
          required
          multiple
        />
        <div class="img-list">
          <div class="img"></div>
        </div>
      </div>
      <button @click="submitForm">호텔 입력</button>
    </form>
  </div>
</template>

<script>
import { createHotel } from "@/api/hotelManage";
import { VueDaumPostcode } from "vue-daum-postcode";
import { validateImgFile } from "@/utils/validation.js";
export default {
  data() {
    return {
      titie: "",
      address: "",
      imgFiles: [],
      showModal: false,
    };
  },
  components: {
    VueDaumPostcode,
  },
  computed: {
    hotelImgSizeCheck() {
      return validateImgFile(this.imgFiles);
    },
  },
  methods: {
    async submitForm() {
      const title = this.titie;
      const address = this.address;
      return await createHotel(address, title);
    },
    addressSearch(data) {
      this.address = data.roadAddress;
      this.showModal = false;
    },
  },
};
</script>

<style scoped>
.postcode {
  width: 400px;
  height: 400px;
}
</style>
