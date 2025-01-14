/**
 * 
 */
'use strict'
function correctCheck(corrections){
	var table = document.getElementById("table");
	var tr = table.tBodies[0].rows;
	
	
	for(let i = 0; i < corrections.length; i++){
		if(corrections[i] === true){
			// img_element = createCorrectImage();
			tr[i].style.background = "#F0B9B9";
			// tr[i].cells[1].appendChild(img_element);
			console.log("true");
		} else {
			var img_element = createInCorrectImage();
			tr[i].style.background = "#D3DEF1";
			tr[i].cells[1].appendChild(img_element);
			console.log("false")
		}
	}
}



function createInCorrectImage(){
	let img_element = document.createElement('img');
	img_element.src = '../img/incorrect.png';
	img_element.alt = '不正解';
	img_element.width = 100;
	img_element.height = 100;
	img_element.classList.add("img");
	// source要素を追加
	// let source_element = document.createElement('source');
	// source_element.srcset = '..img/incorrect.png';
	// source_element.media = '(min-width: 800px)';

	// picture要素にimg要素とsource要素を追加
	// picture_element.appendChild(img_element);
	// picture_element.appendChild(source_element);
	return img_element;
}

// ページ読み込み後に実行
document.addEventListener('DOMContentLoaded', () => {
    const corrections = JSON.parse(correctionsData.textContent);
	correctCheck(corrections);
});
