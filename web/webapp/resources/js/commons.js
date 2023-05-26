/**
 * 
 */
feather.replace();
$(document).on("click", ".linkBtn", function(){
	let url = $(this).data("url");
	if(url)
		location.href=url;
});
$("a").each(function(){
	if(this.href==location.pathname || this.href==location.href)
		$(this).addClass("active");
	else
		$(this).removeClass("active");
});