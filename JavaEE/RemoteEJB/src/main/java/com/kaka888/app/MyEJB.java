package com.kaka888.app;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Remote
@Stateless
public class MyEJB implements IMyEJB {
    @Override
    public int add(int a, int b) {
        return a + b;
    }
}
