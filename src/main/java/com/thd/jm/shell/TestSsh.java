package com.thd.jm.shell;

import com.sshtools.j2ssh.SftpClient;
import com.sshtools.j2ssh.SshClient;
import com.sshtools.j2ssh.authentication.AuthenticationProtocolState;
import com.sshtools.j2ssh.authentication.PasswordAuthenticationClient;
import com.sshtools.j2ssh.connection.ChannelOutputStream;
import com.sshtools.j2ssh.session.SessionChannelClient;
import com.sshtools.j2ssh.sftp.SftpFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class TestSsh {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        SshClient client = new SshClient();
        try {
            ConsoleKnownHostsKeyVerification console = new ConsoleKnownHostsKeyVerification();
            client.connect("101.95.155.238", 4392, console);//IP和端口
            //设置用户名和密码
            PasswordAuthenticationClient pwd = new PasswordAuthenticationClient();
            pwd.setUsername("guest");
            pwd.setPassword("guestTbc.179");
            int result = client.authenticate(pwd);
            if (result == AuthenticationProtocolState.COMPLETE) {//如果连接完成
                SftpClient sftp = client.openSftpClient();
                //List<SftpFile> list = sftp.ls("ls /eln4/");
                List<SftpFile> list = sftp.ls();
                for (SftpFile f : list) {
                    String filename = f.getFilename();
                    if(filename.startsWith(".")){
                        continue;
                    }
                    System.out.println(filename);
                    //下载文件
                        /* FileOutputStream fos = new FileOutputStream(new File("c:/1.txt"));
	                	 FileAttributes fa =sftp.get(f.getAbsolutePath(),fos);
	                	 fos.write(fa.toByteArray());
	                	 fos.close();*/

                    //取日志(取2012-09-12 删除用户  的日志)
                    /*if (f.getFilename().endsWith(".out") || f.getFilename().endsWith(".log")) {
                        SessionChannelClient session = client.openSessionChannel();
                        if (session.startShell()) {
                            ChannelOutputStream writer = session.getOutputStream();
                            writer.write("grep -E '2012-09-12  删除用户'\n".getBytes());
                            writer.flush();
                            writer.write("exit\n".getBytes());
                            writer.flush();
                            BufferedReader in = new BufferedReader(new InputStreamReader(session.getInputStream()));
                            BufferedReader err = new BufferedReader(new InputStreamReader(session.getStderrInputStream()));
                            String line;
                            while ((line = in.readLine()) != null) {
                                System.out.println(line);
                            }
                            System.out.println("------------------------");
                            while ((line = err.readLine()) != null) {
                                System.out.println(line);
                            }
                        }
                    }*/
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

