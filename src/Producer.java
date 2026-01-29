import java.time.LocalDateTime;
import java.util.ArrayList;

public class Producer implements Runnable {
    //Runnable实现类用来提交咖啡订单的
    @Override
    public void run() {

        switch (WareHouse.coffeeType) {
            case 1:
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("\n冰水好了");
                WareHouse.coffeeType = -1;
                WareHouse.coffeeAmount--;
                WareHouse.orders.add(new CoffeeOrder(WareHouse.orders.size()+1,
                        "冰水",
                        2,
                        LocalDateTime.now()
                ));
                JsonCreator.writeOrderToJson(WareHouse.orders
                        , "./coffee_orders.json"
                );
                break;
            case 2:
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("\n手冲好了");
                WareHouse.coffeeType = -1;
                WareHouse.coffeeAmount--;
                WareHouse.orders.add(new CoffeeOrder(WareHouse.orders.size()+1,
                        "手冲咖啡",
                        30,
                        LocalDateTime.now()
                ));
                JsonCreator.writeOrderToJson(WareHouse.orders
                        , "./coffee_orders.json"
                );
                break;
        }
        WareHouse.isProduced = true;
    }
}

