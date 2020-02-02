package org.hello.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.hello.dao.PointDAO;
import org.hello.domain.PointVO;
import org.springframework.stereotype.Repository;

@Repository
public class PointServiceImpl implements PointService {
	@Inject 
	private PointDAO dao;
	
	@Override
	public List<PointVO> point(PointVO vo) throws Exception{
		return dao.point(vo);
	}
	
	@Override
	public void point_reg(PointVO vo) throws Exception{
		dao.point_reg(vo);
	}
	
	@Override
	public Map<String,Object> point_max_id(PointVO vo) throws Exception{
		return dao.point_max_id(vo);
	}
	
	@Override
	public void point_mod(PointVO vo) throws Exception{
		dao.point_mod(vo);
	}
}
