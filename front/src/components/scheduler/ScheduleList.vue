<template>
  <div class="container">
    <div class="config">
      <h2>{{ trip.destination }}</h2>
      <div class="range">{{ trip.start_date }} - {{ trip.end_date }}</div>
    </div>
    <div class="list" v-for="list in schedule" :key="list.scheduleal_id">
      <div class="head">
        <div class="name">{{ list.name }}</div>
        <div class="time">{{ list.start_time }}</div>
        <div class="time">{{ list.end_time }}</div>
      </div>
      <div class="body">
        <div class="content">{{ list.content }}</div>
      </div>
    </div>
    <div class="addBtn" @click="createTrip">
      <div class="circle">
        <div class="plus"><span>+</span></div>
      </div>
    </div>
    <CreateSchedule v-if="createStat" @changeCreateStat="changeCreateStat" />
  </div>
</template>

<script>
import CreateSchedule from "@/components/scheduler/CreateSchedule.vue";
export default {
  data: () => ({
    createStat: false,
    trip: {
      destination: "3년만에 떠나는 우정여행 제주도",
      start_date: "2021-11-13",
      end_date: "2021-11-15",
    },
    schedule: [
      {
        scheduleal_id: 1,
        name: "인생",
        content: "10시까지 고터로 모여",
        start_time: "2021-11-13 09:00:00",
        end_time: "2021-11-13 11:00:00",
      },
      {
        scheduleal_id: 2,
        name: "국밥",
        content: "역전할머니",
        start_time: "2021-11-13 11:00:00",
        end_time: "2021-11-13 12:00:00",
      },
    ],
  }),
  components: {
    CreateSchedule,
  },
  methods: {
    changeCreateStat(val) {
      this.createStat = val;
    },
    createTrip() {
      this.createStat = true;
    },
  },
};
</script>

<style lang="scss" scoped>
.container {
  width: 100%;
  margin: 0 auto;
  height: 100vh;
  .config {
    text-align: center;
    width: 800px;
    margin: 0 auto;
    h2 {
      color: #1670aa;
      text-shadow: 2px 2px 2px #a0acbd;
    }
    .range {
      width: fit-content;
      font-size: 18px;
      font-weight: 700;
      position: relative;
      left: 550px;
      margin-bottom: 20px;
    }
  }
  .list {
    width: 800px;
    display: flex;
    background: #7dbeff;
    margin: 0px auto 20px auto;
    padding: 10px 10px;
    border-radius: 25px;

    .head {
      width: 40%;
      padding: 15px 20px;
      border: solid #f0faff;
      border-width: 4px 2px 4px 4px;
      border-radius: 20px 0 0 20px;
      .name {
        font-size: 1.5rem;
        color: #3c4856;
        font-weight: 700;
        margin-bottom: 5px;
      }
      .time {
        font-size: 1.1rem;
        color: #f0faff;
      }
    }
    .body {
      width: 60%;
      color: #3c4856;
      padding: 15px 15px;
      border: solid #f0faff;
      border-width: 4px 4px 4px 2px;
      border-radius: 0 20px 20px 0;
    }
  }
  .addBtn {
    position: fixed;
    bottom: 50px;
    right: 50px;
    background: #7dbeff;
    border-radius: 50%;
    width: 70px;
    height: 70px;
    display: flex;
    align-items: center;
    justify-content: center;
    z-index: 9;
    &:hover {
      cursor: pointer;
      background: #f99ec0;
      box-shadow: 1px 1px 0 rgb(0, 0, 0, 0.3);
      position: fixed;
      bottom: 49px;
      right: 50px;
    }
    .plus {
      color: #fff;
      text-align: center;
      height: 55px;
      width: 55px;
      font-size: 40px;
      font-weight: 700;
      border: 4px solid #fff;
      border-radius: 50%;
      display: flex;
      align-items: center;
      justify-content: center;
      span {
        position: relative;
        bottom: 3px;
      }
    }
  }
}
</style>
