package com.xiancheng;

/**
 * @Description 懒汉式线程安全
 * @Auther GaoYi
 * @Date 2020/6/13 11:18 上午
 */
public class lanhantest {
    public static void main(String[] args) {

    }
}

class lanhan {
    private static lanhan lanhan = null;
    public lanhan() {
    }
    public static lanhan getLanhan() {
        if (lanhan == null) {//双重检查，效率更高一些
            synchronized (lanhan.class) {
                if (lanhan == null) {
                    lanhan = new lanhan();
                }
            }
        }
        return lanhan;
    }
}
