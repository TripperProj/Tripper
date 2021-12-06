<template>
  <div class="board">
    <h2>{{ $route.query.boardItem.title }}</h2>
    <h4>목적지 : {{ $route.query.boardItem.destination }}</h4>
    <div>
      {{ $route.query.boardItem.startDate }} -
      {{ $route.query.boardItem.endDate }}
    </div>
    <button @click="addLikeButton">좋아요</button>
    <button @click="subLikeButton">좋아요 취소</button>
  </div>
</template>

<script>
import { addLikes, subLikes } from "@/api/board.js";
export default {
  methods: {
    addLikeButton() {
      const id = this.$route.query.boardItem.id;
      addLikes(id);
    },
    subLikeButton() {
      const id = this.$route.query.boardItem.id;
      subLikes(id);
    },
    addHits() {},
  },
  created() {
    this.addHits();
  },
};
</script>

<style></style>
