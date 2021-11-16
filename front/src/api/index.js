import axios from "axios";

const instance = axios.create({
  baseURL: process.env.VUE_APP_API_URL,
});
function loginUser(userData) {
  return axios.post(instance.url, userData);
}
function signUpUser(userData) {
  return axios.post(instance.url, userData);
}
function certipicationUserPhone(phoneNum, certNum) {
  return axios.post(instance.url, phoneNum, certNum);
}
export { loginUser, signUpUser, certipicationUserPhone };
