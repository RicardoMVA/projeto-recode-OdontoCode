document.querySelector("#fetch").addEventListener("click", (e) => {
	fetch(`/api/v1/patients`)
	  .then((response) => {
	    return response.json();
	  })
	  .then((dbResponse) => {
	  	document.querySelector("#result").innerHTML = JSON.stringify(dbResponse);	
	  })
	  .catch((err) => {
	    console.log(err);
	  });
})
