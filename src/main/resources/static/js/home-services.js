app.service("UserService", function ($http) {

    var user = {};

    this.getUser = function () {
        return user;
    };

    this.fetchUser = function () {
        $http.get("/api/nguoidung/me")
            .then(function (response) {
                user = response.data;
            });
    };

});

app.service("BookService", function ($http) {

    var books = [];
    var bookDetail = [];

    this.getBooks = function () {
        return books;
    };

    this.getBookDetail = function (slug) {
        return bookDetail[slug];
    };

    this.fetchBooks = function () {
        if (books.length === 0) {
            $http.get("/api/sach")
                .then(function(response) {
                    books = response.data;
                });
        }
    };

    this.searchBooks = function (tenSach) {
        return $http.get("/api/sach", {
            params: { tenSach: tenSach },
            cache: true
        });
    };

    this.resetCacheOfBooks = function () {
        books = [];
    };

    this.fetchBookDetail = function (slug) {
        return $http.get("/api/sach/" + slug)
            .then(function (response) {
                bookDetail[slug] = response.data;
                bookDetail[slug].donGiaBan = response.data.donGiaBan[0].donGia;
                return new Promise(function (resolve) {
                    resolve(bookDetail[slug]);
                });
            });
    };

    this.resetCacheOfBookDetail = function (slug) {
        delete bookDetail[slug];
    };

});

app.service("CartService", function ($rootScope, $http, BookService) {

    var items = [];

    this.setItems = function (newItems) {
        items = newItems || [];
    };

    this.getItems = function () {
        return items;
    };

    this.addItem = function (slug) {
        // BookService.fetchBookDetail(slug);
        items.push(slug);
    };

    this.clearCart = function () {
        items = [];
    };

    this.removeItem = function (slug) {
        let index = items.findIndex(item => item.slug === slug);
        items.splice(index, 1);
    };

    function getOrder(items) {
        var order = {
            chiTietHoaDonList: []
        };

        order.chiTietHoaDonList = items.map(function (item) {
            return {
                maSach: item.maSach,
                soLuongBan: 1
            };
        });

        return order;
    }

    this.sendCart = function (items) {
        return $http.post("/api/hoadon", getOrder(items));
    };

});
