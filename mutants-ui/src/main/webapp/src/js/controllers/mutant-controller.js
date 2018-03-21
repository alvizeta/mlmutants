(function(app) {
	app.controller('MutantController', ['$scope', '$resource', '$mdToast', function($scope,$resource, $mdToast) {
		$scope.dna = [];

	    var mutant = $resource('/mutant', {}, {
              post: {
                  method: 'POST',
                  isArray:true,
                  headers: [{'Content-Type':'application/json'}]
              }
	    });

        var sendMutant = function sendMutant(){
               return mutant.post({dna: $scope.dna}).$promise;
        };

        $scope.verify = function(){

            $scope.dna.push($scope.dnaChain1);
            $scope.dna.push($scope.dnaChain2);
            $scope.dna.push($scope.dnaChain3);
            $scope.dna.push($scope.dnaChain4);
            $scope.dna.push($scope.dnaChain5);
            $scope.dna.push($scope.dnaChain6);

            sendMutant().then(function(data){
                $scope.response = data;
                clearDnaChains();
                $scope.showSimpleToast('MUTANT verified!');
            }, function(error){
                if(error.status == 403){
                    $scope.showSimpleToast('ugh, another human');
                }
                clearDnaChains();

                console.log(error);
            });
        }

        $scope.showSimpleToast = function(text) {

            $mdToast.show(
                $mdToast.simple()
                    .textContent(text)
                    .hideDelay(3000)
            );
        };

        function clearDnaChains(){
            $scope.dnaChain1 ? $scope.dnaChain1 = undefined : "";
            $scope.dnaChain2 ? $scope.dnaChain2 = undefined : "";
            $scope.dnaChain3 ? $scope.dnaChain3 = undefined : "";
            $scope.dnaChain4 ? $scope.dnaChain4 = undefined : "";
            $scope.dnaChain5 ? $scope.dnaChain5 = undefined : "";
            $scope.dnaChain6 ? $scope.dnaChain6 = undefined : "";
            $scope.dna = [];
        }

        $scope.generateRandomDna = function(){
            clearDnaChains();

            $scope.dnaChain1 = makeString();
            $scope.dnaChain2 = makeString();
            $scope.dnaChain3 = makeString();
            $scope.dnaChain4 = makeString();
            $scope.dnaChain5 = makeString();
            $scope.dnaChain6 = makeString();

        }

        function makeString() {
            var text = "";
            var possible = "ACTG";

            for (var i = 0; i < 6; i++)
                text += possible.charAt(Math.floor(Math.random() * possible.length));

            return text;
        }

	}]);
})(hackathonTeamOne);
