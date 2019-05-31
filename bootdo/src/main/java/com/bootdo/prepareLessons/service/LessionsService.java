package com.bootdo.prepareLessons.service;

import com.bootdo.prepareLessons.domain.CatalogDO;
import com.bootdo.prepareLessons.domain.LessionsDO;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author zyj
 * @email 1992lcg@163.com
 * @date 2019-04-10 13:38:35
 */
public interface LessionsService {
	
	LessionsDO get(Integer id);
	
	List<LessionsDO> list(Map<String, Object> map);

	int updateCatalog(int catalog);
	
	int count(Map<String, Object> map);
	
	int save(LessionsDO lessions) throws ParseException;
	
	int update(LessionsDO lessions);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	List<CatalogDO> infoAll();

	String selectCatalog(Integer catalog);

	int selectCatalogString(String catalog);
}
