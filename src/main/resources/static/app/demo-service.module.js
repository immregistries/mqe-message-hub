angular.module('messageHubDemoApp', ['ngResource', 'ngSanitize', 'ui.router', 'ui.bootstrap', 'angularMoment', 'googlechart', 'angularSpinners']);

angular.isUndefinedOrNullOrEmpty = function(val) {
	if(angular.isArray(val)) {
		return val.length === 0
	}
	//Check if val is an array first, arrays are objects too.
	if(angular.isObject(val)) {
		return Object.getOwnPropertyNames(val).length === 0
	}
	if(angular.isUndefined(val) || val === null) {
		return true;
	}
	return val === '';
};

angular.isNotUndefinedOrNullOrEmpty = function(val) {
		return !angular.isUndefinedOrNullOrEmpty(val);
};