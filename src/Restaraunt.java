import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by java on 10/19/2017.
 */
public class Restaraunt {
    private static final Logger logger = Logger.getLogger(Restaraunt.class);
    String name;
    static List<Cashier> cashiers;

    public Restaraunt(String name) {
        this.name = name;
        cashiers = new ArrayList<>();
        logger.info(name);
    }

    public static List<Cashier> getCashiers() {
        return cashiers;
    }

    public void setCashiers(List<Cashier> cashiers) {
        this.cashiers = cashiers;
    }
    public void addCashier(Cashier cashier){
        cashiers.add(cashier);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
