import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class JsonCreator {
    //用来写订单json的
    public static void writeOrderToJson(ArrayList<CoffeeOrder> orderList, String filePath) {
        if (orderList == null || orderList.isEmpty()) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for (int i = 0; i < orderList.size(); i++) {
            CoffeeOrder order = orderList.get(i);
            sb.append(order.toJsonString());
            if (i != orderList.size() - 1) {
                sb.append(",");
                sb.append("\n");
            }
        }
        sb.append("\n]");
        String jsonArrayStr = sb.toString();

        try (
                OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8);
                BufferedWriter bw = new BufferedWriter(osw)
        ) {
            bw.write(jsonArrayStr);
            bw.flush();
            System.out.println("订单已写入" + filePath);
        } catch (Exception e) {
            System.err.println("写入失败：" + e.getMessage());
            e.printStackTrace();
        }
    }
}
