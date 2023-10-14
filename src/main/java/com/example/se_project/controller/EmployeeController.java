package com.example.se_project.controller;

import com.example.se_project.bean.PurchaseOrder;
import com.example.se_project.bean.Timecard;
import com.example.se_project.service.PurchaseOrderService;
import com.example.se_project.service.TimecardService;
import com.example.se_project.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin
public class EmployeeController {
    @Autowired
    private PurchaseOrderService purchaseOrderService;

    /*
    *  For Commissioned Employees
    *
    */
    @RequestMapping("/AddPurchaseOrder")
    public Result AddPurchaseOrder(@RequestBody Map<String, Object> params) {
        System.out.println("AddPurchaseOrder" + params);
        try {
            PurchaseOrder purchaseOrder = new PurchaseOrder(params);
            Integer purchaseOrderId = purchaseOrderService.insertPurchaseOrder(purchaseOrder);

            return Result.ok("添加成功，订单ID为 " + purchaseOrderId);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @RequestMapping("/UpdatePurchaseOrder")
    public Result UpdatePurchaseOrder(@RequestBody Map<String, Object> params) {
        System.out.println("UpdatePurchaseOrder" + params);
        try {
            PurchaseOrder purchaseOrder = new PurchaseOrder(params);
            Result checkRes = purchaseOrderService.checkPurchaseOrderOwned(purchaseOrder);
            if(!checkRes.getSuccess()){
                return checkRes;
            }
            purchaseOrderService.updatePurchaseOrder(purchaseOrder);

            return Result.ok("修改成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @RequestMapping("/DeletePurchaseOrder")
    public Result DeletePurchaseOrder(@RequestBody Map<String, Object> params) {
        System.out.println("DeletePurchaseOrder" + params);
        try {
            Integer purchaseOrderId = (Integer) params.get("purchase_order_id");
            purchaseOrderService.deletePurchaseOrder(purchaseOrderId);
            return Result.ok("删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }


    @Autowired
    private TimecardService timecardService;
    /*
    *  For Hourly and Salaried Employees
    *
    */
    @RequestMapping("/GetCurrentTimecard")
    public Result GetCurrentTimecard(@RequestBody Map<String, Object> params){
        System.out.println("GetCurrentTimecard" + params);
        try{
            Integer employeeId = (Integer) params.get("employee_id");

            // 保证timecard存在，不存在则新建，返回当前timecard编号
            Timecard currentTimecard = timecardService.checkOrCreateTimecard(employeeId);

            return Result.ok().data("data",currentTimecard);
        }catch (Exception e){
            return Result.error(e.getMessage());
        }

    }


}
