package com.kaka888.app;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class Client {
    public static void main(String[] args) throws NamingException {
        final Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        env.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        env.put("jboss.naming.client.ejb.context", true);

        Context ctx = new InitialContext(env);

        Object ref = ctx.lookup("RemoteEJB/MyEJB!com.kaka888.app.IMyEJB");
        IMyEJB ejb = (IMyEJB) ref;

        for (int n = 0; n < 1000; n++)
            System.out.println(ejb.add(n+1, n));
    }
}
