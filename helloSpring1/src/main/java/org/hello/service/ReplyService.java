package org.hello.service;

import java.util.List;
import java.util.Map;

import org.hello.domain.ReplyVO;

public interface ReplyService {
	List<ReplyVO> readReply(Integer b_no) throws Exception;
	
	public void createRp(ReplyVO vo) throws Exception;
	
	Map<String,Object> ReplyCk(ReplyVO vo) throws Exception;

	
	
}
