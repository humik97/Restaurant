import org.apache.log4j.Logger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by java on 10/2/2017.
 */
public class Visitor  {
    private static Logger logger = Logger.getLogger(Visitor.class);
   private Lock l ;
   private String name;
   private int cashnumb;
   private int orders;

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
                    logger.info(cashier.getVisitors().getLast().getName()+"перешел с кассы # "+cashier.getCashnumber()+" на кассу # "+cashier1.getCashnumber());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Visitor visitor = (Visitor) o;

        if (cashnumb != visitor.cashnumb) return false;
        if (orders != visitor.orders) return false;
        if (l != null ? !l.equals(visitor.l) : visitor.l != null) return false;
        return name != null ? name.equals(visitor.name) : visitor.name == null;

    }

    @Override
    public int hashCode() {
        int result = l != null ? l.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + cashnumb;
        result = 31 * result + orders;
        return result;
    }
}