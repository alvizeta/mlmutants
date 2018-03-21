(function(app) {

	app.controller('StatsController', ['$scope', '$resource', function($scope, $resource) {

        $scope.selected = [];

        var resource = $resource('/stats', {}, {
            get: {
                method: 'GET',
                headers: [
                    {'Content-Type':'application/json'}]
            }
        });

        $scope.data = [
            {label: "Mutants", value: 0, color: "rgb(255,226,71)"},
            {label: "Humans", value: 0, color: "grey"},
        ];

        $scope.options = {thickness: 40};

        var getTickets = function getTickets(){
            return resource.get({}).$promise;
        };

        getTickets().then(function(data){
            $scope.data[0].value = data.count_mutant_dna;
            $scope.data[1].value = data.count_human_dna;

        }).catch(function (err) {
            console.log(err);
        });
	}]);

})(hackathonTeamOne);
