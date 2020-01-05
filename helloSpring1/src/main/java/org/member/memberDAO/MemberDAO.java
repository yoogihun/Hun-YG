package org.member.memberDAO;

import org.member.memberVO.MemberVO;

public interface MemberDAO {
	
	public void register(MemberVO vo) throws Exception;

	public MemberVO login(MemberVO vo) throws Exception;
	
	public void modify(MemberVO vo) throws Exception;
}
