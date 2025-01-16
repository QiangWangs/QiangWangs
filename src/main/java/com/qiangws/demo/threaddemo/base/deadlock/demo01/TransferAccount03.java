package com.qiangws.demo.threaddemo.base.deadlock.demo01;

/**
 * @author 王强
 * @create 2021/7/9 19:48
 */
public class TransferAccount03 implements Runnable {

    private Account fromAccount;//转出账户
    private Account toAccount;//转入账户
    private int amount;

    public TransferAccount03(Account fromAccount, Account toAccount, int amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    @Override
    public void run() {
        Account left = null;
        Account right = null;
        System.out.println("fromAccount:" + fromAccount.hashCode() +",toAccount:" + toAccount.hashCode());
        if (fromAccount.hashCode() > toAccount.hashCode()) {
            left = toAccount;
            right = fromAccount;
        }
        while (true) {
            synchronized (left) {
                synchronized (right) {
                    if (fromAccount.getBalance() >= amount) {
                        fromAccount.debit(amount);
                        toAccount.credit(amount);
                    }
                }
            }
            //转出账户的余额
            System.out.println(fromAccount.getAccountName() + "->" + fromAccount.getBalance());
            //转入账户的余额
            System.out.println(toAccount.getAccountName() + "->" + toAccount.getBalance());
        }
    }

    public static void main(String[] args) {
        Account fromAccount = new Account("帅哥", 100000);
        Account toAccount = new Account("美女", 300000);
        Thread a = new Thread(new TransferAccount03(fromAccount, toAccount, 10), "a");
        Thread b = new Thread(new TransferAccount03(toAccount, fromAccount, 30), "b");

        a.start();
        b.start();

    }
}
