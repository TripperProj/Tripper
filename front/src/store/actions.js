export default {
  setToken(context, token) {
    context.commit("SET_TOKEN", token);
  },
  loginSuccess(context, memId) {
    context.commit("SET_USER_LOGIN", memId);
  },
  setUserRole(context, userRole) {
    context.commit("SET_USER_ROLE", userRole);
  },
  setLogout(context) {
    context.commit("SET_LOGOUT");
  },
};
