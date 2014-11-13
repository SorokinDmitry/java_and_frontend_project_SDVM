package resources;

import java.io.Serializable;

/**
 * Created by Dmitry on 025 25.10.14.
 */
public class ServerResources implements Serializable {
    private static final long serialVersionUID = -3895203507200457732L;
    private int port;

    public ServerResources() {
        this.port = 8080;
    }

    public ServerResources(int port) {
        this.setPort(port);
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return this.port;
    }

    public String toString() {
        return "Host: " + port;
    }
}