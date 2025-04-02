package com.johndeere.adzone.admin.requestdto;

import java.util.List;

public class OrderRequestFilterDto {

	private List<String> orderStatusList;
    private String racfId;
    private Integer lastNDays;
    private Integer minPrice;
    private Integer maxPrice;

    private Integer productFlag;


    public Integer getProductFlag() {
        return productFlag;
    }

    public void setProductFlag(Integer productFlag) {
        this.productFlag = productFlag;
    }



    public List<String> getOrderStatusList() {
		return orderStatusList;
	}

	public void setOrderStatusList(List<String> orderStatusList) {
		this.orderStatusList = orderStatusList;
	}

	public Integer getLastNDays() {
        return lastNDays;
    }

    public void setLastNDays(Integer lastNDays) {
        this.lastNDays = lastNDays;
    }

    public String getRacfId() {
        return racfId;
    }

    public void setRacfId(String racfId) {
        this.racfId = racfId;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }
}
