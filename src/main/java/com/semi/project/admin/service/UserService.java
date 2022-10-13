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
import com.semi.project.admin.repository.UserRepository;
import com.semi.project.login.dto.MemberDTO;
import com.semi.project.login.entity.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class UserService {
	
	public static final int TEXT_PAGE_SIZE = 10;
	public static final String SORT_BY = "memberNo";
	public static final String ACTIVE_STATUS = "N";
	
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	
	public UserService(UserRepository userRepository, ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}
	
	
	public Page<MemberDTO> selectUserList(int page, String searchValue) {
		
		Pageable pageable = PageRequest.of(page - 1, TEXT_PAGE_SIZE, Sort.by(SORT_BY).ascending());
		Page<Member> userList = null;
		
		if(searchValue != null && !searchValue.isEmpty()) {
		} else {
			userList = userRepository.findByMemberStatus(ACTIVE_STATUS, pageable);
//			userList = userRepository.findByAll(pageable);
		}
		
		return userList.map(member -> modelMapper.map(member, MemberDTO.class));
		
	}


	public MemberDTO updateUserAdmin(Long memberNo) {
		
		Member user = userRepository.findByMemberNo(memberNo);
		
		return modelMapper.map(user, MemberDTO.class);
	}


	public void modifyUser(MemberDTO updateUser) {
		
		Member savedUser = userRepository.findByMemberNo(updateUser.getMemberNo());
		savedUser.setMemberName(updateUser.getMemberName());
		savedUser.setMemberbirth(updateUser.getMemberBirth());
		savedUser.setMemberNickname(updateUser.getMemberNickname());
		savedUser.setMemberAddress(updateUser.getMemberAddress());
		savedUser.setMemberEmail(updateUser.getMemberEmail());
		savedUser.setMemberPhone(updateUser.getMemberPhone());

		
	}

	/* 강사님한테 ... */
	public void removeUser(MemberDTO deleteUser) {
		
		Member savedUser = userRepository.findByMemberNo(deleteUser.getMemberNo());
		savedUser.setMemberStatus("Y");
		
	}

	
	




}
