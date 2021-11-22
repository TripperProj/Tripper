import { instance } from "./index";

function hotelList(location) {
  return instance.post("/hotel", location);
}

export { hotelList };
