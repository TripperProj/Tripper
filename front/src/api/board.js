import { board } from "./index";

function createPost(postData) {
  return board.post("/create", postData);
}
function deletePost(postId) {
  return board.delete(`/post/${postId}/delete`);
}
function infoPost(postId) {
  return board.get(`/post/${postId}`);
}
function editPost(postData) {
  return board.post(`/post/${postData.postId}/update`, postData);
}
function fetchBoardList() {
  return board.get(`/list`);
}
function addLikes(boardId) {
  return board.get(`/post/${boardId}/addLikes`);
}
function subLikes(boardId) {
  return board.get(`/post/${boardId}/subtrackLikes`);
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
