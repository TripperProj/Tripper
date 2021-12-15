<template>
  <div>
    <DatePicker v-model="range" is-range>
      <template v-slot="{ inputValue, inputEvents }">
        <input :value="inputValue.start" v-on="inputEvents.start" />
        <input :value="inputValue.end" v-on="inputEvents.end" />
      </template>
    </DatePicker>
  </div>
</template>

<script>
import DatePicker from "v-calendar/lib/components/date-picker.umd";

export default {
  components: {
    DatePicker,
  },
  data() {
    return {
      range: {
        start: new Date(2021, 0, 1),
        end: new Date(2021, 0, 3),
      },
    };
  },
};
</script>

<style></style>
