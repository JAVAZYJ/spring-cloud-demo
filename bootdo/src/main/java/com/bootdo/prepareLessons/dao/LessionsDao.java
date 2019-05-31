package com.bootdo.prepareLessons.dao;

import com.bootdo.prepareLessons.domain.CatalogDO;
import com.bootdo.prepareLessons.domain.LessionsDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author zyj
 * @email 1992lcg@163.com
 * @date 2019-04-10 13:38:35
 */
@Mapper
public interface LessionsDao {

	LessionsDO get(Integer id);
	
	List<LessionsDO> list(Map<String, Object> map);

	int listCount(int catalog);

	List<CatalogDO> listCata(Map<String, Object> map);

	int updateCatalog(String catalog,int num);
	
	int count(Map<String, Object> map);
	
	int save(LessionsDO lessions);
	
	int update(LessionsDO lessions);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	List<CatalogDO> infoAll();

	String selectCatalog(Integer catalog);

	int selectCatalogString(String catalog);
}
