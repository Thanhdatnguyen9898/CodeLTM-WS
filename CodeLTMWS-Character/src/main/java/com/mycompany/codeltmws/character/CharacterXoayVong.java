package com.mycompany.codeltmws.character;

import java.util.*;
// Import đúng package mà NetBeans sinh ra từ CharacterService
import ptit.dblab.judge.network.server.soap.CharacterService;
import ptit.dblab.judge.network.server.soap.SoapCharacterService;

public class CharacterXoayVong {
    public static void main(String[] args) throws Exception {
        // 1. Khởi tạo Service và Port
        CharacterService service = new CharacterService();
        SoapCharacterService port = service.getSoapCharacterServicePort();

        String studentCode = "B22DCCN138"; // Thay MSV của bạn
        String qCode = "XNNIXKpF";           // Thay mã câu hỏi của bạn

        // a. Nhận danh sách ký tự (dạng List<Integer>) từ server
        List<Integer> list = port.requestCharacter(studentCode, qCode);
        System.out.println("Danh sach ban dau: " + list);

        if (list == null || list.isEmpty()) {
            System.out.println("Danh sach rong!");
            return;
        }

        // b. Xoay vòng phải: số lần xoay = giá trị ASCII của ký tự đầu tiên
        int k = list.get(0); // Ký tự đầu tiên (giá trị ASCII)
        System.out.println("So lan xoay (ASCII cua " + (char) k + "): " + k);

        // Collections.rotate(list, k) tự động xoay danh sách sang phải k lần
        Collections.rotate(list, k);

        System.out.println("Danh sach sau khi xoay: " + list);

        // c. Gửi danh sách đã xoay trở lại server
        port.submitCharacterCharArray(studentCode, qCode, list);
        System.out.println("Da gui thanh cong!");
    }
}