package com.semi.project.admin.service;



import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.semi.project.admin.dto.NoticeDTO;
import com.semi.project.admin.entity.Notice;
import com.semi.project.admin.entity.NoticeType;
import com.semi.project.admin.repository.NoticeMainRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class NoticeService {
	

	
	public static final int TEXT_PAGE_SIZE = 10;
//	public static final int THUMBNAIL_PAGE_SIZE = 3;
	public static final int TEXT_NOTICE_CODE = 1; /* 1은 공지사항, 2는 이벤트 */
//	public static final int THUMBNAIL_BOARD_TYPE = 0;
	public static final String SORT_BY = "noticeNo";
	public static final String ACTIVE_STATUS = "N";
	
	private final NoticeMainRepository noticeMainRepository;
	private final ModelMapper modelMapper;
	
	public NoticeService(NoticeMainRepository noticeMainRepository, ModelMapper modelMapper) {
		this.noticeMainRepository = noticeMainRepository;
		this.modelMapper = modelMapper;
	}
	
	
	public Page<NoticeDTO> selectNoticeList(int page, String searchValue) {
		/* 페이지 타입의 NoticeDTO를 반환하기 위한 작업. */
		Pageable pageable = PageRequest.of(page - 1, TEXT_PAGE_SIZE, Sort.by(SORT_BY).descending());
		NoticeType noticeCode = new NoticeType();
		noticeCode.setNoticeCode(1L);
		Page<Notice> noticeMain = null;
		
		if(searchValue != null && !searchValue.isEmpty()) {
		} else {
			noticeMain = noticeMainRepository.findByNoticeCodeAndNoticeDelete(noticeCode, ACTIVE_STATUS, pageable);
		}
			
		return noticeMain.map(notice -> modelMapper.map(notice, NoticeDTO.class));
	}


	public NoticeDTO selectNoticeDetail(Long noticeNo) {
		NoticeType noticeCode = new NoticeType();
		noticeCode.setNoticeCode(1L);
		Notice notice = noticeMainRepository.findByNoticeNoAndNoticeCodeAndNoticeDelete(noticeNo, noticeCode, ACTIVE_STATUS);
		notice.setNoticeViews(notice.getNoticeViews() + 1);
		
		return modelMapper.map(notice, NoticeDTO.class);
	}


	public void removeNoticeMain(NoticeDTO removeNoticeMain) {
		
		Notice foundNotice = noticeMainRepository.findByNoticeNo(removeNoticeMain.getNoticeNo());
		foundNotice.setNoticeDelete("Y");
	}


}


