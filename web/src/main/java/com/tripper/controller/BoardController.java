package com.tripper.controller;

import com.tripper.dto.request.CreateBoardDto;
import com.tripper.domain.board.Board;
import com.tripper.dto.response.GetBoardListDto;
import com.tripper.service.BoardService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


/**
 * @author HanJiyoung
 * '여행 메이트 찾기 게시판' 기능 컨트롤러 클래스
 */
@Api(tags = "여행메이트 찾기 게시판 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @ApiOperation(
            value = "게시글 등록"
            , notes = "게시글 폼에서 입력한 정보로 글 등록을 실행한다.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "ok: 글 등록 성공.") })
    @PostMapping("/create")
    public String create(Authentication authentication,
                         @RequestBody @ApiParam(value = "게시글 폼에 입력한 정보를 담고있는 객체") CreateBoardDto dto,
                         BindingResult result) {

        if (result.hasErrors()) {
            return "boardForm";
        }

        /* 현재 로그인한 사용자 정보 가져오기 */
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();

        /* 글 등록하기 */
        boardService.registerPost(dto, userDetails.getUsername());

        return "ok";

    }

    @ApiOperation(
            value = "게시글 목록"
            , notes = "db에서 게시글 목록을 가져온 후 뷰페이지로 넘겨준다.")
    @GetMapping("/list")
    public ResponseEntity<GetBoardListDto> getBoardList() {
        GetBoardListDto boards = boardService.findAllBoards();
        return ResponseEntity.ok().body(boards);

    }

    @ApiOperation(
            value = "게시글 상세 페이지로 이동"
            , notes = "db에서 게시글 목록을 가져온 후 뷰페이지로 넘겨준다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/post/{board_id}")
    public String goBoardPost(@PathVariable("board_id") @ApiParam(value = "조회할 게시글의 고유 id") Long board_id,
                              Model model) {

        Board board = boardService.findByBoardId(board_id);
        model.addAttribute("board", board);

        return "boardPost";

    }

    @ApiOperation(
            value = "좋아요수 증가"
            , notes = "해당 게시글의 좋아요수를 증가시킨다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/post/{board_id}/addLikes")
    public String addLikes(@PathVariable("board_id") @ApiParam(value = "좋아요수를 증가시킬 게시글의 고유 id") Long board_id) {

        boardService.addLikes(board_id);

        return "redirect:/board/post/{board_id}";

    }

    @ApiOperation(
            value = "좋아요수 감소"
            , notes = "해당 게시글의 좋아요수를 감소시킨다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/post/{board_id}/subtractLikes")
    public String subtractLikes(@PathVariable("board_id") @ApiParam(value = "좋아요수를 감소시킬 게시글의 고유 id") Long board_id) {

        boardService.subtractLikes(board_id);

        return "redirect:/board/post/{board_id}";

    }

    @ApiOperation(
            value = "게시글 수정 폼으로 이동"
            , notes = "게시글 수정 폼으로 이동한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/post/{board_id}/update")
    public String updatePost(@PathVariable("board_id") @ApiParam(value = "수정할 게시글의 고유 id") Long board_id,
                             Model model) {

        Board board = boardService.findByBoardId(board_id);

        CreateBoardDto form = new CreateBoardDto();
        form.setTitle(board.getTitle());
        form.setDestination(board.getDestination());
        form.setRecruitment(board.getRecruitment());
        form.setContent(board.getContent());

        model.addAttribute("form", form);
        model.addAttribute("board_id", board_id);

        return "boardUpdateForm";
    }

    @ApiOperation(
            value = "게시글 수정"
            , notes = "게시글 수정을 실행한다.")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/post/{board_id}/update")
    public String updateItem(@PathVariable @ApiParam(value = "수정할 게시글의 고유 id") Long board_id,
                             @RequestBody @ApiParam(value = "게시글 수정 폼에 입력한 정보를 갖고있는 객체") @ModelAttribute("form") CreateBoardDto dto) {

        boardService.updatePost(board_id, dto.getTitle(), dto.getDestination(), dto.getRecruitment(), dto.getContent());

        return "redirect:/board/post/{board_id}";

    }

    @ApiOperation(
            value = "게시글 삭제"
            , notes = "게시글 삭제를 실행한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/post/{board_id}/delete")
    public String deletePost(@PathVariable("board_id") @ApiParam(value = "삭제할 게시글의 고유 id") Long board_id) {

        boardService.deletePostById(board_id);

        return "boardList";

    }

}
