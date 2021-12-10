import { instance } from "./index.js";
import store from "@/store/index";

async function loginUser(formData) {
  const response = await instance.post("/login", formData);
  console.log(response.data.token);
  store.state.token = response.data.token;
  instance.defaults.headers.common[
    "Authorization"
  ] = `Bearer ${store.state.token}`;
  return;
}
function signupUser(userData) {
  console.log(userData);
  const response = instance.post("/user/signup", userData);
  return response.code;
}
function socialSignupUser(userData) {
  console.log(userData);
  return instance.post("/user/socicalSignup", userData);
}
function certUserEmail(Email) {
  return instance.post("/user/certEmail", Email);
}
function userIdCheck(userId) {
  return instance.get("/user/checkExists", userId);
}

export { loginUser, signupUser, certUserEmail, userIdCheck, socialSignupUser };
