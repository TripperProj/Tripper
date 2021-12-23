<template>
  <div class="content">
    <HotelForm></HotelForm>
    <HotelList></HotelList>
  </div>
</template>

<script>
import HotelForm from "@/components/hotel/HotelForm.vue";
import HotelList from "@/components/hotel/HotelList.vue";
export default {
  components: {
    HotelForm,
    HotelList,
  },
};
</script>

<style></style>
