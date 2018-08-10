function surligne(champ, erreur) {
   if(erreur)
      champ.style.backgroundColor = "#fba";
   else
      champ.style.backgroundColor = "";
}

function verifNomUser(champ) {
	if(champ.value.length < 1) {
		document.getElementById("userNameError").style.display = "block";
		surligne(champ,true);
		return false;
	} else {
		document.getElementById("userNameError").style.display = "none";
		surligne(champ,false);
		return true;
	}
}

function verifPassword(champ) {
	if(champ.value.length < 1) {
		document.getElementById("passwordError").style.display = "block";
		surligne(champ,true);
		return false;
	} else {
		document.getElementById("passwordError").style.display = "none";
		surligne(champ,false);
		return true;
	}
}

function verifLoginForm() {
	var nomOk = verifNomUser(document.forms["form"]["userName"]);
	var passwordOk = verifPassword(document.forms["form"]["password"]);
	return nomOk && passwordOk;
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
	var today = date.getFullYear() + "-" + (date.getMonth() < 9 ? "0" : "") + (date.getMonth() + 1) + "-" + (date.getDate() < 9 ? "0" : "")  + date.getDate();
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
	var today = date.getFullYear() + "-" + (date.getMonth() < 9 ? "0" : "") + (date.getMonth() + 1) + "-" + (date.getDate() < 9 ? "0" : "")  + date.getDate();
	if(champ.value!="" && champ.value <= introduced.value){
		document.getElementById("discontinuedTodayError").style.display = "none";
		document.getElementById("discontinuedIntroError").style.display = "block";
		surligne(champ,true);
		return false;
	} else if(champ.value > today) {
		document.getElementById("discontinuedIntroError").style.display = "none";
		document.getElementById("discontinuedTodayError").style.display = "block";
		surligne(champ,true);
		return false;
	} else {
		document.getElementById("discontinuedIntroError").style.display = "none";
		document.getElementById("discontinuedTodayError").style.display = "none";
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