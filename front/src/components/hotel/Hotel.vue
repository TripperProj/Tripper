<template>
  <li class="hotel-item" v-for="">
    <div>
      <span class="hotel-title"></span>
    </div>
  </li>
</template>

<script>
export default {
  data() {
    return {
      hotel: {
        title: "",
        constent: "",
      },
    };
  },
};
</script>

<style></style>
