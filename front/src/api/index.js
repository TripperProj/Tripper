import axios from "axios";

const instance = axios.create({
  baseURL: process.env.VUE_APP_API_URL,
});
function loginUser(userData) {
  console.log(userData);
  return axios.post("login", userData);
}
function signupUser(userData) {
  console.log(userData);
  return axios.post(instance.url, userData);
}
function certUserEmail(Email, certNum) {
  return axios.post(instance.url, Email, certNum);
}
function userIdCheck(userId) {
  return axios.post(instance.url, userId);
}
export { loginUser, signupUser, certUserEmail, userIdCheck };
