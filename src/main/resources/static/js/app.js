const cloud = {
    "format": "png",
    "width": 1000,
    "height": 1000,
    "fontScale": 15,
    "scale": "linear",
    "removeStopwords": true,
    "minWordLength": 4,
    "text": "Welcome to Project Cloud!"
}

function updateCloud(wordCloud) {
	if(document.getElementById("cloud-img")!==null){
		fetch("https://quickchart.io/wordcloud", {
			body: JSON.stringify(cloud),
			headers: {
				"dataType": "json",
				"content-type": "application/json"
			},
			method: "POST"
		})
		.then(response => {
			const loadingMessageDiv = document.getElementById("fetching-cloud");
			removeChildren(loadingMessageDiv);
			document.getElementById("cloud-img").src = response.url+"?text="+wordCloud;})
		.catch(err => console.error(err));
	}
}

const removeChildren = (parent) => {
    while(parent.lastChild){
        parent.removeChild(parent.lastChild);
    }
}

function getCloudWords() {
  	let req = new XMLHttpRequest();
	req.open('GET', "/api/cloud");
  	req.onload = function() {
    	if (req.status == 200) {
			var cloudWords = "";
			const jsonData = JSON.parse(this.responseText);
			for (var i = 0; i < jsonData.length; i++) {
				cloudWords += jsonData[i].name + " ";
			}
			if(cloudWords.length<3){
				cloudWords = "Welcome to Project Cloud"
			}
			updateCloud(cloudWords);
    	} else {
      		myCallback("Error: " + req.status);
    	}
  	}
  	req.send();
}

getCloudWords();