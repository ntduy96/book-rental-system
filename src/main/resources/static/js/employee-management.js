app.service("EmployeeService", function ($http) {
    var nhanViens = [];
    var chucVus = [];
    var nhanVienDetails = [];

    this.getNhanViens = function () {
        return nhanViens;
    };

    this.getChucVus = function () {
        return chucVus;
    };

    this.getNhanVienDetail = function (tenNhanVien) {
        return nhanVienDetails[tenNhanVien];
    };

    this.fetchNhanViens = function () {
        $http.get("/api/nhanvien")
            .then(function (response) {
                nhanViens = response.data;
            });
    };

    this.fetchNhanVienDetail = function (tenNhanVien) {
        $http.get("/api/nhanvien/" + tenNhanVien)
            .then(function (response) {
                nhanVienDetails[tenNhanVien] = response.data;
            });
    };

    this.fetchChucVus = function () {
        $http.get("/api/chucvu")
            .then(function (response) {
                chucVus = response.data;
            });
    };

    this.resetCacheOfNhanViens = function () {
        nhanViens = [];
    };

    this.resetCacheOfChucVus = function () {
        chucVus = [];
    };

    this.resetCacheOfNhanVienDetail = function (tenNhanVien) {
        delete nhanVienDetails[tenNhanVien];
    };

});

app.controller("employeeCtrl", function ($scope, $http, $cacheFactory, EmployeeService) {

    // close modal and go back to parent state
    $scope.closeModal = function () {
        $state.go("^.all", { inherite: true });
    };

    $scope.$watch(function () {
        return EmployeeService.getNhanViens();
    }, function () {
        $scope.nhanViens = EmployeeService.getNhanViens();
    });

    EmployeeService.fetchNhanViens();

});

