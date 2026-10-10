/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.codeltmws;

import java.util.*;
import ptit.dblab.judge.network.server.soap.DataService;
import ptit.dblab.judge.network.server.soap.SoapDataService;

public class DataTongPhanTu {
    public static void main(String[] args) throws Exception {
        DataService service = new DataService();
        SoapDataService port = service.getSoapDataServicePort();

        String msv = "B23DCCN138"; 
        String qCode = "0Iqr4Zfd";
        List<Integer> list = (List<Integer>) port.getData(msv, qCode);
        int res = 0;
        for(Integer x : list) {
            res +=x;
        }
        
        port.submitDataInt(msv, qCode, res);
}
}
