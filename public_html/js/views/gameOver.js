define([
    'backbone'
], function(Backbone){

	var WIDTH_CANVAS = 1200,
		HEIGHT_CANVAS = 600;

    var View = Backbone.View.extend({

		tagName: 'canvas',

        initialize: function () {
			this.el.width = WIDTH_CANVAS;
            this.el.height = HEIGHT_CANVAS;
			this.el.id = "drawing";
			this.ctx = this.el.getContext('2d');
        },

        render: function (winner) {
       		this.draw(winner);
			return this.el;
        },

		draw: function(winner) {
				this.ctx.fillStyle = "black";
				this.ctx.strokeStyle = "black";
				this.ctx.font = "60px Palatino Linotype";
				this.ctx.textAlign = "center"
				this.ctx.fillText("Winner is " + winner + "!", 400, 240);
		}

	});

    return new View();
});