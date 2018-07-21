myApp.controller("ChatController", function($scope,$rootScope,chatService)
{
	$scope.messages=[];
	$scope.message='';
	$scope.max=250;
	
	$scope.addMessage=function()
	{
		console.log('inside add message'+$rootScope.currentUser.emailID);
		chatService.send($rootScope.currentUser.emailID+":" +$scope.message);
		$scope.message='';
	};

	chatService.receive().then(null,null,function(message)
	{
		console.log($scope.message);
		$scope.messages.push(message);
	});	

});