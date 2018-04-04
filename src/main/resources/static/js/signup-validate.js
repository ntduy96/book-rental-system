var form = $('.needs-validation');
var tenNguoiDungInput = $('#tenNguoiDung');
var hoTenNguoiDungInput = $('#hoTenNguoiDung');
var soCmndInput = $('#soCmnd');
var diaChiNguoiDungInput = $('#diaChiNguoiDung');
var emailInput = $('#email');
var passwordInput = $('#password');
var confirmPasswordInput = $('#confirmPassword');

form[0].onsubmit = function () {
    form.addClass("was-validated");
    return !(checkTenNguoiDung().length > 0 || checkEmail().length > 0 || checkHoTenNguoiDung().length > 0 || checkSoCmnd().length > 0 || checkDiaChiNguoiDung().length > 0 || checkPassword().length > 0 || checkConfirmPassword().length > 0);
};

tenNguoiDungInput.keyup(function () {
    var errors = checkTenNguoiDung();
    var tenNguoiDungErrors = $("#tenNguoiDungErrors");
    tenNguoiDungErrors.empty();
    if (errors.length > 0) {
        tenNguoiDungInput.removeClass('is-valid').addClass('is-invalid');
        errors.forEach(function (error) {
            tenNguoiDungErrors.prepend("<p>" + error + "</p>");
        });
    } else tenNguoiDungInput.removeClass('is-invalid').addClass('is-valid');
});

emailInput.keyup(function () {
    var errors = checkEmail();
    var emailErrors = $("#emailErrors");
    emailErrors.empty();
    if (errors.length > 0) {
        emailInput.removeClass('is-valid').addClass('is-invalid');
        errors.forEach(function (error) {
            emailErrors.prepend("<p>" + error + "</p>");
        });
    } else emailInput.removeClass('is-invalid').addClass('is-valid');
});

hoTenNguoiDungInput.keyup(function () {
    var errors = checkHoTenNguoiDung();
    var hoTenNguoiDungErrors = $("#hoTenNguoiDungErrors");
    hoTenNguoiDungErrors.empty();
    if (errors.length > 0) {
        hoTenNguoiDungInput.removeClass('is-valid').addClass('is-invalid');
        errors.forEach(function (error) {
            hoTenNguoiDungErrors.prepend("<p>" + error + "</p>");
        });
    } else hoTenNguoiDungInput.removeClass('is-invalid').addClass('is-valid');
});

soCmndInput.keyup(function () {
    var errors = checkSoCmnd();
    var soCmndErrors = $("#soCmndErrors");
    soCmndErrors.empty();
    if (errors.length > 0) {
        soCmndInput.removeClass('is-valid').addClass('is-invalid');
        errors.forEach(function (error) {
            soCmndErrors.prepend("<p>" + error + "</p>");
        });
    } else soCmndInput.removeClass('is-invalid').addClass('is-valid');
});

diaChiNguoiDungInput.keyup(function () {
    var errors = checkDiaChiNguoiDung();
    var diaChiNguoiDungErrors = $("#diaChiNguoiDungErrors");
    diaChiNguoiDungErrors.empty();
    if (errors.length > 0) {
        diaChiNguoiDungInput.removeClass('is-valid').addClass('is-invalid');
        errors.forEach(function (error) {
            diaChiNguoiDungErrors.prepend("<p>" + error + "</p>");
        });
    } else diaChiNguoiDungInput.removeClass('is-invalid').addClass('is-valid');
});

passwordInput.keyup(function () {
    var errors = checkPassword();
    var passwordErrors = $("#passwordErrors");
    passwordErrors.empty();
    if (errors.length > 0) {
        passwordInput.removeClass('is-valid').addClass('is-invalid');
        errors.forEach(function (error) {
            passwordErrors.prepend("<p>" + error + "</p>");
        });
    } else passwordInput.removeClass('is-invalid').addClass('is-valid');
});

confirmPasswordInput.keyup(function () {
    var errors = checkConfirmPassword();
    var confirmPasswordErrors = $("#confirmPasswordErrors");
    confirmPasswordErrors.empty();
    if (errors.length > 0) {
        confirmPasswordInput.removeClass('is-valid').addClass('is-invalid');
        errors.forEach(function (error) {
            confirmPasswordErrors.prepend("<p>" + error + "</p>");
        });
    } else confirmPasswordInput.removeClass('is-invalid').addClass('is-valid');
});

function checkTenNguoiDung() {
    var errors = [];
    var usernameRegex = /^\w+$/g;
    if (tenNguoiDungInput.val().trim() === '') {
        errors.push('This field is required!');
    }
    if (tenNguoiDungInput.val().trim().length < 5) {
        errors.push('Username have to be longer than 6 characters');
    }
    if (!usernameRegex.test(tenNguoiDungInput.val())) {
        errors.push('Username only contains alphabet characters and numbers');
    }
    return errors;
}

function checkEmail() {
    var errors = [];
    var emailRegex = /^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$/g;
    if (emailInput.val().trim() === '') {
        errors.push('This field is required!');
    }
    if (!emailRegex.test(emailInput.val())) {
        errors.push("It doesn't look like a valid email");
    }
    return errors;
}

function checkHoTenNguoiDung() {
    var errors = [];
    if (hoTenNguoiDungInput.val().trim() === '') {
        errors.push('This field is required!');
    }
    return errors;
}

function checkSoCmnd() {
    var errors = [];
    var soCmndRegex = /^\d+$/g;
    if (soCmndInput.val().trim() === '') {
        errors.push('This field is required!');
    }
    if (soCmndInput.val().trim().length !== 9 && soCmndInput.val().trim().length !== 12) {
        errors.push('Valid length of a CMND is 9 or 12');
    }
    if (!soCmndRegex.test(soCmndInput.val())) {
        errors.push('So CMND only contains digit characters');
    }
    return errors;
}

function checkDiaChiNguoiDung() {
    var errors = [];
    if (diaChiNguoiDungInput.val().trim() === '') {
        errors.push('This field is required!');
    }
    return errors;
}

function checkPassword() {
    var errors = [];
    var passwordRegex = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=_{}()])(?=\S+$).{8,}$/g;
    if (passwordInput.val().trim() === '') {
        errors.push('This field is required!');
    }
    if (passwordInput.val().trim().length < 8) {
        errors.push('Username have to be longer than 7 characters');
    }
    if (!passwordRegex.test(passwordInput.val())) {
        errors.push('Your password seems to be unsecured!');
    }
    return errors;
}

function checkConfirmPassword() {
    var errors = [];
    if (confirmPasswordInput.val().trim() === '') {
        errors.push('This field is required!');
    }
    if (confirmPasswordInput.val() !== passwordInput.val()) {
        errors.push('Your passwords is not matching');
    }
    return errors;
}
