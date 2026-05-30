package com.mycompany.trendstock;

public class EntradaInvalidaException extends Exception{
    public EntradaInvalidaException(String msg) {
        System.out.println(msg);
    }
}
