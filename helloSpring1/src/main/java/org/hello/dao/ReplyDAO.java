package org.hello.dao;

import java.util.List;
import java.util.Map;

import org.hello.domain.ReplyVO;

public interface ReplyDAO {
	
	public List<ReplyVO> readReply(Integer b_no) throws Exception;
	
	public void createRp(ReplyVO vo) throws Exception;
	
	public Map<String, Object> ReplyCk(ReplyVO vo) throws Exception;
}
