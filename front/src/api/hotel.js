import { instance } from "./index";

function hotelList() {
  return instance.get("hotels");
}
// function hotelList(searchCondition) {
//   return instance.post("", searchCondition);
// }
function dibsHotel(userId, hotelName) {
  return instance.post("/hotelDibs", userId, hotelName);
}
function hotelInfo(hotelId) {
  return instance.get(`/hotel/${hotelId}`);
}
export { hotelList, dibsHotel, hotelInfo };
