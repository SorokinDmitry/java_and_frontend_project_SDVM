package sockets;

import base.UserGame;
import mechanics.Codes;
import mechanics.GameMechanics;
import mechanics.Ship;
import mechanics.ShipImpl;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Dmitry on 025 25.10.14.
 */

@WebSocket
public class GameWebSocket {
    private String myName;
    private Session session;
    private GameMechanics gameMechanics;
    private WebSocketService webSocketService;

    public GameWebSocket(String myName, GameMechanics gameMechanics, WebSocketService webSocketService) {
        this.myName = myName;
        this.gameMechanics = gameMechanics;
        this.webSocketService = webSocketService;
    }

    public String getMyName() {
        return myName;
    }

    public void startGame(String enemyName) {
        try {
            JSONObject jsonStart = new JSONObject();
            jsonStart.put("status", "start");
            jsonStart.put("enemyName", enemyName);
            session.getRemote().sendString(jsonStart.toJSONString());
        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }

    public void ready() {
        try {
            JSONObject jsonStart = new JSONObject();
            jsonStart.put("status", "ready");
            session.getRemote().sendString(jsonStart.toJSONString());
        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }

    public void gameOver(String winner) {
        try {
            JSONObject json = new JSONObject();
            json.put("status", "finish");
            json.put("winner", winner);
            session.getRemote().sendString(json.toJSONString());
        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }

    @OnWebSocketMessage
    public void onMessage(String data) throws JSONException {
        org.json.JSONObject jsonObject = new org.json.JSONObject(data);
        System.out.println(data);

        if ( jsonObject.getString("action").equals("setShips")) {
            JSONArray jsonShips = jsonObject.getJSONArray("ships");
            ArrayList<Ship> ships = new ArrayList<>();
            for (int i = 0; i < jsonShips.length(); ++i ) {
                org.json.JSONObject jsonShip = jsonShips.getJSONObject(i);
                Ship ship = new ShipImpl(jsonShip.getInt("x"), jsonShip.getInt("y"), jsonShip.getInt("x")+jsonShip.getInt("width")-1, jsonShip.getInt("y")+jsonShip.getInt("height")-1);
                ships.add(ship);
            }
            Codes result = gameMechanics.setShips(myName, ships);
            System.out.println(result);
            try {
                JSONObject json = new JSONObject();
                json.put("status", result.toString());
                session.getRemote().sendString(json.toJSONString());
            } catch (Exception e) {
                System.out.print(e.toString());
            }
        }

        if ( jsonObject.getString("action").equals("fire")) {
            Codes result = gameMechanics.fire(myName,jsonObject.getInt("x")-1,jsonObject.getInt("y")-1);
            System.out.println(result);

            try {
                JSONObject json = new JSONObject();
                json.put("status", result.toString());
                json.put("x", jsonObject.getInt("x"));
                json.put("y", jsonObject.getInt("y"));
                session.getRemote().sendString(json.toJSONString());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @OnWebSocketConnect
    public void onOpen(Session session) {
        setSession(session);
        webSocketService.addUser(this);
        gameMechanics.addUser(myName);
        System.out.print("WebSocketConnect");
    }

    public void setMyScore(UserGame user) {
        JSONObject jsonStart = new JSONObject();
        jsonStart.put("status", "increment");
        jsonStart.put("name", myName);
        // Для запуска комменчу
        //jsonStart.put("score", user.getMyScore());
        try {
            session.getRemote().sendString(jsonStart.toJSONString());
        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }

    public void setEnemyScore(UserGame user) {
        JSONObject jsonStart = new JSONObject();
        jsonStart.put("status", "increment");
        jsonStart.put("name", user.getEnemyName());
        // Для запуска комменчу
        //jsonStart.put("score", user.getEnemyScore());
        try {
            session.getRemote().sendString(jsonStart.toJSONString());
        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
    }
}

