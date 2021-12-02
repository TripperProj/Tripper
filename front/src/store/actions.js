import { fetchBoardList } from "@/api/board";

export default {
  FETCH_BOARD(context) {
    fetchBoardList()
      .then((response) => {
        console.log(response.data);
        context.commit("SET_BOARD", response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  },
};
