module.exports = {
  devServer: {
    proxy: {
      "/login": {
        target: "http://localhost:8089",
        changeOrigin: true,
        secure: false,
        logLevel: "debug",
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
