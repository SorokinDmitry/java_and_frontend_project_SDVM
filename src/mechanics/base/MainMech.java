package base;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Vadim on 08.11.14.
 */

public class MainMech {
    public static void main(String[] args) {
        System.out.print("Hello, World!\n");

        /*
        Queue<Long> queueUsers = new LinkedList<Long>();
        for (long i = 0; i < 5; ++i) {
            queueUsers.add(new Long(i));
        }

        while(!queueUsers.isEmpty()) {
            System.out.print(queueUsers.poll() + "\n");
        }
        */

        long idUser1 = 1;
        long idUser2 = 2;
        GameSession gameSession = new GameSession(idUser1, idUser2);


        Ship fourDecker1 = new Ship(0, 0, 3, 0);

        Ship threeDecker1 = new Ship(5, 0, 7, 0);
        Ship threeDecker2 = new Ship(0, 2, 2, 2);

        Ship twoDecker1 = new Ship(4, 2, 5, 2);
        Ship twoDecker2 = new Ship(7, 2, 8, 2);
        Ship twoDecker3 = new Ship(0, 4, 1, 4);

        Ship oneDecker1 = new Ship(3, 4, 3, 4);
        Ship oneDecker2 = new Ship(5, 4, 5, 4);
        Ship oneDecker3 = new Ship(7, 4, 7, 4);
        Ship oneDecker4 = new Ship(9, 4, 9, 4);


        ArrayList<Ship> ships = new ArrayList<>();
        ships.add(fourDecker1);
        ships.add(threeDecker1);
        ships.add(threeDecker2);
        ships.add(twoDecker1);
        ships.add(twoDecker2);
        ships.add(twoDecker3);
        ships.add(oneDecker1);
        ships.add(oneDecker2);
        ships.add(oneDecker3);
        ships.add(oneDecker4);

        //System.out.print(ships.size());
        gameSession.placeShips(ships, idUser1);
        //gameSession.placeShips(ships, idUser2);

        int[][] arr;
        arr = new int[10][10];
        arr[0][0] = 200;
        System.out.print(arr[0][0]);

    }
}
