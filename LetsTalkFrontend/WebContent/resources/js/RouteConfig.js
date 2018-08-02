var myApp=angular.module("myApp",['ngRoute','ngCookies']);

myApp.config(function($routeProvider)
		{
			/*alert("I am route module")*/
			$routeProvider.when("/",{templateUrl:"Home.html"})
			.when("/login",{templateUrl:"c_user/Login.html"})
			.when("/register",{templateUrl:"c_user/Register.html"})
			.when("/editBlog",{templateUrl:"c_blog/editBlog.html"})
	        .when("/addBlog",{templateUrl:"c_blog/addBlog.html"})
        	.when("/showMyBlogs",{templateUrl:"c_blog/showMyBlogs.html"})
        	.when("/showBlog",{templateUrl:"c_blog/showBlog.html"})
        	.when("/manageBlog",{templateUrl:"c_blog/manageBlog.html"})
        	.when("/showAllBlogs",{templateUrl:"c_blog/showAllBlogs.html"})
        	.when("/showAppliedJobs",{templateUrl:"c_job/showAppliedJobs.html"})
        	.when("/showAllJob",{templateUrl:"c_job/showAllJob.html"})
        	.when("/viewJob",{templateUrl:"c_job/viewJob.html"})
        	.when("/editJob",{templateUrl:"c_job/editJob.html"})
        	.when("/addJob",{templateUrl:"c_job/addJob.html"})
        	.when("/about",{templateUrl:"aboutus.html"})
        	.when("/displayProfile",{templateUrl:"c_user/viewProfile.html"})
        	.when("/updateProfile",{templateUrl:"c_user/UpdateProfile.html"})
        	.when("/uploadProfilePicture",{templateUrl:"c_user/updateProfilePic.html"})
        	.when("/chat",{templateUrl:"c_chat/chat.html"})
        	.when("/showAllFriends",{templateUrl:"c_friend/showAllFriends.html"})
        	.when("/suggestedFriend",{templateUrl:"c_friend/suggestedFriend.html"})
        	.when("/pendingFriend",{templateUrl:"c_friend/pendingFriend.html"})
        	.when("/sentFriendRequest",{templateUrl:"c_friend/sentFriendRequest.html"})
        	.when("/aboutUs",{templateUrl:"aboutus.html"})
        	.when("/contactUs",{templateUrl:"contactus.html"})
		});

myApp.run(function($rootScope,$cookieStore)
		{
			console.log('I am in run function');
			console.log($rootScope.currentUser);
			
				if($rootScope.currentUser==undefined)
				{
				$rootScope.currentUser=$cookieStore.get('userDetails');
				}
				if($rootScope.currentBlog==undefined || $rootScope.blogComments==undefined ||$rootScope.blogData==undefined ||$rootScope.homeBlogData==undefined)
				{
				$rootScope.currentBlog=$cookieStore.get('blogDetails');
				$rootScope.blogComments=$cookieStore.get('blogCommentsList');
				$rootScope.blogData=$cookieStore.get('blogData');
				$rootScope.homeBlogData=$cookieStore.get('homeBlogData');
				}
				
				if($rootScope.currentJob==undefined){
				$rootScope.currentJob=$cookieStore.get('jobDetails');
				}
				if($rootScope.userDetail==undefined)
				{
				$rootScope.userDetail=$cookieStore.get('userDetail');
				}
});