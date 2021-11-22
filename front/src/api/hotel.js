import { instance } from "./index";

function hotelList(searchCondition) {
  return instance.post("/hotel", searchCondition);
}

export { hotelList };
