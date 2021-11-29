package com.tripper.controller;

import com.tripper.domain.board.BoardForm;
import com.tripper.domain.board.BoardInfo;
import com.tripper.service.BoardService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author HanJiyoung
 * '여행 메이트 찾기 게시판' 기능 컨트롤러 클래스
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @ApiOperation(
            value = "게시글 작성 폼으로 이동"
            , notes = "게시글 작성 폼으로 이동한다.")
    @GetMapping("/board/create")
    public String goBoardForm() {
        return "boardForm";
    }

    @ApiOperation(
            value = "게시글 등록"
            , notes = "게시글 폼에서 입력한 정보로 글 등록을 실행한다.")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/board/create")
    public String create(Authentication authentication,
                         @RequestBody @ApiParam(value = "게시글 폼에 입력한 정보를 담고있는 객체") BoardForm form,
                         BindingResult result) {

        if (result.hasErrors()) {
            return "boardForm";
        }

        /* 현재 로그인한 사용자 정보 가져오기 */
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();

        /* 글 등록하기 */
        boardService.registerPost(form, userDetails.getUsername());

        return "redirect:/";

    }

    @ApiOperation(
            value = "게시글 목록"
            , notes = "db에서 게시글 목록을 가져온 후 뷰페이지로 넘겨준다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/board/list")
    public String getBoardList(Model model) {

        List<BoardInfo> boards = boardService.findAllBoards();
        model.addAttribute("boards", boards);

        return "boardList";

    }

    @ApiOperation(
            value = "게시글 상세 페이지로 이동"
            , notes = "db에서 게시글 목록을 가져온 후 뷰페이지로 넘겨준다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/board/post/{board_id}")
    public String goBoardPost(@PathVariable("board_id") @ApiParam(value = "조회할 게시글의 고유 id") Long board_id,
                              Model model) {

        BoardInfo board = boardService.findByBoardId(board_id);
        model.addAttribute("board", board);

        return "boardPost";

    }

    @ApiOperation(
            value = "좋아요수 증가"
            , notes = "해당 게시글의 좋아요수를 증가시킨다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/board/post/{board_id}/addLikes")
    public String addLikes(@PathVariable("board_id") @ApiParam(value = "좋아요수를 증가시킬 게시글의 고유 id") Long board_id) {

        boardService.addLikes(board_id);

        return "redirect:/board/post/{board_id}";

    }

    @ApiOperation(
            value = "좋아요수 감소"
            , notes = "해당 게시글의 좋아요수를 감소시킨다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/board/post/{board_id}/subtractLikes")
    public String subtractLikes(@PathVariable("board_id") @ApiParam(value = "좋아요수를 감소시킬 게시글의 고유 id") Long board_id) {

        boardService.subtractLikes(board_id);

        return "redirect:/board/post/{board_id}";

    }

    @ApiOperation(
            value = "게시글 수정 폼으로 이동"
            , notes = "게시글 수정 폼으로 이동한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/board/post/{board_id}/update")
    public String updatePost(@PathVariable("board_id") @ApiParam(value = "수정할 게시글의 고유 id") Long board_id,
                             Model model) {

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

    @ApiOperation(
            value = "게시글 수정"
            , notes = "게시글 수정을 실행한다.")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/board/post/{board_id}/update")
    public String updateItem(@PathVariable @ApiParam(value = "수정할 게시글의 고유 id") Long board_id,
                             @RequestBody @ApiParam(value = "게시글 수정 폼에 입력한 정보를 갖고있는 객체") @ModelAttribute("form") BoardForm form) {

        boardService.updatePost(board_id, form.getTitle(), form.getDestination(), form.getRecruitment(), form.getContent());

        return "redirect:/board/post/{board_id}";

    }

    @ApiOperation(
            value = "게시글 삭제"
            , notes = "게시글 삭제를 실행한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/board/post/{board_id}/delete")
    public String deletePost(@PathVariable("board_id") @ApiParam(value = "삭제할 게시글의 고유 id") Long board_id) {

        boardService.deletePostById(board_id);

        return "boardList";

    }

}
