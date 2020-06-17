package com.xiancheng;

/**
 * @Description class
 * @Auther GaoYi
 * @Date 2020/6/12 8:51 下午
 */
public class RunnableTest {
    public static void main(String[] args) {
        mThread mThread = new mThread();
        Thread thread = new Thread(mThread);
        thread.setName("mThread");
        thread.start();
    }
}
class mThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                //Thread.currentThread()相当于this，返回当前代码的线程
                System.out.println(Thread.currentThread().getName() + i + "是偶数" + "   优先级：" + getPriority());
            }
        }
    }
}
