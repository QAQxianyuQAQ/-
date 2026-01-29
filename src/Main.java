import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                5,
                10,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        System.out.println("欢迎使用咖啡厅点餐系统：");
        System.out.println("----------------------");
        System.out.println("[1]点餐    [2]查看营业额");
        System.out.println("[3]查看剩余订单    [0]退出");
        System.out.println("----------------------");
        lo:
        while (true) {
            switch (sc.nextInt()) {
                case 1:
                    System.out.println("[1]冰水(2元)    [2]手冲(30元)    [0]返回");
                    switch (sc.nextInt()) {
                        case 1:
                            WareHouse.coffeeType = 1;
                            WareHouse.coffeeAmount++;
                            WareHouse.coffeeCount++;
                            pool.submit(new Producer());
                            JsonCreator.writeOrderToJson(
                                    new CoffeeOrder(WareHouse.coffeeCount,
                                            "冰水",
                                            2,
                                            LocalDateTime.now()
                                    ), "./coffee_order" + WareHouse.coffeeCount + ".json"
                            );
                            System.out.println("----------------------");
                            System.out.println("[1]点餐    [2]查看营业额");
                            System.out.println("[3]查看剩余订单    [0]退出");
                            System.out.println("----------------------");
                            break;
                        case 2:
                            WareHouse.coffeeType = 2;
                            WareHouse.coffeeAmount++;
                            WareHouse.coffeeCount++;
                            pool.submit(new Producer());
                            JsonCreator.writeOrderToJson(
                                    new CoffeeOrder(WareHouse.coffeeCount,
                                            "手冲咖啡",
                                            2,
                                            LocalDateTime.now()
                                    ), "./coffee_order" + WareHouse.coffeeCount + ".json"
                            );
                            System.out.println("----------------------");
                            System.out.println("[1]点餐    [2]查看营业额");
                            System.out.println("[3]查看剩余订单    [0]退出");
                            System.out.println("----------------------");
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("输入错误！");
                            System.out.println("----------------------");
                            System.out.println("[1]点餐    [2]查看营业额");
                            System.out.println("[3]查看剩余订单    [0]退出");
                            System.out.println("----------------------");
                            break;
                    }
                    break;
                case 2:
                    System.out.println("总营业额为：" + WareHouse.money + ".00元");
                    System.out.println("----------------------");
                    System.out.println("[1]点餐    [2]查看营业额");
                    System.out.println("[3]查看剩余订单    [0]退出");
                    System.out.println("----------------------");
                    break;
                case 3:
                    System.out.println("剩余订单量为：" + WareHouse.coffeeAmount);
                    System.out.println("----------------------");
                    System.out.println("[1]点餐    [2]查看营业额");
                    System.out.println("[3]查看剩余订单    [0]退出");
                    System.out.println("----------------------");
                    break;
                case 0:
                    System.out.println("程序已退出");
                    pool.shutdown();
                    break lo;
                default:
                    System.out.println("输入错误!");
                    System.out.println("----------------------");
                    System.out.println("[1]点餐    [2]查看营业额");
                    System.out.println("[3]查看剩余订单    [0]退出");
                    System.out.println("----------------------");
                    break;
            }
        }
    }
}