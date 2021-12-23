import { instance } from "./index";

function createReview(reviewInfo) {
  return instance.post("/review/create", reviewInfo);
}

export { createReview };
