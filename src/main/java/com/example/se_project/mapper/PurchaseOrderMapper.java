package com.example.se_project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.se_project.bean.PurchaseOrder;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseOrderMapper extends BaseMapper<PurchaseOrder> {
    @Insert(
            "INSERT INTO purchase_order(" +
                    "`customer_contact`, `customer_address`, `purchased_product`, `purchased_date`, `employee_id`" +
                    ") values (" +
                    "#{customerContact}, #{customerAddress}, #{purchasedProduct}, #{purchasedDate}, #{employeeId}" +
                    ")"
    )
    @Options(useGeneratedKeys=true, keyProperty="purchaseOrderId", keyColumn="purchase_order_id")
void insertPurchaseOrder(PurchaseOrder purchaseOrder);

    @Update(
            "UPDATE `purchase_order`\n" +
                    "SET\n" +
                    "`customer_contact` = #{customerContact},\n" +
                    "`customer_address` = #{customerAddress},\n" +
                    "`purchased_product` = #{purchasedProduct},\n" +
                    "`purchased_date` = #{purchasedDate}\n" +
                    "WHERE `purchase_order_id` = #{purchaseOrderId}"
            // 不允许更改employee_id
    )
    void updatePurchaseOrder(PurchaseOrder purchaseOrder);

    @Delete("DELETE FROM `purchase_order`\n" +
            "WHERE `purchase_order_id` = #{purchaseOrderId}")
    void deletePurchaseOrder(Integer purchaseOrderId);

    @Select("SELECT * FROM `purchase_order`\n" +
            "WHERE `purchase_order_id` = #{purchaseOrderId}")
    List<PurchaseOrder> selectPurchaseOrderByPurchaseOrderId(Integer purchaseOrderId);


    @Select("SELECT * FROM `purchase_order`\n" +
            "WHERE `employee_id` = #{employeeId}")
    List<PurchaseOrder> selectPurchaseOrderByEmployeeId(Integer employeeId);


}
