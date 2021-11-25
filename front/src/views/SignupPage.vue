<template>
  <div>
    <h1 class="header">회원가입</h1>
    <signup-form></signup-form>
  </div>
</template>

<script>
import signupForm from "@/components/signup/SignupForm.vue";

export default {
  components: {
    signupForm,
  },
};
</script>

<style scoped>
div {
  align-items: center;
}
</style>
