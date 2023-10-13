package com.example.se_project.service;
import com.example.se_project.bean.PurchaseOrder;
import com.example.se_project.mapper.PurchaseOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseOrderService {
    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;

    public void insertPurchaseOrder(PurchaseOrder purchaseOrder) {
        purchaseOrderMapper.insertPurchaseOrder(purchaseOrder);
    }

    public void updatePurchaseOrder(PurchaseOrder purchaseOrder) {
        purchaseOrderMapper.updatePurchaseOrder(purchaseOrder);
    }

    public void deletePurchaseOrder(Integer purchaseOrderId) {
        purchaseOrderMapper.deletePurchaseOrder(purchaseOrderId);
    }
}
