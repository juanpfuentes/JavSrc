package com.trifulcas.clases;

import java.util.ArrayList;

public class Genericas implements Cloneable {
    protected int num;
    public ArrayList<String> nombres=new ArrayList<>();

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Object clone()throws CloneNotSupportedException{
        return (Genericas)super.clone();
    }
}
