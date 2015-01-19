/**
 * JS usado para possibilitar a configuração de interface.
 */

function alterarBGHtml () {
	var corBGHtml = $('.corPlanoFundo *').eq(3).val();
	$('html').eq(0).css('background-color', '#' + corBGHtml);
}

function alterarBGContent() {
	var corBGConteudo = $('.corConteudo *').eq(3).val();
	$('#container').eq(0).css('background-color', '#' + corBGConteudo);
}

function habilitaDesabilitaSombra(checkbox) {
	var container =  document.getElementById("container");
	if(checkbox.checked) {
		container.style.boxShadow = "none";
	} else {
		container.style.boxShadow = "0px 0px 1em #666";
	}
}

function alterarArredontamento() {
	var arredondamento = $('.tamanhoFonteSlide').parents().eq(2).find('input');
	$('.ui-corner-all').css('border-radius', arredondamento + 'px')
}

function addOnChangeEvent() {
	$('.ui-colorpicker_color').eq(0).mouseover(function() {alterarBGHtml();});
	$('.ui-colorpicker_color').eq(1).mouseover(function() {alterarBGContent();});
	$('.ui-colorpicker_color').eq(0).mouseout(function() {alterarBGHtml();});
	$('.ui-colorpicker_color').eq(1).mouseout(function() {alterarBGContent();});
	
	$('.tamanhoFonteSlide').eq(0).mouseover(function() {alterarArredontamento();});
}
