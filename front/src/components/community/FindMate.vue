<template>
  <div class="content">
    <h2>동행자 찾기</h2>
    <div class="search-condition">
      <input type="text" class="find-mate" />
      <div>
        <router-link to="/writeBoard"> 동행자 찾기 글 쓰기</router-link>
      </div>
    </div>
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
import { postList } from "@/api/board.js";
export default {
  data() {
    return {
      board: {
        id: "",
        title: "제주도 같이가실분",
        destination: "제주",
        content: "",
        likes: 0,
        hits: 0,
      },
    };
  },
  methods: {
    async boardList() {
      try {
        const boardList = postList();
        return boardList;
      } catch (error) {
        console.log(error);
      }
    },
  },
  created() {},
};
</script>

<style scoped></style>
