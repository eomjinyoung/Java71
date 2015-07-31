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
	  return this;
  };
  
  ebox.append = function(eboxChild) {
	  for (var i = 0; i < this.length; i++) {
		  for (var j = 0; j < eboxChild.length; j++) {
			  this[i].appendChild(eboxChild[j]);
		  }
	  }
	  return this;
  };
  
  ebox.appendTo = function(eboxParent) {
	  for (var i = 0; i < eboxParent.length; i++) {
		  for (var j = 0; j < this.length; j++) {
			  eboxParent[i].appendChild(this[j]);
		  }
	  }
	  return this;
  };
  return ebox;
}

bit.ajax = function(url, settings) {
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (settings.dataType == 'json') {
				settings.success(JSON.parse(xhr.responseText));
			} else {
				settings.success(xhr.responseText);
			}
		}
	};
	
	xhr.open(settings.method, url, true);
	
	xhr.send();
};

bit.getJSON = function(url, success) {
	bit.ajax(url, {
		method: 'GET',
		dataType: 'json',
		success: success
	});
};



var $ = bit;













