package org.hello.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.hello.dao.ReplyDAO;
import org.hello.domain.ReplyVO;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyServiceImpl implements ReplyService {
	
	@Inject 
	private ReplyDAO dao;
	
	
	@Override
	public List<ReplyVO> readReply(Integer b_no) throws Exception{
		return dao.readReply(b_no);
	}
	
	@Override
	public void createRp(ReplyVO vo) throws Exception{
		dao.createRp(vo);
	}
	
	@Override
	public Map<String, Object> ReplyCk(ReplyVO vo) throws Exception{
		return dao.ReplyCk(vo);
	}
	
	@Override
	public void updateReply(ReplyVO vo) throws Exception{
		dao.updateReply(vo);
	}
	
	@Override
	public Map<String, Object> detailReply(ReplyVO vo) throws Exception{
		return dao.detailReply(vo);
	}
}
