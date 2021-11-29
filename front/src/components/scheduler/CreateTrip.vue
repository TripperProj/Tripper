<template>
  <form class="form" @submit.prevent="submitForm">
    <div>
      <h2>여행생성</h2>
      <input type="text" id="id" placeholder="아이디" v-model="userid" /><br />
      <input
        type="password"
        id="password"
        placeholder="비밀번호"
        v-model="password"
      /><br />
      <button class="btn" type="submit">로그인하기</button>
    </div>
    <hr class="hr-login" />
    <div class="find-idpassword">
      <span>아이디, 비밀번호 찾기</span>
      <span>회원가입</span>
    </div>
  </form>
</template>

<script>
export default {
  data: () => ({
    trip: {
      title: "",
      startDate: "",
      endDate: "",
      memo: "",
    },
  }),
};
</script>

<style lang="scss" scoped>
form {
  z-index: 4;
  width: 600px;
  height: 700px;
  background: #d8f3ff;
}
h2 {
  text-align: center;
}
.hr-login {
  width: 4px;
  opacity: 0.5;
}
.find-idpassword {
  color: grey;
  opacity: 0.6;
}
span:hover {
  opacity: 1;
}
</style>
