<template>
  <div>
    <div class="find-mate-list">
      <div class="board">
        <div>
          <h3>글제목</h3>
          <h4>목적지 : {{ board.destination }}</h4>
        </div>
        <div class="board-content">
          <span>{{ board.title }}</span>
        </div>
        <div>
          <h5>추천 수 : {{ board.likes }}</h5>
          <h5>조회 수 : {{ board.hits }}</h5>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { postList } from "@/api/board";
export default {
  data() {
    return {
      board: [],
    };
  },
  created() {},
  methods: {
    async boardList() {
      try {
        const boardList = await postList();
        this.board = boardList;
      } catch (error) {
        console.log(error);
      }
    },
  },
};
</script>

<style></style>
