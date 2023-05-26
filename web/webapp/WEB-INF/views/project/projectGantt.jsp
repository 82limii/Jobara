<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Highcharts Gantt Example</title>

		<style type="text/css">
#container,
#buttonGroup {
    max-width: 1200px;
    min-width: 320px;
    margin: 1em auto;
}

.hidden {
    display: none;
}

.main-container button {
    font-size: 12px;
    border-radius: 2px;
    border: 0;
    background-color: #ddd;
    padding: 13px 18px;
}

.main-container button[disabled] {
    color: silver;
}

.button-row button {
    display: inline-block;
    margin: 0;
}

.overlay {
    position: fixed;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    background: rgba(0 0 0 / 30%);
    transition: opacity 500ms;
}

.popup {
    margin: 70px auto;
    padding: 20px;
    background: #fff;
    border-radius: 5px;
    width: 300px;
    position: relative;
}

.popup input,
.popup select {
    width: 100%;
    margin: 5px 0 15px;
}

.popup button {
    float: right;
    margin-left: 0.2em;
}

.popup .clear {
    height: 50px;
}

.popup input[type="text"],
.popup select {
    height: 2em;
    font-size: 16px;
}

		</style>
	</head>
<script src="${cPath }/resources/Highcharts-Gantt/code/highcharts-gantt.js"></script>
<script src="${cPath }/resources/Highcharts-Gantt/code/modules/draggable-points.js"></script>
<script src="${cPath }/resources/Highcharts-Gantt/code/modules/exporting.js"></script>

<div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
       	<div class="card-body">
        <h4 class="card-title">간트 차트</h4>
	    <div id="container"></div>
		    <div id="buttonGroup" class="button-row">
		    	<input type="button" class="btn btn-outline-secondary linkBtn" value="업무추가"
					data-url="${cPath }/project/projTaskInsert.do"
				/><br>
		    </div>
		</div>
	</div>
</div>



		<script type="text/javascript">
/*
    Simple demo showing some interactivity options of Highcharts Gantt. More
    custom behavior can be added using event handlers and API calls. See
    http://api.highcharts.com/gantt.
*/

var today = new Date(),
    day = 1000 * 60 * 60 * 24,
    each = Highcharts.each,
    reduce = Highcharts.reduce,
    btnShowDialog = document.getElementById('btnShowDialog'),
    btnRemoveTask = document.getElementById('btnRemoveSelected'),
    btnAddTask = document.getElementById('btnAddTask'),
    btnCancelAddTask = document.getElementById('btnCancelAddTask'),
    addTaskDialog = document.getElementById('addTaskDialog'),
    inputName = document.getElementById('inputName'),
    selectDepartment = document.getElementById('selectDepartment'),
    selectDependency = document.getElementById('selectDependency'),
    chkMilestone = document.getElementById('chkMilestone'),
    isAddingTask = false;

// Set to 00:00:00:000 today
today.setUTCHours(0);
today.setUTCMinutes(0);
today.setUTCSeconds(0);
today.setUTCMilliseconds(0);
today = today.getTime();


// Update disabled status of the remove button, depending on whether or not we
// have any selected points.
function updateRemoveButtonStatus() {
    var chart = this.series.chart;
    // Run in a timeout to allow the select to update
    setTimeout(function () {
        btnRemoveTask.disabled = !chart.getSelectedPoints().length ||
            isAddingTask;
    }, 10);
}


