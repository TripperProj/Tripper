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
//
//    /**
//     * 호텔 사진 삭제
//     */
//    public void deletePhotoByHotelId(Long hotel_id) {
//
//        /* 호텔에 해당하는 사진들 삭제 */
//        photoRepository.deleteByHotelId(hotel_id);
//
//    }
//
    public void deletePhotoById(Long photo_id) {
        photoRepository.deleteById(photo_id);
    }
//
//    /**
//     * 객실 사진 삭제
//     */
//    public void deletePhotoByRoomTypeId(Long roomtype_id) {
//
//        photoRepository.deleteByRoomTypeId(roomtype_id);
//    }
}
