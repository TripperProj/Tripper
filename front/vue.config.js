module.exports = {
  devServer: {
    proxy: {
      "/user": {
        target: "http://localhost:8080",
      },
    },
  },
};
