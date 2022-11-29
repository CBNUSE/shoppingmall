angular.module('orderSys', [])
    .controller('orderSheetController', ['$scope', function($scope) {
        $scope.contents = [
            {plistnum: 0, pname: '', count: 0, osprice: 0, sid: 0}
        ];

      //합계 출력
        $scope.total = function() {
            var result = 0;

            angular.forEach($scope.contents, function (content) {
                result += content.count * content.osprice;
            });
          
            return result;
        };


        $scope.addSheet = function() {
            $scope.contents.push({
                plistnum: 0,
                pname: '',
                count: 0,
                osprice: 0,
                sid: 0,
            });
        };

        $scope.deleteSheet = function(index) {
            $scope.contents.splice(index, 1);
        };

    }]);
