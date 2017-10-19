import org.apache.log4j.Logger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by java on 10/2/2017.
 */
public class Visitor  {
    private static Logger logger = Logger.getLogger(Visitor.class);
    Lock l ;
    String name;
    int cashnumb;
    int orders;

    public Visitor(String name, int orders, int cashnumb) {
        l=new ReentrantLock();
        this.name = name;
        this.orders = orders;
        this.cashnumb = cashnumb;
    }

    public void chooseAnother() {
        l.lock();
        try {
            Cashier cashier = Restaraunt.getCashiers().get(cashnumb - 1);
            for (Cashier cashier1 : Restaraunt.getCashiers()) {
                if (cashier.getVisitors().size() > cashier1.getVisitors().size()) {
                    logger.info(this.getName()+"blocked");
                    cashier1.addVisitor(cashier.getVisitors().getLast());
                    cashier.removeVisitor(cashier.getVisitors().getLast());
                    logger.info(cashier.getVisitors().getLast().getName()+"перешел с кассы # "+cashier.cashnumber+" на кассу # "+cashier1.cashnumber);
                    logger.info(this.getName()+"unblocked");
                }
            }
        } finally {
            l.unlock();
        }
    }


    public String getName() {
        return name;
    }

    public int getCashnumb() {
        return cashnumb;
    }

    public int getOrders() {
        return orders;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public void setCashnumb(int cashnumb) {
        this.cashnumb = cashnumb;
    }
}