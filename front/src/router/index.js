import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    redirect: "/main",
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
    path: "/scheduler",
    component: () => import("@/views/SchedulerPage.vue"),
  },
  {
    path: "/schedule-list",
    component: () => import("@/components/scheduler/ScheduleList.vue"),
  },
  {
    path: "/budget",
    component: () => import("@/components/budget/BudgetPage.vue"),
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
