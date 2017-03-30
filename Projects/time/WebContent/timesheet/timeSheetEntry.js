
$("document").ready(function(){
	eventHandlers1();
	loadcurrentWeekData();
});
	
	
Date.prototype.getNextWeekDay = function (d) {
    if (d) {
        var next = this;
        next.setDate( this.getDate() - this.getDay() + 7 + d );
        return next;
    }
}

Date.prototype.getCurentWeekDay = function (d) {
    if (d) {
        var next = this;
        next.setDate( this.getDate() - this.getDay()+ d );
        return next;
    }
}

Date.prototype.getPreviousWeekDay = function (d) {
    if (d) {
        var next = this;
        next.setDate( this.getDate() - this.getDay()-7+ d );
        return next;
    }
}
function eventHandlers1(){
	$("#timeSheet_EntryDilog_Div").dialog({
		autoOpen:false,
		height: "auto",
		width: 800
	});
	$("#timeSheet_prevWeek").click(function(){
		loadPrevWeekData();
	})

	$("#timeSheet_nextWeek").click(function(){
		loadNextWeekData();
	});
	
	$("#timeSheet_EntryDilog_Add").click(function(){
		addTimeSheetEntry();
	});
}
function eventHandlers2(){
	$(".timeSheet_EntryDilog_Edit").click(function(){
		editTimeSheetData($(this));
	})
	$(".timeSheet_EntryDilog_Save").click(function(){
		SaveEditedTimeSheetData($(this));
	})
}

function eventHandlers(){
	$(".timeSheet_task_href").click(function(){
		loaddialogdata($(this).attr("taskid"),$(this).text());
		$("#timeSheet_EntryDilog_Div").dialog("open");
	})
	tablePagination();
}

function getModifiedDate(currentDay){
	var date=currentDay.getDate();
	var weekDay=currentDay.getDay();
	var fullYear=currentDay.getFullYear();
	var month=currentDay.getMonth();
	return [months[month]+" "+date+" "+fullYear,fullYear+"/"+(parseInt(month)+1)+"/"+date];
}

function loadPrevWeekData(){	
	var currentDay1 = new Date($("#timeSheet_Startdate").attr("curMonday"));
	var curMonday = getModifiedDate(currentDay1.getPreviousWeekDay(1));
		
	var currentDay2 = new Date($("#timeSheet_Startdate").attr("curMonday"));
	var curSunday = getModifiedDate(currentDay2.getPreviousWeekDay(7));
	
	$("#timeSheet_Startdate").val(curMonday[0]);
	$("#timeSheet_Startdate").attr("curMonday",curMonday[1]);

	$("#timeSheet_Enddate").val(curSunday[0]);
	$("#timeSheet_Enddate").attr("curSunday",curSunday[1]);
	
	filterData(curMonday[1],curSunday[1]);
}

function loadNextWeekData(){
	var currentDay1 = new Date($("#timeSheet_Startdate").attr("curMonday"));
	var curMonday = getModifiedDate(currentDay1.getNextWeekDay(1));
		
	var currentDay2 = new Date($("#timeSheet_Startdate").attr("curMonday"));
	var curSunday = getModifiedDate(currentDay2.getNextWeekDay(7));
	
	$("#timeSheet_Startdate").val(curMonday[0]);
	$("#timeSheet_Startdate").attr("curMonday",curMonday[1]);

	$("#timeSheet_Enddate").val(curSunday[0]);
	$("#timeSheet_Enddate").attr("curSunday",curSunday[1]);
	
	filterData(curMonday[1],curSunday[1]);
}

function loadcurrentWeekData(){
	var currentDay1 = new Date();
	var curMonday = getModifiedDate(currentDay1.getCurentWeekDay(1));	
	
	var currentDay2 = new Date();
	var curSunday = getModifiedDate(currentDay2.getCurentWeekDay(7));
	
	$("#timeSheet_Startdate").val(curMonday[0]);
	$("#timeSheet_Startdate").attr("curMonday",curMonday[1]);
	
	$("#timeSheet_Enddate").val(curSunday[0]);
	$("#timeSheet_Enddate").attr("curSunday",curSunday[1]);
	
	filterData(curMonday[1],curSunday[1]);
}

