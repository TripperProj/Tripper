import Vue from "vue";
import VueRouter from "vue-router";
import store from "@/store/index";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    redirect: store.state.loginSuccess ? "/main" : "/auth",
  },
  {
    path: "/main",
    component: () => import("@/views/MainPage.vue"),
  },
  {
    path: "/auth",
    component: () => import("@/views/LoginPage.vue"),
  },
  {
    path: "/signup",
    component: () => import("@/views/SignupPage.vue"),
  },
  {
    path: "/findroom",
    component: () => import("@/views/FindRoomPage.vue"),
    meta: {
      auth: true,
    },
  },
  {
    path: "/community",
    component: () => import("@/views/CommunityPage.vue"),
    meta: {
      auth: true,
    },
  },
  {
    path: "/writeboard",
    component: () => import("@/views/WriteBoardPage.vue"),
    meta: {
      auth: true,
    },
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

router.beforeEach((to, from, next) => {
  to.meta.auth && !store.state.loginSuccess ? next("/auth") : "";
  console.log(from, next);
});

export default router;
