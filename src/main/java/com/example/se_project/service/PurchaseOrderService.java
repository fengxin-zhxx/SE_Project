package com.example.se_project.service;
import com.example.se_project.bean.PurchaseOrder;
import com.example.se_project.mapper.PurchaseOrderMapper;
import com.example.se_project.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PurchaseOrderService {
    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;

    public Integer insertPurchaseOrder(PurchaseOrder purchaseOrder) {
        purchaseOrderMapper.insertPurchaseOrder(purchaseOrder);
        return purchaseOrder.getPurchaseOrderId();
        // 返回自增的主键ID
    }

    public void updatePurchaseOrder(PurchaseOrder purchaseOrder) {
        purchaseOrderMapper.updatePurchaseOrder(purchaseOrder);
    }

    public void deletePurchaseOrder(Integer purchaseOrderId) {
        purchaseOrderMapper.deletePurchaseOrder(purchaseOrderId);
    }

    public Result checkPurchaseOrderOwned(PurchaseOrder purchaseOrder){
        List<Map<String, Object>> res = purchaseOrderMapper.selectPurchaseOrder(purchaseOrder.getPurchaseOrderId());
        if(res.size() == 0) return Result.error("订单不存在！");
        Integer trueEmployeeId = (Integer) res.get(0).get("employee_id");
        if(trueEmployeeId.equals(purchaseOrder.getEmployeeId())){
            return Result.ok();
        }else{
            return Result.error("订单与员工ID不匹配！");
        }
    }
}
