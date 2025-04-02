package com.johndeere.adzone.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:message.properties")
public class MessageProperties {
    
    @Value("${Order.list.product.success.code}")
    String OrderListedProductSuccessCode;

    @Value("${Order.list.product.success.description}")
    String OrderListedProductSuccessMessage;

    @Value("${Order.list.document.success.code}")
    String orderListedDocumentSuccessCode;

    @Value("${Order.list.document.success.description}")
    String orderListedDocumentSuccessMessage;

    @Value("${order.exist.success.code}")
    String orderExistSuccessCode;
 
    @Value("${order.exist.success.description}")
    String orderExistSuccessMessage;

    @Value("${order.addtocart.success.code}")
    String orderAddToCartSuccessCode;

    @Value("${order.addtocart.success.description}")
    String orderAddToCartSuccessMessage;

    @Value("${order.remove.success.code}")
    String orderRemoveSuccessCode;

    @Value("${order.remove.success.code}")
    String orderRemoveSuccessMessage;

    @Value("${order.place.document.sucess.code}")
    String orderPlaceDocumentSuccessCode;

    @Value("${order.place.document.success.description}")
    String orderPlaceDocumentSuccessMessage;

    @Value("${order.place.product.success.code}")
    String orderPlaceProductSuccessCode;

    @Value("${order.place.product.success.description}")
    String orderPlaceProductSuccessMessage;

    @Value("${order.myorder.success.code}")
    String myOrderListedSuccessCode;

    @Value("${orde.myorder.success.description}")
    String myOrderListedSuccessMessage;

    @Value("${order.myorder.admin.success.code}")
    String myOrderAdminListedSuccessCode;

    @Value("${order.myorder.admin.success.description}")
    String myOrderAdminLIstedSuccessMessage;

    @Value("${order.admin.update.status.success.code}")
    String orderUpdateSuccessCode;

    @Value("${order.admin.update.status.success.description}")
    String  orderUpdatedSuccessDescription;
    
    @Value("${order.dropDown.List.successMessage}")
	private String OrderExportSuccessCode;
	
	@Value("${order.dropDown.List.successMessage}")
	private String OrderExportSuccessMessage;

    
    public String getOrderExportSuccessCode() {
		return OrderExportSuccessCode;
	}

	public void setOrderExportSuccessCode(String orderExportSuccessCode) {
		OrderExportSuccessCode = orderExportSuccessCode;
	}

	public String getOrderExportSuccessMessage() {
		return OrderExportSuccessMessage;
	}

	public void setOrderExportSuccessMessage(String orderExportSuccessMessage) {
		OrderExportSuccessMessage = orderExportSuccessMessage;
	}

	public String getOrderUpdateSuccessCode() {
        return orderUpdateSuccessCode;
    }

    public void setOrderUpdateSuccessCode(String orderUpdateSuccessCode) {
        this.orderUpdateSuccessCode = orderUpdateSuccessCode;
    }

    public String getOrderUpdatedSuccessDescription() {
        return orderUpdatedSuccessDescription;
    }

    public void setOrderUpdatedSuccessDescription(String orderUpdatedSuccessDescription) {
        this.orderUpdatedSuccessDescription = orderUpdatedSuccessDescription;
    }

    public String getMyOrderAdminListedSuccessCode() {
        return myOrderAdminListedSuccessCode;
    }

    public void setMyOrderAdminListedSuccessCode(String myOrderAdminListedSuccessCode) {
        this.myOrderAdminListedSuccessCode = myOrderAdminListedSuccessCode;
    }

    public String getMyOrderAdminLIstedSuccessMessage() {
        return myOrderAdminLIstedSuccessMessage;
    }

    public void setMyOrderAdminLIstedSuccessMessage(String myOrderAdminLIstedSuccessMessage) {
        this.myOrderAdminLIstedSuccessMessage = myOrderAdminLIstedSuccessMessage;
    }

   

    public String getMyOrderListedSuccessCode() {
        return myOrderListedSuccessCode;
    }

    public void setMyOrderListedSuccessCode(String myOrderListedSuccessCode) {
        this.myOrderListedSuccessCode = myOrderListedSuccessCode;
    }

    public String getMyOrderListedSuccessMessage() {
        return myOrderListedSuccessMessage;
    }

