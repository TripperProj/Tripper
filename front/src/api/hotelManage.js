import { instance } from "./index";

function loadHotel(managerID) {
  return instance.get("", managerID);
}
function createHotel(address, hotelName) {
  return instance.post(`/admin/hotel`, address, hotelName);
}
function createRoom(hotelId, roomInfo) {
  return instance.post(`/hotel/create/${hotelId}`, hotelId, roomInfo);
}
function deleteRoom(hotelId, roomId) {
  return instance.post(`/hotel/delete/${hotelId}/${roomId}`);
}

export { loadHotel, createHotel, createRoom, deleteRoom };
