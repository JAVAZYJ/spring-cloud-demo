package com.bootdo.prepareLessons.service.impl;

import com.bootdo.prepareLessons.domain.CatalogDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.prepareLessons.dao.LessionsDao;
import com.bootdo.prepareLessons.domain.LessionsDO;
import com.bootdo.prepareLessons.service.LessionsService;



@Service
public class LessionsServiceImpl implements LessionsService {
	@Autowired
	private LessionsDao lessionsDao;
	
	@Override
	public LessionsDO get(Integer id){
        LessionsDO lessionsDO = lessionsDao.get(id);
        LessionsDO lie = new LessionsDO();
        lie.setId(lessionsDO.getId());//序号
        lie.setTitle(lessionsDO.getTitle());//标题
        lie.setCatalogDTO(lessionsDao.selectCatalog(lessionsDO.getCatalog())); //所属目录
        lie.setTermDTO(lessionsDao.selectCatalog(lessionsDO.getTerm())); //学期
        lie.setCreationtime(lessionsDO.getCreationtime()); //创建时间
        lie.setContent(lessionsDO.getContent());//备课内容
	    return lie;
	}
	
	@Override
	public List<LessionsDO> list(Map<String, Object> map){
        List<LessionsDO> list = new ArrayList<>();
        List<LessionsDO> list1 = lessionsDao.list(map);
        for (LessionsDO liessionsDo : list1){
            LessionsDO lie = new LessionsDO();
            lie.setId(liessionsDo.getId());//序号
            lie.setTitle(liessionsDo.getTitle());//标题
            lie.setCatalogDTO(lessionsDao.selectCatalog(liessionsDo.getCatalog())); //所属目录
            lie.setTermDTO(lessionsDao.selectCatalog(liessionsDo.getTerm())); //学期
            lie.setCreationtime(liessionsDo.getCreationtime()); //创建时间
            lie.setContent(liessionsDo.getContent());//备课内容
            list.add(lie);
        }
		return list;
	}

    /**
     *  更新课程目录统计数量
     */
    public int updateCatalog(int catalog){
        String clg = lessionsDao.selectCatalog(catalog);
        int i = lessionsDao.listCount(catalog);
        return lessionsDao.updateCatalog(clg,i);
    }

    /**
     * 课程目录数据
     */
    public List<CatalogDO> catalogDOList(Map<String, Object> map){
        List<CatalogDO> list = new ArrayList<>();
        List<CatalogDO> list1 = lessionsDao.listCata(map);
        for (CatalogDO liessionsDo : list1){
            CatalogDO lie = new CatalogDO();
            lie.setId(liessionsDo.getId());//序号
            lie.setCatalog(liessionsDo.getCatalog());//科目


            lie.setLessionsCount(lie.getLessionsCount());//科目数量统计
            list.add(lie);
        }
        return list;
    }

    @Override
	public int count(Map<String, Object> map){
		return lessionsDao.count(map);
	}
	
	@Override
	public int save(LessionsDO lessions) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
        LessionsDO lessionsD = new LessionsDO();
        lessionsD.setTitle(lessions.getTitle());
        lessionsD.setCatalog(lessionsDao.selectCatalogString(lessions.getCatalogDTO()));
        lessionsD.setTerm(lessionsDao.selectCatalogString(lessions.getTermDTO()));
        lessionsD.setCreationtime(df.format(new Date()));
        lessionsD.setContent(lessions.getContent());
	    return lessionsDao.save(lessionsD);
	}
	
	@Override
	public int update(LessionsDO lessions){
        LessionsDO lie = new LessionsDO();
        lie.setId(lessions.getId());//序号
        lie.setTitle(lessions.getTitle());//标题
        System.out.println("++++++++++++++++++++++++" + lessions.getCatalogDTO());
        lie.setCatalog(this.selectCatalogString(lessions.getCatalogDTO()));//所属目录
        lie.setTerm(this.selectCatalogString(lessions.getTermDTO()));//学期
        lie.setCreationtime(lessions.getCreationtime()); //创建时间
        lie.setContent(lessions.getContent());//备课内容
		return lessionsDao.update(lie);
	}
	
	@Override
	public int remove(Integer id){
		return lessionsDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return lessionsDao.batchRemove(ids);
	}

	@Override
	public List<CatalogDO> infoAll(){
		return lessionsDao.infoAll();
	}

    @Override
    public String selectCatalog(Integer catalog) {
        return lessionsDao.selectCatalog(catalog);
    }

    @Override
    public int selectCatalogString(String catalog) {
        return lessionsDao.selectCatalogString(catalog);
    }

}
