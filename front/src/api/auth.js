import { instance } from "./index";

function loginUser(userData) {
  console.log(userData);
  return instance.post(`/login`, userData);
}
function signupUser(infoDto) {
  console.log(infoDto);
  return instance.post(`http://localhost:8080/user`, infoDto);
}
function socialSignupUser(userData) {
  console.log(userData);
  return instance.post(instance.url, userData);
}

function certUserEmail(Email, certNum) {
  return instance.post(instance.url, Email, certNum);
}
function userIdCheck(userId) {
  return instance.post(instance.url, userId);
}

export { loginUser, signupUser, certUserEmail, userIdCheck, socialSignupUser };
