package org.hello.service;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.hello.dao.BoardDAO;
import org.hello.domain.BoardVO;
import org.hello.domain.Criteria;
import org.hello.domain.ImgVO;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {
	@Inject
    private BoardDAO dao;
    
    
    @Override
    public void create(BoardVO vo) throws Exception {
        dao.create(vo);
 
    }
 
    @Override
    public List<BoardVO> listAll_Select(Map<String,Object> paramMap) throws Exception {
        
        
        return dao.listAll_Select(paramMap);
    }
    
    @Override
    public List<BoardVO> listAll(Criteria cri) throws Exception {
        
        
        return dao.listAll(cri);
    }
 
    @Override
    public BoardVO read(Integer b_no) throws Exception {
        
        return dao.read(b_no);
    }
 
    @Override
    public void delete(Integer b_no) throws Exception {
       
		dao.delete(b_no);
 
    }
 
    @Override
    public void update(BoardVO vo) throws Exception {
        
        dao.update(vo);
 
    }
    
    @Override
    public void updatecnt(Integer b_no) throws Exception {
        
        dao.updatecnt(b_no);
 
    }
    
    @Override
    public BoardVO search(Integer b_no) throws Exception {
        
       return dao.search(b_no);
 
    }
    
    @Override
    public int listCnt() throws Exception {
        
       return dao.listCnt();
 
    }
    
    @Override
    public void register_img(ImgVO vo) throws Exception {
        
       dao.register_img(vo);
 
    }
    
    @Override
    public ImgVO ImgView() throws Exception {
        
       return dao.ImgView();
 
    }
    
    @Override
    public void ImgModify(ImgVO vo) throws Exception{
    	dao.ImgModify(vo);
    }
    
    @Override
    public void saveImage(Map<String, Object> hmap) throws Exception{
    	dao.saveImage(hmap);
    }
    
    @Override
    public Map<String, Object> getByteImage() throws Exception{
    	return dao.getByteImage();
    }
    
    @Override
    public String jsonTest() throws Exception{
    	return dao.jsonTest();
    }

}
