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
                WareHouse.money += 2;
                System.out.println("冰水好了  +2￥");
                WareHouse.coffeeType = -1;
                WareHouse.coffeeAmount--;
                break;
            case 2:
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                WareHouse.money += 30;
                System.out.println("手冲好了  +30￥");
                WareHouse.coffeeType = -1;
                WareHouse.coffeeAmount--;
                break;
        }
        WareHouse.isProduced = true;
    }
}

