package com.example.se_project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.se_project.bean.PurchaseOrder;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderMapper extends BaseMapper<PurchaseOrder> {
    @Insert(
            "INSERT INTO purchase_order(" +
                    "`customer_contact`, `customer_address`, `purchased_product`, `purchased_date`" +
                    ") values (" +
                    "#{customerContact}, #{customerAddress}, #{purchasedProduct}, #{purchasedDate}" +
                    ")"
    )
    void insertPurchaseOrder(PurchaseOrder purchaseOrder);

    @Update(
            "UPDATE `purchase_order`\n" +
                    "SET\n" +
                    "`customer_contact` = #{customerContact},\n" +
                    "`customer_address` = #{customerAddress},\n" +
                    "`purchased_product` = #{purchasedProduct},\n" +
                    "`purchased_date` = #{purchasedDate}\n" +
                    "WHERE `purchase_order_id` = #{purchaseOrderId}"
    )
    void updatePurchaseOrder(PurchaseOrder purchaseOrder);

    @Delete("DELETE FROM `purchase_order`\n" +
            "WHERE `purchase_order_id` = #{purchaseOrderId}")
    void deletePurchaseOrder(Integer purchaseOrderId);
}
