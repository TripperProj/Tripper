module.exports = {
  devServer: {
    proxy: {
      "/login": {
        target: process.env.VUE_APP_API_URL,
      },
      "/user": {
        target: process.env.VUE_APP_API_URL,
      },
      "/board": {
        target: process.env.VUE_APP_API_URL,
      },
    },
  },
};
