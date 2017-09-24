var app = angular.module('Library');

app.factory('BookById', function ($resource) {
    return $resource('http://localhost:8080/Library_war_exploded/rest/books/:id', {}, {
        query: {
            method: 'GET',
            isArray: false
        }
    });
});

app.factory('BookSearch', function ($resource) {
    return $resource('http://localhost:8080/Library_war_exploded/rest/books/by:type/:searchtext', {}, {
        query:{
            method: 'GET',
            isArray: true
        }
    });

});

app.factory('Lending', function ($resource){
    return $resource('http://localhost:8080/Library_war_exploded/rest/lending/add',{}, {
        save:{
            method: 'POST',
			isaArray: false
        }
    });
});

app.factory('Lend', function ($resource){
   return $resource('http://localhost:8080/Library_war_exploded/rest/lending/:id', {}, {
       query:{
           method: 'GET',
           isArray: false
       }
   })
});

app.factory('DeleteLending', function ($resource) {
    return $resource('http://localhost:8080/Library_war_exploded/rest/lending/delete/:firstname/:lastname/:bookID', {}, {
        delete:{
            method: 'DELETE',
            isArray: false
        }
    })
});