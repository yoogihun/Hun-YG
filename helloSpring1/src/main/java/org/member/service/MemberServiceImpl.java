package org.member.service;

import javax.inject.Inject;

import org.member.memberDAO.MemberDAO;
import org.member.memberVO.MemberVO;
import org.springframework.stereotype.Service;


@Service
public class MemberServiceImpl implements MemberService{

	@Inject
	private MemberDAO dao;
	
	
	@Override
	public void register(MemberVO vo) throws Exception {
		dao.register(vo);
		
	}
	
	@Override
	public MemberVO login(MemberVO vo) throws Exception{
		return dao.login(vo);
	}
	
	@Override
	public void modify(MemberVO vo) throws Exception{
		dao.modify(vo);
	}
	
}