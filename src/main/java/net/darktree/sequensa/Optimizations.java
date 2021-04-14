package net.darktree.sequensa;

public class Optimizations {

    int flag = 0;

    public static Optimizations empty() {
        return new Optimizations();
    }

    public int getFlags() {
        return flag;
    }

    public Optimizations name() {
        this.flag |= 0b1000;
        return this;
    }

    public Optimizations pureExpr() {
        this.flag |= 0b0100;
        return this;
    }

    public Optimizations strPreGen() {
        this.flag |= 0b0010;
        return this;
    }

    public Optimizations all() {
        this.flag |= 0b1111;
        return this;
    }

}
