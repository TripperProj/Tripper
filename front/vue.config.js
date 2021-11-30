module.exports = {
  devServer: {
    port: 8900,
    proxy: {
      "/login": {
        target: process.env.VUE_APP_API_URL,
        changeOrigin: true,
      },
      "/user": {
        target: process.env.VUE_APP_API_URL,
        changeOrigin: true,
      },
      "/board": {
        target: process.env.VUE_APP_API_URL,
        changeOrigin: true,
      },
    },
  },
};
