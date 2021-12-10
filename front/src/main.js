import Vue from "vue";
import App from "./App.vue";
import router from "@/router/index";
import store from "@/store/index";
import DragItDude from "vue-drag-it-dude";

Vue.config.productionTip = false;

new Vue({
  render: (h) => h(App),
  router,
  store,
}).$mount("#app");

Vue.component("vue-drag-it-dude", DragItDude);
