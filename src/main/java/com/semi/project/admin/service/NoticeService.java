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
import com.semi.project.login.entity.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class NoticeService {
	

	
	public static final int TEXT_PAGE_SIZE = 10;
	public static final int THUMBNAIL_PAGE_SIZE = 3;
	public static final int TEXT_NOTICE_CODE = 1; /* 공지사항 */ 
	public static final int THUMBNAIL_BOARD_TYPE = 2; /*이벤트*/
	public static final String SORT_BY = "noticeNo";
	public static final String ACTIVE_STATUS = "N";
	
	private final NoticeMainRepository noticeMainRepository;
	private final ModelMapper modelMapper;
	
	public NoticeService(NoticeMainRepository noticeMainRepository, ModelMapper modelMapper) {
		this.noticeMainRepository = noticeMainRepository;
		this.modelMapper = modelMapper;
	}
	
	
	
	
	public Page<NoticeDTO> selectNoticeList(int page, String searchValue) {
		Pageable pageable = PageRequest.of(page - 1, TEXT_PAGE_SIZE, Sort.by(SORT_BY).descending());
		NoticeType noticeCode = new NoticeType();
		noticeCode.setNoticeCode(1L);
		Page<Notice> noticeMain = null;
		
		if(searchValue != null && !searchValue.isEmpty()) {
			noticeMain = noticeMainRepository.findBySearchValue(noticeCode, ACTIVE_STATUS, searchValue, pageable);
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





	public void registNoticeMain(NoticeDTO notice) {
		
		
		noticeMainRepository.save(modelMapper.map(notice, Notice.class));
	}




	public NoticeDTO goUpdateNoticeMain(Long noticeNo) {

		Notice notice = noticeMainRepository.findByNoticeNo(noticeNo);
		
		return modelMapper.map(notice, NoticeDTO.class);
	}




	public void modifyNoticeMain(NoticeDTO updateNotice) {

		Notice savedNotice = noticeMainRepository.findByNoticeNo(updateNotice.getNoticeNo());
		savedNotice.setNoticeTitle(updateNotice.getNoticeTitle());
		savedNotice.setNoticeContent(updateNotice.getNoticeContent());
		
	}




	public void removeNotice(NoticeDTO deleteNotice) {
		
		Notice savedNotice = noticeMainRepository.findByNoticeNo(deleteNotice.getNoticeNo());
		savedNotice.setNoticeDelete("Y");
		
	}




}