function filterData(prev,next){
	var prevDate = new Date(prev);
	var nextDate = new Date(next);
	
	var filtertaskList=[];
	$.each(taskList,function(index,task){
		var taskCreatedDate = new Date(task.taskCreatedDate);
		if(taskCreatedDate.getTime() >= prevDate.getTime() && taskCreatedDate.getTime() <= nextDate.getTime() ){
			filtertaskList.push(task);
		}
	})
	
	var filtercrsList=[];
	$.each(crsList,function(index,crs){
		var crCreatedDate = new Date(crs.crCreatedDate);
		if(crCreatedDate.getTime() >= prevDate.getTime() && crCreatedDate.getTime() <= nextDate.getTime() ){
			filtercrsList.push(crs);
		}
	})

	var filterdefectsList=[];
	$.each(defectsList,function(index,defect){
		var defectCreatedDate = new Date(defect.defcreatedDate);
		if(defectCreatedDate.getTime() >= prevDate.getTime() && defectCreatedDate.getTime() <= nextDate.getTime() ){
			filterdefectsList.push(defect);
		}
	})
	loadTableData(filtertaskList,filtercrsList,filterdefectsList)
}

function loadTableData(filtertaskList,filtercrsList,filterdefectsList){
	$("#timeSheet_Task_Table tbody").html("");
	var taletbody="";
	$.each(filtertaskList,function(index,task){
		taletbody += '<tr class="table-task">'+
				        '<td><a href="#" class="timeSheet_task_href" taskId="'+task.taskId+'">'+task.taskName+'</a></td>'+
				        '<td>'+task.status+'</td>'+
				        '<td>'+task.mon+'</td>'+
				        '<td>'+task.tue+'</td>'+
				        '<td>'+task.wed+'</td>'+
				        '<td>'+task.thu+'</td>'+
				        '<td>'+task.fri+'</td>'+
				        '<td>'+task.sat+'</td>'+
				        '<td>'+task.sun+'</td>'+
				      '</tr>';
	});
	$.each(filtercrsList,function(index,crs){
		taletbody += '<tr class="table-crs">'+
				        '<td><a href="#" class="timeSheet_crs_href" crId="'+crs.crId+'">'+crs.crName+'</a></td>'+
				        '<td>'+crs.status+'</td>'+
				        '<td>'+crs.mon+'</td>'+
				        '<td>'+crs.tue+'</td>'+
				        '<td>'+crs.wed+'</td>'+
				        '<td>'+crs.thu+'</td>'+
				        '<td>'+crs.fri+'</td>'+
				        '<td>'+crs.sat+'</td>'+
				        '<td>'+crs.sun+'</td>'+
				      '</tr>';
	});
	$.each(filterdefectsList,function(index,defect){
		taletbody += '<tr class="table-defect">'+
				        '<td><a href="#" class="timeSheet_defect_href" defId="'+defect.defId+'">'+defect.defName+'</a></td>'+
				        '<td>'+defect.status+'</td>'+
				        '<td>'+defect.mon+'</td>'+
				        '<td>'+defect.tue+'</td>'+
				        '<td>'+defect.wed+'</td>'+
				        '<td>'+defect.thu+'</td>'+
				        '<td>'+defect.fri+'</td>'+
				        '<td>'+defect.sat+'</td>'+
				        '<td>'+defect.sun+'</td>'+
				      '</tr>';
	});
	$("#timeSheet_Task_Tale tbody").html(taletbody);
	eventHandlers();
}

