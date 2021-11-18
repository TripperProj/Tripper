<template>
  <div id="app">
    <app-Header />
    <router-view />
  </div>
</template>

<script>
import AppHeader from "@/components/common/AppHeader";

export default {
  name: "App",
  components: {
    AppHeader,
  },
};
</script>

<style>
@import "./css/common.css";
</style>
