package com.xiancheng;

/**
 * @Description class
 * @Auther GaoYi
 * @Date 2020/6/12 6:21 下午
 */
public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        MyThread1 myThread1 = new MyThread1("线程二");//给线程命名方式1
        myThread.setName("线程一");//给线程命名方式2
        myThread.setPriority(8);
        myThread.start();//1.启动当前线程 2.自动调用run方法
        myThread1.start();
        Thread.currentThread().setName("主线程");//给主线程命名
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "main=======" + i + "   优先级：" + Thread.currentThread().getPriority());
            if (i == 20) {
                System.out.println("加入" + myThread1.getName());
                myThread1.join();//join用于加入线程,中途加入的线程执行完后,调用此线程的线程才会继续执行
            }
        }
        if (myThread1.isAlive()) {//isAlive判断当前线程是否存活
            System.out.println("线程二存活");
        } else if (!myThread1.isAlive()) {
            System.out.println("线程二死亡");
        }
    }
}

class MyThread extends Thread {
    @Override
    public void run() {//不可直接调用run
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                //Thread.currentThread()相当于this，返回当前代码的线程
                System.out.println(Thread.currentThread().getName() + i + "是偶数" + "   优先级：" + getPriority());
            }
            if (i % 20 == 0) {
                System.out.println("释放" + this.getName());
                this.yield();//yield用于暂时释放当前线程
            }
        }
    }
}

class MyThread1 extends Thread {
    public MyThread1(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 != 0) {
                try {
                    sleep(100);//sleep用于表示线程执行一次操作后休眠时间
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + i + "是奇数" + "   优先级：" + this.getPriority());
            }
        }
    }
}