//개인회원 로그인시 --------------------------
$(document).ready(function() {
//     var options = {
//         chart: {
//             renderTo: 'container',
//             type: 'spline'
//         },
//         series: [{}]
//     };
    
    var url =  "/project/projGantt.do";
    $.getJSON(url,  function(data) {
//     	$.each(data, function(idx, single){
//     		single.start = Date.parse( single.start );
//     		single.end = Date.parse( single.end );
//     	});
//         options.series[0].data = data;
//         var chart = new Highcharts.Chart(options);
    	//Create the chart
    	var chart = Highcharts.ganttChart('container', {
    		
    		 yAxis: {
    		        uniqueNames: true
    		    },

    		    navigator: {
    		        enabled: true,
    		        liveRedraw: true,
    		        series: {
    		            type: 'gantt',
    		            pointPlacement: 0.5,
    		            pointPadding: 0.25
    		        },
    		        yAxis: {
    		            min: 0,
    		            max: 3,
    		            reversed: true,
    		            categories: []
    		        }
    		    },
    		
    		 scrollbar: {
    		        enabled: true
    		    },
    		    rangeSelector: {
    		        enabled: true,
    		        selected: 0
    		    },
    		
    	    chart: {
    	        spacingLeft: 1
    	    },

    	    plotOptions: {
    	        series: {
    	            animation: false, // Do not animate dependency connectors
    	            dragDrop: {
    	                draggableX: true,
    	                draggableY: true,
    	                dragMinY: 1,
    	                dragMaxY: 10
    	            },
    	            dataLabels: {
    	                enabled: true,
    	                format: '{point.name}',
    	                style: {
    	                    cursor: 'default',
    	                    pointerEvents: 'none'
    	                }
    	            },
    	            allowPointSelect: true,
    	            point: {
    	                events: {
    	                    select: updateRemoveButtonStatus,
    	                    unselect: updateRemoveButtonStatus,
    	                    remove: updateRemoveButtonStatus
    	                }
    	            }
    	        }
    	    },
    	    
    	//세로줄
    	    yAxis: {
    	        type: 'category',
    	        categories: ['0%','10%','20%','30%','40%','50%','60%','70%','80%','90%','100%'],
    	        min: 0,
    	        max: 10
    	    },

    	    xAxis: {
    	        currentDateIndicator: true
    	    },

    	    tooltip: {
    	        xDateFormat: '%a %b %d'
    	    },
    	// 보여지는 업무 목록들
    	    series: [{
    	        name: 'Project 1',
    	        data: data
    	    }]
    	});

    });
});

//기업 회원 로그인시 --------------------------
$(document).ready(function() {
//     var options = {
//         chart: {
//             renderTo: 'container',
//             type: 'spline'
//         },
//         series: [{}]
//     };
    
    var url =  "/project/com/projGantt.do";
    $.getJSON(url,  function(data) {
//     	$.each(data, function(idx, single){
//     		single.start = Date.parse( single.start );
//     		single.end = Date.parse( single.end );
//     	});
//         options.series[0].data = data;
//         var chart = new Highcharts.Chart(options);
    	//Create the chart
    	var chart = Highcharts.ganttChart('container', {
    		
    		 yAxis: {
    		        uniqueNames: true
    		    },

    		    navigator: {
    		        enabled: true,
    		        liveRedraw: true,
    		        series: {
    		            type: 'gantt',
    		            pointPlacement: 0.5,
    		            pointPadding: 0.25
    		        },
    		        yAxis: {
    		            min: 0,
    		            max: 3,
    		            reversed: true,
    		            categories: []
    		        }
    		    },
    		
    		 scrollbar: {
    		        enabled: true
    		    },
    		    rangeSelector: {
    		        enabled: true,
    		        selected: 0
    		    },
    		
    	    chart: {
    	        spacingLeft: 1
    	    },

    	    plotOptions: {
    	        series: {
    	            animation: false, // Do not animate dependency connectors
    	            dragDrop: {
    	                draggableX: true,
    	                draggableY: true,
    	                dragMinY: 1,
    	                dragMaxY: 10
    	            },
    	            dataLabels: {
    	                enabled: true,
    	                format: '{point.name}',
    	                style: {
    	                    cursor: 'default',
    	                    pointerEvents: 'none'
    	                }
    	            },
    	            allowPointSelect: true,
    	            point: {
    	                events: {
    	                    select: updateRemoveButtonStatus,
    	                    unselect: updateRemoveButtonStatus,
    	                    remove: updateRemoveButtonStatus
    	                }
    	            }
    	        }
    	    },
    	    
    	//세로줄
    	    yAxis: {
    	        type: 'category',
    	        categories: ['0%','10%','20%','30%','40%','50%','60%','70%','80%','90%','100%'],
    	        min: 0,
    	        max: 10
    	    },

    	    xAxis: {
    	        currentDateIndicator: true
    	    },

    	    tooltip: {
    	        xDateFormat: '%a %b %d'
    	    },
    	// 보여지는 업무 목록들
    	    series: [{
    	        name: 'Project 1',
    	        data: data
    	    }]
    	});

    });
});

</script>

