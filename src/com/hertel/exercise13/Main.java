package com.hertel.exercise13;

class CheckingAccount {
    private int balance;

    public CheckingAccount(int initialBalance)

    {
        balance = initialBalance;
    }

    public int withdraw(int amount)
    {
        if (amount <= balance) {
            try {
                Thread.sleep((int) (Math.random() * 200));
            } catch (InterruptedException ie) {
            }

            balance -= amount;
        }
        return balance;
    }
}

public class Main {
    public static void main(String[] args) {
        CheckingAccount account = new CheckingAccount(100);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                for (int i = 0; i < 10; i++) {

                    System.out.println(name + " tries to withdraw $10, balance: " +
                            account.withdraw(10));

                }
            }
        };



        Thread thdHusband = new Thread(r);
        thdHusband.setName("Husband");

        Thread thdWife = new Thread(r);
        thdWife.setName("Wife");

        synchronized(account) {
            thdHusband.start();
            thdWife.start();
        }
    }
}