package com.mycompany.codeltmws.object;

import java.util.*;
import ptit.dblab.judge.network.server.soap.ObjectService;
import ptit.dblab.judge.network.server.soap.SoapObjectService;
import ptit.dblab.judge.network.server.soap.EmployeeY;

public class ObjectEmployee {
    public static void main(String[] args) throws Exception {
        ObjectService service = new ObjectService();
        SoapObjectService port = service.getSoapObjectServicePort();

        String studentCode = "B23DCCN138"; // Thay MSV của bạn
        String qCode = "ixoe8hj8";

        // a. Nhận danh sách EmployeeY từ server
        List<EmployeeY> list = port.requestListEmployeeY(studentCode, qCode);
        if (list == null || list.isEmpty()) return;

        // b. Sắp xếp thâm niên cao đến thấp (ngày bắt đầu sớm nhất đứng đầu)
        // Collections.sort trong Java có tính chất Stable (giữ nguyên thứ tự nếu cùng ngày)
        Collections.sort(list, (e1, e2) -> {
            Date d1 = e1.getStartDate().toGregorianCalendar().getTime();
            Date d2 = e2.getStartDate().toGregorianCalendar().getTime();
            return d1.compareTo(d2);
        });

        // In kiểm tra
        for (EmployeeY emp : list) {
            System.out.println(emp.getName() + " - " + emp.getStartDate());
        }

        // c. Gửi danh sách đã sắp xếp về server
        port.submitListEmployeeY(studentCode, qCode, list);
        System.out.println("Nop bai thanh cong!");
    }
}