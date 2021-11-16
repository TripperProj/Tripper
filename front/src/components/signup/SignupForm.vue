<template>
  <div class="contents">
    <div class="form-wrapper form-wrapper-sm">
      <form class="form" @submit.prevent="">
        <div>
          <label class="userid"> 회원정보 입력 </label>
          <input type="text" id="userid" placeholder="아이디" />
          <button class="btn" id="duplicateCheck">중복확인</button>
          <!-- 아이디 검증 메세지 자리 -->
        </div>
        <div>
          <input type="password" id="password" placeholder="비밀번호" />
          <input
            type="password"
            id="passwordCheck"
            placeholder="비밀번호 확인"
          />
          <!-- 비밀번호 확인 검증 메세지 자리 -->
          <hr class="hr-signup" />
        </div>
        <div>
          <input type="text" id="name" placeholder="이름" /><br />
          <input type="text" id="phone" placeholder="이메일" />
          <button class="btn" id="certNumSend">인증번호 전송</button>
          <input type="text" id="certNum" placeholder="인증번호 " />
          <span class="log">로그메세지</span><br />
          <span class="warning">경고메세지</span>
        </div>
        <button class="btn" type="submit">회원가입</button>
      </form>
    </div>
  </div>
</template>

<script>
import { signUpUser } from "@/api/index.js";
import {} from "@/utils/validation";

export default {
  data() {
    return {
      userid: "",
      password: "",
      passwordCheck: "",
      name: "",
      phone: "",
      certNum: "",
    };
  },
  methods: {
    signUpUser,
  },
  computed: {},
  components: {},
};
</script>

<style scoped></style>
