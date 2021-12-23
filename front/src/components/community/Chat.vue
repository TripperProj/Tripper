<template>
  <div>
    <div class="chat-box">
      <div class="chat-bos-header">채팅</div>
      <div class="chat-box-body">
        <div>
          <span v-bind:key="idx" v-for="(msg, idx) in msgList">{{ msg }}</span>
        </div>
      </div>
      <div class="caht-input">
        <form @submit.prevent="send">
          <input type="text" placeholder="메세지를 입력해주세요" />
          <button>send</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import Stomp from "webstomp-client";
import SockJS from "sockjs-client";

export default {
  data() {
    return {
      memid: "",
      message: "",
      connected: false,
      msgList: [],
    };
  },
  created() {
    this.connect();
  },
  methods: {
    connect() {
      const serverURL = "http://localhost:8089/stomp/chat";
      let socket = new SockJS(serverURL);
      this.stompClient = Stomp.over(socket);
      console.log(`소켓 연결 시도 ${serverURL}`);
      let headers = { Authorization: `Bearer ${this.$store.getters.getToken}` };
      this.stompClient.connect(
        headers,
        (frame) => {
          this.connected = true;
          console.log("연결", frame);
          this.stompClient.subscribe(`/sub/chat/room/1`, (res) => {
            console.log("구독", res.body);
            this.msgList.push(JSON.parse(res.body));
          });
        },
        (error) => {
          console.log(error);
          this.connected = false;
        }
      );
    },
    send() {
      console.log("메세지 전송");
      if (this.stompClient && this.stompClient.connected) {
        const msg = this.message;
        this.stompClient.send("/", JSON.stringify(msg), {});
      }
    },
  },
};
</script>

<style scoped>
.chat-box {
  background: #efefef;

  width: 350px;
  max-width: 85vw;
  max-height: 100vh;
  border-radius: 5px;
  box-shadow: 0px 5px 35px 9px #ccc;
}
.chat-box-header {
  background: #5a5eb9;
  height: 70px;
  border-top-left-radius: 5px;
  border-top-right-radius: 5px;
  color: white;
  text-align: center;
  font-size: 20px;
  padding-top: 17px;
}
.chat-box-body {
  position: relative;
  height: 370px;
  border: 1px solid #ccc;
}
</style>
