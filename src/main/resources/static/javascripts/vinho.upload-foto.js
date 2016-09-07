$(function(){
	
	var settings = {
		type:'jason',
		filelimit:1,
		allow:'*.(jpg|jpeg|png)',
		action:'/fotos/',
		complete:function(response){
			console.log('resposta = '+response);
		}
	};
	
	UIkit.uploadSelect($('#upload-select'),settings);
	UIkit.uploadDrop($('#upload-drop'),settings);
	
	
});