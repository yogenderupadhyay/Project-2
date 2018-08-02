myApp.controller("JobController", function($scope,$http,$location, $rootScope,$window,$cookieStore) {
			$scope.job = {    "jobid": '',
				    "jobTitle": "",
				    "jobDescription": "",
				    "jobQualification": "",
				    "jobSalary": '',
				    "jobStatus": "",
				    "noOfOpenings": '' ,
				    "dateOfPost": ''};
			$scope.jobApplication={
					 "jobappid": '',
				        "email": "",
				        "jobid": '',
				        "applied_date": '',
				        "jobappstatus": "",
				        "reason": ""
			}
			       $scope.jobData;
			       $scope.myJobs;
			       
//*******************************************add job**********************************************
			$scope.insertJob = function(){
				console.log('inside insert Job');
				console.log($scope.job);
				$http.post('http://localhost:8085/LetsTalkMiddleware/addJob',$scope.job)
				.then (function(response) {
					alert('Job Added Success');
					$scope.fetchAllJob();
				$window.location.reload();
				},function(response)

				{

					alert('Cannot Add Try Again')
				});
			};
			
//****************************************fetch all job***********************************************
			$scope.fetchAllJob=function() 
			{
				console.log('Fetch All Jobs');
				$http.get("http://localhost:8085/LetsTalkMiddleware//listAllJobs")
				.then(function(response)
				{
					
					$rootScope.jobData=response.data;
					console.log($scope.jobData);
				});
			}

				
//***************************************get job*****************************************************
			$scope.getJob = function(jobid) {
				console.log('inside get job')
				$http.get('http://localhost:8085/LetsTalkMiddleware/getJob/'+jobid)
						.then(function(response) {
							console.log(response.status);
							$scope.job=response.data;
							$rootScope.currentJob = $scope.job;
							$cookieStore.put('jobDetails',$scope.Job);
							console.log($rootScope.currentJob.jobTitle);
						});
			};

//*************************************update job******************************************************
			$scope.updateJob = function(jobId){
				$http.put('http://localhost:8085/LetsTalkMiddleware/jobUpdate',$scope.currentJob)
				.then(function(response){
					console.log('updated job'+ $scope.currentJob.jobid+ ' successfully');
					$window.alert("Job Id : "+$scope.currentJob.jobid +" updated successfully");
					$scope.fetchAllJob();
					$location.path("/showAllJob");
				},function(response){
					alert('something went wrong');
				});
			};
			
//*************************************delete job********************************************************
			$scope.deleteJob = function(jobId){
				console.log('inside delete job');
				$http.delete('http://localhost:8085/LetsTalkMiddleware/deleteJob/'+jobId)
				.then(function(response){
					$scope.jobApplication=response.data;
					console.log($scope.jobApplication.statusMessage);
					$window.alert($scope.jobApplication.statusMessage);
					$scope.fetchAllJob();
					$window.location.reload();
				},function(response)

				{
					$scope.jobApplication=response.data;
					console.log($scope.jobApplication.statusMessage);
					$window.alert($scope.jobApplication.statusMessage);
				});
			};
//***********************************Apply Job**************************************************
			$scope.applyJob=function(jobid){
				console.log('inside apply job');
				$scope.jobApplication.email=$rootScope.currentUser.emailID;
				$scope.jobApplication.jobid=jobid;
				$http.post('http://localhost:8085/LetsTalkMiddleware/applyJob',$scope.jobApplication)
				.then(function(response){
				$scope.jobApplication=response.data;
				console.log($scope.jobApplication.statusMessage);
				$scope.fetchAllJob();
				alert($scope.jobApplication.statusMessage);
				},function(response){
					$scope.jobApplication=response.data;
					console.log($scope.jobApplication.statusMessage);
					alert($scope.jobApplication.statusMessage);
				});
			}
			
//**********************************cancel job application*****************************************
			$scope.cancelJobApplication = function(jobAppId){
				console.log('inside delete JobApplication');
				$http.delete('http://localhost:8085/LetsTalkMiddleware/deleteJobApplication/'+jobAppId)
				.then(function(response){
					console.log('Job Application deleted ');
					$window.alert("Job Application Id : "+jobAppId +" deleted successfully");
					$scope.fetchAllJob();
					$scope.showAppliedJobs();
				},function(response)

				{

					alert('Cannot delete Try Again')
				});
			};

//**********************************List Applied Jobs***********************************************
			$scope.showAppliedJobs=function(){
				console.log('inside show applied jobs');
				$http.get('http://localhost:8085/LetsTalkMiddleware/listAppliedJobs/'+$scope.currentUser.emailID+'.')
				.then(function(response){
					$rootScope.appliedJobs=response.data;
					console.log($rootScope.appliedJobs);
				})
			}
//********************************close job******************************************
			$scope.closeJob=function(jobID){
				console.log('inside close job');
				$http.get('http://localhost:8085/LetsTalkMiddleware/closeJob/'+jobID)
					.then(function(response){
						alert('JobId : '+jobID+' closed successfully');
						$scope.fetchAllJob();
						$window.location.reload();
					});
				}
			//********************************open job******************************************
			$scope.openJob=function(jobID){
				console.log('inside open job');
				$http.get('http://localhost:8085/LetsTalkMiddleware/openJob/'+jobID)
					.then(function(response){
						alert('JobId : '+jobID+' open successfully');
						$scope.fetchAllJob();
						$window.location.reload();
					});
				}
});
