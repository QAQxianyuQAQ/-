import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class WareHouse {
    //公共区
    public static boolean isProduced =  false;
    public static int coffeeType = -1;
    public static int money = 0;
    public static int coffeeAmount = 0;
    public static int coffeeCount = 0;
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition produce = lock.newCondition();
    public static Condition consume = lock.newCondition();
}
