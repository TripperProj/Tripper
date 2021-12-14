import { instance } from "./index";

function createPost(postData) {
  return instance.post("/post/create", postData);
}
function deletePost(postData) {
  return instance.request("/post/delete", postData);
}
function editPost(postData) {
  return instance.post("/post/edit", postData);
}
function postList() {
  return instance.get("/list");
}

export { createPost, deletePost, editPost, postList };
