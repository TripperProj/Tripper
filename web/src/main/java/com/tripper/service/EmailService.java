package com.tripper.service;

import com.tripper.domain.board.BoardInfo;
import com.tripper.domain.user.UserInfo;
import com.tripper.domain.user.UserRepository;
import com.tripper.dto.EmailDto;
import com.tripper.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class EmailService {

    private final MemberRepository memberRepository;
    private final UserRepository userRepository;
    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "본인 이메일 주소 입력";

    /**
     * 전송할 메일 생성 + 이메일 인증코드 저장 하는 함수
     * @param email
     * @param memId
     * @return
     */
    public EmailDto createMailAndChangeAuth(String email, String memId) {

        String authCode = getTempCode();
        EmailDto emailDto = new EmailDto();
        emailDto.setAddress(email);
        emailDto.setTitle(memId+"님의 회원 인증 메일입니다.");
        emailDto.setMessage("인증 코드는" + authCode + " 입니다.");
        updateEmailAuthCode(authCode, memId);

        return emailDto;
    }

    /**
     * 회원 db의 이메일 인증코드 저장하는 함수
     * @param authCode
     * @param memId
     */
    @Transactional
    public void updateEmailAuthCode(String authCode, String memId){

        /* 엔티티 조회 */
        UserInfo userInfo = memberRepository.findUserByMemId(memId);

        /* 인증코드 저장 */
        userInfo.setEmailAuthCode(authCode);
        userRepository.save(userInfo);

    }

    /**
     * 인증 코드 생성해주는 함수
     * @return
     */
    public String getTempCode(){

        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }

        return str;

    }

    /**
     * 이메일 보내는 함수
     * @param emailDto
     */
    public void mailSend(EmailDto emailDto) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailDto.getAddress());
        message.setFrom(EmailService.FROM_ADDRESS);
        message.setSubject(emailDto.getTitle());
        message.setText(emailDto.getMessage());
        log.info("이메일 전송 완료");

        mailSender.send(message);

    }

    /**
     * 회원이 입력한 인증 코드와 db의 인증코드가 일치하는지 확인하는 함수
     * @param authCode
     * @param memId
     * @return
     */
    public String checkEmailAuthCode(String authCode, String memId) {

        String rslt = memberRepository.checkEmailAuthCode(authCode, memId);

        return rslt;

    }
}
