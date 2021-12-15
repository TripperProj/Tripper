<template>
  <div class="content">
    <div class="form-container">
      <div class="form-wrapper form-wrapper-wd">
        <form class="room-search-form">
          <div class="location">
            <div for="">위치</div>
            <input type="text" placeholder="어디로 가시나요?" />
          </div>
          <div class="check-in">
            <div>체크인</div>
            <DatePicker v-model="range" is-range>
              <template v-slot="{ inputValue, inputEvents }">
                <div>
                  <input :value="inputValue.start" v-on="inputEvents.start" />
                  <span> ~ </span>
                  <input :value="inputValue.end" v-on="inputEvents.end" />
                </div>
              </template>
            </DatePicker>
          </div>
          <div class="num-of-people">
            <div>인원</div>
            <div class="num-people">인원추가</div>
          </div>
          <div><button class="search-room" type="submit">검색</button></div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import DatePicker from "v-calendar/lib/components/date-picker.umd";

export default {
  data() {
    return {
      range: {
        start: new Date(),
        end: new Date(),
      },
    };
  },
  components: {
    DatePicker,
  },
};
</script>

<style scoped></style>
