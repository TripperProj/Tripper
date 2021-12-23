export default {
  isLogin: (state) => {
    return state.loginSuccess;
  },
  getMemId: (state) => {
    return state.memId;
  },
  getToken: (state) => {
    return state.token;
  },
  getRole: (state) => {
    return state.userRole;
  },
};
