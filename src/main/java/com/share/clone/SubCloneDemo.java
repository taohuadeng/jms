package com.share.clone;

public class SubCloneDemo extends CloneDemo implements Cloneable {

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
