package com.qhtr.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.qhtr.dao.StoreMapper;
import com.qhtr.dto.LinkDto;
import com.qhtr.dto.PictureDto;
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
			Category category = categoryService.getById(store1.getCategoreId());
			return new StoreDto(store1,category.getName());
		}
	}

	public StoreDto getStoreById(int id) {
		Store store = storeMapper.selectByPrimaryKey(id);
		if (store == null) {
			return null;
		} else {
			Category category = categoryService.getById(store.getCategoreId());
			return new StoreDto(store,category.getName());
		}
	}

	@Override
	public List<Map<String, Object>> getStoresByDistance(String longitude, String latitude, String distance) {
		List<Store> allStores = storeMapper.selectByConditions(new Store());
		List<Map<String, Object>> stores = new ArrayList<Map<String, Object>>();
		for (Store store : allStores) {
			if (store.getLongitudeLatitude() == null) {
				continue;
			}
			String[] s = store.getLongitudeLatitude().split(",");
			if (DistributionUtils.getDistance(Double.parseDouble(s[0]), Double.parseDouble(s[1]),
					Double.parseDouble(longitude), Double.parseDouble(latitude)) <= Double.parseDouble(distance)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", store.getId() + "");

				// 行业分类
				Category category = categoryService.getById(store.getCategoreId());
				map.put("category", category.getName());
				map.put("name", store.getName());
				map.put("phone", store.getPhone());
				map.put("avatar", store.getAvatar());

				List<PictureDto> picList = new ArrayList<PictureDto>();
				String bannerStr = store.getPicture1();
				JSONArray jArray = JSONArray.parseArray(bannerStr);
				for (int i = 0; i < jArray.size(); i++) {
					PictureDto picDto = new PictureDto();
					JSONObject jObj = jArray.getJSONObject(i);
					Object imageURLObj = jObj.get("imageURL");
					Object linkObj = jObj.get("link");

					JSONObject link1 = (JSONObject) JSONObject.parse(linkObj.toString());
					LinkDto linkDto = new LinkDto();
					linkDto.setId(link1.get("id").toString());
					linkDto.setType(link1.get("type").toString());

					picDto.setImageURL(imageURLObj.toString());
					picDto.setLink(linkDto);

					picList.add(picDto);
				}
				map.put("bannerPic", picList);
				map.put("showPic", store.getPicture2());
				map.put("detail", store.getDetails());
				map.put("collect_num", store.getCollectNum() + "");
				map.put("sell_num", store.getSellNum() + "");
				map.put("location", store.getLocation());
				map.put("longitude", store.getLongitudeLatitude().split(",")[0]);
				map.put("latitude", store.getLongitudeLatitude().split(",")[1]);
				map.put("score", store.getScore());
				map.put("type", store.getType());
				stores.add(map);
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
		if (verifyPhone(phone) == 2) {
			return -1;
		}

		Store store = new Store();
		store.setPhone(phone);
		store.setPassword(MD5Utils.getString(password));
		store.setCreateTime(new Date());
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

}
