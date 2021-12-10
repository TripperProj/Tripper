<template>
  <div class="community-content">
    <br />
    <div class="content-wrapper">
      <div class="search-condition">
        <div class="find-mate-search">
          <form @submit.prevent="" class="find-mate-form">
            <div class="destination">
              <div>목적지</div>
              <input type="text" />
            </div>
            <div class="check-in">
              <div>여행시작</div>
              <input type="text" placeholder="여행시작 날짜" />
            </div>
            <div class="check-out">
              <div>여행 끝</div>
              <input type="text" placeholder="여행 끝 날짜" />
            </div>
            <div class="num-people">
              <div>인원</div>
              <input type="text" />
            </div>
            <button class="board-search-btn">검색</button>
          </form>
        </div>
      </div>
    </div>
    <div>
      <router-link class="write-board" to="/writeBoard">
        동행자 찾기 글 쓰기</router-link
      >
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
  methods: {},
  created() {},
};
</script>

<style scoped></style>
