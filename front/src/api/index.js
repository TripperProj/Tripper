import axios from "axios";

function createInstance(url) {
  const instance = axios.create({
    baseUrl: `http://localhost:8080${url}`,
  });
  return instance;
}
const instance = createInstance();

export { instance };
