//package com.semi.project.admin.service;
//
//import javax.transaction.Transactional;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//
//import com.semi.project.admin.repository.UserRepository;
//import com.semi.project.login.dto.MemberDTO;
//import com.semi.project.login.entity.Member;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Service
//@Transactional
//public class UserService {
//	
//	public static final int TEXT_PAGE_SIZE = 10;
//	public static final String SORT_BY = "memberNo";
//	public static final String ACTIVE_STATUS = "N";
//	
//	private final UserRepository userRepository;
//	private final ModelMapper modelMapper;
//	
//	public UserService(UserRepository userRepository, ModelMapper modelMapper) {
//		this.userRepository = userRepository;
//		this.modelMapper = modelMapper;
//	}
//	
//	
//	public Page<MemberDTO> selectUserList(int page, String searchValue) {
//		
//		Pageable pageable = PageRequest.of(page - 1, TEXT_PAGE_SIZE, Sort.by(SORT_BY).ascending());
//		Page<Member> userList = null;
//		
//		if(searchValue != null && !searchValue.isEmpty()) {
//		} else {
//			userList = userRepository.findAll(pageable);
//		}
//		
//		return userList.map(member -> modelMapper.map(member, MemberDTO.class));
//		
//	}
//
//
//}
