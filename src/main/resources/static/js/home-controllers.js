app.controller("homeCtrl", function ($scope, UserService) {

    $scope.isOpen = false;

    UserService.fetchUser();

    $scope.$watch(function () {
        return UserService.getUser();
    }, function () {
        $scope.user = UserService.getUser();
        console.log($scope.user);
    });

    $scope.toggleDropdown = function () {
        $scope.isOpen = !$scope.isOpen;
    };

});

app.controller("bookCtrl", function ($scope, $state, UserService, BookService, CartService) {

    $scope.sachs = [];
    $scope.searchResults = [];

    $scope.$watch(function () {
        return BookService.getBooks();
    }, function () {
        $scope.sachs = BookService.getBooks();
        $scope.sachs.forEach(sach => BookService.fetchBookDetail(sach.slug));
        console.log($scope.sachs);
    });

    BookService.fetchBooks();
    
    $scope.addToCart = function (slug) {
        console.log(slug);
        CartService.addItem(slug);
    };

    $scope.searchBooks = function (tenSach) {
        if (tenSach !== "") {
            BookService.searchBooks(tenSach)
                .then(function (response) {
                    $scope.sachs = response.data;
                })
        } else {
            $scope.sachs = BookService.getBooks();
        }
    }

});

app.controller("bookDetailCtrl", function ($scope, $stateParams, $state, BookService, CartService) {
    
    $scope.sach = {};

    $scope.$watch(function () {
        return BookService.getBookDetail($stateParams.slug);
    }, function () {
        $scope.sach = BookService.getBookDetail($stateParams.slug);
    });

    $scope.getTenTheLoais = function () {
        return $scope.sach.sachThuocTheLoai.map(theLoai => theLoai.tenTheLoai).join(", ");
    };

    $scope.getTenTacGias = function () {
        return $scope.sach.sachCuaTacGia.map(tacGia => tacGia.tenTacGia).join(", ");
    }

    $scope.closeModal = function () {
        $state.go("^", { inherite: true });
    };

    $scope.addToCart = function (slug) {
        CartService.addItem(slug);
    };

});

app.controller("cartCtrl", function ($scope, CartService, BookService) {

    $scope.items = [];
    $scope.success = false;

    $scope.$watchCollection(function () {
        return CartService.getItems();
    }, function (newValue) {
        $scope.items = newValue.map(function (slug) {
            return BookService.getBookDetail(slug);
        });
        console.log($scope.items);
    });

    $scope.getTotal = function () {
        var total = 0;
        $scope.items.forEach(item => total+=item.donGiaBan);
        return total;
    };

    $scope.sendCart = function () {
        CartService.sendCart($scope.items)
            .then(function (response) {
                $scope.success = true;
                console.log(response);
            }).catch(function () {
                $scope.success = false;
            });
    }

});
