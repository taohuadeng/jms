package com.thd.jm.go;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * SSH工具类
 *
 * @author 赵聪慧
 *         2013-4-7
 */
public class SSHHelper {
    private static final Log LOG = LogFactory.getLog(SSHHelper.class);

    /**
     * 远程 执行命令并返回结果调用过程 是同步的（执行完才会返回）
     *
     * @param host    主机名
     * @param user    用户名
     * @param psw     密码
     * @param port    端口
     * @param command 命令
     * @return 命令执行结果
     */
    public static String exec(String host, String user, String psw, int port, String command) {
        String result = "";
        Session session = null;
        ChannelExec openChannel = null;
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(user, host, port);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.setPassword(psw);
            session.connect();
            openChannel = (ChannelExec) session.openChannel("exec");
            openChannel.setCommand(command);
            int exitStatus = openChannel.getExitStatus();
            System.out.println(exitStatus);
            openChannel.connect();
            InputStream in = openChannel.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String buf = null;
            while ((buf = reader.readLine()) != null) {
                result += new String(buf.getBytes("gbk"), "UTF-8") + "  <br>\r\n";
            }
        } catch (JSchException e) {
            result += e.getMessage();
        } catch (IOException e) {
            result += e.getMessage();
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
        String exec = exec("101.95.155.238", "guest", "guestTbc.179", 4392, "sleep 20;ls;");
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://10.0.0.1:5433/ems", "eln4user",
                    "elnforuser");

        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;

        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
        System.out.println(exec);
    }
}
