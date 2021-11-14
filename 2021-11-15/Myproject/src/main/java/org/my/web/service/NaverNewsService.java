package org.my.web.service;

import java.util.List;

import org.my.web.domain.NewsVO;

public interface NaverNewsService {
	public List<NewsVO> searchNewsByKeyword(String keyword) throws Exception;
}
