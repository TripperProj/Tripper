<template>
  <div class="board-list-wrapper">
    <div class="find-mate-list">
      <ul class="board-list">
        <li
          class="board-column"
          v-bind:key="board.id"
          v-for="board in this.$store.state.boards"
        >
          <div class="board-item">
            <div class="title-box">
              <span class="title">{{ board.title }}</span>
              <span class="destination"> 목적지 : {{ board.destination }}</span>
              <span class="date"> {{ board.start }} - {{ board.end }} </span>
            </div>
            <router-link to=""> {{ board.content }}</router-link>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
  created() {
    this.$store.dispatch("FETCH_BOARD");
  },
  methods: {},
};
</script>

<style></style>
