var Wine = Wine || {};

Wine.UploadFoto = (function(){
	
	function UploadFoto(){
		this.uploadDrop = $('#upload-drop');
		this.containerFoto = $('.js-container-foto');
	}
	
	UploadFoto.prototype.iniciar = function(){
		var settings = {
			type: 'json',
			filelimit: 1,
			allow: '*.(jpg|jpeg|png)',
			action: '/fotos/' + uploadDrop.data('codigo'),
			complete: onUploadComplete.bind(this),			
			beforeSend: adicionarCsfrToken
		};
			
			UIkit.uploadSelect($('#upload-select'), settings);
			UIkit.uploadDrop(this.uploadDrop, settings);	
	}
	
	function adicionarCsrfToken(xhr){
		var header = $('input[name =_csrf_header]').val();
		var token = $('input[name = _csrf').val();
		xhr.setRequestHeader(header, token);
	}
	
	function onUploadComplete(){
		this.uploadDrop.addClass('hidden');
		this.containerFoto.prepend('<img src="'+foto.url+'" class="img-responsive" style="margin:auto;"/>');
	}
	
	return UploadFoto;
	
})();

$(function(){
	
	var uploadFoto = new Wine.UploadFoto();
	uploadFoto.iniciar();
	
		
});
	
