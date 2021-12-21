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
          @change="this.onFilesChange"
          @click="this.clearImgPreview"
          required
          multiple
        />
        <div class="img-list">
          <div class="img-preview"></div>
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
      const imgs = this.imgFiles;
      return await createHotel(address, title, imgs);
    },
    addressSearch(data) {
      this.address = data.roadAddress;
      this.showModal = false;
    },
    onFilesChange(e) {
      this.imgFiles = e.target.files;
      const container = document.getElementsByClassName("img-preview");
      const filesList = Array.from(e.target.files);
      filesList.forEach((file) => {
        const reader = new FileReader();
        const $imgDiv = document.createElement("div");
        const $img = document.createElement("img");
        const $label = document.createElement("label");

        $label.textContent = file.name;
        $imgDiv.appendChild($img);
        $imgDiv.appendChild($label);

        reader.onload = (e) => {
          $img.src = e.target.result;
          $img.style.width = 200 + "px";
          $img.style.height = 200 + "px";
        };
        reader.readAsDataURL(file);
        container[0].appendChild($imgDiv);
      });
    },
    clearImgPreview() {
      const container = document.getElementsByClassName("img-preview");
      container[0].innerHTML = "";
    },
  },
};
</script>

<style scoped>
.postcode {
  width: 400px;
  height: 400px;
}
.img-list {
  display: flex;
}
</style>
