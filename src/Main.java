/**
 * Created by java on 10/4/2017.
 */
public class Main {
    public static void main (String[]args){
     Restaraunt rest = new Restaraunt("Burger King");
        Cashier cr1 = new Cashier(1,5,5);
        Cashier cr2 = new Cashier(2,6,6);

        rest.addCashier(cr1);
        rest.addCashier(cr2);
       cr1.start();
       cr2.start();
        try {
                cr1.join();
                cr2.join();
                } catch (InterruptedException e) {
                e.printStackTrace();
                }

                }
                }
