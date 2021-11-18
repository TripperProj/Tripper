<template>
  <div class="contents">
    <div class="form-wrapper form-wrapper-sm">
      <form class="form" @submit.prevent="submitUserData">
        <div>
          <label class="userid"> 회원정보 입력 </label>
          <input
            type="text"
            id="userid"
            placeholder="아이디"
            v-model="userid"
          />
          <button v-bind:disabled="idCheckBox" class="btn" id="duplicateCheck">
            중복확인
          </button>
          <span v-if="idValid" class="log"> 확인되었습니다. </span>
          <span v-if="!idValid" class="warning"> {{ idCheckMessage }}</span>
        </div>
        <div>
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
          <span class="warning" v-if="passValid">비밀번호를 확인해주세요.</span>
          <hr class="hr-signup" />
        </div>
        <div>
          <input
            type="text"
            id="name"
            placeholder="이름"
            v-model="name"
          /><br />
          <input
            type="text"
            id="userEmail"
            placeholder="이메일"
            v-model="email"
          />
          <div>
            <span class="warning" v-if="true">
              이메일주소를 정확히 입력해주세요.
            </span>
          </div>
          <button class="btn" id="certNumSend">인증번호 전송</button>
          <input
            type="text"
            id="certNum"
            placeholder="인증번호 "
            v-model="certNum"
          />
          <span class="warning">{{ certEmailMessage }}</span>
        </div>
        <button class="btn" type="submit">회원가입</button>
      </form>
    </div>
  </div>
</template>

<script>
import { signupUser, certUserEmail, userIdCheck } from "@/api/index.js";
import { validateEmail, validatePassword } from "@/utils/validation";

export default {
  data() {
    return {
      userid: "",
      password: "",
      passwordCheck: "",
      name: "",
      email: "",
      certNum: "",
      emailValid: false,
      certEmailMessage: "",
      passValid: false,
      idValid: false,
      idCheckBox: false,
      idCheckMessage: "",
    };
  },
  computed: {
    userEmailValid() {
      return validateEmail(this.email);
    },
    passwordValid() {
      return validatePassword(this.password, this.passwordCheck);
    },
  },
  methods: {
    async submitUserData() {
      const userData = {
        userid: this.userid,
        password: this.password,
        name: this.name,
        email: this.email,
      };
      try {
        const { data } = await signupUser.post(userData);
        console.log(data);
      } catch (error) {
        console.log(error);
      }
    },
    async certEmail() {
      const userData = {
        email: this.email,
        certNum: "12345",
      };
      try {
        const { data } = await certUserEmail.post(userData);
        console.log(data);
      } catch (error) {
        console.log(error);
      }
    },
    async idCheck() {
      this.idCheckBox = !this.idCheckBox;
      const userData = {
        userid: this.userid,
      };
      try {
        const { data } = await userIdCheck.post(userData);
        console.log(data);
      } catch (error) {
        console.log(error);
      }
    },
  },
  components: {},
};
</script>

<style scoped></style>
