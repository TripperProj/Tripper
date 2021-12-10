import axios from "axios";

function createInstance() {
  const instance = axios.create({});
  return instance;
}

// function createInstanceWithUrl(url) {
//   const instance = axios.create({
//     baseURL: `${process.env.VUE_APP_API_URL}${url}`,
//   });
//   return instance;
// }

const instance = createInstance();

export { instance };
