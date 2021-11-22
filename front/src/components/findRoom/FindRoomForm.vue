<template>
  <div class="content">
    <div class="form-container">
      <div class="form-wrapper form-wrapper-wd">
        <form class="room-search-form">
          <div class="location">
            <div for="">위치</div>
            <input type="text" placeholder="어디로 가시나요?" />
          </div>
          <div class="check-in">
            <div>체크인</div>
            <input type="text" placeholder="체크인 날짜" />
          </div>
          <div class="check-out">
            <div>체크아웃</div>
            <input type="text" placeholder="체크아웃 날짜" />
          </div>
          <div class="num-of-people">
            <div>인원</div>
            <input type="text" />
          </div>
          <div><button class="search-room" type="submit">검색</button></div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      modal: true,
    };
  },
};
</script>

<style scoped></style>
