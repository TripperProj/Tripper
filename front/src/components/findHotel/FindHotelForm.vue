<template>
  <div class="content">
    <div class="form-container">
      <div class="form-wrapper form-wrapper-wd">
        <form class="room-search-form" @submit.prevent="">
          <div class="location">
            <div for="">위치</div>
            <input type="text" placeholder="어디로 가시나요?" />
          </div>
          <div class="check-in check-out">
            <DatePicker v-model="range" is-range>
              <template v-slot="{ inputValue, inputEvents }">
                <div>체크인</div>
                <input :value="inputValue.start" v-on="inputEvents.start" />
                <div>체크아웃</div>
                <input :value="inputValue.end" v-on="inputEvents.end" />
              </template>
            </DatePicker>
          </div>
          <div class="num-of-people">
            <div>인원</div>
            <div>
              <input type="text" placeholder="성인" /><br /><br />
              <input type="text" placeholder="아동" />
            </div>
          </div>
          <div>
            <button class="hotel-search-btn" type="submit">검색</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import DatePicker from "v-calendar/lib/components/date-picker.umd";
import { hotelList } from "@/api/hotel.js";

export default {
  components: {
    DatePicker,
  },
  data() {
    return {
      range: {
        start: new Date(),
        end: new Date(),
      },
      keyword: "",
    };
  },
  methods: {
    async hotelListing() {
      const searchCondition = {
        keyword: this.keyword,
      };
      try {
        const { data } = await hotelList(searchCondition);
        console.log(data);
      } catch (error) {
        console.log(error);
      }
    },
  },
};
</script>

<style scoped></style>
