package com.trifulcas.clases;

import java.util.ArrayList;
import java.util.List;

public class Stack <E> {
    List<E> stack;

    public Stack() {
        stack=new ArrayList<>();
    }

    public void push(E elemento){
        stack.add(elemento);
    }

    public E pop(){
        return stack.remove(0);
    }

    public int length(){
        return stack.size();
    }
}