function loaddialogdata(taskid,taskName){
	$("#timeSheet_EntryDilog_Taskname").val(taskName);
	$("#timeSheet_EntryDilog_Taskname").attr("taskId",taskid);
	
	$("#timeSheet_EntryDilog_Startdate").attr("curMonday",$("#timeSheet_Startdate").attr("curMonday"));
	$("#timeSheet_EntryDilog_Startdate").val($("#timeSheet_Startdate").val());
	
	$("#timeSheet_EntryDilog_Enddate").attr("curSunday",$("#timeSheet_Enddate").attr("curSunday"));
	$("#timeSheet_EntryDilog_Enddate").val($("#timeSheet_Enddate").val());
	
	var activityLiStr="";
	$("#timeSheet_EntryDilog_Activity").html("");
	$.each(activityList,function(index,act){
		activityLiStr += '<option value="'+act.activityId+'">'+act.activity+'</option>';
	})
	$("#timeSheet_EntryDilog_Activity").html(activityLiStr);
	
	var subactivityLiStr="";
	$("#timeSheet_EntryDilog_Subactivity").html("");
	$.each(subactivityList,function(index,subact){
		subactivityLiStr += '<option value="'+subact.subactivityId+'">'+subact.subactivity+'</option>';
	})
	$("#timeSheet_EntryDilog_Subactivity").html(subactivityLiStr);
	
	$("#timeSheet_EntryDilog_Task_Tale tbody").find("tr:gt(0)").remove();
	var taletbody="";
	
	if(timeSheetEntryIndexed[taskid]){
		var timeSheetSubActIndexed=timeSheetEntryIndexed[taskid];
		for (var key in timeSheetSubActIndexed) {
		    if (timeSheetSubActIndexed.hasOwnProperty(key)) {
		    	var subtimeIndexed=subActTimeIndexed[key];
		    	var timeIndexed=subtimeIndexed[$("#timeSheet_Startdate").attr("curMonday")];
		    	if(timeIndexed){
		    		taletbody = '<tr class="table-task">'+
			        '<td>'+timeSheetSubActIndexed[key].activity+'</td>'+
			        '<td>'+timeSheetSubActIndexed[key].subactivity+'</td>';
		    		var i=0;
			        $.each(timeIndexed,function(index,timeobj){
			        	taletbody += '<td weekDay="'+(++i)+'">'+timeobj.time+'</td>';
			        })
			        
			        taletbody += '<td><button type="button" id="" class="btn btn-success timeSheet_EntryDilog_Edit">Edit</button>'+
			        			 '<button type="button" id="" class="btn btn-info timeSheet_EntryDilog_Save" style="display:none">Save</button></td></tr>';
			        $("#timeSheet_EntryDilog_Task_Tale tbody").append(taletbody);
		    	}  			
		    }
		}
	}
	eventHandlers2();
}

