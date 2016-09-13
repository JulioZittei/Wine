var Wine = Wine || {};

Wine.UploadFoto = (function() { 
	
	function UploadFoto() {
		this.uploadDrop = $('#upload-drop');
		this.containerFoto = $('.js-container-foto');
	}
	
	UploadFoto.prototype.iniciar = function() {
		var settings = {
			type: 'json',
			filelimit: 1,
			allow: '*.(jpg|jpeg|png)',
			action: '/fotos/' + this.uploadDrop.data('codigo'),
			complete: onUploadCompleto.bind(this), 
			beforeSend: adicionarCsrfToken
		};
		
		UIkit.uploadSelect($('#upload-select'), settings);
		UIkit.uploadDrop(this.uploadDrop, settings);
	}
	
	function adicionarCsrfToken(xhr) {
		var header = $('input[name=_csrf_header]').val();
		var token = $('input[name=_csrf]').val();
		xhr.setRequestHeader(header, token);
	}
	
	function onUploadCompleto(foto) {
		this.uploadDrop.addClass('hidden');
		this.containerFoto.append('<img src="' + foto.url + '" class="img-responsive" style="margin: auto"/>');
		$('.close#remove-button').removeClass('hidden');
	}
	
	return UploadFoto;
	
})();

var Winee = Winee || {};

Winee.ExclusaoFoto = (function() { 
	
	function ExclusaoFoto() {
		this.exclusao = $('.js-container-foto');
		this.foto = $('#upload-drop');
	}
	
	ExclusaoFoto.prototype.iniciar = function() {
		var response = $.ajax({
			type: 'DELETE',
			url: '/fotos/' + this.foto.data('codigo'),
			beforeSend: adicionarCsrfToken
		});
		
		response.done(function(data){
			console.log($('#upload-drop').data('codigo') + " " + data.url);
			$('.img-responsive').addClass('hidden');
			$('#upload-drop').removeClass('hidden');
			$('.js-container-foto').prepend('<img class="hidden" src="'+data.url+'"/>');
			$('#remove-button').addClass('hidden');
		});
		
	}
	
	function adicionarCsrfToken(xhr) {
		var header = $('input[name=_csrf_header]').val();
		var token = $('input[name=_csrf]').val();
		xhr.setRequestHeader(header, token);
	}

	
	return ExclusaoFoto;
	
})();

$(function() {
	
	var uploadFoto = new Wine.UploadFoto();
	uploadFoto.iniciar();
	
	$('#remove-button').on('click',function(){
		var exclusaoFoto = new Winee.ExclusaoFoto();
		exclusaoFoto.iniciar();
	});

	
});