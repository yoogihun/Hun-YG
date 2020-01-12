package org.hello.service;

import java.util.List;

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
}
