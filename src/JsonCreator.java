import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class JsonCreator {
    //用来写订单json的
    public static void writeOrderToJson(CoffeeOrder order, String filePath) {
        String jsonStr = order.toJsonString();
        try (
                OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8);
                BufferedWriter bw = new BufferedWriter(osw)
        ) {
            bw.write(jsonStr);
            bw.flush();
            System.out.println("已保存订单在：" + filePath);
        } catch (Exception e) {
            System.err.println("订单生成失败：" + e.getMessage());
            e.printStackTrace();
        }
    }
}
