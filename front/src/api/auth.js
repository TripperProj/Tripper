import { instance } from "./index.js";

let authNum = Math.floor(Math.random() * 100000);

function loginUser(form) {
  console.log(form);
  return instance.post(`/login`, form);
}
function signupUser(userData) {
  console.log(userData);
  return instance.post("/signup", userData);
}
function socialSignupUser(userData) {
  console.log(userData);
  return instance.post("/user/socicalSignup", userData);
}
function certUserEmail(Email) {
  return instance.post("/user/certEmail", { email: Email, certNum: authNum });
}
function userIdCheck(userId) {
  return instance.post("/user/idCheck", userId);
}

export { loginUser, signupUser, certUserEmail, userIdCheck, socialSignupUser };
