<template>
  <div>제목 : {{ board.title }} 목적지 : {{ board.destination }}</div>
</template>

<script>
export default {
  data() {
    return {
      board: {
        id: "",
        title: "",
        destination: "",
        content: "",
        start: "",
        end: "",
      },
    };
  },
  methods: {},
  computed: {},
};
</script>

<style></style>
