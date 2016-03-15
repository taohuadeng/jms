package com.thd.jm.go;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;

import com.jcraft.jsch.*;
import expect4j.Expect4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * SSH工具类
 *
 * @author 赵聪慧
 *         2013-4-7
 */
public class ShellHelper {
    private static final Log LOG = LogFactory.getLog(SSHHelper.class);

    private String result = "";
    private Session session = null;
    private ChannelExec openChannel = null;

    //ssh服务器的ip地址
    private String ip;
    //ssh服务器的登入端口
    private int port;
    //ssh服务器的登入用户名
    private String user;
    //ssh服务器的登入密码
    private String password;

    public ShellHelper(String ip, int port, String user, String password) {
        this.ip = ip;
        this.port = port;
        this.user = user;
        this.password = password;
        getExpect();
    }

    //获得Expect4j对象，该对用可以往SSH发送命令请求
    private Expect4j getExpect() {
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(user, ip, port);
            session.setPassword(password);
            Hashtable<String, String> config = new Hashtable<String, String>();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            openChannel = (ChannelExec) session.openChannel("exec");
        } catch (JSchException e) {
            result += e.getMessage();
        }

        return null;
    }

    private String executeCommands(String command) {
        try {
            openChannel.setCommand(command);
            int exitStatus = openChannel.getExitStatus();
            System.out.println(exitStatus);
            openChannel.connect();
            InputStream in = null;
            in = openChannel.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String buf = null;
            while ((buf = reader.readLine()) != null) {
                result += new String(buf.getBytes("gbk"), "UTF-8") + "  <br>\r\n";
            }
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (openChannel != null && !openChannel.isClosed()) {
                openChannel.disconnect();
            }
            if (session != null && session.isConnected()) {
                session.disconnect();
            }
        }
        return result;
    }


    public static void main(String args[]) {
        ShellHelper shellHelper = new ShellHelper("101.95.155.238", 4392, "guest", "guestTbc.179");
        String exec = shellHelper.executeCommands("ls;");
//        String exec = exec("101.95.155.238", "guest", "guestTbc.179", 4392, "sleep 20;ls;");
        System.out.println(exec);
    }

}
