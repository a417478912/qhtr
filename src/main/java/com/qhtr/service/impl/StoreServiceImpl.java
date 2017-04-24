package com.qhtr.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.qhtr.dao.StoreMapper;
import com.qhtr.dto.StoreDto;
import com.qhtr.model.Category;
import com.qhtr.model.Store;
import com.qhtr.service.CategoryService;
import com.qhtr.service.StoreService;
import com.qhtr.utils.CookieUtils;
import com.qhtr.utils.DistributionUtils;
import com.qhtr.utils.MD5Utils;

@Service
public class StoreServiceImpl implements StoreService {
	@Resource
	public StoreMapper storeMapper;
	@Resource
	public CategoryService categoryService;

	@Override
	public StoreDto getStoreByIdOrPhone(Store store) {
		List<Store> list = storeMapper.selectByConditions(store);
		if (list.isEmpty()) {
			return null;
		} else {
			Store store1 = list.get(0);
			return new StoreDto(store1);
		}
	}

	public StoreDto getStoreById(int id) {
		Store store = storeMapper.selectByPrimaryKey(id);
		if (store == null) {
			return null;
		} else {
			return new StoreDto(store);
		}
	}

	@Override
	public List<StoreDto> getStoresByDistance(String longitude, String latitude, String distance) {
		List<Store> allStores = storeMapper.selectByConditions(new Store());
		List<StoreDto> stores = new ArrayList<StoreDto>();
		for (Store store : allStores) {
			if (store.getLongitudeLatitude() == null) {
				continue;
			}
			String[] s = store.getLongitudeLatitude().split(",");
			if (DistributionUtils.getDistance(Double.parseDouble(s[0]), Double.parseDouble(s[1]),
					Double.parseDouble(longitude), Double.parseDouble(latitude)) <= Double.parseDouble(distance)) {
				stores.add(new StoreDto(store));
			}
		}
		return stores;
	}

	@Override
	public List<Store> getStoresByType(int type, int page, int num) {
		Store s = new Store();
		s.setCategoreId(type);
		PageHelper.startPage(page, num);
		return storeMapper.selectByConditions(s);
	}

	@Override
	public List<Store> getHotStores(int page, int num) {
		PageHelper.startPage(page, num);
		return storeMapper.selectHotStore();
	}

	@Override
	public List<Store> selectStoreBySearch(String searchContent, int page, int num) {
		PageHelper.startPage(page, num);
		return storeMapper.selectStoreBySearch(searchContent);
	}

	public int addRegister(String phone, String password) {
		/*if (verifyPhone(phone) == 2) {
			return -1;
		}*/

		Store store = new Store();
		store.setPhone(phone);
		store.setPassword(MD5Utils.getString(password));
		store.setCreateTime(new Date());
		store.setCollectNum(0);
		store.setType(0);
		store.setSellNum(0);
		store.setScore(0);
		storeMapper.insert(store);
		return store.getId();
	}

	/**
	 * 验证手机号是否注册
	 * 
	 * @param phone
	 * @return 2 ：已注册 1：未注册
	 */
	public int verifyPhone(String phone) {
		Store store = new Store();
		store.setPhone(phone);
		List<Store> stores = storeMapper.selectByConditions(store);
		if (!stores.isEmpty()) {
			return 2;
		} else {
			return 1;
		}
	}

	@Override
	public int updateByConditions(Store store) {
		return storeMapper.updateByPrimaryKeySelective(store);
	}

	@Override
	public int login(String phone, String password, HttpServletResponse response) {
		Store store = new Store();
		store.setPhone(phone);
		store.setPassword(MD5Utils.getString(password));
		List<Store> stores = storeMapper.selectByConditions(store);
		if (stores.isEmpty()) {
			return 0;
		} else {
			Store st = stores.get(0);
			if (st == null) {
				return 0;
			}
			/**
			 * 保存时间 7天
			 */
			CookieUtils.addCookie(response, "sellerPhone", st.getPhone(), 60 * 60 * 24 * 7);
			CookieUtils.addCookie(response, "storeId", st.getId() + "", 60 * 60 * 24 * 7);
			return 1;
		}
	}

	@Override
	public int updatePassword(String phone, String password) {
		Store storeTem = new Store();
		storeTem.setPhone(phone);
		List<Store> list = storeMapper.selectByConditions(storeTem);
		if(list.isEmpty()){
			return 0;
		}else{
			Store store = list.get(0);
			store.setPassword(MD5Utils.getString(password));
			storeMapper.updateByPrimaryKey(store);
		}
		return 1;
	}

	@Override
	public List<Map<String, Object>> getUserListByStoreId(int storeId) {
		return storeMapper.getUserListByStoreId(storeId);
	}

}
