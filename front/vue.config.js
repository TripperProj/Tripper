module.export = {
  devserver: {
    proxy: {
      "/user": {
        target: "http://localhost:8080",
        changeOrigin: true,
        logLevel: "debug",
      },
    },
  },
};