function addTimeSheetEntry(){
	var taskId=$("#timeSheet_EntryDilog_Taskname").attr("taskId");
	
	var activity=$("#timeSheet_EntryDilog_Activity option:selected").text();
	var activityId=$("#timeSheet_EntryDilog_Activity option:selected").val();
	
	var subactivity=$("#timeSheet_EntryDilog_Subactivity option:selected").text();
	var subactivityId=$("#timeSheet_EntryDilog_Subactivity option:selected").val();
	
	var monHour=$("#timeSheet_monHour").val();
	var currentDay1 = new Date($("#timeSheet_EntryDilog_Startdate").attr("curMonday"));
	var mondayDate = getModifiedDate(currentDay1.getCurentWeekDay(1))[1];	
	
	var tueHour=$("#timeSheet_tueHour").val();
	var currentDay1 = new Date($("#timeSheet_EntryDilog_Startdate").attr("curMonday"));
	var tueDate = getModifiedDate(currentDay1.getCurentWeekDay(2))[1];
	
	var wedHour=$("#timeSheet_wedHour").val();
	var currentDay1 = new Date($("#timeSheet_EntryDilog_Startdate").attr("curMonday"));
	var wedDate = getModifiedDate(currentDay1.getCurentWeekDay(3))[1];
	
	var thuHour=$("#timeSheet_thuHour").val();
	var currentDay1 = new Date($("#timeSheet_EntryDilog_Startdate").attr("curMonday"));
	var thuDate = getModifiedDate(currentDay1.getCurentWeekDay(4))[1];
	
	var friHour=$("#timeSheet_friHour").val();
	var currentDay1 = new Date($("#timeSheet_EntryDilog_Startdate").attr("curMonday"));
	var friDate = getModifiedDate(currentDay1.getCurentWeekDay(5))[1];
	
	var satHour=$("#timeSheet_satHour").val();
	var currentDay1 = new Date($("#timeSheet_EntryDilog_Startdate").attr("curMonday"));
	var satDate = getModifiedDate(currentDay1.getCurentWeekDay(6))[1];
	
	var sunHour=$("#timeSheet_sunHour").val();
	var currentDay1 = new Date($("#timeSheet_EntryDilog_Startdate").attr("curMonday"));
	var sunDate = getModifiedDate(currentDay1.getCurentWeekDay(7))[1];
	
	var timeEntry=[ {date:mondayDate,time:monHour},{date:tueDate,time:tueHour},{date:wedDate,time:wedHour},
					{date:thuDate,time:thuHour},{date:friDate,time:friHour},{date:satDate,time:satHour},
					{date:sunDate,time:sunHour}];
	
	if(subActTimeIndexed[subactivityId]){
		var timeEntries=subActTimeIndexed[subactivityId];
		timeEntries[mondayDate]=timeEntry;
	}
	else{
		subActTimeIndexed[subactivityId]={};
		var timeEntries=subActTimeIndexed[subactivityId];
		timeEntries[mondayDate]=timeEntry;
	}
	
	var entryObj={	taskId:taskId,activity:activity,activityId:activityId,subactivity:subactivity,subactivityId:subactivityId,timeEntry:timeEntry};
	if(timeSheetEntryIndexed[taskId]){
		var subactivityEntries=timeSheetEntryIndexed[taskId];
		subactivityEntries[subactivityId]=entryObj;
	}
	else{
		timeSheetEntryIndexed[taskId]={};
		var subactivityEntries=timeSheetEntryIndexed[taskId];
		subactivityEntries[subactivityId]=entryObj;
	}
	loaddialogdata(taskId,$("#timeSheet_EntryDilog_Taskname").val());
}

function editTimeSheetData($this){
	var tds=$this.parent().siblings();
	var i=0;
	$.each(tds,function(index,td){
		var $tddum=td;
		switch(i){
			case 2:
				$tddum.innerHTML='<input type="text" id="timeSheet_monHour" class="timeSheet_Hour" value='+$tddum.innerHTML+'>';
				break;
			case 3:
				$tddum.innerHTML='<td><input type="text" id="timeSheet_tueHour" class="timeSheet_Hour" value='+$tddum.innerHTML+'></td>';
				break;
			case 4:
				$tddum.innerHTML='<td><input type="text" id="timeSheet_wedHour" class="timeSheet_Hour" value='+$tddum.innerHTML+'></td>';
				break;
			case 5:
				$tddum.innerHTML='<td><input type="text" id="timeSheet_thuHour" class="timeSheet_Hour" value='+$tddum.innerHTML+'></td>';
				break;
			case 6:
				$tddum.innerHTML='<td><input type="text" id="timeSheet_friHour" class="timeSheet_Hour" value='+$tddum.innerHTML+'></td>';
				break;
			case 7:
				$tddum.innerHTML='<td><input type="text" id="timeSheet_satHour" class="timeSheet_Hour" value='+$tddum.innerHTML+'></td>';
				break;
			case 8:
				$tddum.innerHTML='<td><input type="text" id="timeSheet_sunHour" class="timeSheet_Hour" value='+$tddum.innerHTML+'></td>';
				break;
		}i++;
	})
	$this.hide();
	$this.next().show();
	//(".timeSheet_EntryDilog_Edit")
	//$this.removeClass("timeSheet_EntryDilog_Edit");
	//$this.addClass("timeSheet_EntryDilog_Save");
};

