package com.tripper.service;

import com.tripper.domain.board.BoardForm;
import com.tripper.domain.board.BoardInfo;
import com.tripper.domain.user.UserInfo;
import com.tripper.domain.user.UserRepository;
import com.tripper.repository.BoardRepository;
import com.tripper.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final UserRepository userRepository;

    /**
     * 글 등록
     */
    public Long registerPost(BoardForm form, String memId) {

        /* 엔티티 조회 */
        List<UserInfo> users = memberRepository.findByMemId(memId);
        UserInfo user = users.get(0);

        /* 글 생성*/
        BoardInfo boardinfo = BoardInfo.createBoard(form, user);

        /* 글 저장 */
        boardRepository.save(boardinfo);

        return boardinfo.getId();
    }
}
