<template>
  <div class="write-wrapper">
    <div class="write-board">
      <form class="write-board-form" @submit.prevent="submitForm">
        <h2>여행 메이트 모집글 작성</h2>
        <div class="board-head">
          <div class="title">
            <label for="title"> </label>
            <input type="text" placeholder="제목" v-model="title" />
          </div>
          <div class="destination">
            <label for="destination"> </label>
            <input type="text" placeholder="목적지" v-model="destination" />
          </div>
          <div class="recrutiment">
            <input type="number" placeholder="인원 수" v-model="recrutiment" />
          </div>
          <div class="date">
            <label for="date"> 여행 기간 </label>
            <DatePicker v-model="range" is-range>
              <template v-slot="{ inputValue, inputEvents }">
                <div>
                  <input :value="inputValue.start" v-on="inputEvents.start" />
                  <span> ~ </span>
                  <input :value="inputValue.end" v-on="inputEvents.end" />
                </div>
              </template>
            </DatePicker>
            <hr />
          </div>
        </div>
        <div class="board-content">
          <input type="text" placeholder="글 작성" v-model="content" />
        </div>
        <button>글 작성</button>
        <br />
      </form>
    </div>
  </div>
</template>

<script>
import DatePicker from "v-calendar/lib/components/date-picker.umd";
import { createPost } from "@/api/board";

export default {
  data() {
    return {
      title: "",
      destination: "",
      content: "",
      recrutiment: 0,
      range: {
        start: new Date(),
        end: new Date(),
      },
    };
  },
  components: {
    DatePicker,
  },
  methods: {
    async submitForm() {
      const board = {
        title: this.title,
        destination: this.destination,
        recrutiment: this.recrutiment,
        content: this.content,
        startDate: this.range.start,
        endDate: this.range.end,
      };
      try {
        const response = await createPost(board);
        console.log(response);
        this.$router.push("/community");
      } catch (error) {
        console.log(error);
      }
    },
    clearAll() {
      this.board = { title: "", destination: "", content: "", recrutiment: "" };
    },
  },
};
</script>

<style scoped></style>
