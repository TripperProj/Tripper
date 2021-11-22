import Vue from "vue";
import App from "./App.vue";
import router from "@/router/index";
import store from "@/store/index";
import DatePicker from "v-calendar/lib/components/date-picker.umd";

Vue.component("date-picker", DatePicker);

Vue.config.productionTip = false;

new Vue({
  render: (h) => h(App),
  router,
  store,
}).$mount("#app");
