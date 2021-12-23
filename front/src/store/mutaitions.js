export default {
  SET_TOKEN(state, token) {
    state.token = token;
  },
  SET_USER_LOGIN(state, memId) {
    state.loginSuccess = true;
    state.memId = memId;
  },
  SET_USER_ROLE(state, userRole) {
    state.userRole = userRole;
  },
  SET_LOGOUT(state) {
    state.token = "";
    state.loginSuccess = false;
    state.memId = "";
    state.userRole = "";
  },
};
