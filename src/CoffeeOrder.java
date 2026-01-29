import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CoffeeOrder {
    //显然这是个订单的JAVABean
    private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private int id;
    private String type;
    private int price;
    private LocalDateTime time;

    public CoffeeOrder() {
    }

    public CoffeeOrder(int id, String type, int price, LocalDateTime time) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "CoffeeOrder{" +
                "id=" + id +
                ", type=\"" + type + '\"' +
                ", price=" + price +
                ", time=" + time +
                '}';
    }

    public String toJsonString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t{\n");
        sb.append("\t\t\"id\":").append(this.id).append(",\n");
        sb.append("\t\t\"type\":\"").append(this.type == null ? "" : this.type).append("\",\n");
        sb.append("\t\t\"price\":").append(this.price).append(",\n");
        sb.append("\t\t\"time\":\"").append(this.time.format(DF)).append("\"\n");
        sb.append("\t}");
        return sb.toString();
    }
}
