function surligne(champ, erreur) {
   if(erreur)
      champ.style.backgroundColor = "#fba";
   else
      champ.style.backgroundColor = "";
}

function verifNomComputer(champ) {
	if(champ.value < 1){
		surligne(champ,true);
		return false;
	} else {
		surligne(champ,false);
		return true;
	}
}

function verifIntroducedDate(champ) {
	var date = new Date();
	if(champ.value < date.getDate+"/"+date.getMonth()+"/"+date.getFullYear()){
		surligne(champ,true);
		return false;
	} else {
		surligne(champ,false);
		return true;
	}
}

function verifDiscountedDate(introduced, champ){
	if(champ.value > introduced.value){
		surligne(champ,true);
		return false;
	} else {
		surligne(champ,false);
		return true;
	}
}