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
		this.containerFoto.prepend('<img src="' + foto.url + '" class="img-responsive" style="margin: auto"/>'
				+ '<button type="button" id="remove-button" class="close" data-dismiss="alert" aria-label="Close" th:if="${vinho.temFoto()}"><span aria-hidden="true">&times;</span></button>');
	}
	
	return UploadFoto;
	
})();

var Winee = Winee || {};

Winee.ExclusaoFoto = (function() { 
	
	function ExclusaoFoto() {
		this.exclusao = $('#upload-drop');
		this.foto = $('.img-responsive');
	}
	
	ExclusaoFoto.prototype.iniciar = function() {
		var response = $.ajax({
			type: 'DELETE',
			url: '/fotos/' + this.foto.data('codigo'),
			complete: onComplete.bind(this), 
			beforeSend: adicionarCsrfToken
		});
		
	}
	
	function adicionarCsrfToken(xhr) {
		var header = $('input[name=_csrf_header]').val();
		var token = $('input[name=_csrf]').val();
		xhr.setRequestHeader(header, token);
	}
	
	function onComplete(foto) {
		$('.img-responsive').addClass('hidden');
		this.exclusao.prepend('<div id="upload-drop" class="wn-upload" th:attr="data-codigo=${vinho.codigo}" th:if="${not vinho.temFoto()}">'
				+'<img th:src="@{/layout/images/mockup-garrafa.png}"/><div><span>Arraste a foto aqui ou </span>'+
						'<a class="wn-upload-form-file"> selecione <input id="upload-select" type="file" accept=".jpg,.jpeg,.png"/> </a>'
					+'</div>'+
				'</div>');
		$('#remove-button').addClass('hidden');
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