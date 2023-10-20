package com.example.se_project.controller;

import com.example.se_project.bean.*;
import com.example.se_project.service.*;
import com.example.se_project.utils.Result;
import com.example.se_project.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class EmployeeController {
    @Autowired
    private PurchaseOrderService purchaseOrderService;
    @Autowired
    private TimecardService timecardService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private PayrollRecordService payrollRecordService;
    @Autowired
    private EmployeeService employeeService;

    /*
    *  For Commissioned Employees
    *
    */

    @RequestMapping("/GetPurchaseOrder")
    public Result GetPurchaseOrder(@RequestBody Map<String, Object> params){
        System.out.println("GetPurchaseOrder" + params);
        try{
            Integer employeeId = Integer.valueOf(String.valueOf(params.get("employee_id")));
            List<PurchaseOrder> purchaseOrders = purchaseOrderService.getPurchaseOrdersByEmployeeId(employeeId);

            return Result.ok().data("data",purchaseOrders).data("count", purchaseOrders.size());
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @RequestMapping("/GetPurchaseOrderByPurchaseOrderId")
    public Result GetPurchaseOrderByPurchaseOrderId(@RequestBody Map<String, Object> params){
        System.out.println("GetPurchaseOrderByPurchaseOrderId" + params);
        try{
            Integer employeeId = Integer. valueOf(String.valueOf(params.get("employee_id")));
            Integer purchaseOrderId = Integer.valueOf(String.valueOf(params.get("purchase_order_id")));
            Result checkRes = purchaseOrderService.checkPurchaseOrderOwned(purchaseOrderId, employeeId);
            if(!checkRes.getSuccess()){
                return checkRes;
            }
            return checkRes;
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }


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
            Integer purchaseOrderId = Integer.valueOf(String.valueOf(params.get("purchaseOrderId")));
            purchaseOrderService.deletePurchaseOrder(purchaseOrderId);
            return Result.ok("删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }


    /*
    *  For Hourly and Salaried Employees
    *
    */
    @RequestMapping("/GetCurrentTimecard")
    public Result GetCurrentTimecard(@RequestBody Map<String, Object> params){
        System.out.println("GetCurrentTimecard" + params);
        try{
            Integer employeeId = Integer.valueOf(String.valueOf(params.get("employee_id")));

            // 保证timecard存在，不存在则新建，返回当前timecard编号
            Timecard currentTimecard = timecardService.checkOrCreateTimecard(employeeId);

            return Result.ok().data("data",currentTimecard);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @RequestMapping("/GetTimecards")
    public Result GetTimecards(@RequestBody Map<String, Object> params){
        System.out.println("GetTimecards" + params);
        try{
            Integer employeeId = Integer.valueOf(String.valueOf(params.get("employee_id")));
            Integer page = Integer.valueOf(String.valueOf(params.get("page")));
            Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
            List<Timecard> timecards = timecardService.getTimecards(employeeId);
            Integer count = timecards.size();
            List<Timecard> timecardsRes= Utils.pageHelper(timecards, page, limit);

            return Result.ok().data("data",timecardsRes).data("count", count);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @RequestMapping("/GetTimecardByTimecardId")
    public Result GetTimecardByTimecardId(@RequestBody Map<String, Object> params){
        System.out.println("GetTimecardByTimecardId" + params);
        try{
            Integer timecardId = Integer.valueOf(String.valueOf(params.get("timecard_id")));

            Timecard timecards = timecardService.getTimecardByTimecardId(timecardId);

            return Result.ok().data("data",timecards);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @RequestMapping("/GetCurrentTimecardEntries")
    public Result GetCurrentTimecardEntries(@RequestBody Map<String, Object> params){
        System.out.println("GetCurrentTimecardEntries" + params);
        try{
            Integer employeeId = Integer.valueOf(String.valueOf(params.get("employee_id")));
            
            // 保证timecard存在，不存在则新建，返回当前timecard编号
            Timecard currentTimecard = timecardService.checkOrCreateTimecard(employeeId);

            Integer page = Integer.valueOf(String.valueOf(params.get("page")));
            Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
            Integer count = currentTimecard.getTimecardEntries().size();
            List<TimecardEntry> timecardEntries = timecardService.getTimecardEntries(currentTimecard, page, limit);

            return Result.ok().data("data",timecardEntries).data("count", count);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @RequestMapping("/GetTimecardEntries")
    public Result GetTimecardEntries(@RequestBody Map<String, Object> params){
        System.out.println("GetTimecardEntries" + params);
        try{
            Integer timecardId = Integer.valueOf(String.valueOf(params.get("timecard_id")));
            Integer page = Integer.valueOf(String.valueOf(params.get("page")));
            Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
            List<TimecardEntry> timecardEntries = timecardService.getTimecardEntriesByTimcardId(timecardId);
            Integer count = timecardEntries.size();
            List<TimecardEntry> payrollRecordsRes = Utils.pageHelper(timecardEntries, page, limit);

            return Result.ok().data("data",payrollRecordsRes).data("count", count);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @RequestMapping("/UpdateTimecardEntry")
    public Result UpdateTimecardEntry(@RequestBody Map<String, Object> params) {
        System.out.println("UpdateTimecardEntry" + params);
        try {
            TimecardEntry timecardEntry = new TimecardEntry(params);
            Boolean checkRes = timecardService.checkUpdateTimecardEntry(timecardEntry);
            if(checkRes == Boolean.FALSE){
                return Result.error("更新失败，超过本周工作时限");
            }
            timecardService.updateTimecardEntry(timecardEntry);
            return Result.ok("修改成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @RequestMapping("/DeleteTimecardEntry")
    public Result DeleteTimecardEntry(@RequestBody Map<String, Object> params) {
        System.out.println("DeleteTimecardEntry" + params);
        try {
            Integer timecardEntryId = Integer.valueOf(String.valueOf(params.get("timecardEntryId")));
            timecardService.deleteTimecardEntry(timecardEntryId);
            return Result.ok("删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @RequestMapping("/GetProjects")
    public Result GetProjects() {
        System.out.println("GetProjects");
        try{
            List<Project> allProject = projectService.getAllProjects();
            return Result.ok().data("data", allProject);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @RequestMapping("/AddTimecardEntry")
    public Result AddTimecardEntry(@RequestBody Map<String, Object> params) {
        System.out.println("AddTimecardEntry" + params);
        try{
            TimecardEntry timecardEntry = new TimecardEntry(params);
            Boolean checkRes = timecardService.checkInsertTimecardEntry(timecardEntry);
            if(checkRes == Boolean.FALSE){
                return Result.error("上传失败，超过本周工作时限");
            }
            Integer timecardEntryId = timecardService.insertTimecard(timecardEntry);
            return Result.ok("上传成功！时间片表项编号为 " + timecardEntryId);

        }catch (Exception e){
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @RequestMapping("/SubmitTimecard")
    public Result SubmitTimecardEntry(@RequestBody Map<String, Object> params) {
        System.out.println("SubmitTimecardEntry" + params);
        try{
            Integer timecardEntryId = Integer.valueOf(String.valueOf(params.get("timecard_id")));
            /*
            todo: 检查总工作时长是否超过规定
            */
            timecardService.submitTimecard(timecardEntryId);
            return Result.ok("提交成功！");
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @RequestMapping("/GetPayrollRecord")
    public Result GetPayrollRecord(@RequestBody Map<String, Object> params){
        System.out.println("GetPayrollRecord" + params);
        try{
            Integer employeeId = Integer.valueOf(String.valueOf(params.get("employee_id")));
            Integer page = Integer.valueOf(String.valueOf(params.get("page")));
            Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
            List<PayrollRecord> payrollRecords = payrollRecordService.getPayrollRecordByEmployeeRecordId(employeeId);
            Integer count = payrollRecords.size();
            List<PayrollRecord> payrollRecordsRes = Utils.pageHelper(payrollRecords, page, limit);

            return Result.ok().data("data",payrollRecordsRes).data("count", count);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @RequestMapping("/GetAllPayrollRecord")
    public Result GetAllPayrollRecord(@RequestBody Map<String, Object> params){
        System.out.println("GetAllPayrollRecord" + params);
        try{
            Integer page = Integer.valueOf(String.valueOf(params.get("page")));
            Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
            List<PayrollRecord> payrollRecords = payrollRecordService.getAllPayrollRecord();
            Integer count = payrollRecords.size();
            List<PayrollRecord> payrollRecordsRes = Utils.pageHelper(payrollRecords, page, limit);

            return Result.ok().data("data",payrollRecordsRes).data("count", count);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @RequestMapping("/ConfirmPayrollRecord")
    public Result ConfirmPayrollRecord(@RequestBody Map<String, Object> params){
        System.out.println("ConfirmPayrollRecord" + params);
        try{
            Integer payrollRecordId = Integer.valueOf(String.valueOf(params.get("payrollRecordId")));


            payrollRecordService.confirmPayrollRecord(payrollRecordId);

            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @RequestMapping("/GetProjectTotalHours")
    public Result GetProjectTotalHours(@RequestBody Map<String, Object> params){
        System.out.println("GetProjectTotalHours" + params);
        try{
            Integer employeeId = Integer.valueOf(String.valueOf(params.get("employee_id")));
            Integer page = Integer.valueOf(String.valueOf(params.get("page")));
            Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
            List<Map<String, Object>> projectTotalHours = timecardService.getProjectTotalHours(employeeId);
            Integer count = projectTotalHours.size();
            List<Map<String, Object>> payrollRecordsRes = Utils.pageHelper(projectTotalHours, page, limit);

            return Result.ok().data("data",payrollRecordsRes).data("count", count);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @RequestMapping("/GetReport")
    public Result GetReport(@RequestBody Map<String, Object> params){
        System.out.println("GetReport" + params);
        try{
            Integer employeeId = Integer.valueOf(String.valueOf(params.get("employee_id")));

            Boolean checkRes = employeeService.checkEmployeeId(employeeId);

            if(!checkRes){
                return Result.error("员工ID不存在！");
            }


            String dataRange = String.valueOf(params.get("date_range"));
            String type = String.valueOf(params.get("type"));

            String[] dataSplit = dataRange.split(" ~ ");
            String startDate = dataSplit[0], endDate = dataSplit[1];

            Integer project_id;
            if(Utils.isEmpty(String.valueOf(params.get("project_id")))){
                project_id = null;
            }else{
                project_id = Integer.valueOf(String.valueOf(params.get("project_id")));
            }

            List<Map<String, Object>> reportRes = null;
            switch (type){
                case "TotalHoursWorked":
                    reportRes = timecardService.getTotalHoursWorked(employeeId, startDate, endDate);
                    break;
                case "TotalHoursWorkedForAProject":
                    reportRes = timecardService.getTotalHoursWorkedForAProject(employeeId, startDate, endDate, project_id);
                    break;
                case "Vacation/SickLeave":
                    reportRes = timecardService.getVacationOrSickLeaveDays(employeeId, startDate, endDate);
                    break;
                case "TotalPay":
                    reportRes = payrollRecordService.getTotalPay(employeeId, startDate, endDate);
                    break;
                default:
                    throw new Exception("Unknown Type");
            }
//            Integer count = projectTotalHours.size();
//            List<Map<String, Object>> payrollRecordsRes = Utils.pageHelper(projectTotalHours, page, limit);
//
            return Result.ok().data("data",reportRes);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }


}
