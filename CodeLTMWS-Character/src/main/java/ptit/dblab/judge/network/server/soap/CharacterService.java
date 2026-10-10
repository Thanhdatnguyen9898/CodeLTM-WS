package com.mycompany.codeltmws.character;

import java.util.*;
import ptit.dblab.judge.network.server.soap.CharacterService;
import ptit.dblab.judge.nzetwork.server.soap.SoapCharacterService;

public class CharacterNguyenAm {
    public static void main(String[] args) throws Exception {
        CharacterService service = new CharacterService();
        SoapCharacterService port = service.getSoapCharacterServicePort();

        String studentCode = "B23DCCN138";
        String qCode = "nT1uSZEm";

        // a. Nhận danh sách chuỗi từ server
        List<String> list = port.requestStringArray(studentCode, qCode);
        System.out.println("Input: " + list);

        if (list == null || list.isEmpty()) return;

        // b. Phân loại theo số lượng nguyên âm tăng dần
        Map<Integer, List<String>> map = new TreeMap<>();

        for (String word : list) {
            int count = countVowels(word);
            if (!map.containsKey(count)) {
                map.put(count, new ArrayList<>());
            }
            map.get(count).add(word);
        }

        // c. Mỗi từ là một phần tử riêng trong danh sách kết quả
        List<String> result = new ArrayList<>();
        for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
            List<String> group = entry.getValue();
            Collections.sort(group); // Sắp xếp từ điển trong nhóm
            result.addAll(group);    // Thêm từng từ vào danh sách kết quả
        }

        System.out.println("Result: " + result);

        // d. Gửi danh sách kết quả về server
        port.submitCharacterStringArray(studentCode, qCode, result);
        System.out.println("Nop bai thanh cong!");
    }

    private static int countVowels(String s) {
        int count = 0;
        String vowels = "aeiouAEIOU";
        for (char c : s.toCharArray()) {
            if (vowels.indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }
}