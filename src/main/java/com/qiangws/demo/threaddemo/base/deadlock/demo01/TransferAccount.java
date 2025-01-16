package com.qiangws.demo.threaddemo.base.deadlock.demo01;

/**
 * @author 王强
 * @create 2021/7/9 19:48
 */
public class TransferAccount implements Runnable {

    private Account fromAccount;//转出账户
    private Account toAccount;//转入账户
    private int amount;
    Allocator allocator;

//    public TransferAccount(Account fromAccount, Account toAccount, int amount) {
//        this.fromAccount = fromAccount;
//        this.toAccount = toAccount;
//        this.amount = amount;
//    }


    public TransferAccount(Account fromAccount, Account toAccount, int amount, Allocator allocator) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.allocator = allocator;
    }

//    @Override
//    public void run() {
//        while(true){
//            synchronized (fromAccount){
//                synchronized (toAccount){
//                    if(fromAccount.getBalance()>=amount){
//                        fromAccount.debit(amount);
//                        toAccount.credit(amount);
//                    }
//                }
//            }
//            //转出账户的余额
//            System.out.println(fromAccount.getAccountName()+"->"+fromAccount.getBalance());
//            //转入账户的余额
//            System.out.println(toAccount.getAccountName()+"->"+toAccount.getBalance());
//        }
//    }

    @Override
    public void run() {
        while (true) {
            if (allocator.apply(fromAccount, toAccount)) {
                try {
                    synchronized (fromAccount) {
                        synchronized (toAccount) {
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
                } finally {
                    allocator.free(fromAccount, toAccount);
                }
            }

        }
    }

    public static void main(String[] args) {
        Account fromAccount = new Account("帅哥", 100000);
        Account toAccount = new Account("美女", 300000);
        Allocator allocator = new Allocator();
//        Thread a = new Thread(new TransferAccount(fromAccount, toAccount, 10));
//        Thread b = new Thread(new TransferAccount(toAccount, fromAccount, 30));
        Thread a = new Thread(new TransferAccount(fromAccount, toAccount, 10, allocator), "a");
        Thread b = new Thread(new TransferAccount(toAccount, fromAccount, 30, allocator), "b");

        a.start();
        b.start();

    }
}
