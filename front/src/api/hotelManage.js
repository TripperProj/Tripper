import { instance } from "./index";

function loadHotel(managerID) {
  return instance.get("", managerID);
}
function createHotel() {
  return instance.post("/hotelManage/");
}
function createRoom(hotelId) {
  return instance.post("", hotelId);
}

export { loadHotel, createHotel, createRoom };
