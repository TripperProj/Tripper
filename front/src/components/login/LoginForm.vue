<template>
  <div>
    <form class="form">
      <div>
        <input type="text" id="id" placeholder="아이디를 입력해주세요." /><br />
        <input
          type="password"
          id="password"
          placeholder="비밀번호를 입력해주세요."
        /><br />
        <button class="btn" type="submit">로그인하기</button>
      </div>
      <hr class="hr-login" />
      <div class="find-idpassword">
        <span>아이디, 비밀번호 찾기</span>
        <span>회원가입</span>
      </div>
    </form>
  </div>
</template>

<script>
import SocialLogin from "@/components/login/SocialLogin.vue";
import { loginUser } from "@/api/index.js";
import {} from "@/utils/validation";

export default {
  data() {
    return {
      userid: "",
      password: "",
    };
  },
  methods: {
    loginUser,
    SocialLogin,
  },
};
</script>

<style scoped>
.hr-login {
  width: 480px;
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
