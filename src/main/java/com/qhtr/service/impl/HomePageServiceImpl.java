package com.qhtr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qhtr.dao.HomePageMapper;
import com.qhtr.model.HomePage;
import com.qhtr.service.HomePageService;
/**
 * @author Harry
 * @Description 获取首页轮播图的业务层实现类
 * @date  2017年6月5日
 */
@Service
public class HomePageServiceImpl implements HomePageService{
	
	@Resource
	private HomePageMapper homePageMapper;

	@Override
	public List<HomePage> getHomePagePic() {
		try {
			
			List<HomePage> list = homePageMapper.getHomePagePic();
			return list;
			
		} catch (Exception e) {
			return null;
		}
		
	}
}
