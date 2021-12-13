export default {
  isLogin: (state) => {
    return state.loginSuccess;
  },
  userId: (state) => {
    return state.userId;
  },
  getToken: (state) => {
    return state.token;
  },
};
