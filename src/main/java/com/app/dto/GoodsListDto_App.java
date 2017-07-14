package com.app.dto;

import java.util.List;
import java.util.Map;

import com.qhtr.model.Goods;
import com.qhtr.model.Picture;
import com.qhtr.model.Sku;
import com.qhtr.service.PictureService;
import com.qhtr.service.SkuService;
import com.qhtr.utils.ApplicationContextUtils;
import com.qhtr.utils.PicUtils;

public class GoodsListDto_App {
	public int id;
	public Picture picture;
	public String name;
	public String thumb;
	public String resultPic;
	public int topPrice;
	public int lowPrice;
	public int totalStock = 0;
	public int collect_num = 0;
	public int sell_num = 0;
	public String details;
	
	public GoodsListDto_App(){
	}
	
	public GoodsListDto_App(Goods goods){
		
		SkuService skuService = (SkuService) ApplicationContextUtils.getContext().getBean("SkuService");
		PictureService pictureService = (PictureService) ApplicationContextUtils.getContext().getBean("PictureService");
		this.id = goods.getId();
		this.thumb = goods.getThumb();
		this.resultPic = goods.getResultPicture();
		int topPrice = 0;
		int lowPrice = 10000000;
		int totalStock = 0;
		List<Sku> skuList = skuService.selectListByGoodsId(goods.getId());
		if (!skuList.isEmpty()) {
			for (Sku sku : skuList) {
				
				System.out.println(sku);
				
				if (sku != null) {
					
					if (sku.getPrice() != null) {
						
						if(sku.getPrice() > topPrice){
							topPrice = sku.getPrice();
						}
						if(sku.getPrice() < lowPrice){
							lowPrice = sku.getPrice();
						}
					}
					
					if (sku.getStock()!=null) {
						totalStock += sku.getStock();
					}
				}
			}
		}
		this.setLowPrice(lowPrice);
		this.setTopPrice(topPrice);
		this.setTotalStock(totalStock);
		
		this.setCollect_num(goods.getCollectNum());
		this.setSell_num(goods.getSellNum());
		
		this.setName(goods.getName());
		
		//详情图
		if(goods.getDetailPictures() != null){
			
			int picId = Integer.parseInt(goods.getDetailPictures().split(",")[0]);
			Picture pic = pictureService.getById(picId);
			/*Picture pic = new Picture();
			
			pic.setHeight(new StringBuilder().append(PicUtils.getImgHeight(goods.getThumb())).toString());
			pic.setWidth(new StringBuilder().append(PicUtils.getImgWidth(goods.getThumb())).toString());
			
			pic.setUrl(goods.getThumb());*/
			
			this.setPicture(pic);
		}
		
		this.setDetails(goods.getDetails());
		}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getTotalStock() {
		return totalStock;
	}

	public void setTotalStock(int totalStock) {
		this.totalStock = totalStock;
	}

	public int getTopPrice() {
		return topPrice;
	}

	public void setTopPrice(int topPrice) {
		this.topPrice = topPrice;
	}

	public int getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(int lowPrice) {
		this.lowPrice = lowPrice;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public int getCollect_num() {
		return collect_num;
	}

	public void setCollect_num(int collect_num) {
		this.collect_num = collect_num;
	}

	public int getSell_num() {
		return sell_num;
	}

	public void setSell_num(int sell_num) {
		this.sell_num = sell_num;
	}

	@Override
	public String toString() {
		return "GoodsListDto_App [id=" + id + ", picture=" + picture + ", name=" + name + ", topPrice=" + topPrice
				+ ", lowPrice=" + lowPrice + ", totalStock=" + totalStock + ", collect_num=" + collect_num
				+ ", sell_num=" + sell_num + ", details=" + details + "]";
	}



	
	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + collect_num;
		result = prime * result + ((details == null) ? 0 : details.hashCode());
		result = prime * result + id;
		result = prime * result + lowPrice;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((picture == null) ? 0 : picture.hashCode());
		result = prime * result + sell_num;
		result = prime * result + ((thumb == null) ? 0 : thumb.hashCode());
		result = prime * result + topPrice;
		result = prime * result + totalStock;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GoodsListDto_App other = (GoodsListDto_App) obj;
		if (collect_num != other.collect_num)
			return false;
		if (details == null) {
			if (other.details != null)
				return false;
		} else if (!details.equals(other.details))
			return false;
		if (id != other.id)
			return false;
		if (lowPrice != other.lowPrice)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (picture == null) {
			if (other.picture != null)
				return false;
		} else if (!picture.equals(other.picture))
			return false;
		if (sell_num != other.sell_num)
			return false;
		if (thumb == null) {
			if (other.thumb != null)
				return false;
		} else if (!thumb.equals(other.thumb))
			return false;
		if (topPrice != other.topPrice)
			return false;
		if (totalStock != other.totalStock)
			return false;
		return true;
	}

	public String getResultPic() {
		return resultPic;
	}

	public void setResultPic(String resultPic) {
		this.resultPic = resultPic;
	}

}
