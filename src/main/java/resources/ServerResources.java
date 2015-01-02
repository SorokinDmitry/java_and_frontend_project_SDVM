package resources;

import java.io.Serializable;

/**
 * Created by Dmitry on 025 25.10.14.
 */
public class ServerResources implements Serializable {
    private static final long serialVersionUID = -3895203507200457732L;
    private String host;
    private int port;
    private String db_name;
    private int idleTime;

    public ServerResources() {
        host = "localhost";
        port = 8080;
        db_name = "FORUMS_TP";
        idleTime = 60 * 1000;
    }

    public int getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }

    public String getDb_name() {
        return db_name;
    }

    public int getIdleTime() {
        return idleTime;
    }

    public String toString() {
        return " Host: " + host + " port: " + port + " db_name: " + db_name;
    }
}