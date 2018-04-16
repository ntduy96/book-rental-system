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
        templateUrl : "/html/book-management.html",
        controller: "sachCtrl"
    }).state({
        name: "library.book.detail",
        url: "/:slug",
        templateUrl : "/html/book-details.html",
        controller: "sachDetailCtrl"
    }).state({
        name: "library.book.detail.theloai",
        url: "/theloai",
        templateUrl : "/html/book-details-theloai.html",
        controller: "sachDetailTheLoaiCtrl"
    }).state({
        name: "library.book.detail.tacgia",
        url: "/tacgia",
        templateUrl : "/html/book-details-tacgia.html",
        controller: "sachDetailTacGiaCtrl"
    }).state({
        name: "library.author",
        url: "/author",
        templateUrl : "/html/author-management.html",
        controller: "tacGiaCtrl"
    }).state({
        name: "library.author.detail",
        url: "/:slug",
        templateUrl : "/html/author-details.html",
        controller: "tacGiaDetailCtrl"
    }).state({
        name: "library.category",
        url: "/category",
        templateUrl : "/html/category-management.html",
        controller: "theLoaiCtrl"
    }).state({
        name: "library.category.detail",
        url: "/:slug",
        templateUrl : "/html/category-details.html",
        controller: "theLoaiDetailCtrl"
    });

}]);

app.controller("sachCtrl", function ($http, $scope, $cacheFactory) {
    $scope.fetchSachs = function () {
        $http.get("/api/sach", { cache: true })
            .then(function(response) {
                $scope.sachList = response.data;
            });
    };
    $scope.fetchSachs();
    $scope.resetCacheOfSach = function () {
        var $httpDefaultCache = $cacheFactory.get("$http");
        $httpDefaultCache.remove("/api/sach");
    };
    $scope.num = 0;
    $scope.getRecordNumber = function () {
        $scope.num = $scope.num++;
        return $scope.num;
    }
});

