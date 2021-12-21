<template>
  <header>
    <div class="logo-space">
      <div v-if="!this.$store.getters.isLogin">
        <router-link to="/" class="logo">
          <span>TRIPPER</span>
        </router-link>
      </div>
      <div v-else>
        <router-link to="/community" class="logo">
          <span>TRIPPER</span> |
        </router-link>
        <span class="welcome-comment">
          {{ this.$store.getters.getMemId }} 님 환영합니다.
        </span>
      </div>
      <div v-if="this.isAdmin">
        <router-link to="/hotel/manage"> 호텔 관리 </router-link>
      </div>
    </div>
    <div class="menu-bar">
      <div class="menu-guest" v-if="!this.$store.getters.isLogin">
        <router-link to="/auth"> 로그인</router-link> |
        <router-link to="/signup"> 회원가입</router-link>
      </div>
      <div class="menu-client" v-else>
        <router-link to="/scheduler"> 일정 </router-link> |
        <router-link to="/community"> 커뮤니티</router-link> |
        <router-link to="/hotel"> 호텔</router-link> |
      </div>
    </div>
    <div v-if="this.$store.getters.isLogin">
      <span class="mypage" @click="mypage"> 마이페이지 </span> |
      <span class="logout" @click="logout"> 로그아웃 </span>
    </div>
  </header>
</template>

<script>
import { userLogout } from "@/api/auth";
export default {
  methods: {
    logout() {
      userLogout();
      this.$store.dispatch("setLogout");
      this.$router.push("/auth");
    },
    mypage() {
      this.$router.push("/mypage");
    },
  },
  computed: {
    isAdmin: function () {
      return this.$store.getters.getRole === "ROLE_MANAGER";
    },
  },
};
</script>

<style scoped>
header {
  position: sticky;
  top: 0px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 30px;
  background-color: #7dbeff;
}

.logo {
  color: white;
  font-size: 30px;
  font-weight: 800;
}
.welcome-comment {
  color: white;
  font-size: 20px;
  font-weight: 500;
}
.menu-bar {
  color: white;
  font-size: 15px;
  font-weight: 400;
}
.logout:hover {
  cursor: pointer;
}
.menu-bar a {
  color: white;
  font-size: 15px;
  font-weight: 400;
  text-decoration: none;
}
</style>
