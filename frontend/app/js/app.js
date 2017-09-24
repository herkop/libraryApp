var app = angular.module('Library', ['ui.router', 'ngResource']);

app.config(function($stateProvider, $urlRouterProvider, $locationProvider, $qProvider) {

    $stateProvider
        .state('home', {
            url: '/',
            templateUrl: 'view/main.html',
            controller: 'MainController',
            resolve: {}
        })
        .state('search', {
            url: '/search/:searchingParam/:searchingText',
            templateUrl: 'view/main.html',
            controller: 'SearchController'
            })
        .state('book', {
            url: '/book/:bookId',
            templateUrl: 'view/book.html',
            controller: 'BookController'
        })
        .state('lending', {
            url: '/lending/:bookId',
            templateUrl: 'view/lending.html',
            controller: 'BookController'
        })
        .state('return', {
            url: '/return/:bookId',
            templateUrl: 'view/return.html',
            controller: 'BookController'
        });
    $urlRouterProvider.otherwise("/");
    $locationProvider.hashPrefix('');
    $qProvider.errorOnUnhandledRejections(false);
});