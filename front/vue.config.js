module.exports = {
  devServer: {
    port: 8900,
    proxy: {
      "/login": {
        target: "http://localhost:8089",
        changeOrigin: true,
        secure: false,
        logLevel: "debug",
      },
      "/user": {
        target: process.env.VUE_APP_API_URL,
        changeOrigin: true,
      },
      "/board": {
        target: process.env.VUE_APP_API_URL,
      },
    },
  },
};
