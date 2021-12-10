<template>
  <div class="cotent">
    <div class="community-container">
      <FindMate></FindMate>
      <BoardList></BoardList>
    </div>
  </div>
</template>

<script>
import FindMate from "@/components/community/FindMate.vue";
import BoardList from "@/components/community/BoardList.vue";
export default {
  data() {
    return {};
  },
  components: {
    FindMate,
    BoardList,
  },
  computed: {},
};
</script>

<style></style>
