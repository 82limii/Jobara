<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link href="https://www.jqueryscript.net/css/jquerysctipttop.css" rel="stylesheet" type="text/css">
 <link href="https://www.jqueryscript.net/demo/Simple-Chart-Plugin-with-jQuery-Gantt-Chart/css/style.css?v2" rel="stylesheet" type="text/css">
<!--  <script type="text/javascript" src="https://www.jqueryscript.net/demo/Simple-Chart-Plugin-with-jQuery-Gantt-Chart/js/jquery.fn.gantt.js?v2"></script> -->
<div class="col-lg-12">
  <div class="card-body">
	<div class="gantt">
		<div id="calendar"></div>
	</div>
  </div>
</div>
<script>
window.addEventListener("load", function(){
	let script = document.createElement("script");
	script.src = "https://www.jqueryscript.net/demo/Simple-Chart-Plugin-with-jQuery-Gantt-Chart/js/jquery.fn.gantt.js?v2";
	document.body.appendChild(script);
	
	script.addEventListener("load", function(){

		var myData = [
			{
		    name: "Name 1",
		    desc: "Description 1",
		    values: [{
		      from: 1320192000000, // <a href="https://www.jqueryscript.net/time-clock/">date</a> string
		      to: 1322401600000,
		      label: "Label 1",
		      desc: "Value Description 1",
		      customClass: "custom-1",
		      dataObj: {}
		    }]
		},{
		    name: "Name 2",
		    desc: "Description 2",
		    values: [{
		      from: 1320192000000,
		      to: 1322401600000,
		      label: "Label 2",
		      desc: "Value Description 2",
		      customClass: "custom-2",
		      dataObj: {}
		    }]
		},{
		    name: "Name 3",
		    desc: "Description 3",
		    values: [{
		      from: 1320192000000,
		      to: 1322401600000,
		      label: "Label 3",
		      desc: "Value Description 3",
		      customClass: "custom-3",
		      dataObj: {}
			}]
			}
		];

		$(".gantt").gantt({
			  // or 'data/data.json'
			  source: myData
			});

	})
});


</script>