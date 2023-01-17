function prepareOptions(fragment, optionList, classValue) {
	
	optionList.forEach(function(option, index) {
	    let opt = document.createElement('option');
	    opt.className = classValue;
	    opt.innerHTML = option.name;
	    opt.value = option.id;
	    fragment.appendChild(opt);
	});
}

function isOdd(n) {
	return Math.abs(n % 2) == 1;
}

function dateFormat(x){
	var d = new Date();
	d.setTime(x);
	var n = d.toLocaleDateString();

	return n
}

function seperateDigits(nStr){
	nStr += '';
    x = nStr.split('.');
    x1 = x[0];
    x2 = x.length > 1 ? '.' + x[1] : '';
    var rgx = /(\d+)(\d{3})/;
    while (rgx.test(x1)) {
        x1 = x1.replace(rgx, '$1' + ',' + '$2');
    }
    return x1 + x2;
}
