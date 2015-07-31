var bit = function(value) {
  var ebox;	
  if (value.charAt(0) == '<') {
	ebox = [document.createElement(value.substr(1,(value.length - 2)))];
	
  } else if (value.charAt(0) == '#') {
	ebox = [document.getElementById(value.substr(1))];
	
  } else {
    ebox = document.querySelectorAll(value);
  }
  
  ebox.text = function(value) {
	  for (var i = 0; i < this.length; i++) {
		  this[i].textContent = value;
	  }
  };
  
  ebox.append = function(eboxChild) {
	  for (var i = 0; i < this.length; i++) {
		  for (var j = 0; j < eboxChild.length; j++) {
			  this[i].appendChild(eboxChild[j]);
		  }
	  }
  };
  return ebox;
}

var $ = bit;













