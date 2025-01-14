/**
 * 
 */
'use strict'
// 正誤チェックを行う
function correctCheck(corrections){
	var table = document.getElementById("table");
	var tr = table.tBodies[0].rows;
	
	
	for(let i = 0; i < corrections.length; i++){
		if(corrections[i] === true){
			var img_element = createCorrectImage();
			tr[i].style.background = "#F0B9B9";
			tr[i].cells[1].appendChild(img_element);
			console.log("true");
		} else {
			var img_element = createInCorrectImage();
			tr[i].style.background = "#D3DEF1";
			tr[i].cells[1].appendChild(img_element);
			console.log("false")
		}
	}
}

// 「〇」の画像要素を作成する
function createCorrectImage(){
	let img_element = document.createElement('img');
	img_element.src = '../img/correct.png';
	img_element.alt = '正解';
	img_element.width = 100;
	img_element.height = 100;
	img_element.classList.add("img");
	
	return img_element;
}

// 「×」の画像要素を作成する
function createInCorrectImage(){
	let img_element = document.createElement('img');
	img_element.src = '../img/incorrect.png';
	img_element.alt = '不正解';
	img_element.width = 100;
	img_element.height = 100;
	img_element.classList.add("img");
	
	return img_element;
}

// ページ読み込み後に実行
document.addEventListener('DOMContentLoaded', () => {
    const corrections = JSON.parse(correctionsData.textContent);
	correctCheck(corrections);
});
