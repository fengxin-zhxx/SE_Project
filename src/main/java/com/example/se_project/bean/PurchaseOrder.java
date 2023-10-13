package com.example.se_project.bean;

import java.util.Map;

public class PurchaseOrder {
    public Integer purchaseOrderId;
    public String customerContact;
    public String customerAddress;
    public String purchasedProduct;
    public String purchasedDate;

    public PurchaseOrder(Integer purchaseOrderId, String customerContact, String customerAddress, String purchaseProduct, String purchasedDate) {
        this.purchaseOrderId = purchaseOrderId;
        this.customerContact = customerContact;
        this.customerAddress = customerAddress;
        this.purchasedProduct = purchaseProduct;
        this.purchasedDate = purchasedDate;
    }

    public Integer getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Integer purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public String getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getPurchasedProduct() {
        return purchasedProduct;
    }

    public void setPurchasedProduct(String purchasedProduct) {
        this.purchasedProduct = purchasedProduct;
    }

    public String getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(String purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    public PurchaseOrder(Map<String, Object> params) {
        this.purchaseOrderId = (Integer) params.get("purchase_order_id");
        this.customerContact = (String) params.get("customer_contact");
        this.customerAddress = (String) params.get("customer_address");
        this.purchasedProduct = (String) params.get("purchased_product");
        this.purchasedDate = (String) params.get("purchased_date");
    }
}
