<template>
  <div class="write-board">
    <form class="write-board-form" @submit.prevent="submitForm">
      <div class="board-head">
        <div class="title">
          <label for="title"> 제목 </label>
          <input type="text" placeholder="제목" v-model="title" />
        </div>
        <div class="destination">
          <label for="destination"> 목적지 : </label>
          <input type="text" placeholder="목적지" v-model="destination" />
        </div>
        <div class="date">
          <label for="date"> 여행 기간 </label>
          <input type="text" placeholder="여행시작일" v-model="startDate" />
          <input type="text" placeholder="여행종료일" v-model="endDate" />
        </div>
        <div class="recrutiment">
          <input type="number" placeholder="인원 수" v-model="recrutiment" />
        </div>
      </div>
      <div class="board-content">
        <input type="text" placeholder="글 작성" v-model="content" />
      </div>
      <button>글 작성</button>
    </form>
  </div>
</template>

<script>
import { createPost } from "@/api/board";

export default {
  data() {
    return {
      title: "",
      destination: "",
      content: "",
      recrutiment: 0,
      startDate: "",
      endDate: "",
    };
  },
  methods: {
    async submitForm() {
      const board = {
        title: this.title,
        destination: this.destination,
        recrutiment: this.recrutiment,
        content: this.content,
        startDate: this.startDate,
        endDate: this.endDate,
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
