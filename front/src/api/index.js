import axios from "axios";
import { setInterceptors } from "./common/interceptors.js";

function createInstance() {
  return axios.create({
    baseURL: process.env.VUE_APP_API_URL,
  });
}
function createInstanceWithAuth(url) {
  const instance = axios.create({
    baseURL: `${process.env.VUE_APP_API_URL}${url}`,
    headers: {
      Authorization: "",
    },
  });
  return setInterceptors(instance);
}
const instance = createInstance();
const board = createInstanceWithAuth("/board");

export { instance, board };
