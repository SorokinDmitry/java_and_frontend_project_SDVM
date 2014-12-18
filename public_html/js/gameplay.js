var ws;
var started = false;
var finished = false;
var myName = "";
var enemyName = "";


function startGame() {
    ws = new WebSocket("ws://localhost:8090/gameplay");

    ws.onopen = function() {
        console.log("Соединение установлено.");
    };

    ws.onmessage = function (event) {
        var data = JSON.parse(event.data);

        if(data.status == "DECK") {
            i = (data.y - 1) * 10 + data.x - 1;
            enemyField.get(i).set("value", "miss");
        }

        if(data.status == "start"){
            //document.getElementById("wait").style.display = "none";
            //document.getElementById("gameplay").style.display = "block";
            //document.getElementById("enemyName").innerHTML = data.enemyName;
        }

        if(data.status == "finish"){
            //document.getElementById("gameOver").style.display = "block";
            //document.getElementById("gameplay").style.display = "none";
            //if(data.win)
            //    document.getElementById("win").innerHTML = "winner!";
            //else
            //    document.getElementById("win").innerHTML = "loser!";
        }

    	if(data.status == "fire"){
            //document.getElementById("myScore").innerHTML = data.score;
        }

        if(data.status == "increment" && data.name == "${myName}"){
            //document.getElementById("myScore").innerHTML = data.score;
        }

        if(data.status == "increment" && data.name == document.getElementById("enemyName").innerHTML){
            //document.getElementById("enemyScore").innerHTML = data.score;
        }
    }

    ws.onclose = function (event) {
        console.log("Соединение разорвано.");
    }
};

function fire(x, y) {
    var message = {
        action: "fire",
        x: x,
        y: y
    }
    console.log(JSON.stringify(message));
    ws.send(JSON.stringify(message));
};