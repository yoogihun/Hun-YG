package org.hello.dao;

import java.util.List;
import java.util.Map;

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

	@Override
	public Map<String,Object> point_max_id(PointVO vo) throws Exception{
		return sqlSession.selectOne(namespace + ".point_id_select", vo);
	}
}