    public void setMyOrderListedSuccessMessage(String myOrderListedSuccessMessage) {
        this.myOrderListedSuccessMessage = myOrderListedSuccessMessage;
    }

  

    public String getOrderListedProductSuccessCode() {
        return OrderListedProductSuccessCode;
    }

    public void setOrderListedProductSuccessCode(String orderListedProductSuccessCode) {
        OrderListedProductSuccessCode = orderListedProductSuccessCode;
    }

    public String getOrderListedProductSuccessMessage() {
        return OrderListedProductSuccessMessage;
    }

    public void setOrderListedProductSuccessMessage(String orderListedProductSuccessMessage) {
        OrderListedProductSuccessMessage = orderListedProductSuccessMessage;
    }

    public String getOrderListedDocumentSuccessCode() {
        return orderListedDocumentSuccessCode;
    }

    public void setOrderListedDocumentSuccessCode(String orderListedDocumentSuccessCode) {
        this.orderListedDocumentSuccessCode = orderListedDocumentSuccessCode;
    }

    public String getOrderListedDocumentSuccessMessage() {
        return orderListedDocumentSuccessMessage;
    }

    public void setOrderListedDocumentSuccessMessage(String orderListedDocumentSuccessMessage) {
        this.orderListedDocumentSuccessMessage = orderListedDocumentSuccessMessage;
    }

    public String getOrderExistSuccessCode() {
        return orderExistSuccessCode;
    }

    public void setOrderExistSuccessCode(String orderExistSuccessCode) {
        this.orderExistSuccessCode = orderExistSuccessCode;
    }

    public String getOrderExistSuccessMessage() {
        return orderExistSuccessMessage;
    }

    public void setOrderExistSuccessMessage(String orderExistSuccessMessage) {
        this.orderExistSuccessMessage = orderExistSuccessMessage;
    }

    public String getOrderAddToCartSuccessCode() {
        return orderAddToCartSuccessCode;
    }

    public void setOrderAddToCartSuccessCode(String orderAddToCartSuccessCode) {
        this.orderAddToCartSuccessCode = orderAddToCartSuccessCode;
    }

    public String getOrderAddToCartSuccessMessage() {
        return orderAddToCartSuccessMessage;
    }

    public void setOrderAddToCartSuccessMessage(String orderAddToCartSuccessMessage) {
        this.orderAddToCartSuccessMessage = orderAddToCartSuccessMessage;
    }

    public String getOrderRemoveSuccessCode() {
        return orderRemoveSuccessCode;
    }

    public void setOrderRemoveSuccessCode(String orderRemoveSuccessCode) {
        this.orderRemoveSuccessCode = orderRemoveSuccessCode;
    }

    public String getOrderRemoveSuccessMessage() {
        return orderRemoveSuccessMessage;
    }

    public void setOrderRemoveSuccessMessage(String orderRemoveSuccessMessage) {
        this.orderRemoveSuccessMessage = orderRemoveSuccessMessage;
    }

    public String getOrderPlaceDocumentSuccessCode() {
        return orderPlaceDocumentSuccessCode;
    }

    public void setOrderPlaceDocumentSuccessCode(String orderPlaceDocumentSuccessCode) {
        this.orderPlaceDocumentSuccessCode = orderPlaceDocumentSuccessCode;
    }

    public String getOrderPlaceDocumentSuccessMessage() {
        return orderPlaceDocumentSuccessMessage;
    }

    public void setOrderPlaceDocumentSuccessMessage(String orderPlaceDocumentSuccessMessage) {
        this.orderPlaceDocumentSuccessMessage = orderPlaceDocumentSuccessMessage;
    }

    public String getOrderPlaceProductSuccessCode() {
        return orderPlaceProductSuccessCode;
    }

    public void setOrderPlaceProductSuccessCode(String orderPlaceProductSuccessCode) {
        this.orderPlaceProductSuccessCode = orderPlaceProductSuccessCode;
    }

    public String getOrderPlaceProductSuccessMessage() {
        return orderPlaceProductSuccessMessage;
    }

    public void setOrderPlaceProductSuccessMessage(String orderPlaceProductSuccessMessage) {
        this.orderPlaceProductSuccessMessage = orderPlaceProductSuccessMessage;
    }
    

    

    

}
