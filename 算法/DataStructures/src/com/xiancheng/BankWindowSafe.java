package com.xiancheng;

/**
 * @Description 售票安全方式
 * @Auther GaoYi
 * @Date 2020/6/12 10:31 下午
 */
public class BankWindowSafe {
    public static void main(String[] args) {
        windowsafe w = new windowsafe();
        Thread w1 = new Thread(w);
        Thread w2 = new Thread(w);
        Thread w3 = new Thread(w);
        w1.setName("窗口1");
        w2.setName("窗口2");
        w3.setName("窗口3");
        w1.start();
        w2.start();
        w3.start();
    }
}

class windowsafe implements Runnable {
    private int ticket = 100;
    //Object obj = new Object();

    public windowsafe() {
    }

    @Override
    public void run() {
        while (true) {
            //synchronized (this) {//同步代码块，多线程共用一把锁时，确保一次只执行一个线程，obj即为锁 //此时this为唯一(慎用)
            synchronized (BankWindowSafe.class){//类也为对象,类对象也是唯一的，只会加载一次
                if (ticket > 0) {
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "卖票,票号为" + ticket);
                    ticket--;
                } else {
                    break;
                }
            }
        }
    }
}

class windowsafe1 extends Thread {
    private static int ticket = 100;
    private static Object obj = new Object();
    public windowsafe1(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (true) {
            synchronized (obj) {
                if (ticket > 0) {
                    System.out.println(getName() + "卖票,票号为" + ticket);
                    ticket--;
                } else {
                    break;
                }
            }
        }
    }
}