app.controller("sachDetailCtrl", function ($http, $scope, $stateParams, $state, $cacheFactory) {
    $scope.fetchSachDetail = function () {
        $http.get("/api/sach/" + $stateParams.slug, { cache: true })
            .then(function(response) {
                $scope.originalSach = {};
                $scope.originalSach = Object.assign({}, response.data);
                $scope.originalSach.sachThuocTheLoai = response.data.sachThuocTheLoai.map(function (theLoai) { return theLoai.tenTheLoai; });
                $scope.originalSach.sachCuaTacGia = response.data.sachCuaTacGia.map(function (tacGia) { return tacGia.tenTacGia; });
                $scope.originalSach.ngayXuatBan = new Date(response.data.ngayXuatBan);
                $scope.originalSach.ngayTao = new Date(response.data.ngayTao);
                $scope.editedSach = Object.assign({}, $scope.originalSach);
            });
    };
    $scope.fetchSachDetail();

    $scope.resetCacheOfSachDetail = function () {
        var $httpDefaultCache = $cacheFactory.get("$http");
        $httpDefaultCache.remove("/api/sach/" + $stateParams.slug);
    };

    $scope.closeModal = function () {
        $state.go("^", { inherite: true });
    };

    // open child theloai of a specific book
    $scope.openTheLoai = function () {
        $state.go(".theloai", { inherite: true });
    };

    // open child tacgia of a specific book
    $scope.openTacGia = function () {
        $state.go(".tacgia", { inherite: true });
    };

    function checkTenSachValid(tenSach) {
        return $http.get("/api/sach/tenSach",
                {
                    params: { tenSach: tenSach },
                    cache: true
                });
    }

    function getChanges(originalSach, editedSach) {
        var changes = {};
        if (originalSach.tenSach !== editedSach.tenSach && editedSach.tenSach.length > 8) {
            changes.tenSach = editedSach.tenSach;
        }
        if (originalSach.soLuong !== editedSach.soLuong) {
            changes.soLuong = editedSach.soLuong;
        }
        if (originalSach.soTrang !== editedSach.soTrang) {
            changes.soTrang = editedSach.soTrang;
        }
        if (originalSach.ngayXuatBan !== editedSach.ngayXuatBan && editedSach.ngayXuatBan != null) {
            changes.ngayXuatBan = editedSach.ngayXuatBan;
        }
        if (originalSach.tenSach !== editedSach.tenSach) {
            changes.tenSach = editedSach.tenSach;
        }
        if (!checkArraysEquality(originalSach.sachThuocTheLoai, editedSach.sachThuocTheLoai)) {
            changes.theLoai = editedSach.sachThuocTheLoai;
        }
        if (!checkArraysEquality(originalSach.sachCuaTacGia, editedSach.sachCuaTacGia)) {
            changes.tacGia = editedSach.sachCuaTacGia;
        }
        if (originalSach.donGiaBan !== editedSach.donGiaBan) {
            changes.donGiaBan = editedSach.donGiaBan;
        }
        return changes;
    }

    $scope.saveChanges = function () {
        console.log($scope.originalSach);
        console.log($scope.editedSach);
        // checkTenSachValid($scope.editedSach.tenSach)
        //     .then(function(response) {
        //         if (response.data.length === 0) {
        //             $scope.editedSach.tenSach.isValid = true;
        //         } else $scope.editedSach.tenSach.isValid = false;
        //     });

        var changes = getChanges($scope.originalSach, $scope.editedSach);
        if (changes != {}) {
            console.log(changes);
            $http.put("/api/sach/" + $stateParams.slug, changes)
                .then(function (response) {
                    $scope.resetCacheOfSachDetail();
                    $scope.originalSach = {};
                    $scope.originalSach = Object.assign({}, response.data.sach);
                    $scope.originalSach.sachThuocTheLoai = response.data.sach.sachThuocTheLoai.map(function (theLoai) { return theLoai.tenTheLoai; });
                    $scope.originalSach.sachCuaTacGia = response.data.sach.sachCuaTacGia.map(function (tacGia) { return tacGia.tenTacGia; });
                    $scope.originalSach.ngayXuatBan = new Date(response.data.sach.ngayXuatBan);
                    $scope.originalSach.ngayTao = new Date(response.data.sach.ngayTao);
                    $scope.editedSach = Object.assign({}, $scope.originalSach);
                    $scope.$parent.resetCacheOfSach();
                    $scope.$parent.fetchSachs();
                });
        }

        // update anhBia of sach by slug
        if ($scope.editedSach.anhBia !== null && $scope.editedSach.anhBia !== $scope.originalSach.anhBia) {
            var fd = new FormData();
            fd.append("anhBia", $scope.editedSach.anhBia);

            $http.post("/api/sach/" + $stateParams.slug + "/anhBia", fd, {
                headers: {'Content-Type': undefined },
                transformRequest: angular.identity
            }).then(function (response) {
                $scope.resetCacheOfSachDetail();
                $scope.fetchSachDetail();
                $scope.$parent.resetCacheOfSach();
                $scope.$parent.fetchSachs();
            });
        }
    };

    $scope.deleteSach = function () {
        $http.delete("/api/sach/" + $stateParams.slug)
            .then(function (response) {
                console.log(response);
                $scope.closeModal();
                $scope.$parent.resetCacheOfSach();
                $scope.$parent.fetchSachs();
            });
    };

    document.querySelector(".img-input-thumbnail input").onchange = function () {
        console.log(this.files[0]);
        var file = this.files[0];
        var fileReader = new FileReader();
        fileReader.onloadend = function (e) {
            $scope.editedSach.anhBia = file;
            document.querySelector(".img-input-thumbnail img").src = "data:image/jpeg;base64," + btoa(e.target.result);
        };
        fileReader.readAsBinaryString(file);
    }
});

