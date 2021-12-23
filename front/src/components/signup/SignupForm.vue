<template>
  <div class="contents">
    <div class="form-wrapper form-wrapper-sm">
      <form class="form" @submit.prevent="submitForm">
        <div class="signup-id">
          <label class="userid"> 회원정보 입력 </label>
          <input type="text" id="memId" placeholder="아이디" v-model="memId" />
          <div class="btn" v-on:click="idCheck" id="duplicateCheck">
            중복확인
          </div>
          <span class="warning"> {{ idCheckMessage }}</span>
        </div>
        <div class="signup-pass">
          <input
            type="password"
            id="password"
            v-model="password"
            placeholder="비밀번호 (숫자, 문자, 특수문자 조합 8~25글자)"
          />
          <input
            type="password"
            id="passwordCheck"
            v-model="passwordCheck"
            placeholder="비밀번호 확인"
          />
          <span class="warning" v-if="!userPasswordValid && password">
            비밀번호 형식을 지켜주세요 (숫자, 문자, 특수문자 조합 8~25글자)<br />
          </span>
          <span class="warning" v-if="!userPasswordCheck && passwordCheck">
            비밀번호가 다릅니다.<br />
          </span>
          <hr class="hr-signup" />
        </div>
        <div class="signup-name-email">
          <input
            type="text"
            id="name"
            placeholder="이름"
            v-model="name"
          /><br />
          <input
            type="text"
            id="phone"
            placeholder="전화번호"
            v-model="phone"
          /><br />
          <input
            type="text"
            id="nickname"
            placeholder="닉네임"
            v-model="nickName"
          /><br />
          <input
            type="text"
            id="userEmail"
            placeholder="이메일"
            v-model="email"
          />
          <div>
            <span class="warning" v-if="!userEmailValid && email">
              이메일주소를 정확히 입력해주세요.
            </span>
          </div>
          <span class="warning">{{ certEmailMessage }}</span>
        </div>
        <button class="btn" type="submit" v-bind:disabled="btnAble">
          회원가입
        </button>
      </form>
    </div>
  </div>
</template>

<script>
import {
  signupUser,
  certUserEmail,
  userIdCheck,
  // certNumCheck,
} from "@/api/auth.js";
import {
  validateEmail,
  validatePassword,
  checkPassword,
} from "@/utils/validation";

export default {
  data() {
    return {
      memId: "",
      password: "",
      passwordCheck: "",
      name: "",
      phone: "",
      email: "",
      nickName: "",
      certNum: "",
      certEmailMessage: "",
      idCheckMessage: "",
      idChecked: false,
      emailChecked: false,
    };
  },
  computed: {
    userEmailValid() {
      return validateEmail(this.email);
    },
    userPasswordValid() {
      return validatePassword(this.password);
    },
    userPasswordCheck() {
      return checkPassword(this.password, this.passwordCheck);
    },
    btnAble() {
      return (
        this.userEmailValid &&
        this.userPasswordValid &&
        this.userPasswordCheck &&
        this.emailChecked &&
        this.idChecked
      );
    },
  },
  methods: {
    async submitForm() {
      // 사용자 회원가입
      const userData = {
        memId: this.memId,
        password: this.password,
        name: this.name,
        phone: this.phone,
        email: this.email,
        nickname: this.nickName,
        auth: "ROLE_USER",
      };
      try {
        const { status } = await signupUser(userData);
        status === 200 ? this.$router.push("/auth") : console.log(status);
      } catch (error) {
        console.log(error);
      } finally {
        this.clearAll();
      }
    },
    async certEmail() {
      // 사용자 이메일 인증
      const email = this.email;
      try {
        const response = await certUserEmail(email);
        console.log(response);
      } catch (error) {
        console.log(error);
      }
    },
    // async certEmailNum() {
    //   const certNum = this.certNum;
    //   const { status } = await certNumCheck(certNum);
    //   status === 200
    //     ? (this.emailChecked = true)
    //     : (this.certEmailMessage = "인증번호를 확인해주세요.");
    // },
    async idCheck() {
      // 사용자 아이디 중복확인
      const memid = this.memid;
      try {
        const { data } = await userIdCheck(memid);
        console.log(data);
        data === "ok"
          ? ((this.idCheckMessage = "사용가능한 아이디입니다."),
            (this.idChecked = true))
          : (this.idCheckMessage = "다른 아이디를 사용해주세요.");
      } catch (error) {
        console.log(error);
      }
    },
    clearAll() {
      // input 초기화1
      this.memId = "";
      this.password = "";
      this.passwordCheck = "";
      this.name = "";
      this.email = "";
      this.phone = "";
      this.nickName = "";
    },
  },
  components: {},
};
</script>

<style scoped></style>
