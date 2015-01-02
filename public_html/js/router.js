define([
    'backbone',
    'views/main',
    'views/game',
    'views/login',
	'views/scoreboard',
	'views/manager',
	'views/joystick'
], function(Backbone, mainScreen, gameScreen, loginScreen, scoreboardScreen, viewManager, joystickScreen) {

    var Router = Backbone.Router.extend({
	
        routes: {
            'scoreboard': 'scoreboardAction',
            'game': 'gameAction',
            'login': 'loginAction',
            'joystick': 'joystickAction',
            '*default': 'defaultActions'
        },
		
		initialize : function() {
			viewManager.addView(gameScreen);
			viewManager.addView(scoreboardScreen);
			viewManager.addView(mainScreen);
			viewManager.addView(loginScreen);
            $('#loader').hide();
		},
		
        defaultActions: function () {
            mainScreen.show();
        },
		
        scoreboardAction: function () {
            scoreboardScreen.show();
        },
		
        gameAction: function () {
            $.ajax({
                type: "POST",
            	url: "/auth/",
            	statusCode: {
            	    200: function (response) {
            			//result = JSON.parse(response.responseText);
                        //login = result.login;
                        console.log("SC_OK");
                        gameScreen.show();
            	    },
            		403: function (response) {
            			//alert(response.status);
                        console.log("SC_FORBIDDEN");
                        loginScreen.show();
            		}
            	}
            });
        },

        loginAction: function () {
            loginScreen.show();
        },

        joystickAction: function() {
            joystickScreen.show();
        }
		
    });
	
    return new Router();
});