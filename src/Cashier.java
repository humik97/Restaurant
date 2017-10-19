/**
 * Created by java on 10/2/2017.
 */

import org.apache.log4j.Logger;

import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Разработать многопоточное приложение.
 Использовать возможности, предоставляемые пакетом java.util.concurrent.
 Не использовать слово synchronized.
 Все сущности, желающие получить доступ к ресурсу, должны быть потоками.
 Использовать возможности ООП.1
 Не использовать графический интерфейс. Приложение должно быть кон-
 сольным
 * Свободная касса. В ресторане быстрого обслуживания есть несколько
 касс. Посетители стоят в очереди в конкретную кассу, но могут перейти
 в другую очередь при уменьшении или исчезновении там очереди.
 */
public class Cashier extends Thread {
    private static final Logger logger = Logger.getLogger(Cashier.class);
    private Deque<Visitor> visitors;
    private int length;
    private int cashnumber;
    private int servicetime;
    private Condition cond;
    private Lock l;


    public Cashier(int cashnumber, int servicetime, int length) {
        logger.info("касса # "+cashnumber);
        l = new ReentrantLock();
        cond = l.newCondition();
        this.cashnumber = cashnumber;
        visitors = new LinkedList<>();
        Add(visitors, length);
        this.length = length;
        this.servicetime = servicetime;
    }

    public void serviceClient(Visitor visitor) {
        visitor = visitors.poll();
        try {
            Thread.sleep(servicetime * visitor.getOrders());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info(visitor.getName() + " обслужила касса " + this.getCashnumber());
    }


    public void Add(Deque<Visitor> visitors, int length) {
        Random random = new Random();
        int rand = random.nextInt(10);
        for (int a = 1; a <= length; a++) {
            visitors.add(new Visitor("visitior " + this.getCashnumber() + "-" + a, rand, getCashnumber()));
            logger.info(visitors.getLast().getName() + " выбрал кассу # " + this.getCashnumber());
        }
    }

    public int getCashnumber() {
        return cashnumber;
    }

    public Deque<Visitor> getVisitors() {
        return visitors;
    }

    public void addVisitor(Visitor visitor) {
        visitors.add(visitor);
    }

    public void removeVisitor(Visitor visitor) {
        visitors.remove(visitor);
    }

    public int getLength() {
        return length;
    }

    public int getServicetime() {
        return servicetime;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setServicetime(int servicetime) {
        this.servicetime = servicetime;
    }

    public void setVisitors(Deque<Visitor> visitors) {
        this.visitors = visitors;
    }

    public void setCashnumber(int cashnumber) {
        this.cashnumber = cashnumber;
    }



    @Override
    public void run() {
        while (true) {
            if (Math.abs(Restaraunt.getCashiers().get(0).getVisitors().size() - Restaraunt.getCashiers().get(1).getVisitors().size()) >= 2) {
                try {
                    visitors.getLast().chooseAnother();
                } catch (NoSuchElementException e) {
                    }
            }
            if (visitors.size() > 0) {
                serviceClient(visitors.getFirst());
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cashier cashier = (Cashier) o;

        if (length != cashier.length) return false;
        if (cashnumber != cashier.cashnumber) return false;
        if (servicetime != cashier.servicetime) return false;
        if (visitors != null ? !visitors.equals(cashier.visitors) : cashier.visitors != null) return false;
        if (cond != null ? !cond.equals(cashier.cond) : cashier.cond != null) return false;
        return l != null ? l.equals(cashier.l) : cashier.l == null;

    }

    @Override
    public int hashCode() {
        int result = visitors != null ? visitors.hashCode() : 0;
        result = 31 * result + length;
        result = 31 * result + cashnumber;
        result = 31 * result + servicetime;
        result = 31 * result + (cond != null ? cond.hashCode() : 0);
        result = 31 * result + (l != null ? l.hashCode() : 0);
        return result;
    }
}
