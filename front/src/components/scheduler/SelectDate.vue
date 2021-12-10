<template>
  <div>
    <div class="label">여행 날짜를 선택하세요</div>
    <date-picker id="date" v-model="time" range></date-picker>
  </div>
</template>

<script>
import DatePicker from "vue2-datepicker";
import "vue2-datepicker/index.css";
export default {
  components: { DatePicker },
  data() {
    return {
      time: null,
    };
  },
};
</script>

<style scoped lang="scss">
#date {
  width: 220px;
}
</style>
