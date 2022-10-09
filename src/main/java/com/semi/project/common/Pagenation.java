package com.semi.project.common;

import org.springframework.data.domain.Page;

/* 페이징 처리 계산용 클래스 */
public class Pagenation {
	
	public static PagingButtonInfo getPagingButtonInfo(Page page) {
		
		int currentPage = page.getNumber() + 1;
		int defaultButtonCount = 10;
		int startPage;
		int endPage;

		startPage = (int) (Math.ceil((double) currentPage / defaultButtonCount) - 1) * defaultButtonCount + 1;
		endPage = startPage + defaultButtonCount - 1;

		if(page.getTotalPages() < endPage)
			endPage = page.getTotalPages();

		if(page.getTotalPages() == 0 && endPage == 0)
			endPage = startPage;
		
		return new PagingButtonInfo(currentPage, startPage, endPage);
	}
}
