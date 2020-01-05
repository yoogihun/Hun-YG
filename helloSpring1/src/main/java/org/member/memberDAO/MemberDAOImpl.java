package org.member.memberDAO;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.member.memberVO.MemberVO;
import org.springframework.stereotype.Service;

@Service
public class MemberDAOImpl implements MemberDAO {
	
	@Inject
	private SqlSession sqlSession;
    
    private static String namespace = "org.hello.mapper.memberMapper";
	
	@Override
	public void register(MemberVO vo) throws Exception {
		sqlSession.insert(namespace+".register", vo);
	}
	
	@Override
	public MemberVO login(MemberVO vo) throws Exception{
		return sqlSession.selectOne(namespace +".login", vo);
	}
	
	@Override
	public void modify(MemberVO vo) throws Exception{
		sqlSession.update(namespace +".modify", vo);
	}
}
