import { instance } from "./index";

function loginUser(userData) {
  console.log(userData);
  return instance.post("login", userData);
}
function signupUser(userData) {
  console.log(userData);
  return instance.post(instance.url, userData);
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
