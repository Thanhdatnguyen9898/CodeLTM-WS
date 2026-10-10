package com.mycompany.codeltmws;

import java.util.*;
import ptit.dblab.judge.network.server.soap.DataService;
import ptit.dblab.judge.network.server.soap.SoapDataService;

public class DataTaosolonnhat {
    public static void main(String[] args) throws Exception {
        DataService service = new DataService();
        SoapDataService port = service.getSoapDataServicePort();

        String msv = "B23DCCN138"; 
        String qCode = "xeOL8hoe";

        // 1. Lấy danh sách số nguyên từ server
        List<Integer> list = (List<Integer>) port.getData(msv, qCode);
        System.out.println("Data: " + list);

        // 2. Chuyển sang chuỗi để ghép số lớn nhất
        List<String> strList = new ArrayList<>();
        for (Integer num : list) {
            strList.add(String.valueOf(num));
        }

        // Sắp xếp theo thứ tự ghép chuỗi giảm dần (b + a so với a + b)
        Collections.sort(strList, (a, b) -> (b + a).compareTo(a + b));

        // Trường hợp tất cả đều là số 0
        if (strList.get(0).equals("0")) {
            port.submitDataString(msv, qCode, "0");
            System.out.println("Result: 0");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (String s : strList) {
            sb.append(s);
        }

        String res = sb.toString();

        port.submitDataString(msv, qCode, res);
    }
}