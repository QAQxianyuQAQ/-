package com.qaqxianyuqaq.appointmentsystem;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class JsonCreator {
    //用来写用户json的
    public static void writeUserToJson(ArrayList<User> orderList, String filePath) {
        if (orderList == null || orderList.isEmpty()) {
            return;
        }

        StringBuilder sb = new StringBuilder();

        if(orderList.size() == 1){
            User user = orderList.get(0);
            sb.append(user.toJsonString());
        }else{
            sb.append("[\n");
            for (int i = 0; i < orderList.size(); i++) {
                User order = orderList.get(i);
                sb.append(order.toJsonString());
                if (i != orderList.size() - 1) {
                    sb.append(",");
                    sb.append("\n");
                }
            }
            sb.append("\n]");
        }

        String jsonArrayStr = sb.toString();

        try (
                OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8);
                BufferedWriter bw = new BufferedWriter(osw)
        ) {
            bw.write(jsonArrayStr);
            bw.flush();
            System.out.println("用户已写入" + filePath);
        } catch (Exception e) {
            System.err.println("写入失败：" + e.getMessage());
            e.printStackTrace();
        }
    }
}
