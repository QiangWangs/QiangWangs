package com.qiangws.demo.threaddemo.base.deadlock.demo01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 王强
 * @create 2021/7/9 19:48
 */
public class TransferAccount02 implements Runnable {

    private Account fromAccount;//转出账户
    private Account toAccount;//转入账户
    private int amount;
    Lock fromLock = new ReentrantLock();
    Lock toLock = new ReentrantLock();

    public TransferAccount02(Account fromAccount, Account toAccount, int amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    @Override
    public void run() {
        while (true) {
            if (fromLock.tryLock()) {//不进行等待没获取到锁返回false 和lock区别就是lock等待,只能被unLock释放
                if (toLock.tryLock()) {
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
        Thread a = new Thread(new TransferAccount02(fromAccount, toAccount, 10));
        Thread b = new Thread(new TransferAccount02(toAccount, fromAccount, 30));

        a.start();
        b.start();
    }
}
