package com.tripper.service;

import com.tripper.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author HanJiyoung
 * 사진 관련 서비스 클래스
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;

    public void deletePhotoById(Long photo_id) {
        photoRepository.deleteById(photo_id);
    }
}