app.controller("sachDetailTheLoaiCtrl", function ($http, $scope, $stateParams, $state, $cacheFactory) {
    // fetch list of the loai from database
    function fetchTheLoais() {
        $http.get("/api/theloai", { cache: true })
            .then(function(response) {
                $scope.theLoais = response.data;
                console.log($scope.theLoais);
            });
    }
    fetchTheLoais();

    // close modal and go back to parent state
    $scope.closeModal = function () {
        $state.go("^", { inherite: true });
    };

    // save all selected the loai and go back to parent state
    $scope.completeAction = function () {
        var tenTheLoaiArray = Array.from(document.querySelectorAll("[type='checkbox'][name= 'sachThuocTheLoai']:checked")).map(function (input) { return input.value; });
        $scope.$parent.editedSach.sachThuocTheLoai = tenTheLoaiArray;
        $state.go("^", { inherite: true });
    };

    // return true if there's no matching the loai with value in search box
    $scope.noMatchingTheLoai = function () {
        return document.querySelectorAll("[type='checkbox'][name= 'sachThuocTheLoai']").length === 0;
    };
    
    function resetCacheOfTheLoai() {
        var $httpDefaultCache = $cacheFactory.get("$http");
        $httpDefaultCache.remove("/api/theloai");
    }

    $scope.createNewTheLoai = function () {
        $http({
            method: "POST",
            url: "/api/theloai",
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            data: 'tenTheLoai='+$scope.search
        }).then(function(response) {
                console.log(response);
                resetCacheOfTheLoai();
                fetchTheLoais();
            });
    }
});

app.controller("sachDetailTacGiaCtrl", function ($http, $scope, $stateParams, $state, $cacheFactory) {
    // fetch list of the loai from database
    function fetchTacGias() {
        $http.get("/api/tacgia", { cache: true })
            .then(function(response) {
                $scope.tacGias = response.data;
                console.log($scope.tacGias);
            });
    }
    fetchTacGias();

    // close modal and go back to parent state
    $scope.closeModal = function () {
        $state.go("^", { inherite: true });
    };

    // save all selected the loai and go back to parent state
    $scope.completeAction = function () {
        var tenTacGiaArray = Array.from(document.querySelectorAll("[type='checkbox'][name= 'sachCuaTacGia']:checked")).map(function (input) { return input.value; });
        $scope.$parent.editedSach.sachCuaTacGia = tenTacGiaArray;
        $state.go("^", { inherite: true });
    };

    // return true if there's no matching the loai with value in search box
    $scope.noMatchingTacGia = function () {
        return document.querySelectorAll("[type='checkbox'][name= 'sachCuaTacGia']").length === 0;
    };

    function resetCacheOfTacGia() {
        var $httpDefaultCache = $cacheFactory.get("$http");
        $httpDefaultCache.remove("/api/tacgia");
    }

    $scope.createNewTacGia = function () {
        $http({
            method: "POST",
            url: "/api/tacgia",
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            data: 'tenTacGia='+$scope.search
        }).then(function(response) {
            console.log(response);
            resetCacheOfTacGia();
            fetchTacGias();
        });
    }
});

app.controller("tacGiaCtrl", function ($http, $scope, $cacheFactory) {
    $scope.fetchTacGias = function () {
        $http.get("/api/tacgia", { cache: true })
            .then(function (response) {
                $scope.tacGias = response.data;
            });
    };
    $scope.fetchTacGias();
    $scope.resetCacheOfTacGia = function () {
        var $httpDefaultCache = $cacheFactory.get("$http");
        $httpDefaultCache.remove("/api/tacgia");
    };
});

