import { instance } from "./index";

function loadHotel() {
  return instance.get("/hotels");
}
function createHotel(address, hotelName) {
  return instance.post(`/admin/hotel`, address, hotelName);
}
function deleteHotel(hotelId, hotelInfo) {
  return instance.delete(`admin/hotel/${hotelId}`, hotelInfo);
}
function updateHotel(hotelId, hotelInfo) {
  return instance.put(`admin/hotel/${hotelId}`, hotelInfo);
}
function createRoom(roomInfo) {
  return instance.post(`/admin/roomtype`, roomInfo);
}
function deleteRoom(roomId) {
  return instance.delete(`/admin/roomtype/${roomId}`);
}
function updateRoom(roomTypeId, roomInfo) {
  return instance.put(`/admin/roomtype/${roomTypeId}`, roomInfo);
}
function hotelReservation(hotelId) {
  return instance.get(`/admin/reservations/hotel/${hotelId}`);
}
function roomReservation(roomId) {
  return instance.get(`/admin/reservations/room/${roomId}`);
}
export {
  loadHotel,
  createHotel,
  deleteHotel,
  updateHotel,
  createRoom,
  deleteRoom,
  updateRoom,
  hotelReservation,
  roomReservation,
};
