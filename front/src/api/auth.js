import { instance } from "./index.js";

async function loginUser(formData) {
  const response = await instance.post("/login", formData);
  instance.defaults.headers.common[
    "Authorization"
  ] = `Bearer ${response.data.token}`;
  return response;
}
function signupUser(userData) {
  console.log(userData);
  const response = instance.post("/signup", userData);
  return response.code;
}
function socialSignupUser(userData) {
  console.log(userData);
  return instance.post("/user/socicalSignup", userData);
}
function certUserEmail(Email) {
  return instance.post("/email-auth", Email);
}
function certNumCheck(certNum) {
  return instance.put(`/email-auth`, certNum);
}
function userIdCheck(userId) {
  return instance.get("/user/checkExists", userId);
}
function userLogout() {
  return instance.post("/user/logout");
}

export {
  loginUser,
  signupUser,
  certUserEmail,
  userIdCheck,
  socialSignupUser,
  certNumCheck,
  userLogout,
};
