<template>
  <div class="content">
    <div class="writeBoard">
      <form class="write-board-form" action="">
        <div class="board-head">
          <input type="text" placeholder="제목" v-model="board.title" />
          <input type="text" placeholder="목적지" v-model="board.destination" />
          <input type="text" placeholder="여행시작일" v-model="board.start" />
          <input type="text" placeholder="여행종료일" v-model="board.end" />
          <input
            type="text"
            placeholder="인원 수"
            v-model="board.recrutiment"
          />
        </div>
        <div class="board-content">
          <input type="text" placeholder="글 작성" v-model="board.content" />
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import { createPost } from "@/api/board";

export default {
  data() {
    return {
      board: {
        title: "",
        destination: "",
        content: "",
        recrutiment: "",
        start: "",
        end: "",
      },
    };
  },
  methods: {
    async submitForm() {
      const board = {
        title: this.title,
        destination: this.destination,
        recrutiment: this.recrutiment,
        content: this.content,
      };
      try {
        await createPost(board);
      } catch (error) {
        console.log(error);
      } finally {
        this.clearAll();
      }
    },
    clearAll() {
      this.board = { title: "", destination: "", content: "", recrutiment: "" };
    },
  },
};
</script>

<style></style>
