package com.example.se_project.utils;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static <T> List<T> pageHelper(List<T> list, Integer page, Integer limit) {
        int startIndex = (page - 1) * limit;
        int endIndex = Math.min(startIndex + limit, list.size());

        if (startIndex >= list.size()) {
            // 如果请求的页码超出了数组的范围，返回空列表或适当的错误处理
            return new ArrayList<>();
        } else {
            // 根据页码和限制返回相应的数据
            return list.subList(startIndex, endIndex);
        }
    }
    public static Boolean isEmpty(String str){
        return str == null || "".equals(str) || "null".equals(str) ;
    }

}
