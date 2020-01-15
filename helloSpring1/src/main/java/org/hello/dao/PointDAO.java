package org.hello.dao;

import java.util.List;
import java.util.Map;

import org.hello.domain.PointVO;

public interface PointDAO {
	
	List<PointVO> point(PointVO vo) throws Exception;
	
	public void point_reg(PointVO vo) throws Exception;
	
	Map<String,Object> point_max_id(PointVO vo) throws Exception;
}
