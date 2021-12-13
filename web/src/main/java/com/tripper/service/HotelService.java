package com.tripper.service;

import com.tripper.domain.Photo;
import com.tripper.domain.hotel.Hotel;
import com.tripper.domain.hotel.Room;
import com.tripper.domain.hotel.RoomType;
import com.tripper.domain.user.User;
import com.tripper.dto.request.hotel.*;
import com.tripper.dto.response.hotel.GetCrawlingHotelDto;
import com.tripper.dto.response.hotel.GetHotelDto;
import com.tripper.dto.response.hotel.GetHotelListDto;
import com.tripper.dto.response.hotel.GetRoomDto;
import com.tripper.handler.FileHandler;
import com.tripper.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author HanJiyoung
 * 호텔 관련 서비스 클래스
 */
@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class HotelService {

    private final UserRepository userRepository;
    private final HotelRepository hotelRepository;
    private final PhotoRepository photoRepository;
    private final RoomRepository roomRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final FileHandler fileHandler;
    
    private WebDriver driver;
    public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public static final String WEB_DRIVER_PATH = "C:\\Temp\\driver\\chromedriver.exe";
    private String baseUrl = "https://hotel.naver.com/hotels/main";

    /*---------------------------------    생성    -------------------------------*/
    /**
     * 호텔 등록
     */
    @Transactional
    public Long createHotel(CreateHotelDto createHotelDto, String memId) throws Exception {

        User user = userRepository.findByMemId(memId);
        Hotel hotel = new Hotel();
        hotel.setName(createHotelDto.getName());
        hotel.setAddress(createHotelDto.getAddress());
        hotel.setUser(user);

        List<Photo> photoList = fileHandler.parseFileInfo(createHotelDto.getPhotos());

        // 파일이 존재할 때에만 처리
        if(!photoList.isEmpty()){
            for(Photo photo : photoList) {
                // 파일을 DB에 저장
                hotel.addPhoto(photoRepository.save(photo));
            }
        }

        return hotelRepository.save(hotel).getId();
    }

    /**
     * 객실 등록
     */
    @Transactional
    public void createRoom(Long hotel_id, CreateRoomDto createRoomDto)  throws Exception {

        /* 엔티티 조회 */
        Hotel hotel = hotelRepository.findById(hotel_id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 호텔 입니다."));

        GetHotelDto getHotelDto = new GetHotelDto(hotel);

        /* 객실 분류 저장*/
        RoomType roomType = new RoomType();
        roomType.setName(createRoomDto.getName());
        roomType.setStandardCapacity(createRoomDto.getStandardCapacity());
        roomType.setMaxCapacity(createRoomDto.getMaxCapacity());
        roomType.setHotel(hotel);

        List<Photo> photoList = fileHandler.parseFileInfo(createRoomDto.getPhotos());

        // 파일이 존재할 때에만 처리
        if(!photoList.isEmpty()){
            for(Photo photo : photoList) {
                // 파일을 DB에 저장
                roomType.addPhoto(photoRepository.save(photo));
            }
        }

        roomTypeRepository.save(roomType);

        /* 객실 저장 */
        List<Integer> roomNumList = createRoomDto.getRoomNum();
        if(!roomNumList.isEmpty()) {
            for(Integer i : roomNumList) {
                Room room = new Room();
                room.setRoomType(roomType);
                room.setRoomNum(i);
                room.setPrice(createRoomDto.getPrice());
                roomRepository.save(room);
            }
        }

    }

    /*---------------------------------    조회    -------------------------------*/
    /**
     * 호텔 목록 조회
     */
    public GetHotelListDto findAllHotels() {

        List<Hotel> hotels = hotelRepository.findAll();
        List<GetHotelDto> getHotelDtos = new ArrayList<>();

        for (Hotel hotel : hotels) {
            GetHotelDto getHotelDto = new GetHotelDto(hotel);
            getHotelDtos.add(getHotelDto);
        }

        return new GetHotelListDto(getHotelDtos);
    }

    /**
     * hotel_id로 호텔 1개 조회하는 함수
     */
    public GetHotelDto findByHotelId(Long hotel_id) {

        /* 엔티티 조회 */
        Hotel hotel = hotelRepository.findById(hotel_id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 호텔 입니다."));

        GetHotelDto getHotelDto = new GetHotelDto(hotel);

        return getHotelDto;
    }

    /**
     * roomtype_id로 특정 객실 조회하는 함수
     */
    public GetRoomDto findByRoomTypeId(Long roomtype_id) {

        /* 엔티티 조회 */
        RoomType roomType = roomTypeRepository.findById(roomtype_id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 객실 분류 입니다."));

        GetRoomDto getRoomDto = new GetRoomDto(roomType);

        return getRoomDto;
    }

    /*---------------------------------    수정    -------------------------------*/
    /**
     * 호텔 정보 수정
     */
    @Transactional
    public void updateHotel(Long hotel_id, UpdateHotelDto updateHotelDto) throws Exception {

        /* 엔티티 조회 */
        Hotel hotel = hotelRepository.findById(hotel_id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 호텔 입니다."));

        hotel.setName(updateHotelDto.getName());
        hotel.setAddress(updateHotelDto.getAddress());

        List<Photo> photoList = fileHandler.parseFileInfo(updateHotelDto.getPhotos());

        if(!photoList.isEmpty()){
            for(Photo photo : photoList) {
                hotel.addPhoto(photoRepository.save(photo));
            }
        }

    }

    /**
     * 객실 수정
     */
    @Transactional
    public void updateRoom(Long roomtype_id, UpdateRoomDto updateRoomDto) throws Exception {

        /* 객실 분류 엔티티 조회 */
        RoomType roomType = roomTypeRepository.findById(roomtype_id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 객실 분류 입니다."));

        roomType.setName(updateRoomDto.getName());
        roomType.setStandardCapacity(updateRoomDto.getStandardCapacity());
        roomType.setMaxCapacity(updateRoomDto.getMaxCapacity());

        List<Photo> photoList = fileHandler.parseFileInfo(updateRoomDto.getPhotos());

        if(!photoList.isEmpty()){
            for(Photo photo : photoList) {
                roomType.addPhoto(photoRepository.save(photo));
            }
        }

        roomTypeRepository.flush();

        /* 객실 엔티티 조회 */
//        List<Room> rooms = roomRepository.findAllByRoomTypeId(roomtype_id);
        List<Integer> roomNumList = updateRoomDto.getRoomNum();

        for(Integer i : roomNumList) {
            Room room = new Room();
            room.setRoomType(roomType);
            room.setRoomNum(i);
            room.setPrice(updateRoomDto.getPrice());
            roomRepository.save(room);
        }

    }

    /*---------------------------------    삭제    -------------------------------*/
    /**
     * 호텔 삭제
     */
    @Transactional
    public void deleteHotelById(Long hotel_id) {

        /* 엔티티 조회 */
        Hotel hotel = hotelRepository.findById(hotel_id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 호텔 입니다."));

        /* 호텔 삭제 */
        hotelRepository.delete(hotel);

    }

    /**
     * 객실 분류 삭제
     */
    @Transactional
    public void deleteRoomTypeById(Long roomtype_id) {

        /* 엔티티 조회 */
        RoomType roomType = roomTypeRepository.findById(roomtype_id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 객실 분류 입니다."));

        /* 객실 분류 삭제 */
        roomTypeRepository.delete(roomType);

    }

    /**
     * 방 호수 삭제
     */
    @Transactional
    public void deleteRoomById(Long room_id) {

        /* 엔티티 조회 */
        Room room = roomRepository.findById(room_id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 객실 입니다."));

        /* 객실 삭제 */
        roomRepository.delete(room);

    }

    /**
     * 네이버 호텔 크롤링
     */
    public List<GetCrawlingHotelDto> crawlingHotels(CrawlingHotelDto crawlingHotelDto) {
        List<GetCrawlingHotelDto> hotelList = new ArrayList<>();

        /* 크롬 드라이버 로딩 후 baseUrl페이지를 불러온다 */
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        driver = new ChromeDriver();
        driver.get(baseUrl);
        WebDriverWait wait = new WebDriverWait(driver, 50);

        try {
            /* 네이버 호텔 메인 페이지의 지역 input 태그에 파라미터로 넘어온 location 값을 넣고 엔터 */
            WebElement locationForm = driver.findElement(By.id("hotel_search"));
            locationForm.sendKeys(crawlingHotelDto.getLocation());
            locationForm.sendKeys(Keys.ENTER);

            /* '호텔 검색' 버튼 클릭*/
            WebElement searchButton = driver.findElement(By.cssSelector("body > div > div > div.container.main.ng-scope > " +
                    "div.aside > div:nth-child(1) > div > div.search_select_box > a"));
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("arguments[0].click()", searchButton);

            /*
             * 여기까지 하면 location='제주'일 경우,
             * https://hotel.naver.com/hotels/list?destination=place:Jeju&rooms=2
             * -> 이렇게목적지 파라미터 뒤에 rooms파라미터(인원수)가 자동으로 붙음.
             * 그래서 Jeju까지만 url을 split 해주고, 그 뒤에 Tripper 사용자가 입력했던 정보들을 넣어줘서 최종 url(rsltUrl)을 완성함.
             */
            String curUrl = driver.getCurrentUrl();
            String splitUrl = curUrl.split("&")[0];
            String rsltUrl = splitUrl + crawlingHotelDto.makeRsltUrl();
            driver.get(rsltUrl);
            log.info("rsltUrl=" + rsltUrl);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            /* 페이지 번호 개수 세어서 아래 for문 반복 횟수(repeatNum)를 구함 */
            WebElement pageList = driver.findElement(By.cssSelector("body > div > div > div.container.ng-scope > " +
                    "div.content > div.hotel_list_area > div.paginate.ng-scope"));
            List<WebElement> pages = pageList.findElements(By.className("ng-scope"));
            int repeatNum = pages.size();

            for(int i = 0; i < repeatNum; i++) {
                /* 한 페이지의 호텔들이 모두 담겨있는 ul */
                WebElement ul = driver.findElement(By.cssSelector("body > div > div > div.container.ng-scope > " +
                        "div.content > div.hotel_list_area > ul"));

                /* ul 하위 li들 */
                List<WebElement> li = ul.findElements(By.cssSelector("body > div > div > div.container.ng-scope > " +
                        "div.content > div.hotel_list_area > ul > li"));

                /* li개수만큼 반복하면서 각 호텔의 이름, 이미지, 최저가 가져옴 */
                for(WebElement e : li) {
                    WebElement name = e.findElement(By.tagName("strong"));
                    List<WebElement> imgs = e.findElements(By.tagName("img"));
                    WebElement img = imgs.get(1);
                    String imgSrc = img.getAttribute("src");
                    WebElement lowestPrice = e.findElement(By.tagName("em"));

                    /* 크롤링 한 호텔 정보들을 넣을 객체 */
                    GetCrawlingHotelDto hotel = new GetCrawlingHotelDto();
                    hotel.setName(name.getText());
                    hotel.setImgsrc(imgSrc);
                    hotel.setLowestprice(lowestPrice.getText());

                    hotelList.add(hotel);
                }
                WebElement nextButton = driver.findElement(By.xpath("/html/body/div/div/div[1]/div[2]/div[6]/div[2]/a[2]/span[1]"));
                nextButton.click();

                /* '다음' 버튼 클릭 후 페이지 로딩 기다림 */
                Thread.sleep(1000);
            }
            return hotelList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.close();
        }

        return hotelList;
    }

}
