var app = angular.module("app", []);
app.controller('sumctrl', ['$scope', 'calcService', function($scope, calcService){
    $scope.a = 10;
    $scope.b = 20;

    $scope.doSum = function(){
        //$scope.sum = calcService.getSum($scope.a, $scope.b);

        calcService.getSum($scope.a, $scope.b, function(r){
            $scope.sum = r;
        });
    };

}]);

app.service('calcService', ['$http', '$log', function($http, $log){
    $log.log("instantiating calcService..");

    //this.getSum = function(a,b){
    //  return parseInt(a) + parseInt(b);
    //};

    //this.getSum = function(a, b, cb){
    //  var s = parseInt(a) + parseInt(b);
    //  cb(s);
    //};

    this.getSum = function(a, b, cb){
        $http({
            url: 'http://localhost:4467/Sum?a=' + a + '&b=' + b,
            method: 'GET'
        }).then(function(resp){
            $log.log(resp.data);
            cb(resp.data);
        },function(resp){
            $log.error("ERROR occurred");
        });
    };

}]);