function SaveEditedTimeSheetData($this){
	var tds=$this.parent().siblings();
	var i=0;
	$.each(tds,function(index,td){
		var $tddum=td;
		var $input=$tddum.innerHTML;
		console.log($tddum.children());
		
		switch(i){
			case 2:
				console.log($input,$input.val());
				//$tddum.innerHTML='<input type="text" id="timeSheet_monHour" class="timeSheet_Hour" value='+$tddum.innerHTML+'>';
				break;
			case 3:
				//$tddum.innerHTML='<td><input type="text" id="timeSheet_tueHour" class="timeSheet_Hour" value='+$tddum.innerHTML+'></td>';
				break;
			case 4:
				//$tddum.innerHTML='<td><input type="text" id="timeSheet_wedHour" class="timeSheet_Hour" value='+$tddum.innerHTML+'></td>';
				break;
			case 5:
				//$tddum.innerHTML='<td><input type="text" id="timeSheet_thuHour" class="timeSheet_Hour" value='+$tddum.innerHTML+'></td>';
				break;
			case 6:
				//$tddum.innerHTML='<td><input type="text" id="timeSheet_friHour" class="timeSheet_Hour" value='+$tddum.innerHTML+'></td>';
				break;
			case 7:
				//$tddum.innerHTML='<td><input type="text" id="timeSheet_satHour" class="timeSheet_Hour" value='+$tddum.innerHTML+'></td>';
				break;
			case 8:
				//$tddum.innerHTML='<td><input type="text" id="timeSheet_sunHour" class="timeSheet_Hour" value='+$tddum.innerHTML+'></td>';
				break;
		}i++;
	})
	$this.hide();
	$this.next().show();

}
function tablePagination(){
	$(".pager").remove();
	/*$('td', 'table').each(function(i) {
	    $(this).text(i+1);
	});*/

	$('#timeSheet_Task_Tale').each(function() {
	    var currentPage = 0;
	    var numPerPage = 5;
	    var $table = $(this);
	    $table.bind('repaginate', function() {
	        $table.find('tbody tr').hide().slice(currentPage * numPerPage, (currentPage + 1) * numPerPage).show();
	    });
	    $table.trigger('repaginate');
	    var numRows = $table.find('tbody tr').length;
	    var numPages = Math.ceil(numRows / numPerPage);
	    var $pager = $('<div class="pager"></div>');
	    for (var page = 0; page < numPages; page++) {
	        $('<span class="page-number"></span>').text(page + 1).bind('click', {
	            newPage: page
	        }, function(event) {
	            currentPage = event.data['newPage'];
	            $table.trigger('repaginate');
	            $(this).addClass('active').siblings().removeClass('active');
	        }).appendTo($pager).addClass('clickable');
	    }
	    $pager.insertBefore($table).find('span.page-number:first').addClass('active');
	});

}








// data sets
var days = ["Sun","Mon","Tue","Wed","Thu","Fri","Sat"];
var months = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];

