var app = angular.module("home", ["ui.router", "ngCookies"]);

app.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/book');

    $stateProvider.state("home", {
        url: "",
        views: {
            "": {},
            "cart": {
                templateUrl: "/html/home-cart.html",
                controller: "cartCtrl"
            }
        }
    }).state("home.book", {
        url: "/book",
        templateUrl : "/html/home-book.html",
        controller: "bookCtrl"
    }).state({
        name: "home.book.detail",
        url: "/:slug",
        templateUrl : "/html/home-book-detail.html",
        controller: "bookDetailCtrl"
    }).state({
        name: "home.category",
        url: "/category",
        templateUrl : "/html/home-category.html",
        controller: "categoryCtrl"
    }).state({
        name: "home.author",
        url: "/author",
        templateUrl : "/html/home-author.html",
        controller: "authorCtrl"
    });

}]);
