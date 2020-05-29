package com.pojo;

public class Userspace {
    public double maxsize, usesize;

    public Userspace() {
    }

    public Userspace(double maxsize, double usesize) {
        this.maxsize = maxsize;
        this.usesize = usesize;
    }

    public double getMaxsize() {
        return maxsize;
    }

    public void setMaxsize(double maxsize) {
        this.maxsize = maxsize;
    }

    public double getUsesize() {
        return usesize;
    }

    public void setUsesize(double usesize) {
        this.usesize = usesize;
    }

    @Override
    public String toString() {
        return "Userspace{" +
                "maxsize=" + maxsize +
                ", usesize=" + usesize +
                '}';
    }
}
