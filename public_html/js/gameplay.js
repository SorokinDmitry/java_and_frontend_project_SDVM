define([
    'backbone',
    'collections/enemyField',
    'collections/ships'
], function(Backbone, enemyField, ships) {

    var ws,
        started = false,
        finished = false,
        myName = "",
        enemyName = "",
        SHIP_NUMBER = 10,
        WIDTH_CELL = 40,
        HEIGHT_CELL = 40,
        game = this;

    var View = Backbone.View.extend({
        setGame: function(currentGame) {
            game = currentGame;
        },

        startGame: function() {
            ws = new WebSocket("ws://" + location.origin.substring(7) + "/gameplay");
            //console.log(location.origin.substring(7));
            ws.onopen = function() {
                console.log("Соединение установлено.");
            };
            ws.onmessage = function(event) {
                var data = JSON.parse(event.data);
                console.log(data);

                if (data.status == "start") {
                    console.log(game);
                    game.render_build();
                    enemyName = data.enemyName;
                }

                if (data.status == "ERROR") {
                    game.render_build();
                }

                if (data.status == "ready") {
                    game.render_game();
                }

                if (data.status == "DECK"  || data.status == "GAME_OVER" || data.status == "KILLED") {
                    i = (data.y - 1) * 10 + data.x - 1;
                    enemyField.get(i).set("value", "hurt");
                }

                if (data.status == "EMPTY") {
                    i = (data.y - 1) * 10 + data.x - 1;
                    enemyField.get(i).set("value", "miss");
                }

                if (data.status == "finish") {
                    game.render_gameOver(data.winner);
                }

            };
            ws.onclose = function (event) {
                console.log("Соединение разорвано.");
            };
        },

        fire: function(x, y) {
            var message = {
                action: "fire",
                x: x,
                y: y
            }
            console.log(JSON.stringify(message));
            ws.send(JSON.stringify(message));
        },

        setShips: function() {
            var currentShip,
                jsonships = new Array(10),
                message;
            for ( i = 0; i < SHIP_NUMBER; i++ ) {
                currentShip = ships.get(i);
                jsonships[i] = {
                    x: (currentShip.get("y") - 10) / WIDTH_CELL,
                    y: (currentShip.get("x") - 10) / HEIGHT_CELL,
                    width: currentShip.get("h") / WIDTH_CELL,
                    height: currentShip.get("w") /HEIGHT_CELL
                };
            }
            message = {
                action: "setShips",
                ships: jsonships
                //"ships": [
                //    {
                //        x: 3,
                //        y: 2,
                //        width: 2,
                //        height: 1
                //    },
                //    {
                //        x: 3,
                //        y: 1,
                //        width: 1,
                //        height: 2
                //    }
                //]
            };
            console.log(JSON.stringify(message));
            ws.send(JSON.stringify(message));
        }
    });

    return new View();
});