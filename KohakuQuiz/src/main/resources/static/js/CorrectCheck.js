/**
 * 
 */
'use strict'
function correctCheck(corrections){
	var table = document.getElementById("table");
	var tr = table.tr.rows;
	
	
	for(let i = 0; i < corrections.length; i++){
		if(corrections[i] === true){
			tr[i].style.background = "blue";
			console.log("true");
		} else {
			tr[i].style.background = "red";
			console.log("false")
		}
	}
}

// ページ読み込み後に実行
document.addEventListener('DOMContentLoaded', () => {
    const corrections = document.getElementById('correctionsData');
    console.log(corrections);
	correctCheck(corrections);
});
