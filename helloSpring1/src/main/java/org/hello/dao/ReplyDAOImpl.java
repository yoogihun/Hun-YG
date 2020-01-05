package org.hello.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.hello.domain.ReplyVO;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	private static String namespace = "org.hello.mapper.replyMapper";
	
	@Override
	public List<ReplyVO> readReply(Integer b_no) throws Exception{
		return sqlSession.selectList(namespace + ".readReply", b_no);
	}
	
	@Override
	public void createRp(ReplyVO vo) throws Exception{
		sqlSession.insert(namespace + ".CreateReply", vo);
	}
	
	@Override
	public Map<String,Object> ReplyCk(ReplyVO vo) throws Exception{
		return sqlSession.selectOne(namespace+".ReplyCk", vo);
	}
}