var taskList=[	{taskId:"01",taskName:"task_01",status:"open",taskCreatedDate:"2017/03/29",mon:"00:30",tue:"01:00",wed:"02:00",thu:"08:00",fri:"10:40",sat:"05:00",sun:"05:00"},
				{taskId:"02",taskName:"task_02",status:"open",taskCreatedDate:"2017/03/30",mon:"00:30",tue:"01:00",wed:"02:00",thu:"08:00",fri:"10:40",sat:"05:00",sun:"05:00"},
				{taskId:"03",taskName:"task_03",status:"open",taskCreatedDate:"2017/03/31",mon:"00:30",tue:"01:00",wed:"02:00",thu:"08:00",fri:"10:40",sat:"05:00",sun:"05:00"},
				{taskId:"04",taskName:"task_04",status:"open",taskCreatedDate:"2017/04/01",mon:"00:30",tue:"01:00",wed:"02:00",thu:"08:00",fri:"10:40",sat:"05:00",sun:"05:00"},
				{taskId:"05",taskName:"task_05",status:"open",taskCreatedDate:"2017/04/03",mon:"00:30",tue:"01:00",wed:"02:00",thu:"08:00",fri:"10:40",sat:"05:00",sun:"05:00"},
				{taskId:"06",taskName:"task_06",status:"open",taskCreatedDate:"2017/04/08",mon:"00:30",tue:"01:00",wed:"02:00",thu:"08:00",fri:"10:40",sat:"05:00",sun:"05:00"},
				{taskId:"07",taskName:"task_07",status:"open",taskCreatedDate:"2017/04/09",mon:"00:30",tue:"01:00",wed:"02:00",thu:"08:00",fri:"10:40",sat:"05:00",sun:"05:00"},
				{taskId:"08",taskName:"task_08",status:"open",taskCreatedDate:"2017/04/11",mon:"00:30",tue:"01:00",wed:"02:00",thu:"08:00",fri:"10:40",sat:"05:00",sun:"05:00"},
				{taskId:"09",taskName:"task_09",status:"open",taskCreatedDate:"2017/04/02",mon:"00:30",tue:"01:00",wed:"02:00",thu:"08:00",fri:"10:40",sat:"05:00",sun:"05:00"},
				{taskId:"10",taskName:"task_10",status:"open",taskCreatedDate:"2017/02/28",mon:"00:30",tue:"01:00",wed:"02:00",thu:"08:00",fri:"10:40",sat:"05:00",sun:"05:00"},
				{taskId:"11",taskName:"task_11",status:"open",taskCreatedDate:"2017/03/15",mon:"00:30",tue:"01:00",wed:"02:00",thu:"08:00",fri:"10:40",sat:"05:00",sun:"05:00"},
				{taskId:"12",taskName:"task_12",status:"open",taskCreatedDate:"2017/03/19",mon:"00:30",tue:"01:00",wed:"02:00",thu:"08:00",fri:"10:40",sat:"05:00",sun:"05:00"}];

var crsList=[	{crId:"01",crName:"cr_01",status:"open",crCreatedDate:"2017/03/29",mon:"00:30",tue:"01:00",wed:"02:00",thu:"08:00",fri:"10:40",sat:"05:00",sun:"05:00"},
				{crId:"02",crName:"cr_02",status:"open",crCreatedDate:"2017/03/30",mon:"00:30",tue:"01:00",wed:"02:00",thu:"08:00",fri:"10:40",sat:"05:00",sun:"05:00"},
				{crId:"03",crName:"cr_03",status:"open",crCreatedDate:"2017/03/31",mon:"00:30",tue:"01:00",wed:"02:00",thu:"08:00",fri:"10:40",sat:"05:00",sun:"05:00"},
				{crId:"04",crName:"cr_04",status:"open",crCreatedDate:"2017/04/01",mon:"00:30",tue:"01:00",wed:"02:00",thu:"08:00",fri:"10:40",sat:"05:00",sun:"05:00"},
				{crId:"05",crName:"cr_05",status:"open",crCreatedDate:"2017/04/03",mon:"00:30",tue:"01:00",wed:"02:00",thu:"08:00",fri:"10:40",sat:"05:00",sun:"05:00"},
				{crId:"06",crName:"cr_06",status:"open",crCreatedDate:"2017/04/08",mon:"00:30",tue:"01:00",wed:"02:00",thu:"08:00",fri:"10:40",sat:"05:00",sun:"05:00"},
				{crId:"07",crName:"cr_07",status:"open",crCreatedDate:"2017/04/09",mon:"00:30",tue:"01:00",wed:"02:00",thu:"08:00",fri:"10:40",sat:"05:00",sun:"05:00"},
				{crId:"08",crName:"cr_08",status:"open",crCreatedDate:"2017/04/11",mon:"00:30",tue:"01:00",wed:"02:00",thu:"08:00",fri:"10:40",sat:"05:00",sun:"05:00"},
				{crId:"09",crName:"cr_09",status:"open",crCreatedDate:"2017/04/02",mon:"00:30",tue:"01:00",wed:"02:00",thu:"08:00",fri:"10:40",sat:"05:00",sun:"05:00"},
				{crId:"10",crName:"cr_10",status:"open",crCreatedDate:"2017/02/28",mon:"00:30",tue:"01:00",wed:"02:00",thu:"08:00",fri:"10:40",sat:"05:00",sun:"05:00"},
				{crId:"11",crName:"cr_11",status:"open",crCreatedDate:"2017/03/15",mon:"00:30",tue:"01:00",wed:"02:00",thu:"08:00",fri:"10:40",sat:"05:00",sun:"05:00"},
				{crId:"12",crName:"cr_12",status:"open",crCreatedDate:"2017/03/19",mon:"00:30",tue:"01:00",wed:"02:00",thu:"08:00",fri:"10:40",sat:"05:00",sun:"05:00"}];

