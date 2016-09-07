
$(function(){
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
});
	
