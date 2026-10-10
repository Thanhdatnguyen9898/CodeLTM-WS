package com.mycompany.codeltmws.object;

import java.util.*;
import ptit.dblab.judge.network.server.soap.ObjectService;
import ptit.dblab.judge.network.server.soap.SoapObjectService;
import ptit.dblab.judge.network.server.soap.Customer;

public class ObjectCustomer {
    public static void main(String[] args) throws Exception {
        ObjectService service = new ObjectService();
        SoapObjectService port = service.getSoapObjectServicePort();

        String studentCode = "B23DCCN138";
        String qCode = "0EXnuuab";

        // a. Nhận danh sách khách hàng từ server
        List<Customer> list = port.requestListCustomer(studentCode, qCode);
        if (list == null) return;

        // b. Lọc: totalSpent > 500 và purchaseCount >= 3
        List<Customer> filteredList = new ArrayList<>();
        for (Customer c : list) {
            if (c.getTotalSpent() > 500 && c.getPurchaseCount() >= 3) {
                filteredList.add(c);
            }
        }

        System.out.println("So luong sau khi loc: " + filteredList.size());

        // c. Gửi danh sách đã lọc về server
        port.submitListCustomer(studentCode, qCode, filteredList);
        System.out.println("Nop bai thanh cong!");
    }
}