package org.hello.service;

import java.util.List;

import org.hello.domain.PointVO;

public interface PointService {
	
	List<PointVO> point(PointVO vo) throws Exception;
}
