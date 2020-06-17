package com.xiancheng;

/**
 * @Description class
 * @Auther GaoYi
 * @Date 2020/6/12 11:02 下午
 */
public class SafeMethod {
    public static void main(String[] args) {
        windowsafemethod w = new windowsafemethod();
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

class windowsafemethod implements Runnable {
    private int ticket = 100;

    //Object obj = new Object();
    @Override
    public void run() {
        show();
    }

    public synchronized void show() {//可以直接声明同步方法类型，不必写同步代码块
        while (true) {
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