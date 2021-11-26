import { instance } from "./index";

function createPost(postData) {
  return instance.post("/board/create", postData);
}
function deletePost(postData) {
  return instance.delete("/board/delete", postData);
}
function editPost(postData) {
  return instance.put("/board/edit", postData);
}
function postList() {
  return instance.get("/board/list");
}

export { createPost, deletePost, editPost, postList };
