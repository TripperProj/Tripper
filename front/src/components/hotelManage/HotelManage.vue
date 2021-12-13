<template>
  <div class="hotel-manage">
    <div class="hotel-manage-header">
      <div></div>
    </div>
    <div class="hotel-manage-body">
      <div></div>
      <ul class="hotel-manage-list">
        <li class="hotel-manage-item">
          <div></div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import {} from "@/api/hotel";

export default {
  data() {
    return {
      managerID: this.$store.getters.getManageId,
    };
  },
  methods: {
    loadHotelList() {},
  },
  created() {
    this.loadHotelList();
  },
};
</script>

<style scoped></style>
