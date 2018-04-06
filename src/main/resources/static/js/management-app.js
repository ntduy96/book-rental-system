var app = angular.module("managementApp", ["ui.router"]);

app.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/');

    $stateProvider.state({
        name: "main",
        url: "/",
        templateUrl : "/html/main-management.html"
    }).state({
        name: "orders",
        url: "/orders",
        templateUrl : "/html/order-management.html"
    }).state({
        name: "library",
        url: "/library",
        templateUrl : "/html/library-management.html"
    }).state({
        name: "library.book",
        url: "/book",
        templateUrl : "/html/book-management.html"
    }).state({
        name: "library.author",
        url: "/author",
        templateUrl : "/html/author-management.html"
    }).state({
        name: "library.category",
        url: "/category",
        templateUrl : "/html/category-management.html"
    });

}]);