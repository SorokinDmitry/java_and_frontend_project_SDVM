define([
    	'backbone',
    	'tmpl/game',
	'views/waitEnemy',
	'views/buildField',
	'views/gameField',
	'views/gameOver',
	'collections/yourField',
	'gameplay'
], function(Backbone, tmpl, waitEnemy, build, game, gameOver, field, gameplay) {

	var View = Backbone.View.extend({

		template: tmpl,
       		className: 'wrap',
		collection: field,

		events: {
			"click button.fieldBattle__ready": "ready"
		},

	       initialize: function() {
	            	gameplay.setGame(this);
			$('body').append(this.el);
			//this.render_build();
			this.render_wait();
			this.hide();
	       },

		ready: function() {
			//this.render_game();
			gameplay.setShips();
			this.render_wait();
		},

		render_wait: function() {
	           	this.$el.html(this.template);
	        	this.$el.find(".fieldBattle__myField").append(waitEnemy.render());
	            return this;
	       },

		render_build: function() {
	            	this.$el.html(this.template);
			this.$el.find(".fieldBattle__myField").append(build.render());
			return this;
	      	},

		render_game: function() {
	            	this.$el.html(this.template);
			this.$el.find(".fieldBattle__myField").append(game.render());
			return this;
	       },

	        render_gameOver: function(winner) {
	        	this.$el.html(this.template);
			this.$el.find(".fieldBattle__myField").append(gameOver.render(winner));
			return this;
	       },

       	 	show: function() {
            		this.$el.show();
            		gameplay.startGame();
			this.trigger("show", this);
        	},

      		hide: function() {
       			this.$el.hide();
        	}
	});

    	return new View();

});