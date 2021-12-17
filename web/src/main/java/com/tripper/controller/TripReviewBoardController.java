package com.tripper.controller;

import com.tripper.dto.request.board.CreateCommentDto;
import com.tripper.dto.request.board.CreateTripReviewBoardDto;
import com.tripper.dto.request.board.UpdateTripReviewBoardDto;
import com.tripper.dto.response.board.GetFindMateBoardDto;
import com.tripper.dto.response.board.GetTripReviewBoardDto;
import com.tripper.dto.response.board.GetTripReviewHeaderListDto;
import com.tripper.service.boardservice.TripReviewBoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(tags = "여행후기 게시판 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/board/trip-review")
public class TripReviewBoardController {

    private final TripReviewBoardService boardService;

    @ApiOperation(value = "게시물 생성", notes = "여행 후기 리뷰 게시물을 헤더들을 조회한다.")
    @PostMapping
    public ResponseEntity create(Authentication authentication, @RequestBody CreateTripReviewBoardDto dto) {
        /* 현재 로그인한 사용자 정보 가져오기 */
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();

        /* 글 등록하기 */
        boardService.registerPost(dto, userDetails.getUsername());

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "게시물 헤더 리스트 조회", notes = "여행 후기 리뷰 게시물을 헤더들을 조회한다.")
    @GetMapping
    public ResponseEntity<GetTripReviewHeaderListDto> getBoardList() {
        GetTripReviewHeaderListDto boards = boardService.findAllBoards();

        return ResponseEntity.ok().body(boards);
    }

    @ApiOperation(value = "특정 게시물 조회", notes = "boardId와 일치하는 게시물을 조회한다.")
    @GetMapping("/{boardId}")
    public ResponseEntity<GetTripReviewBoardDto> getBoardList(@PathVariable("boardId") Long boardId) {
        GetTripReviewBoardDto board = boardService.findByBoardId(boardId);

        return ResponseEntity.ok().body(board);
    }

    @ApiOperation(value = "좋아요수 증가", notes = "해당 게시글의 좋아요수를 증가시킨다.")
    @PostMapping("/{boardId}/addLikes")
    public ResponseEntity<GetFindMateBoardDto> addLikes(@ApiParam(value = "좋아요수를 증가시킬 게시글의 고유 id") @PathVariable("boardId") Long boardId) {

        boardService.addLikes(boardId);

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "좋아요수 감소", notes = "해당 게시글의 좋아요수를 감소시킨다.")
    @PostMapping("/{boardId}/subtractLikes")
    public ResponseEntity subtractLikes(@ApiParam(value = "좋아요수를 감소시킬 게시글의 고유 id") @PathVariable("boardId") Long boardId) {

        boardService.subtractLikes(boardId);

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "게시글 수정", notes = "게시글 수정을 실행한다.")
    @PutMapping("/{boardId}")
    public ResponseEntity updateItem(@ApiParam(value = "수정할 게시글의 고유 id") @PathVariable("boardId") Long boardId,
                                     @ApiParam(value = "게시글 수정 폼에 입력한 정보를 갖고있는 객체") @RequestBody UpdateTripReviewBoardDto dto) {

        boardService.updatePost(boardId, dto);

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "게시글 삭제", notes = "게시글 삭제를 실행한다.")
    @DeleteMapping("/{boardId}")
    public ResponseEntity deletePost(@ApiParam(value = "삭제할 게시글의 고유 id") @PathVariable("boardId") Long boardId) {

        boardService.deletePostById(boardId);

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "댓글 추가", notes = "게시글에 댓글을 추가한다")
    @PostMapping("/{boardId}/comment")
    public ResponseEntity addComment(@RequestBody CreateCommentDto dto,
                                     @PathVariable("boardId") Long boardId, Authentication authentication) {
        /* 현재 로그인한 사용자 정보 가져오기 */
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();

        /* 글 등록하기 */
        boardService.addComment(dto, boardId, userDetails.getUsername());

        return ResponseEntity.ok().build();
    }
}
