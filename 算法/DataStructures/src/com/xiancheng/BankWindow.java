package com.xiancheng;

/**
 * @Description 线程练习:银行窗口
 * @Auther GaoYi
 * @Date 2020/6/12 8:00 下午
 */
public class BankWindow {
    public static void main(String[] args) {
        window w1 = new window("窗口1");
        window w2 = new window("窗口2");
        window w3 = new window("窗口3");
        w1.start();
        w2.start();
        w3.start();
    }
}

//创建多线程方法一
class window extends Thread {
    private static int ticket = 100;
    public window(String name){
        super(name);
    }
    @Override
    public void run() {
        while (true) {
            if (ticket > 0) {
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(getName() + "卖票,票号为" + ticket);
                ticket--;
            }else {
                break;
            }
        }
    }
}

class window1 implements Runnable {
    private static int ticket = 100;
    @Override
    public void run() {
        while (true) {
            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + "卖票,票号为" + ticket);
                ticket--;
            }else{
                break;
            }
        }
    }
}
