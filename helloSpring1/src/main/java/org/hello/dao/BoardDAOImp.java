package org.hello.dao;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.hello.domain.BoardVO;
import org.hello.domain.Criteria;
import org.hello.domain.ImgVO;
import org.springframework.stereotype.Repository;
 
@Repository
public class BoardDAOImp implements BoardDAO {
    
    @Inject
    private SqlSession sqlSession;
    
    private static String namespace = "org.hello.mapper.boardMapper";
 
    //�Խ����߰�
    @Override
    public void create(BoardVO vo) throws Exception {
        sqlSession.insert(namespace+".insertBoard", vo);
    }
    
    //�Խ��Ǻ���
    @Override
    public List<BoardVO> listAll_Select(Map<String,Object> paramMap) throws Exception {
        
        sqlSession.selectList(namespace+".listBoard",paramMap);
        return sqlSession.selectList(namespace+".listBoard",paramMap);
    }
    
    @Override
    public List<BoardVO> listAll(Criteria cri) throws Exception {
        
        sqlSession.selectList(namespace+".listBoard", cri);
        return sqlSession.selectList(namespace+".listBoard", cri);
    }
    
    
    //�Խ��� ��
    @Override
    public BoardVO read(Integer b_no) throws Exception {
        sqlSession.selectOne(namespace+".detailBoard", b_no);
        return sqlSession.selectOne(namespace+".detailBoard", b_no);
    }
 
    //�Խ��� ����
    @Override
    public void delete(Integer b_no) throws Exception {
        sqlSession.delete(namespace+".deleteBoard", b_no);
 
    }
 
    //�Խ��� ����
    @Override
    public void update(BoardVO vo) throws Exception {
        sqlSession.update(namespace+".updateBoard", vo);
 
    }
    
    //�Խ��� ��ȸ��
    @Override
    public void updatecnt(Integer b_no) throws Exception {
        sqlSession.update(namespace+".updatecntBoard", b_no);
 
    }
    
    //�Խ��� �˻�
    @Override
    public BoardVO search(Integer b_no) throws Exception {
       return sqlSession.selectOne(namespace+".searchBoard", b_no);
 
    }
    
    @Override
    public int listCnt() throws Exception {
        return sqlSession.selectOne(namespace+".listCnt");
		
        
    }
    @Override
    public void register_img(ImgVO vo) throws Exception{
        sqlSession.insert(namespace+".register_img", vo);
		
        
    }
    
    @Override
    public ImgVO ImgView() throws Exception{
        return sqlSession.selectOne(namespace+".ImgView");
		
        
    }
    
    @Override
    public void ImgModify(ImgVO vo) throws Exception{
    	sqlSession.update(namespace+".ImgModify",vo);
    }
    
    @Override
    public void saveImage(Map<String, Object> hmap) throws SQLException{
    	sqlSession.insert(namespace+".saveImage",hmap);
    }
    
    @Override
    public Map<String, Object> getByteImage() throws Exception{
    	return sqlSession.selectOne(namespace+".getByteImage");
    }
    
 
}


