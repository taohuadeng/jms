package com.thd.jms;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.jetty.webapp.WebAppContext;

/**
 * jms web启动入口
 *
 * @author Fish
 * @version 1.0
 * @since 2015年3月12日8:40:53
 */
public class JmsMain {
    public static void main(String[] args) throws Exception {
        Server jettyServer = new Server();

        SocketConnector conn = new SocketConnector();
        conn.setPort(8989);
        jettyServer.setConnectors(new Connector[] { conn });

        WebAppContext wah = new WebAppContext();
        wah.setContextPath("/jms");
        wah.setWar("web");
        jettyServer.setHandler(wah);
        jettyServer.start();
    }
}
