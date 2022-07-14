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

if(document.getElementById("cloud-img")!==null){
	fetch("https://quickchart.io/wordcloud", {
		body: JSON.stringify(cloud),
		headers: {
			"dataType": "json",
			"content-type": "application/json"
		},
		method: "POST"
	})
	.then(response => document.getElementById("cloud-img").src = response.url+"?text=Welcome to Project Cloud")
	.catch(err => console.error(err));
}