package com.qhtr.dao;

import java.util.List;

import com.qhtr.model.HomePage;
/**
 * @author Harry
 * @Description 获取首页轮播图的持久层接口
 * @date  2017年6月5日
 */
public interface HomePageMapper {

	List<HomePage> getHomePagePic();

}
