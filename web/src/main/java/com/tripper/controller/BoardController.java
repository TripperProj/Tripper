package com.tripper.controller;

import com.tripper.domain.board.BoardForm;
import com.tripper.domain.board.BoardInfo;
import com.tripper.domain.user.UserInfo;
import com.tripper.service.BoardService;
import com.tripper.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;
    private final UserService userService;

    /**
     * 게시판 글 작성 폼으로 이동하는 함수
     * @param authentication
     * @param model
     * @return
     */
    @GetMapping("/board/create")
    public String goBoardForm(Authentication authentication, Model model) {

        /* 현재 로그인한 사용자 정보 가져오기 */
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        String username = userDetails.getUsername();

        UserInfo user = userService.findUserByMemId(username);
        model.addAttribute("user", user);

        return "boardForm";
    }

    /**
     * 작성한 게시글을 db에 저장하는 함수
     * @param form 게시글 폼에서 입력한 데이터가 저장돼있는 객체
     * @param result
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/board/create")
    public String create(@RequestBody BoardForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "boardForm";
        }

        /* 현재 로그인한 사용자 정보 가져오기 */
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String memId = ((UserDetails) principal).getUsername();
        log.info("현재 사용자 아이디: " + memId);

        boardService.registerPost(form, memId);

        return "redirect:/";

    }

    /**
     * 게시판 글 목록을 가져온 후 model에 담아서 뷰페이지로 넘겨주는 함수
     * @param model 뷰로 넘겨줄 모델 객체
     * @return 게시판 글 목록 페이지
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/board/list")
    public String getBoardList(Model model) {

        List<BoardInfo> boards = boardService.findAllBoards();
        model.addAttribute("boards", boards);

        return "boardList";

    }

    /**
     * 게시판 목록에서 글 하나 클릭하면 해당 글 상세 페이지로 이동하는 함수
     * @param model 뷰로 넘겨줄 모델 객체
     * @return 해당 글 상세 페이지
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/board/post/{board_id}")
    public String goBoardPost(@PathVariable("board_id") Long board_id, Model model) {

        BoardInfo board = boardService.findByBoardId(board_id);
        model.addAttribute("board", board);

        return "boardPost";

    }

    /**
     * 좋아요수 증가 함수
     * @param board_id 게시글 고유 아이디
     * @return 해당 게시글 상세 페이지
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/board/post/{board_id}/addLikes")
    public String addLikes(@PathVariable("board_id") Long board_id) {

        boardService.addLikes(board_id);

        return "redirect:/board/post/{board_id}";

    }

    /**
     * 좋아요수 감소 함수
     * @param board_id 게시글 고유 아이디
     * @return 해당 게시글 상세 페이지
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/board/post/{board_id}/subtractLikes")
    public String subtractLikes(@PathVariable("board_id") Long board_id) {

        boardService.subtractLikes(board_id);

        return "redirect:/board/post/{board_id}";

    }

    /**
     * 게시글 수정 폼으로 이동하는 함수
     * @param board_id 게시글 고유 아이디
     * @return 게시글 폼
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/board/post/{board_id}/update")
    public String updatePost(@PathVariable("board_id") Long board_id, Model model) {

        BoardInfo boardInfo = boardService.findByBoardId(board_id);

        BoardForm form = new BoardForm();
        form.setTitle(boardInfo.getTitle());
        form.setDestination(boardInfo.getDestination());
        form.setRecruitment(boardInfo.getRecruitment());
        form.setContent(boardInfo.getContent());

        model.addAttribute("form", form);
        model.addAttribute("board_id", board_id);

        return "boardUpdateForm";
    }

    /**
     * 게시글을 수정하는 함수
     * @param board_id 게시글 고유 아이디
     * @return 해당 게시글 상세 페이지
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/board/post/{board_id}/update")
    public String updateItem(@PathVariable Long board_id, @RequestBody @ModelAttribute("form") BoardForm form) {

        boardService.updatePost(board_id, form.getTitle(), form.getDestination(), form.getRecruitment(), form.getContent());

        return "redirect:/board/post/{board_id}";

    }

    /**
     * 게시글 삭제하는 함수
     * @param board_id 게시글 고유 아이디
     * @return 게시판 글 목록 페이지
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/board/post/{board_id}/delete")
    public String deletePost(@PathVariable("board_id") Long board_id) {

        boardService.deletePostById(board_id);

        return "boardList";

    }

}
