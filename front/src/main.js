import Vue from "vue";
import App from "./App.vue";
import router from "@/router/index";
import store from "@/store/index";

import DragItDude from "vue-drag-it-dude";

import { formatDate } from "@/utils/filters";

Vue.filter("formatDate", formatDate);
Vue.config.productionTip = false;

new Vue({
  render: (h) => h(App),
  router: router,
  store: store,
}).$mount("#app");

Vue.component("vue-drag-it-dude", DragItDude);
