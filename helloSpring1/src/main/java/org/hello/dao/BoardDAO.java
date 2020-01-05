package org.hello.dao;

import java.util.List;
import java.util.Map;

import org.hello.domain.BoardVO;
import org.hello.domain.Criteria;

public interface BoardDAO {
	public void create(BoardVO vo) throws Exception;
    
    public List<BoardVO> listAll_Select(Map<String, Object> paramMap) throws Exception;
    
    public List<BoardVO> listAll(Criteria cri) throws Exception;
    
    public BoardVO read(Integer b_no) throws Exception;
    
    public void delete(Integer b_no) throws Exception;
    
    public void update(BoardVO vo) throws Exception;
    
    public void updatecnt(Integer b_no) throws Exception;
    
    public BoardVO search(Integer b_no) throws Exception;
    
    public int listCnt() throws Exception;

	

	
}