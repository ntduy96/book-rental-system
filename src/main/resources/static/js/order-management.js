app.service("OrderService", function ($http) {

    var orders = [];
    var orderDetails = [];

    this.getOrders = function () {
        return orders;
    };

    this.fetchOrders = function () {
        $http.get("/api/hoadon")
            .then(function (response) {
                orders = response.data;
            });
    };

    this.clearOrders = function () {
        orders = [];
    };

    this.getOrderDetails = function (maHoaDon) {
        return orderDetails[maHoaDon];
    };

    this.fetchOrderDetails = function (maHoaDon) {
        $http.get("/api/hoadon/" + maHoaDon)
            .then(function (response) {
                orderDetails[maHoaDon] = response.data;
            });
    };

    this.deleteOrder = function (maHoaDon) {
        return $http.delete("/api/hoadon/" + maHoaDon);
    };

    this.clearOrdersDetails = function (maHoaDon) {
        delete orderDetails[maHoaDon];
    };

});

app.controller("orderCtrl", function ($scope, OrderService) {

    $scope.hoaDons = [];

    $scope.$watch(function () {
        return OrderService.getOrders();
    }, function () {
        $scope.hoaDons = OrderService.getOrders();
    });

    OrderService.fetchOrders();

});

app.controller("orderDetailCtrl", function ($scope, $stateParams, $state, OrderService) {

    $scope.hoaDon = {};
    $scope.editedHoaDon = {};

    $scope.$watch(function () {
        return OrderService.getOrderDetails($stateParams.maHoaDon);
    }, function () {
        $scope.hoaDon = OrderService.getOrderDetails($stateParams.maHoaDon);
        $scope.editedHoaDon = Object.assign({}, $scope.hoaDon);
    });

    OrderService.fetchOrderDetails($stateParams.maHoaDon);

    $scope.closeModal = function () {
        $state.go("^", { inherite: true });
    };

    $scope.deleteHoaDon = function () {
        OrderService.deleteOrder($stateParams.maHoaDon)
            .then(function (response) {
                if (response.status === 200) {
                    $scope.closeModal();
                    OrderService.clearOrders();
                    OrderService.fetchOrders();
                }
            });
    };

});
