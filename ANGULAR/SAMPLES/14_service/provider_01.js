/**
 * Created by ninganna.c on 6/2/2017.
 */
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

app.provider('calcService', function(){

    var baseUrl = '';

    this.config = function(url){
        baseUrl = url;
    };

    this.$get = ['$log', '$http', function($log, $http){
        $log.log("instantiating calcService...")
        var oCalcService = {};

        //oCalcService.getSum = function(a,b){
        //  return parseInt(a) + parseInt(b);
        //};

        //oCalcService.getSum = function(a, b, cb){
        //  var s = parseInt(a) + parseInt(b);
        //  cb(s);
        //};

        oCalcService.getSum = function(a, b, cb){

            $http({
                url: baseUrl + '/Sum?a=' + a + '&b=' + b,
                method: 'GET'
            }).then(function(resp){
                $log.log(resp.data);
                cb(resp.data);
            },function(resp){
                $log.error("ERROR occurred");
            });
        };

        return oCalcService;
    }];

});

app.config(['calcServiceProvider', function(calcServiceProvider){
    calcServiceProvider.config("http://localhost:4467");
}]);