var defectsList=[	{defId:"01",defName:"def_01",status:"open",defcreatedDate:"2017/03/29",mon:"00:30",tue:"01:00",wed:"02:00",thu:"08:00",fri:"10:40",sat:"05:00",sun:"05:00"},
					{defId:"02",defName:"def_02",status:"open",defcreatedDate:"2017/03/30",mon:"00:30",tue:"01:00",wed:"02:00",thu:"08:00",fri:"10:40",sat:"05:00",sun:"05:00"},
					{defId:"03",defName:"def_03",status:"open",defcreatedDate:"2017/03/31",mon:"00:30",tue:"01:00",wed:"02:00",thu:"08:00",fri:"10:40",sat:"05:00",sun:"05:00"},
					{defId:"04",defName:"def_04",status:"open",defcreatedDate:"2017/04/01",mon:"00:30",tue:"01:00",wed:"02:00",thu:"08:00",fri:"10:40",sat:"05:00",sun:"05:00"},
					{defId:"05",defName:"def_05",status:"open",defcreatedDate:"2017/04/03",mon:"00:30",tue:"01:00",wed:"02:00",thu:"08:00",fri:"10:40",sat:"05:00",sun:"05:00"},
					{defId:"06",defName:"def_06",status:"open",defcreatedDate:"2017/04/08",mon:"00:30",tue:"01:00",wed:"02:00",thu:"08:00",fri:"10:40",sat:"05:00",sun:"05:00"},
					{defId:"07",defName:"def_07",status:"open",defcreatedDate:"2017/04/09",mon:"00:30",tue:"01:00",wed:"02:00",thu:"08:00",fri:"10:40",sat:"05:00",sun:"05:00"},
					{defId:"08",defName:"def_08",status:"open",defcreatedDate:"2017/04/28",mon:"00:30",tue:"01:00",wed:"02:00",thu:"08:00",fri:"10:40",sat:"05:00",sun:"05:00"},
					{defId:"09",defName:"def_09",status:"open",defcreatedDate:"2017/04/02",mon:"00:30",tue:"01:00",wed:"02:00",thu:"08:00",fri:"10:40",sat:"05:00",sun:"05:00"},
					{defId:"10",defName:"def_10",status:"open",defcreatedDate:"2017/02/28",mon:"00:30",tue:"01:00",wed:"02:00",thu:"08:00",fri:"10:40",sat:"05:00",sun:"05:00"},
					{defId:"11",defName:"def_11",status:"open",defcreatedDate:"2017/03/15",mon:"00:30",tue:"01:00",wed:"02:00",thu:"08:00",fri:"10:40",sat:"05:00",sun:"05:00"},
					{defId:"12",defName:"def_12",status:"open",defcreatedDate:"2017/03/19",mon:"00:30",tue:"01:00",wed:"02:00",thu:"08:00",fri:"10:40",sat:"05:00",sun:"05:00"}];


var activityList=[	{activityId:"01",activity:"Act_01"},{activityId:"02",activity:"Act_02"},
					{activityId:"03",activity:"Act_03"},{activityId:"04",activity:"Act_04"}];

var subactivityList=[	{subactivityId:"01",subactivity:"subAct_01"},{subactivityId:"02",subactivity:"subAct_02"},
					{subactivityId:"03",subactivity:"subAct_03"},{subactivityId:"04",subactivity:"subAct_04"}];

var timeSheetEntryIndexed={};

var subActTimeIndexed={};












