myApp.controller("BlogController", function($scope,$http,$location, $rootScope,$window,$cookieStore) {
			$scope.blog = {"blogId":'',
			        "blogTitle": '',
			        "emailId":'',
			        "blogContent": '',
			        "status": '',
			        "blogLikes":'' ,
			        "blogDislikes":'' ,
			        "dateCreated":''};
			$scope.blogComment={
					"blogCommentId":'',
					"emailId":'',
					"blogId":'',
					"commentText":'',
					"commentDate":new Date()
			}
			       $scope.blogData;
			       $scope.myBlogs;
			       
//*******************************************add blog**********************************************
			$scope.insertBlog = function(){
				$scope.blog.emailId=$scope.currentUser.emailID;
				console.log('inside insert Blog');
				$http.post('http://localhost:8085/LetsTalkMiddleware/addBlog',$scope.blog)
				.then (function(response) {
					alert('Blog Added Success')
				$window.location.reload();
				},function(response)

				{

					alert('Cannot Add Try Again')
				});
			};
			
//****************************************show all blog***********************************************
			$scope.showAllBlogs=function() 
			{
				console.log('Fetch All Blogs');
				$http.get("http://localhost:8085/LetsTalkMiddleware/listBlogs")
				.then(function(response)
				{
					$rootScope.blogData=response.data;
					$rootScope.title='All Blogs';
					$cookieStore.put('title',$rootScope.title);
					$cookieStore.put('blogData', $rootScope.blogData);
					console.log($scope.blogData);
				});
			}
//***************************************show my blogs************************************************
			$scope.showMyBlogs=function(){
				console.log("fetch all my blogs")
				$http.get("http://localhost:8085/LetsTalkMiddleware/listBlogsOfUser/"+$scope.currentUser.emailID+".")
				.then(function(response) {
					$rootScope.blogData=JSON.stringify(response.data);
					$cookieStore.put('blogData', $rootScope.blogData);
					$rootScope.title='Your Blogs';
					$cookieStore.put('title',$rootScope.title);
					console.log($scope.blogData);
				},function(response)

				{

					alert('you have not created any blog yet')
				});
			}
				
//***************************************get blog*****************************************************
			$scope.getBlog = function(blogId) {
				$http.get('http://localhost:8085/LetsTalkMiddleware/getBlog/' + blogId)
						.then(function(response) {
							if($rootScope.currentUser!=undefined){
							console.log(response.status);
							$scope.blog=response.data;
							$rootScope.currentBlog = $scope.blog;
							$scope.getBlogCommentList($scope.blog.blogId);
							$cookieStore.put('blogDetails',$scope.blog);
							console.log($rootScope.currentBlog.blogId);
							}
							else{
								alert('login to view details')
								$location.path("/login")
							}
						});
			};

//*************************************update blog******************************************************
			$scope.updateBlog = function(blogId){
				$http.put('http://localhost:8085/LetsTalkMiddleware/updateBlog',$scope.currentBlog)
				.then(function(response){
					console.log('updated blog'+ $scope.currentBlog.blogId+ ' successfully');
					$window.alert("Blog Id : "+$scope.currentBlog.blogId +" updated successfully");
					$scope.myBlog();
				});
			};
			
//*************************************delete blog********************************************************
			$scope.deleteBlog = function(blogId){
				// alert("in delete blog");
				$http.delete('http://localhost:8085/LetsTalkMiddleware/deleteBlog/'+blogId)
				.then(function(response){
					console.log('Blog deleted ');
					$window.alert("Blog Id : "+$scope.currentBlog.blogId +" deleted successfully");
					$scope.myBlog();
				},function(response)

				{

					alert('Cannot delete Try Again')
				});
			};
			
//*************************************incrment Likes********************************************************
			$scope.incrementLike = function(blogId) {
				console.log('Into like increment');
				$http.get(
						'http://localhost:8085/LetsTalkMiddleware/likeBlog/'+ blogId)
						.then(function(response) {
							console.log('Incremented likes');
							$scope.getBlog(blogId);
							$scope.showAllBlogs();
							$scope.showNewBlogs();
							$scope.showRejectedBlogs();
							$scope.showApprovedBlogs();
						});
		}
			//*************************************incrment Dislikes********************************************************
			$scope.incrementDislike = function(blogId) {
				console.log('Into Dislike increment');
				$http.get(
						'http://localhost:8085/LetsTalkMiddleware/dislikeBlog/'
								+ blogId).then(
						function(response) {
							console.log('Incremented Islikes');
							$scope.getBlog(blogId);
							$scope.showAllBlogs();
							$scope.showNewBlogs();
							$scope.showRejectedBlogs();
							$scope.showApprovedBlogs();
						});
		}
			
			//***************************************delete blogCommentsList*************************************

			$scope.deleteBlogComments=function(blogCommentId){
				console.log('Into deleteBlogComments '+ blogCommentId);
				$http.get(
						'http://localhost:8085/LetsTalkMiddleware/deleteBlogComment/'
								+blogCommentId).then(
						function(response) {
								alert('comment deleted');
							     $scope.getBlogDetails();
						},function(response){
							alert('cannot delete comment try again later');
						});
			}
	
//***************************************get blogCommentsList*************************************

		$scope.getBlogCommentList=function(blogId){
			console.log('Into getBlogComments '+ blogId);
			$http.get(
					'http://localhost:8085/LetsTalkMiddleware/listBlogComments/'
							+blogId).then(
					function(response) {
						$scopeblogcomment=response.data;
						$rootScope.blogComments=response.data;
						$cookieStore.put('blogCommentsList',$rootScope.blogComments);	
						console.log($rootScope.blogComments.length);
						
					});
		}
		
//*************************************get Blog Details*******************************************
		
		$scope.getBlogDetails=function(blogId){
			console.log('into get blog details')
				$scope.getBlog(blogId);
				$scope.getBlogCommentList(blogId);
							
				/*$window.location.reload();*/
			};
			
//************************************add blog Comment*********************************************
			$scope.addBlogComment=function(){
				console.log('into add blog comment')
				console.log($rootScope.currentUser.emailID);
				$scope.blogComment.emailId=$rootScope.currentUser.emailID;
				$scope.blogComment.blogId=$rootScope.currentBlog.blogId;
				$http.post(
					'http://localhost:8085/LetsTalkMiddleware/addBlogComment',$scope.blogComment)
					.then(
					function(response) {
						console.log('Blog Comment Added Success')
						$scope.getBlogDetails($scope.blogComment.blogId);
						$location.path("/showBlog");
					},function(response)

						{

							alert('Cannot Add Try Again')
						});
			}
			
//*************************************approve blog********************************************
			$scope.approveBlog=function(blogId){
				console.log('inside approve Blog');
				console.log(blogId);
				$http.put('http://localhost:8085/LetsTalkMiddleware/approveBlog/'+blogId)
				.then(function(response){
						if(response.status==200){
						alert('blog with Blog Id : '+blogId+' Approved succesfully');
				
						$scope.showApprovedBlogs();
						$location.path("/showAllBlogs");
						}
						
						if(response.status==404){
							alert('No blog with blog Id: '+blogId)
						}
						},function(response){
							alert('cannot approve blog right now , please try again later');
						});

			}
			
//*************************************reject blog********************************************
			$scope.rejectBlog=function(blogId){
				console.log('inside reject Blog');
				console.log(blogId);
				$http.put('http://localhost:8085/LetsTalkMiddleware/rejectBlog/'+blogId)
				.then(function(response){
						if(response.status==200){
						alert('blog with Blog Id : '+blogId+' Rejectd succesfully');
						
						$scope.showRejectedBlogs();
						$location.path("/showAllBlogs");
						}
						
						if(response.status==404){
							alert('No blog with blog Id: '+blogId)
						}
						},function(response){
							alert('cannot reject blog right now , please try again later');
						});

			}
			
//***********************************Show All New Blogs******************************************
			$scope.showNewBlogs=function(){
				console.log('inside new blog');
				$http.get('http://localhost:8085/LetsTalkMiddleware/listNewBlogs')
						.then(function(response)
								{
									if(response.status=200){
									$rootScope.blogData=response.data;
									console.log($scope.blogData);
									$rootScope.title='New Blogs';
									$cookieStore.put('title',$rootScope.title);
									$cookieStore.put('blogData', $rootScope.blogData);
									}
								if(response.status==404){
									alert('No New Blogs');
								}
								},function(response){
									alert('cannot get new blogs try again Later');
								});
							}
			
			//***********************************Show All Rejected Blogs******************************************
			$scope.showRejectedBlogs=function(){
				console.log('inside reject blog');
				$http.get('http://localhost:8085/LetsTalkMiddleware/listRejectedBlogs')
						.then(function(response)
								{
									if(response.status=200){
									$rootScope.blogData=response.data;
									console.log($scope.blogData);
									$rootScope.title='Rejected Blogs';
									$cookieStore.put('title',$rootScope.title);
									$cookieStore.put('blogData', $rootScope.blogData);
									}
								if(response.status==404){
									alert('No Rejected Blogs');
								}
								},function(response){
									alert('cannot get rejected blogs try again Later');
								});
							}
			
			//***********************************Show All Approved Blogs******************************************
			$scope.showApprovedBlogs=function(){
				console.log('inside approved blog');
				$http.get('http://localhost:8085/LetsTalkMiddleware/listApprovedBlogs')
						.then(function(response)
								{
									if(response.status=200){
									$rootScope.blogData=response.data;
									$cookieStore.put('blogData', $rootScope.blogData);
									$rootScope.homeBlogData=response.data;
									$cookieStore.put('homeBlogData', $rootScope.homeBlogData);
									console.log($scope.blogData);
									$rootScope.title='Approved Blogs';
									$cookieStore.put('title',$rootScope.title);
									}
								if(response.status==404){
									alert('No Approved Blogs');
								}
								},function(response){
									alert('cannot get approveded blogs try again Later');
								});
							}
			
			//***********************************home Blogs******************************************
			$scope.homeBlogs=function(){
				console.log('inside approved blog');
				$http.get('http://localhost:8085/LetsTalkMiddleware/listApprovedBlogs')
						.then(function(response)
								{
									if(response.status=200){
									$rootScope.homeBlogData=response.data;
									$cookieStore.put('homeBlogData', $rootScope.homeBlogData);
									$location.path("/")
									}
								},function(response){
								});
							}
});
