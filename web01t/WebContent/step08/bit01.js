var bit = function(value) {
  var ebox;	
  if (value.charAt(0) == '<') {
	ebox = document.createElement(value.substr(1,(value.length - 2)));
	
  } else if (value.charAt(0) == '#') {
	ebox = document.getElementById(value.substr(1));
	
  } else {
    ebox = document.querySelectorAll(value);
  }
  
  return ebox;
}

var $ = bit;













