var hackathonTeamOne = angular.module('hackathonTeamOne', ['ngMaterial', 'ngAnimate', 'ngMessages', 'ngAria', 'ui.router', 'md.data.table',
    'ngResource', 'n3-pie-chart']);

(function(app) {
    app.config(['$stateProvider', '$urlRouterProvider', '$qProvider', function($stateProvider, $urlRouterProvider, $qProvider) {

        $qProvider.errorOnUnhandledRejections(false);

        $urlRouterProvider.otherwise('/');

        $stateProvider.state('stats', {
            url: '/',
            templateUrl: 'partials/stats-partial.html',
            controller: 'StatsController',
        })

        .state('mutant', {
            url: '/mutant',
            templateUrl: 'partials/mutant-partial.html',
            controller: 'MutantController'
        })

    }])

    app.controller('AppCtrl', ['$scope', '$mdToast', function ($scope, $mdToast) {
        $scope.currentNavItem = "stats";

    }]);

})(hackathonTeamOne);
