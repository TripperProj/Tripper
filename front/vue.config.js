module.exports = {
  devServer: {
    proxy: {
      "/login": {
        target: process.env.VUE_APP_API_URL,
        changeOrigin: true,
      },
      "/user": {
        target: "http://localhost:8080",
        changeOrigin: true,
      },
      "/board": {
        target: process.env.VUE_APP_API_URL,
        changeOrigin: true,
      },
    },
  },
};
