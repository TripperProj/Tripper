<template>
  <div>
    <h1 class="Header">로그인</h1>
    <div class="content">
      <div class="form-wrapper form-wrapper-sm">
        <LoginForm></LoginForm>
        <SocialLogin></SocialLogin>
      </div>
    </div>
  </div>
</template>

<script>
import SocialLogin from "@/components/login/SocialLogin.vue";
import LoginForm from "@/components/login/LoginForm.vue";

export default {
  components: {
    LoginForm,
    SocialLogin,
  },
};
</script>

<style></style>
