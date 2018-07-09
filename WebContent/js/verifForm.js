function surligne(champ, erreur) {
   if(erreur)
      champ.style.backgroundColor = "#fba";
   else
      champ.style.backgroundColor = "";
}

function verifNomComputer(champ) {
	if(champ.value.length < 1){
		document.getElementById("nameError").style.display = "block";
		surligne(champ,true);
		return false;
	} else {
		document.getElementById("nameError").style.display = "none";
		surligne(champ,false);
		return true;
	}
}

function verifIntroducedDate(champ) {
	var date = new Date();
	var today = date.getFullYear() + "-" + (date.getMonth() < 9 ? "0" : "") + (date.getMonth() + 1) + "-" + (date.getMonth() < 9 ? "0" : "")  + date.getDate();
	if(champ.value > today){
		document.getElementById("introducedError").style.display = "block";
		surligne(champ,true);
		return false;
	} else {
		document.getElementById("introducedError").style.display = "none";
		surligne(champ,false);
		return true;
	}
}

function verifDiscountedDate(introduced, champ){
	var date = new Date();
	var today = date.getFullYear() + "-" + (date.getMonth() < 9 ? "0" : "") + (date.getMonth() + 1) + "-" + (date.getMonth() < 9 ? "0" : "")  + date.getDate();
	if(champ.value!="" && champ.value <= introduced.value){
		document.getElementById("discontinuedText").innerHTML = "The discontinued date can't be before the introduced date";
		document.getElementById("discontinuedError").style.display = "block";
		surligne(champ,true);
		return false;
	} else if(champ.value > today) {
		document.getElementById("discontinuedText").innerHTML = "The discontinued date can't be after today's date";
		document.getElementById("discontinuedError").style.display = "block";
		surligne(champ,true);
		return false;
	} else {
		document.getElementById("discontinuedError").style.display = "none";
		surligne(champ,false);
		return true;
	}
}

function verifForm() {
	var nomOk = verifNomComputer(document.forms["form"]["computerName"]);
	var introducedOk = verifIntroducedDate(document.forms["form"]["introduced"]);
	var discontinuedOk = verifDiscountedDate(document.forms["form"]["introduced"], document.forms["form"]["discontinued"]);
   
	if(nomOk && introducedOk && discontinuedOk)
		return true;
	else
	{
		alert("Please fill in correctly the fields");
		return false;
	}
}