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
        printMainManu();
        lo:
        while (true) {
            switch (sc.nextInt()) {
                case 1:
                    printViceManu();
                    switch (sc.nextInt()) {
                        case 1:
                            WareHouse.coffeeType = 1;
                            WareHouse.coffeeAmount++;
                            WareHouse.money += 2;
                            pool.submit(new Producer());
                            System.out.println("\n冰水下单成功 +2￥");
                            printMainManu();
                            break;
                        case 2:
                            WareHouse.coffeeType = 2;
                            WareHouse.coffeeAmount++;
                            WareHouse.money += 30;
                            pool.submit(new Producer());
                            System.out.println("\n手冲咖啡下单成功 +30￥");
                            printMainManu();
                            break;
                        case 0:
                            printMainManu();
                            break;
                        default:
                            System.out.println("\n输入错误！");
                            printMainManu();
                            break;
                    }
                    break;
                case 2:
                    System.out.println("\n总营业额为：" + WareHouse.money + ".00元");
                    printMainManu();
                    break;
                case 3:
                    System.out.println("\n剩余订单量为：" + WareHouse.coffeeAmount);
                    printMainManu();
                    break;
                case 0:
                    if(WareHouse.coffeeAmount > 0) {
                        System.out.println("\n有尚未完成的订单，无法退出");
                    }else{
                        System.out.println("\n程序已退出");
                        pool.shutdown();
                        break lo;
                    }
                    break;
                default:
                    System.out.println("\n输入错误!");
                    printMainManu();
                    break;
            }
        }
    }

    private static void printViceManu() {
        System.out.println("\n----------------------");
        System.out.println("[1]冰水(2元)    [2]手冲(30元)    [0]返回");
        System.out.println("----------------------");
    }

    private static void printMainManu() {
        System.out.println("\n----------------------");
        System.out.println("[1]点餐    [2]查看营业额");
        System.out.println("[3]查看剩余订单    [0]退出");
        System.out.println("----------------------");
    }
}