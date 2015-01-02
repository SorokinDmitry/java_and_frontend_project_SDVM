require.config({								// конфигурация require
    urlArgs: "_=" + (new Date()).getTime(),
    baseUrl: "js",								// (основной)путь туда, где лежат все модули (папка js)
    paths: { 									// здесь то, что не лежит в baseUrl + им заданы псевдонимы
        jquery: "lib/jquery",
        underscore: "lib/underscore",
        backbone: "lib/backbone",
        jquerymobile: "lib/jquery.mobile-1.4.5"
    },
    shim: {										// сторонние модули
        'backbone': {
            deps: ['underscore', 'jquery'],		// зависимости
            exports: 'Backbone'
        },
        'underscore': {
            exports: '_'
        }
    }
});

define([
	'backbone',
	'router'
	], function(Backbone, router) {
    Backbone.history.start();
});