app.controller("tacGiaDetailCtrl", function ($http, $scope, $stateParams, $state, $cacheFactory) {

    // initialize variables default hiding alerts
    $scope.saveSuccess = false;
    $scope.deleteSuccess = true;

    $scope.fetchTacGiaDetail = function () {
        $http.get("/api/tacgia/" + $stateParams.slug, { cache: true })
            .then(function (response) {
                $scope.originalTacGia = response.data;
                $scope.editedTacGia = Object.assign({}, $scope.originalTacGia);
                console.log($scope.editedTacGia);
            });
    };
    $scope.fetchTacGiaDetail();
    $scope.resetCacheOfTacGiaDetail = function () {
        var $httpDefaultCache = $cacheFactory.get("$http");
        $httpDefaultCache.remove("/api/tacgia/" + $stateParams.slug);
    };

    $scope.closeModal = function () {
        $state.go("^", { inherite: true });
    };

    function getChanges(originalTacGia, editedTacGia) {
        var changes = {};
        if (originalTacGia.tenTacGia != editedTacGia.tenTacGia) {
            changes.newTenTacGia = editedTacGia.tenTacGia;
        }
        return changes;
    }

    $scope.saveChanges = function () {
        var changes = getChanges($scope.originalTacGia, $scope.editedTacGia);
        if (changes != {}) {
            console.log(changes);
            $http({
                method: "PUT",
                url: "/api/tacgia/" + $stateParams.slug,
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                data: 'newTenTacGia='+changes.newTenTacGia
            }).then(function(response) {
                if (response.status === 200) {
                    $scope.resetCacheOfTacGiaDetail();
                    $scope.originalTacGia = response.data;
                    $scope.editedTacGia = Object.assign({}, $scope.originalTacGia);
                    $scope.$parent.resetCacheOfTacGia();
                    $scope.$parent.fetchTacGias();
                    $scope.saveSuccess = true;
                }
            }).catch(function (error) {
                $scope.saveSuccess = false;
            });
        }
    };

    $scope.deleteTacGia = function () {
        $http.delete("/api/tacgia/" + $stateParams.slug)
            .then(function (response) {
                console.log(response);
                if (response.status === 200) {
                    $scope.closeModal();
                    $scope.$parent.resetCacheOfTacGia();
                    $scope.$parent.fetchTacGias();
                }
            }).catch(function (error) {
                $scope.deleteSuccess = false;
        });
    };

});

app.controller("theLoaiCtrl", function ($http, $scope, $cacheFactory) {
    $scope.fetchTheLoais = function () {
        $http.get("/api/theloai", { cache: true })
            .then(function (response) {
                $scope.theLoais = response.data;
            });
    };
    $scope.fetchTheLoais();
    $scope.resetCacheOfTheLoai = function () {
        var $httpDefaultCache = $cacheFactory.get("$http");
        $httpDefaultCache.remove("/api/theloai");
    };
});

app.controller("theLoaiDetailCtrl", function ($http, $scope, $stateParams, $state, $cacheFactory) {
    // initialize variables default hiding alerts
    $scope.saveSuccess = false;
    $scope.deleteSuccess = true;

    $scope.fetchTheLoaiDetail = function () {
        $http.get("/api/theloai/" + $stateParams.slug, { cache: true })
            .then(function (response) {
                $scope.originalTheLoai = response.data;
                $scope.editedTheLoai = Object.assign({}, $scope.originalTheLoai);
                console.log($scope.editedTheLoai);
            });
    };
    $scope.fetchTheLoaiDetail();
    $scope.resetCacheOfTheLoaiDetail = function () {
        var $httpDefaultCache = $cacheFactory.get("$http");
        $httpDefaultCache.remove("/api/theloai/" + $stateParams.slug);
    };

    $scope.closeModal = function () {
        $state.go("^", { inherite: true });
    };

    function getChanges(originalTheLoai, editedTheLoai) {
        var changes = {};
        if (originalTheLoai.tenTheLoai != editedTheLoai.tenTheLoai) {
            changes.newTenTheLoai = editedTheLoai.tenTheLoai;
        }
        return changes;
    }

    $scope.saveChanges = function () {
        var changes = getChanges($scope.originalTheLoai, $scope.editedTheLoai);
        if (changes != {}) {
            console.log(changes);
            $http({
                method: "PUT",
                url: "/api/theloai/" + $stateParams.slug,
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                data: 'newTenTheLoai='+changes.newTenTheLoai
            }).then(function(response) {
                if (response.status === 200) {
                    $scope.resetCacheOfTheLoaiDetail();
                    $scope.originalTheLoai = response.data;
                    $scope.editedTheLoai = Object.assign({}, $scope.originalTheLoai);
                    $scope.$parent.resetCacheOfTheLoai();
                    $scope.$parent.fetchTheLoais();
                    $scope.saveSuccess = true;
                }
            }).catch(function (error) {
                $scope.saveSuccess = false;
            });
        }
    };

    $scope.deleteTheLoai = function () {
        $http.delete("/api/theloai/" + $stateParams.slug)
            .then(function (response) {
                console.log(response);
                if (response.status === 200) {
                    $scope.closeModal();
                    $scope.$parent.resetCacheOfTheLoai();
                    $scope.$parent.fetchTheLoais();
                }
            }).catch(function (error) {
            $scope.deleteSuccess = false;
        });
    };
});
