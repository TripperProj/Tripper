import { instance } from "./index";
// import Stomp from "webstomp-client";
// import SockJS from "sockjs-client";

function enterChat(boardId) {
  instance.post(`/board/chat/enter/${boardId}`);
}

export { enterChat };
