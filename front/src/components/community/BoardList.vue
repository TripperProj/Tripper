<template>
  <div class="board-list-wrapper">
    <div class="find-mate-list">
      <ul class="board-list">
        <li class="board-column">
          <div class="board-item">
            <div class="title-box">
              <span class="title"></span>
              <span class="destination"> 목적지 : </span>
              <span class="date"> </span>
            </div>
            <router-link to=""> </router-link>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import { fetchBoardList } from "@/api/board";

export default {
  data() {
    return {
      boards: [],
    };
  },
  methods: {
    async fetchBoard() {
      const { data } = await fetchBoardList();
      console.log(data);
    },
  },
  created() {
    this.fetchBoard();
  },
};
</script>

<style></style>
