import { instance } from "./index";

function reservationCreate(hotelId, roomId, rsvtInfo) {
  return instance.post(`/hotel/reservation/${hotelId}/${roomId}`, rsvtInfo);
}

export { reservationCreate };
