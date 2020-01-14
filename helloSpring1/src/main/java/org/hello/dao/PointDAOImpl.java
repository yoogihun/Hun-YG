package org.hello.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.hello.domain.PointVO;
import org.springframework.stereotype.Repository;

@Repository
public class PointDAOImpl implements PointDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	
	private static String namespace = "org.hello.mapper.pointMapper";
	
	
	@Override
	public List<PointVO> point(PointVO vo) throws Exception{
		return sqlSession.selectList(namespace + ".point_select", vo);
	}
	
	@Override
	public void point_reg(PointVO vo) throws Exception{
		sqlSession.insert(namespace + ".point_reg", vo);
	}


}
