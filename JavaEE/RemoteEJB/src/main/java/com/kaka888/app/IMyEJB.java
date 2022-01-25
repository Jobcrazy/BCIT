package com.kaka888.app;

import javax.ejb.Remote;

@Remote
public interface IMyEJB {
    public int add(int a, int b);
}
