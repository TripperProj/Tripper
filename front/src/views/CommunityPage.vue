<template>
  <div class="cotent">
    <div class="community-container">
      <findMate></findMate>
      <boardList></boardList>
    </div>
  </div>
</template>

<script>
import findMate from "@/components/community/FindMate.vue";
import boardList from "@/components/community/BoardList.vue";
export default {
  data() {
    return {};
  },
  components: {
    findMate,
    boardList,
  },
  computed: {},
};
</script>

<style></style>
