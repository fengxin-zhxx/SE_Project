package com.example.se_project.service;
import com.example.se_project.bean.PurchaseOrder;
import com.example.se_project.mapper.PurchaseOrderMapper;
import com.example.se_project.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        List<PurchaseOrder> res = purchaseOrderMapper.selectPurchaseOrderByPurchaseOrderId(purchaseOrder.getPurchaseOrderId());
        if(res == null) return Result.error("订单不存在！");
        Integer trueEmployeeId = res.get(0).getEmployeeId();
        if(trueEmployeeId.equals(purchaseOrder.getEmployeeId())){
            return Result.ok();
        }else{
            return Result.error("订单与员工ID不匹配！");
        }
    }

    public Result checkPurchaseOrderOwned(Integer purchaseOrderId, Integer employeeId){
        List<PurchaseOrder> res = purchaseOrderMapper.selectPurchaseOrderByPurchaseOrderId(purchaseOrderId);
        if(res.size() == 0) return Result.error("订单不存在！");
        Integer trueEmployeeId = res.get(0).getEmployeeId();
        if(trueEmployeeId.equals(employeeId)){
            return Result.ok().data("data", res).data("count", 1);
        }else{
            return Result.error("订单与员工ID不匹配！");
        }
    }
    public List<PurchaseOrder> getPurchaseOrdersByEmployeeId(Integer employeeId) {
        return purchaseOrderMapper.selectPurchaseOrderByEmployeeId(employeeId);
    }

    public List<PurchaseOrder> getPurchaseOrdersByPurchaseOrderId(Integer purchaseOrderId) {
        return purchaseOrderMapper.selectPurchaseOrderByPurchaseOrderId(purchaseOrderId);
    }
}
