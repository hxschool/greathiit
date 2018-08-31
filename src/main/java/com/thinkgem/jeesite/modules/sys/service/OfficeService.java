/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.modules.sys.dao.OfficeDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.sys.web.TreeLink;

/**
 * 机构Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class OfficeService extends TreeService<OfficeDao, Office> {
	@Autowired
	private OfficeDao officeDao;
	
	public Office getOfficeByName(String name) {
		return officeDao.getOfficeByName(name);
	}
	
	public List<Office> findAll(){
		return UserUtils.getOfficeList();
	}
	
	
	public List<Office> findByParentIdGroupByYear(String parnetId){
		Office office = new Office();
		office.setParent(new Office(parnetId));
		return officeDao.findByParentIdGroupByYear(office);
	}
	
	public List<Office> findByParentIdAndYear(String parnetId,String year){
		Office office = new Office();
		office.setParent(new Office(parnetId));
		office.setId(year);
		return officeDao.findByParentIdAndYear(office);
	}
	
	public List<Office> findByParentId(String parnetId){
		Office office = new Office();
		office.setParent(new Office(parnetId));
		return officeDao.findByParentId(office);
	}

	public List<Office> findList(Boolean isAll){
		if (isAll != null && isAll){
			return UserUtils.getOfficeAllList();
		}else{
			return UserUtils.getOfficeList();
		}
	}
	

	
	@Transactional(readOnly = true)
	public List<Office> findList(Office office){
		if(office != null){
			office.setParentIds(office.getParentIds()+"%");
			return dao.findByParentIdsLike(office);
		}
		return  new ArrayList<Office>();
	}
	
	@Transactional(readOnly = false)
	public void save(Office office) {
		super.save(office);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
	}
	
	@Transactional(readOnly = false)
	public void delete(Office office) {
		super.delete(office);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
	}
	public List<TreeLink> treeLink(String parnetId){
		List<Office> list1 = findByParentId(parnetId);
		List<TreeLink> treeLinks1 = new ArrayList<TreeLink>();
		for(Office office:list1) {
			TreeLink treeLink = new TreeLink();
			treeLink.setValue(office.getId());
			treeLink.setName(office.getName());
			List<Office> list2 = findByParentId(office.getId());
			List<TreeLink> treeLinks2 = new ArrayList<TreeLink>();
			for(Office office2:list2) {
				TreeLink treeLink2 = new TreeLink();
				treeLink2.setValue(office2.getId());
				treeLink2.setName(office2.getName());
				List<Office> list3 = findByParentId(office2.getId());
				List<TreeLink> treeLinks3 = new ArrayList<TreeLink>();
				for(Office office3:list3) {
					TreeLink treeLink3 = new TreeLink();
					treeLink3.setValue(office3.getId());
					treeLink3.setName(office3.getName());
					treeLinks3.add(treeLink3);
				}
				treeLink2.setSub(treeLinks3);
				treeLinks2.add(treeLink2);
			}
			treeLink.setSub(treeLinks2);
			treeLinks1.add(treeLink);
			
		}
		return treeLinks1;
	}
	
	public List<TreeLink> treeClassLink(String parnetId){
		List<Office> list1 = findByParentId(parnetId);
		List<TreeLink> treeLinks1 = new ArrayList<TreeLink>();
		for(Office office:list1) {
			TreeLink treeLink = new TreeLink();
			treeLink.setValue(office.getId());
			treeLink.setName(office.getName());
			List<Office> list2 = findByParentId(office.getId());
			List<TreeLink> treeLinks2 = new ArrayList<TreeLink>();
			for(Office office2:list2) {
				TreeLink treeLink2 = new TreeLink();
				treeLink2.setValue(office2.getId());
				treeLink2.setName(office2.getName());
				List<Office> list3 = findByParentIdGroupByYear(office2.getId());
				List<TreeLink> treeLinks3 = new ArrayList<TreeLink>();
				for(Office office3:list3) {
					TreeLink treeLink3 = new TreeLink();
					treeLink3.setValue(office3.getId());
					treeLink3.setName(office3.getId());
					List<Office> list4 = findByParentIdAndYear(office2.getId(), office3.getId());
					List<TreeLink> treeLinks4 = new ArrayList<TreeLink>();
					for(Office office4:list4) {
						TreeLink treeLink4 = new TreeLink();
						treeLink4.setValue(office4.getId());
						treeLink4.setName(office4.getId());
						treeLinks4.add(treeLink4);
					}
					treeLink3.setSub(treeLinks4);
					treeLinks3.add(treeLink3);
				}
				treeLink2.setSub(treeLinks3);
				treeLinks2.add(treeLink2);
			}
			treeLink.setSub(treeLinks2);
			treeLinks1.add(treeLink);
			
		}
		return treeLinks1;
	}
	
}
