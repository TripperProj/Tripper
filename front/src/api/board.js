import { instance } from "./index";

function createPost(postData) {
  return instance.post("/board/find-mate", postData);
}
function deletePost(postId) {
  return instance.delete(`/board/find-mate/${postId}`);
}
function infoPost(postId) {
  return instance.get(`/board/find-mate/${postId}`);
}
function editPost(postData) {
  return instance.put(`/board/find-mate/${postData.id}`, postData);
}
async function fetchBoardList() {
  const response = await instance.get("/board/find-mate");
  console.log(response);
  return response;
}
function addLikes(instanceId) {
  return instance.post(`/board/find-mate/${instanceId}/addLikes`);
}
function subLikes(instanceId) {
  return instance.post(`/board/find-mate/${instanceId}/subtractLikes`);
}
export {
  createPost,
  deletePost,
  editPost,
  fetchBoardList,
  infoPost,
  addLikes,
  subLikes,
};
