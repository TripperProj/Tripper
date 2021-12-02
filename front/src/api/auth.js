import { instance } from "./index.js";

function loginUser(formData) {
  let data;
  instance
    .post(`/login`, formData)
    .then((response) => {
      console.log(response);
      data = response;
      localStorage.setItem("jwtToken", response.data.jwttoken);
      instance.defaults.headers.common["Authorization"] =
        response.data.jwttoken;
    })
    .catch((error) => {
      console.log("auth_api_error" + error);
    });
  return data;
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
