package org.my.web.service;

import java.util.List;
import java.util.Map;

import org.my.web.domain.NewsVO;

public interface NaverNewsService {
	public Map<String, List<NewsVO>> searchNewsByKeyword(String keyword) throws Exception;
}
