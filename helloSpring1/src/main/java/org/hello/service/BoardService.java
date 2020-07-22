package org.hello.service;
 
import java.util.List;
import java.util.Map;

import org.hello.domain.BoardVO;
import org.hello.domain.Criteria;
import org.hello.domain.ImgVO;
 
public interface BoardService {
    
    
    public void create(BoardVO vo) throws Exception;
    
    public List<BoardVO> listAll_Select(Map<String, Object> paramMap) throws Exception;
    
    public List<BoardVO> listAll(Criteria cri) throws Exception;
    
    public BoardVO read(Integer b_no) throws Exception;
    
    public void delete(Integer b_no) throws Exception;
    
    public void update(BoardVO vo) throws Exception;
    
    public void updatecnt(Integer b_no) throws Exception;
    
    public BoardVO search(Integer b_no) throws Exception;
    
    public int listCnt() throws Exception;
    
    public void register_img(ImgVO vo) throws Exception;
	
    public ImgVO ImgView() throws Exception;
    
    public void ImgModify(ImgVO vo) throws Exception;

	public void saveImage(Map<String, Object> hmap) throws Exception;
	
	public Map<String, Object> getByteImage() throws Exception;

	public String jsonTest() throws Exception;

}

