<template>
  <drag-it-dude class="form" @submit.prevent="submitForm">
    <div class="els">
      <h2>여행생성</h2>
      <input
        class="elInput"
        type="text"
        id="title"
        placeholder="여행제목"
        v-model="trip.title"
      />
      <date-picker
        class="elPicker"
        valueType="format"
        v-model="trip.startDate"
        range
        placeholder="여행기간"
      ></date-picker>
      <textarea
        class="elArea elInput"
        type=""
        id="memo"
        placeholder="자유롭게 매모를 작성해보세요 :)"
        v-model="trip.memo"
      />
      <button class="btn" type="submit" @click="create">생성하기</button>
    </div>
  </drag-it-dude>
</template>

<script>
import DragItDude from "vue-drag-it-dude";
import DatePicker from "vue2-datepicker";
import "vue2-datepicker/index.css";
export default {
  data: () => ({
    trip: {
      title: "",
      startDate: "",
      endDate: "",
      memo: "",
    },
  }),
  components: {
    DragItDude,
    DatePicker,
  },
  methods: {
    create() {
      //데이터 전송
      console.log(this.trip.startDate);
      this.$emit("changeCreateStat", false);
    },
  },
};
</script>

<style lang="scss" scoped>
.form {
  height: 430px;
  z-index: 4;
  background: #d8f3ff;
  border-radius: 10px;
  border: 3px solid #fff;
  box-shadow: 0 20px 20px rgba(0, 0, 0, 0.08);
}
h2 {
  text-align: center;
  color: #59a6e9;
  text-shadow: 2px 2px 2px #fff;
}

.els {
  width: 400px;
  margin: 0 auto;
  * {
    margin-bottom: 20px;
  }
  .elInput {
    display: block;
    width: 376px;
    border-radius: 25px;
    border: #d8f3ff 2px solid;
    &:focus {
      border-color: #59a6e9;
      outline: none;
    }
  }
  .elArea {
    margin-top: 20px;
    line-height: 3;
    border: none;
    resize: none;
    border-radius: 30px;
    &:focus {
      border-color: #59a6e9;
      outline: none;
    }
  }
  .mx-input-wrapper input {
    display: block;
    height: 50px;
  }
}

.btn {
  color: #fff;
  width: 400px;
  height: 45px;
  font-size: 17px;
  border-radius: 25px;
  left: 0;
  right: 0;
  padding: 8px 12px;
  margin: 20px auto;
  box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.1);
  &:hover {
    cursor: pointer;
    box-shadow: 1px 1px 0 rgb(0, 0, 0, 0.3);
    position: relative;
    top: 1px;
  }
}

.mx-btn {
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  line-height: 1;
  font-size: 14px;
  font-weight: 500;
  padding: 7px 15px;
  margin: 0;
  cursor: pointer;
  background-color: transparent;
  outline: none;
  border: 1px solid rgba(0, 0, 0, 0.1);
  border-radius: 7px;
  color: #73879c;
  white-space: nowrap;
}

.mx-datepicker-range {
  display: block;
  width: 400px;
  height: 30px;
  border: #d8f3ff 2px solid;
  border-radius: 25px;
  margin: 0 auto;
  .mx-input-wrapper {
    height: 50px;
  }
}
.mx-input {
  display: block;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  width: 100%;
  height: 80px;
  padding: 6px 30px;
  padding-left: 10px;
  font-size: 14px;
  color: #555;
  background-color: #fff;
  border: 1px solid #ccc;
  border-radius: 25px;
  -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
  box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
}
.mx-icon-calendar,
.mx-icon-clear {
  position: relative;
  top: 50%;
  right: 8px;
  -webkit-transform: translateY(-50%);
  transform: translateY(-50%);
  font-size: 16px;
  line-height: 1;
  color: rgba(0, 0, 0, 0.5);
}
</style>
