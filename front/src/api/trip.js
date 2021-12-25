import { instance } from "./index";

function tripList(userId) {
  return instance.get("/trips", userId);
}

function createTrip(tripData) {
  return instance.post("/trips", tripData);
}

function deleteTrip(tripId) {
  return instance.delete("/trips", tripId);
}

export { tripList, createTrip, deleteTrip };
