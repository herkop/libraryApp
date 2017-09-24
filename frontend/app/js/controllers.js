var app = angular.module('Library');

app.controller('MainController', function ($scope, BookSearch, $stateParams, $state) {
    $scope.title = "Lugeja";
    $scope.showLoader = false;
    $scope.notFoundAlert = false;
    $scope.select={
        options: [
            {value: 'title', name: 'Pealkiri'},
            {value: 'author', name: 'Autor'}

        ],
        value: {value: '', name: ''}
    };

    $scope.books = [];
    $scope.searchbox = "";
    $scope.search = function() {
        $state.go('search', {'searchingParam': $scope.select.value.value, 'searchingText': $scope.searchbox});
    }
});

app.controller('SearchController', function($scope, $stateParams, BookSearch, $state){
    $scope.search = function() {
        $state.go('search', {'searchingParam': $scope.select.value.value, 'searchingText': $scope.searchbox});
    };
    $scope.notFoundAlert = false;
    $scope.searchbox = $stateParams.searchingText;
    if($stateParams.searchingParam === "title"){
        $scope.select.value = $scope.select.options[0];
    }
    else if($stateParams.searchingParam === "author") {
        $scope.select.value = $scope.select.options[1];
    }
    if($scope.searchbox !== "") {
        $scope.showLoader = true;
        if($scope.select.value.value !== "") {
            BookSearch.query({searchtext: $scope.searchbox, type: $scope.select.value.value}, function (books) {
                $scope.showLoader = false;
                $scope.books = books;
            }, function (error) {
                $scope.showLoader = false;
                $scope.notFoundAlert = true;
                $scope.books = [];
            });

        }
    }
});

app.controller('BookController', function ($scope, DeleteLending, $stateParams, BookById, Lend, Lending) {
    $scope.emptyAlert = false;
    $scope.notFoundAlert = false;
    $scope.successAlert = false;
    $scope.errorAlert = false;
    $scope.errorRetAlert = false;
    $scope.butDisable = false;
    $scope.returnDisable = false;
    BookById.query({id: $stateParams.bookId}, function (book) {
        $scope.book = book;
        Lend.query({id: book.id}, function (land) {
            $scope.errorAlert = true;
            $scope.butDisable = true;
            $scope.available = true;
        }, function (error) {
            $scope.errorAlert = false;
            $scope.available = false;
            $scope.returnDisable = true;
            $scope.errorRetAlert = true;
        })
    }, function (error) {
        $scope.notFoundAlert = true;
        $scope.butDisable = true;
        $scope.returnDisable = true;
    });

    $scope.lending = function () {
        if(!angular.isUndefined($scope.firstname) && !angular.isUndefined($scope.lastname) && !angular.isUndefined($scope.email)){
            //Lending
            $scope.emptyAlert = false;
            var data = {firstname: $scope.firstname, lastname: $scope.lastname, email: $scope.email, bookID: $scope.book.id};

            Lending.save({}, JSON.stringify(data), function (success) {
                $scope.successAlert = true;
                $scope.errorAlert = false;
            }, function (error) {
                $scope.errorAlert = true;
                $scope.successAlert = false;
            });
        }
        else{
            $scope.emptyAlert = true;
        }
    };

    $scope.returning = function () {
        $scope.emptyAlert = false;
        if (!angular.isUndefined($scope.firstname) && !angular.isUndefined($scope.lastname)) {
            DeleteLending.delete({
                firstname: $scope.firstname,
                lastname: $scope.lastname,
                bookID: $scope.book.id
            }, function (success) {

                $scope.successAlert = true;
                $scope.errorRetAlert = false;
            }, function (error) {
                $scope.errorRetAlert = true;
                $scope.successAlert = false;
            });

        }
        else{
            $scope.emptyAlert = true;
        }
    }
});