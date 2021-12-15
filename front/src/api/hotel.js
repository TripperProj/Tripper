import { instance } from "./index";

function hotelList(searchCondition) {
  return instance.post("/hotel", searchCondition);
}
function dibsHotel(userId, hotelName) {
  return instance.post("/hotelDibs", userId, hotelName);
}

export { hotelList, dibsHotel };
