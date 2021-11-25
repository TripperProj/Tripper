import axios from "axios";

function createInstance() {
  const instance = axios.create({});
  return instance;
}
const instance = createInstance();

export { instance };
