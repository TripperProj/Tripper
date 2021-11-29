import Vue from "vue";
import VueRouter from "vue-router";
import store from "@/store/index";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    redirect: store.state.loginSuccess ? "/main" : "/login",
  },
  {
    path: "/main",
    component: () => import("@/views/MainPage.vue"),
  },
  {
    path: "/login",
    component: () => import("@/views/LoginPage.vue"),
  },
  {
    path: "/signup",
    component: () => import("@/views/SignupPage.vue"),
  },
  {
    path: "/findroom",
    component: () => import("@/views/FindRoomPage.vue"),
  },
  {
    path: "/community",
    component: () => import("@/views/CommunityPage.vue"),
  },
  {
    path: "*",
    component: () => import("@/views/NotFoundPage.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
