<template>
  <div class="board-list-wrapper">
    <div class="find-mate-list">
      <ul class="board-list">
        <li class="board-column" v-bind:key="board.id" v-for="board in boards">
          <div class="board-item">
            <div class="title-box">
              <span class="title">{{ board.title }}</span>
              <span class="destination"> 목적지 : {{ board.destination }}</span>
              <span class="date"> {{ board.start }} - {{ board.end }} </span>
            </div>
            <router-link to="/board/post/"> {{ board.content }}</router-link>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import { postList } from "@/api/board";
export default {
  data() {
    return {
      boards: [
        {
          title: "제목",
          destination: "제주도",
          content: "제주도 여행 같이 가실분",
          start: "2021-12-21",
          end: "2021-12-24",
        },
        {
          title: "제목2",
          destination: "제주도",
          content: "제주도 여행 같이 가실분",
          start: "2021-12-21",
          end: "2021-12-24",
        },
      ],
    };
  },
  created() {
    //this.boardList();
  },
  methods: {
    async boardList() {
      try {
        const boardList = await postList();
        return boardList;
      } catch (error) {
        console.log(error);
      }
    },
  },
};
</script>

<style></style>