app.controller("employeeDetailCtrl", function ($scope, $http, $stateParams, $state, EmployeeService) {

    $scope.saveSuccess = false;
    $scope.deleteSuccess = true;
    $scope.fieldErrors = {
        tenNguoiDung: [],
        email: [],
        hoTenNguoiDung: [],
        chucVu: [],
        soCmnd: [],
        diaChiNguoiDung: [],
        password: [],
        confirmPassword: []
    };

    // close modal and go back to parent state
    $scope.closeModal = function () {
        $state.go("^.all", { inherite: true });
    };

    $scope.$watch(function () {
        return EmployeeService.getNhanVienDetail($stateParams.tenNhanVien);
    }, function () {
        $scope.nhanVien = EmployeeService.getNhanVienDetail($stateParams.tenNhanVien);
        $scope.originalNhanVien = $scope.nhanVien;
        $scope.editedNhanVien = Object.assign({}, $scope.originalNhanVien);
        $scope.editedNhanVien.chucVu = Object.assign({}, $scope.editedNhanVien.chucVu);
    });

    $scope.$watch(function () {
        return EmployeeService.getChucVus();
    }, function () {
        $scope.chucVus = EmployeeService.getChucVus();
    });

    EmployeeService.fetchNhanVienDetail($stateParams.tenNhanVien);

    EmployeeService.fetchChucVus();

    $scope.checkTenNguoiDung = function () {
        var errors = [];
        var usernameRegex = /^\w+$/g;
        if ($scope.editedNhanVien.tenNguoiDung.trim() === '') {
            errors.push('This field is required!');
        }
        if ($scope.editedNhanVien.tenNguoiDung.trim().length < 5) {
            errors.push('Username have to be longer than 6 characters');
        }
        if (!usernameRegex.test($scope.editedNhanVien.tenNguoiDung)) {
            errors.push('Username only contains alphabet characters and numbers');
        }
        $scope.fieldErrors.tenNguoiDung = errors;
    };

    $scope.checkEmail = function () {
        var errors = [];
        var emailRegex = /^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$/g;
        if ($scope.editedNhanVien.email === '') {
            errors.push('This field is required!');
        }
        if (!emailRegex.test($scope.editedNhanVien.email)) {
            errors.push("It doesn't look like a valid email");
        }
        $scope.fieldErrors.email = errors;
    };

    $scope.checkHoTenNguoiDung = function () {
        var errors = [];
        if ($scope.editedNhanVien.hoTenNguoiDung.trim() === '') {
            errors.push('This field is required!');
        }
        $scope.fieldErrors.hoTenNguoiDung = errors;
    };

    $scope.checkChucVu = function () {
        var errors = [];
        if ($scope.editedNhanVien.chucVu.maChucVu === '') {
            errors.push('This field is required!');
        }
        $scope.fieldErrors.chucVu = errors;
    };

    $scope.checkSoCmnd = function () {
        var errors = [];
        var soCmndRegex = /^\d+$/g;
        if ($scope.editedNhanVien.soCmnd.trim() === '') {
            errors.push('This field is required!');
        }
        if ($scope.editedNhanVien.soCmnd.trim().length !== 9 && $scope.editedNhanVien.soCmnd.trim().length !== 12) {
            errors.push('Valid length of a CMND is 9 or 12');
        }
        if (!soCmndRegex.test($scope.editedNhanVien.soCmnd)) {
            errors.push('So CMND only contains digit characters');
        }
        $scope.fieldErrors.soCmnd = errors;
    };

    $scope.checkDiaChiNguoiDung = function () {
        var errors = [];
        if ($scope.editedNhanVien.diaChiNguoiDung.trim() === '') {
            errors.push('This field is required!');
        }
        $scope.fieldErrors.diaChiNguoiDung = errors;
    };

    $scope.checkPassword = function () {
        var errors = [];
        var passwordRegex = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=_{}()])(?=\S+$).{8,}$/g;
        if ($scope.editedNhanVien.password) {
            if ($scope.editedNhanVien.password.trim() === '') {
                errors.push('This field is required!');
            }
            if ($scope.editedNhanVien.password.trim().length < 8) {
                errors.push('Username have to be longer than 7 characters');
            }
            if (!passwordRegex.test($scope.editedNhanVien.password)) {
                errors.push('Your password seems to be unsecured!');
            }
        }
        $scope.fieldErrors.password = errors;
    };

    $scope.checkConfirmPassword = function () {
        var errors = [];
        if ($scope.editedNhanVien.confirmPassword && $scope.editedNhanVien.password) {
            if ($scope.editedNhanVien.confirmPassword.trim() === '') {
                errors.push('This field is required!');
            }
            if ($scope.editedNhanVien.confirmPassword !== $scope.editedNhanVien.password) {
                errors.push('Your passwords is not matching');
            }
        }
        $scope.fieldErrors.confirmPassword = errors;
    };


    function getChanges() {
        let changes = {};
        if ($scope.editedNhanVien.tenNguoiDung != $scope.originalNhanVien.tenNguoiDung) {
            changes.tenNguoiDung = $scope.editedNhanVien.tenNguoiDung;
        }
        if ($scope.editedNhanVien.email != $scope.originalNhanVien.email) {
            changes.email = $scope.editedNhanVien.email;
        }
        if ($scope.editedNhanVien.hoTenNguoiDung != $scope.originalNhanVien.hoTenNguoiDung) {
            changes.hoTenNguoiDung = $scope.editedNhanVien.hoTenNguoiDung;
        }
        if ($scope.editedNhanVien.chucVu.maChucVu != $scope.originalNhanVien.chucVu.maChucVu) {
            changes.maChucVu = $scope.editedNhanVien.chucVu.maChucVu;
        }
        if ($scope.editedNhanVien.soCmnd != $scope.originalNhanVien.soCmnd) {
            changes.soCmnd = $scope.editedNhanVien.soCmnd;
        }
        if ($scope.editedNhanVien.diaChiNguoiDung != $scope.originalNhanVien.diaChiNguoiDung) {
            changes.diaChiNguoiDung = $scope.editedNhanVien.diaChiNguoiDung;
        }
        if ($scope.editedNhanVien.password != $scope.originalNhanVien.password) {
            changes.password = $scope.editedNhanVien.password;
        }
        if ($scope.editedNhanVien.confirmPassword != $scope.originalNhanVien.confirmPassword) {
            changes.confirmPassword = $scope.editedNhanVien.confirmPassword;
        }
        return changes;
    };

    function isValid(errors) {
        for(let field in errors) {
            if(errors[field].length !== 0) return false;
        }
        return true;
    }

    $scope.saveChanges = function () {

        // update anhDaiDien if changes
        if ($scope.editedNhanVien.anhDaiDien !== null && $scope.editedNhanVien.anhDaiDien !== $scope.originalNhanVien.anhDaiDien) {
            var fd = new FormData();
            fd.append("anhDaiDien", $scope.editedNhanVien.anhDaiDien);

            $http.post("/api/nguoidung/" + $stateParams.tenNhanVien + "/anhDaiDien", fd, {
                headers: {'Content-Type': undefined },
                transformRequest: angular.identity
            }).then(function (response) {
                EmployeeService.resetCacheOfNhanVienDetail($scope.originalNhanVien.tenNguoiDung);
            });
        }

        if (isValid($scope.fieldErrors)) {
            let changes = getChanges();
            console.log($scope.originalNhanVien);
            console.log($scope.editedNhanVien);
            console.log(changes);
            if (changes != {}) {
                $http.put("/api/nhanvien/" + $stateParams.tenNhanVien, changes)
                    .then(function (response) {
                        $scope.editedNhanVien = response.data;
                        EmployeeService.fetchNhanViens();
                        EmployeeService.resetCacheOfNhanVienDetail($scope.originalNhanVien.tenNguoiDung);
                        EmployeeService.fetchNhanVienDetail(response.data.tenNguoiDung);
                        $scope.saveSuccess = true;
                    });
            }
        }
    };

    $scope.deleteEmployee = function () {
        $http.delete("/api/nhanvien/" + $stateParams.tenNhanVien)
            .then(function (response) {
                console.log(response);
                $scope.closeModal();
                EmployeeService.fetchNhanViens();
            });
    };

    document.querySelector(".img-input-thumbnail input").onchange = function () {
        console.log(this.files[0]);
        var file = this.files[0];
        var fileReader = new FileReader();
        fileReader.onloadend = function (e) {
            $scope.editedNhanVien.anhDaiDien = file;
            document.querySelector(".img-input-thumbnail img").src = "data:image/jpeg;base64," + btoa(e.target.result);
        };
        fileReader.readAsBinaryString(file);
    };

});

