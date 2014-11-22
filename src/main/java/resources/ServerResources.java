package resources;

import java.io.Serializable;

/**
 * Created by Dmitry on 025 25.10.14.
 */
public class ServerResources implements Serializable {
    private static final long serialVersionUID = -3895203507200457732L;
    private int port;
    private String ip;

    public ServerResources() {
        this.port = 8080;
        this.ip = "localhost";
    }

    public ServerResources(int port, String ip) {
        this.setPort(port);
        this.setIp(ip);
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return this.ip;
    }

    public int getPort() {
        return this.port;
    }

    public String toString() {
        return ip + ":" + port;
    }
}