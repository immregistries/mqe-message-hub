angular.module('messageHubDemoApp')

	.factory('MessageCountHistoryFactory', function($resource) {
	    return $resource('provider/:providerKey/counts/year/:year');
	})
	
	.factory('MessageListFactory',	function($resource) {
		return $resource('messages/:providerKey/date/:date/messages/:messages/page/:page/?filters=:filters');
	})
	
	.factory('MessageDetailFactory', function($resource) {
		return $resource('messages/:id');
	})
	
	.factory('FacilityList', function($resource) {
	    return $resource('facilities/');
	})

    .factory('Hl7JsonPoster', function ($resource) {
        return $resource('messages/json');//posts a json object
    })

    .factory('Hl7JsonExample', function ($resource) {
    	return $resource('messages/json/example');//gets a json object with an example message
    })
    
    .factory('ReportExample', function ($resource) {
    	return $resource('report/demo');//gets a json object with an example message
    })
    
    .factory('ReportMessage', function ($resource) {
    	return $resource('report/message');//gets a json object with an example message
    })
    
    .directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            
            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}])
.service('fileUpload', ['$http', function ($http) {
    this.uploadFileToUrl = function(file, uploadUrl){
        var fd = new FormData();
        fd.append('file', file);
        $http.post(uploadUrl, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
        .success(function(){
        })
        .error(function(){
        });
    }
}])


    .factory('MessageResult', function ($resource) {
    	return $resource('messages/msg-result');//gets a string with ack info
    })
    
    .factory('MessageDownload', function ($resource) {
    	return $resource('messages/msg-download');//gets a string with ack info
    })

	.directive('fileModel', ['$parse', function ($parse) {
	    return {
	        restrict: 'A',
	        link: function(scope, element, attrs) {
	            var model = $parse(attrs.fileModel);
	            var modelSetter = model.assign;
	            
	            element.bind('change', function(){
	                scope.$apply(function(){
	                    modelSetter(scope, element[0].files[0]);
	                });
	            });
	        }
	    };
	}])
	
	.filter('newline', function ($sce) {
	    return function (text) {
	        return text ? $sce.trustAsHtml(text.replace(/\r/g, '<br/>')) : '';
	    };
	})

	


;