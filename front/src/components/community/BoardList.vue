<template>
  <div class="board-list-wrapper">
    <button @click="fetchBoard">불러오기</button>
    <div class="find-mate-list">
      <ul class="board-list">
        <Board v-for="board in boards" :key="board.id" :boardItem="board">
        </Board>
      </ul>
    </div>
  </div>
</template>

<script>
import { fetchBoardList } from "@/api/board";
import Board from "@/components/community/Board.vue";
export default {
  data() {
    return {
      boards: [],
    };
  },
  components: {
    Board,
  },
  methods: {
    async fetchBoard() {
      try {
        const { data } = await fetchBoardList();
        this.boards = data.boards;
      } catch (error) {
        console.log(error);
      }
    },
  },
  created() {
    this.fetchBoard();
  },
};
</script>

<style></style>
