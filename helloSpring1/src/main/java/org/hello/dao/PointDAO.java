package org.hello.dao;

import java.util.List;

import org.hello.domain.PointVO;

public interface PointDAO {
	
	List<PointVO> point(PointVO vo) throws Exception;
}
