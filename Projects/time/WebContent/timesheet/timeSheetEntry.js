
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
};

Date.prototype.getCurentWeekDay = function (d) {
    if (d) {
        var next = this;
        next.setDate( this.getDate() - this.getDay()+ d );
        return next;
    }
};

Date.prototype.getPreviousWeekDay = function (d) {
    if (d) {
        var next = this;
        next.setDate( this.getDate() - this.getDay()-7+ d );
        return next;
    }
};

function eventHandlers1(){
	$("#timeSheet_EntryDilog_Div").dialog({
		title:"Time Sheet Entry",
		autoOpen:false,
		height: "auto",
		width: 800
	});
	$("#timeSheet_EntryDilog2_Div").dialog({
		title:"Time Sheet Entry",
		autoOpen:false,
		height: "auto",
		width: 650
	});
	$("#timeSheet_prevWeek").click(function(){
		loadPrevWeekData();
	})

	$("#timeSheet_nextWeek").click(function(){
		loadNextWeekData();
	});
	
	$(".timeSheet_Hour").on("change paste keyup", function() {
		var time=$(this).val();
		if(time.length > 5){
			$(this).val(time.slice(0,-1));
		}
		
		if(time.length > 3){
			var hour=time.split(":")[0];
			var min=time.split(":")[1];
			if(isNaN(min)  || parseInt(min) > 60 || parseInt(min) < 0){
				if(isNaN(hour)  || parseInt(hour) > 23){
					$(this).val("");
				}
				else{
					$(this).val(hour+":");
				}
			}
			else{
				
			}
		}
		if(time.length <= 2){
			if(isNaN(time)  || parseInt(time) > 23 || parseInt(time) < 0){
				$(this).val("");
			}
			else{
				if(time.length == 2){
					$(this).val(time+":");
				}
				else{
					//$(this).val(time);
				}
			}
		}
	});
	$("#timeSheet_EntryDilog2_THour").on("change paste keyup", function() {
		var time=$(this).val();
		if(time.length <= 2){
			if(isNaN(time)  || parseInt(time) > 23 || parseInt(time) < 0){
				$(this).val("");
			}
			else{
				
			}
		}
		if(time.length > 2){
			$(this).val(time.slice(0,-1));
		}
	});
	$("#timeSheet_EntryDilog2_TMin").on("change paste keyup", function() {
		var time=$(this).val();
		if(time.length <= 2){
			if(isNaN(time)  || parseInt(time) > 60 || parseInt(time) < 0){
				$(this).val("");
			}
			else{
				
			}
		}
		if(time.length > 2){
			$(this).val(time.slice(0,-1));
		}
	});
	$("#timeSheet_EntryDilog_Add").click(function(){
		addTimeSheetEntry();
	});
	$("#timeSheet_EntryDilog2_Save").click(function(){
		saveTimeSheetEntry2();
	});
	
	$("#timeSheet_EntryDilog_Activity").change(function(){
		var activityId=$("#timeSheet_EntryDilog_Activity").val();
		loadSubActivities1(activityId);
	});
	
	$("#timeSheet_EntryDilog2_Activity").change(function(){
		var activityId=$("#timeSheet_EntryDilog2_Activity").val();
		loadSubActivities2(activityId);
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

function dilg2eventHandlers2(){
	$(".timeSheet_EntryDilog2_Edit").click(function(){
		editTimeSheetData2($(this));
	})
	$(".timeSheet_EntryDilog2_Save").click(function(){
		SaveEditedTimeSheetData2($(this));
	})
}

function eventHandlers(){
	$(".timeSheet_task_href").click(function(){
		loaddialogdata($(this).attr("taskid"),$(this).text());
		$("#timeSheet_EntryDilog_Div").dialog("open");
	});
	$(".timeSheet_crs_href").click(function(){
		loaddialogdata($(this).attr("crId"),$(this).text());
		$("#timeSheet_EntryDilog_Div").dialog("open");
	});
	$(".timeSheet_defect_href").click(function(){
		loaddialogdata($(this).attr("defId"),$(this).text());
		$("#timeSheet_EntryDilog_Div").dialog("open");
	});
	
	$(".timeSheet_taskDay_href").click(function(){
		var taskId=$(this).attr("taskid");
		var taskName=$(this).attr("taskName");
		loaddialogdata2(taskId,taskName,$(this).attr("weekday"));
	});
	$(".timeSheet_crsDay_href").click(function(){
		var crId=$(this).attr("crId");
		var crName=$(this).attr("crName");
		loaddialogdata2(crId,crName,$(this).attr("weekday"));
	});
	$(".timeSheet_defectDay_href").click(function(){
		var defId=$(this).attr("defId");
		var defName=$(this).attr("defName");
		loaddialogdata2(defId,defName,$(this).attr("weekday"));
	});
	//tablePagination();
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
				        '<td><a href="#" class="timeSheet_taskDay_href" weekday="1" taskName="'+task.taskName+'" taskId="'+task.taskId+'">'+task.mon+'</a></td>'+
				        '<td><a href="#" class="timeSheet_taskDay_href" weekday="2" taskName="'+task.taskName+'" taskId="'+task.taskId+'">'+task.tue+'</a></td>'+
				        '<td><a href="#" class="timeSheet_taskDay_href" weekday="3" taskName="'+task.taskName+'" taskId="'+task.taskId+'">'+task.wed+'</a></td>'+
				        '<td><a href="#" class="timeSheet_taskDay_href" weekday="4" taskName="'+task.taskName+'" taskId="'+task.taskId+'">'+task.thu+'</a></td>'+
				        '<td><a href="#" class="timeSheet_taskDay_href" weekday="5" taskName="'+task.taskName+'" taskId="'+task.taskId+'">'+task.fri+'</a></td>'+
				        '<td><a href="#" class="timeSheet_taskDay_href" weekday="6" taskName="'+task.taskName+'" taskId="'+task.taskId+'">'+task.sat+'</a></td>'+
				        '<td><a href="#" class="timeSheet_taskDay_href" weekday="7" taskName="'+task.taskName+'" taskId="'+task.taskId+'">'+task.sun+'</a></td>'+
				      '</tr>';
	});
	$.each(filtercrsList,function(index,crs){
		taletbody += '<tr class="table-crs">'+
				        '<td><a href="#" class="timeSheet_crs_href" crId="'+crs.crId+'">'+crs.crName+'</a></td>'+
				        '<td>'+crs.status+'</td>'+
				        '<td><a href="#" class="timeSheet_crsDay_href" weekday="1" crName="'+crs.crName+'" crId="'+crs.crId+'">'+crs.mon+'</a></td>'+
				        '<td><a href="#" class="timeSheet_crsDay_href" weekday="2" crName="'+crs.crName+'" crId="'+crs.crId+'">'+crs.tue+'</a></td>'+
				        '<td><a href="#" class="timeSheet_crsDay_href" weekday="3" crName="'+crs.crName+'" crId="'+crs.crId+'">'+crs.wed+'</a></td>'+
				        '<td><a href="#" class="timeSheet_crsDay_href" weekday="4" crName="'+crs.crName+'" crId="'+crs.crId+'">'+crs.thu+'</a></td>'+
				        '<td><a href="#" class="timeSheet_crsDay_href" weekday="5" crName="'+crs.crName+'" crId="'+crs.crId+'">'+crs.fri+'</a></td>'+
				        '<td><a href="#" class="timeSheet_crsDay_href" weekday="6" crName="'+crs.crName+'" crId="'+crs.crId+'">'+crs.sat+'</a></td>'+
				        '<td><a href="#" class="timeSheet_crsDay_href" weekday="7" crName="'+crs.crName+'" crId="'+crs.crId+'">'+crs.sun+'</a></td>'+
				      '</tr>';
	});
	$.each(filterdefectsList,function(index,defect){
		taletbody += '<tr class="table-defect">'+
				        '<td><a href="#" class="timeSheet_defect_href" defId="'+defect.defId+'">'+defect.defName+'</a></td>'+
				        '<td>'+defect.status+'</td>'+
				        '<td><a href="#" class="timeSheet_defectDay_href" weekday="1" defName="'+defect.defName+'" defId="'+defect.defId+'">'+defect.mon+'</a></td>'+
				        '<td><a href="#" class="timeSheet_defectDay_href" weekday="2" defName="'+defect.defName+'" defId="'+defect.defId+'">'+defect.tue+'</a></td>'+
				        '<td><a href="#" class="timeSheet_defectDay_href" weekday="3" defName="'+defect.defName+'" defId="'+defect.defId+'">'+defect.wed+'</a></td>'+
				        '<td><a href="#" class="timeSheet_defectDay_href" weekday="4" defName="'+defect.defName+'" defId="'+defect.defId+'">'+defect.thu+'</a></td>'+
				        '<td><a href="#" class="timeSheet_defectDay_href" weekday="5" defName="'+defect.defName+'" defId="'+defect.defId+'">'+defect.fri+'</a></td>'+
				        '<td><a href="#" class="timeSheet_defectDay_href" weekday="6" defName="'+defect.defName+'" defId="'+defect.defId+'">'+defect.sat+'</a></td>'+
				        '<td><a href="#" class="timeSheet_defectDay_href" weekday="7" defName="'+defect.defName+'" defId="'+defect.defId+'">'+defect.sun+'</a></td>'+
				      '</tr>';
	});
	$("#timeSheet_Task_Tale tbody").html(taletbody);
	eventHandlers();
	totalTimeCnt();
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
	activityLiStr += '<option value="">-- Select --</option>';
	$.each(activityList,function(index,act){
		activityLiStr += '<option value="'+act.activityId+'">'+act.activity+'</option>';
	})
	$("#timeSheet_EntryDilog_Activity").html(activityLiStr);
	
	var subactivityLiStr="";
	$("#timeSheet_EntryDilog_Subactivity").html("");
	subactivityLiStr += '<option value="">-- Select --</option>';
	$("#timeSheet_EntryDilog_Subactivity").html(subactivityLiStr);
	
/*	$("#timeSheet_EntryDilog_Subactivity").html("");
	$.each(subactivityList,function(index,subact){
		subactivityLiStr += '<option value="'+subact.subactivityId+'">'+subact.subactivity+'</option>';
	});
	
	$("#timeSheet_EntryDilog_Subactivity").html(subactivityLiStr);*/
	
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
			        '<td style="display:none">'+timeSheetSubActIndexed[key].activityId+'</td>'+
			        '<td>'+timeSheetSubActIndexed[key].subactivity+'</td>'+
			        '<td style="display:none">'+timeSheetSubActIndexed[key].subactivityId+'</td>';
		    		var i=0;
			        $.each(timeIndexed,function(index,timeobj){
			        	taletbody += '<td weekDay="'+(++i)+'">'+timeobj.time+'</td>';
			        })
			        
			        taletbody += '<td><button type="button" id="" class="btn btn-success timeSheet_EntryDilog_Edit" style="padding:1% 10% 1% 10%">Edit</button>'+
			        			 '<button type="button" id="" class="btn btn-info timeSheet_EntryDilog_Save" style="display:none;padding:1% 10% 1% 10%">Save</button></td></tr>';
			        $("#timeSheet_EntryDilog_Task_Tale tbody").append(taletbody);
		    	}  			
		    }
		}
	}
	eventHandlers2();
}

function validateTimes(time){
	if(time == ""){
		return true;
	}
	else if(time.length > 5){
		return false;
	}
	else{
		var hour=time.split(":")[0];
		var min=time.split(":")[1];
		console.log(time,hour.min,parseInt(hour), parseInt(min))
		if(isNaN(hour) || isNaN(min) || parseInt(hour) < 0 ||  parseInt(min) < 0 || parseInt(hour) > 23 ||  parseInt(min) > 59 || hour == "" || min == ""){
				return false;
		}
		else if(parseInt(hour) == 0 &&  parseInt(min) == 0){
			return false;
		}
		else{
			return true;
		}
	}
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
	
	if(activityId == "" || subactivityId == ""){
		pnotify("Validation Error","Please select the Activity and Sub activity","error");
	}
	else if(!validateTimes(monHour)){
		pnotify("Validation Error","Enter the valid timings for Monday","error");
	}
	else if(!validateTimes(tueHour)){
		pnotify("Validation Error","Enter the valid timings forTuesday","error");
	}
	else if(!validateTimes(wedHour)){
		pnotify("Validation Error","Enter the valid timings for Wednesday","error");
	}
	else if(!validateTimes(thuHour)){
		pnotify("Validation Error","Enter the valid timings for Thursday","error");
	}
	else if(!validateTimes(friHour)){
		pnotify("Validation Error","Enter the valid timings for Friday","error");
	}
	else if(!validateTimes(satHour)){
		pnotify("Validation Error","Enter the valid timings for Satday","error");
	}
	else if(!validateTimes(sunHour)){
		pnotify("Validation Error","Enter the valid timings for Sunday","error");
	}
	else if(monHour == "" && tueHour == "" && wedHour == "" && thuHour == "" && friHour == "" && satHour =="" && sunHour == ""){
		pnotify("Validation Error","Enter the valid timings for atleast one day","error");
	}
	else{
		var timeEntry=[ {date:mondayDate,time:monHour},{date:tueDate,time:tueHour},{date:wedDate,time:wedHour},
						{date:thuDate,time:thuHour},{date:friDate,time:friHour},{date:satDate,time:satHour},
						{date:sunDate,time:sunHour}];
		
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
		
		if(subActTimeIndexed[subactivityId]){
			var timeEntries=subActTimeIndexed[subactivityId];
			timeEntries[mondayDate]=timeEntry;
		}
		else{
			subActTimeIndexed[subactivityId]={};
			var timeEntries=subActTimeIndexed[subactivityId];
			timeEntries[mondayDate]=timeEntry;
		}
		
		pnotify("Save Sucess","Time sheet entry details saved sucessfully","success");
		loaddialogdata(taskId,$("#timeSheet_EntryDilog_Taskname").val());
	}
}

function editTimeSheetData($this){
	var tds=$this.parent().siblings();
	var i=0;
	$.each(tds,function(index,td){
		var $tddum=td;
		switch(i){
			case 4:
				$tddum.innerHTML='<input type="text" id="edit_timeSheet_monHour" class="timeSheet_Hour" value='+$tddum.innerHTML+'>';
				break;
			case 5:
				$tddum.innerHTML='<td><input type="text" id="edit_timeSheet_tueHour" class="timeSheet_Hour" value='+$tddum.innerHTML+'></td>';
				break;
			case 6:
				$tddum.innerHTML='<td><input type="text" id="edit_timeSheet_wedHour" class="timeSheet_Hour" value='+$tddum.innerHTML+'></td>';
				break;
			case 7:
				$tddum.innerHTML='<td><input type="text" id="edit_timeSheet_thuHour" class="timeSheet_Hour" value='+$tddum.innerHTML+'></td>';
				break;
			case 8:
				$tddum.innerHTML='<td><input type="text" id="edit_timeSheet_friHour" class="timeSheet_Hour" value='+$tddum.innerHTML+'></td>';
				break;
			case 9:
				$tddum.innerHTML='<td><input type="text" id="edit_timeSheet_satHour" class="timeSheet_Hour" value='+$tddum.innerHTML+'></td>';
				break;
			case 10:
				$tddum.innerHTML='<td><input type="text" id="edit_timeSheet_sunHour" class="timeSheet_Hour" value='+$tddum.innerHTML+'></td>';
				break;
		}i++;
	})

	$this.hide();
	$this.next().show();
};

function SaveEditedTimeSheetData($this){
	var tds=$this.parent().siblings();
	var activity="";
	var activityId=null;
	var subactivity="";
	var subactivityId=null;
	var mondayDate= $("#timeSheet_EntryDilog_Startdate").attr("curMonday");
	var i=0;
	monHour=$("#edit_timeSheet_monHour").val();
	tueHour=$("#edit_timeSheet_tueHour").val();
	wedHour=$("#edit_timeSheet_wedHour").val();
	thuHour=$("#edit_timeSheet_thuHour").val();
	friHour=$("#edit_timeSheet_friHour").val();
	satHour=$("#edit_timeSheet_satHour").val();
	sunHour=$("#edit_timeSheet_sunHour").val();
	
	if(!validateTimes(monHour)){
		pnotify("Validation Error","Enter the valid timings for Monday","error");
	}
	else if(!validateTimes(tueHour)){
		pnotify("Validation Error","Enter the valid timings forTuesday","error");
	}
	else if(!validateTimes(wedHour)){
		pnotify("Validation Error","Enter the valid timings for Wednesday","error");
	}
	else if(!validateTimes(thuHour)){
		pnotify("Validation Error","Enter the valid timings for Thursday","error");
	}
	else if(!validateTimes(friHour)){
		pnotify("Validation Error","Enter the valid timings for Friday","error");
	}
	else if(!validateTimes(satHour)){
		pnotify("Validation Error","Enter the valid timings for Satday","error");
	}
	else if(!validateTimes(sunHour)){
		pnotify("Validation Error","Enter the valid timings for Sunday","error");
	}
	else if(monHour == "" && tueHour == "" && wedHour == "" && thuHour == "" && friHour == "" && satHour =="" && sunHour == ""){
		pnotify("Validation Error","Enter the valid timings for atleast one day","error");
	}
	else{
		$.each(tds,function(index,td){
			var $tddum=td;
			switch(i){
				case 0:
					activity=$tddum.innerHTML;
					break;
				case 1:
					activityId=$tddum.innerHTML;
					break;
				case 2:
					subactivity=$tddum.innerHTML;
					break;
				case 3:
					subactivityId=$tddum.innerHTML;
					break;
				case 4:
					monHour=$("#edit_timeSheet_monHour").val();
					$tddum.innerHTML=monHour;
					break;
				case 5:
					tueHour=$("#edit_timeSheet_tueHour").val();
					currentDay1 = new Date($("#timeSheet_EntryDilog_Startdate").attr("curMonday"));
					tueDate = getModifiedDate(currentDay1.getCurentWeekDay(2))[1];
					$tddum.innerHTML=tueHour;
					break;
				case 6:
					wedHour=$("#edit_timeSheet_wedHour").val();
					var currentDay1 = new Date($("#timeSheet_EntryDilog_Startdate").attr("curMonday"));
					wedDate = getModifiedDate(currentDay1.getCurentWeekDay(3))[1];
					$tddum.innerHTML=wedHour;
					break;
				case 7:
					thuHour=$("#edit_timeSheet_thuHour").val();
					var currentDay1 = new Date($("#timeSheet_EntryDilog_Startdate").attr("curMonday"));
					thuDate = getModifiedDate(currentDay1.getCurentWeekDay(4))[1];
					$tddum.innerHTML=thuHour;
					break;
				case 8:
					friHour=$("#edit_timeSheet_friHour").val();
					var currentDay1 = new Date($("#timeSheet_EntryDilog_Startdate").attr("curMonday"));
					friDate = getModifiedDate(currentDay1.getCurentWeekDay(5))[1];
					$tddum.innerHTML=friHour;
					break;
				case 9:
					satHour=$("#edit_timeSheet_satHour").val();
					var currentDay1 = new Date($("#timeSheet_EntryDilog_Startdate").attr("curMonday"));
					satDate = getModifiedDate(currentDay1.getCurentWeekDay(6))[1];
					$tddum.innerHTML=satHour;
					break;
				case 10:
					sunHour=$("#edit_timeSheet_sunHour").val();
					var currentDay1 = new Date($("#timeSheet_EntryDilog_Startdate").attr("curMonday"));
					sunDate = getModifiedDate(currentDay1.getCurentWeekDay(7))[1];
					$tddum.innerHTML=sunHour;
					break;
			}i++;
		});
		var timeEntry=[ {date:mondayDate,time:monHour},{date:tueDate,time:tueHour},{date:wedDate,time:wedHour},
						{date:thuDate,time:thuHour},{date:friDate,time:friHour},{date:satDate,time:satHour},
						{date:sunDate,time:sunHour}];

		subActTimeIndexed[subactivityId][mondayDate]=timeEntry;
		pnotify("Edit Sucess","Time sheet entry details edited sucessfully","info");
		$this.hide();
		$this.prev().show();
	}
}

function pnotify(title,text,type){
	PNotify.prototype.options.styling = "bootstrap3";
	//type: "notice" - Type of the notice. "notice", "info", "success", or "error".
	$(function(){
	    new PNotify({
	        title: title,
	        text: text,
	        type:type
	    });
	});
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
	    $pager.insertAfter($table).find('span.page-number:first').addClass('active');
	});

}

//secod way implementation
function loaddialogdata2(taskId,taskName,weekday){
	$("#timeSheet_EntryDilog2_Taskname").val(taskName);
	$("#timeSheet_EntryDilog2_Taskname").attr("taskId",taskId);
	
	var currentDay1 = new Date($("#timeSheet_Startdate").attr("curmonday"));
	var sunDate = getModifiedDate(currentDay1.getCurentWeekDay(parseInt(weekday)));
	$("#timeSheet_EntryDilog2_Date").val(sunDate[0]);
	$("#timeSheet_EntryDilog2_Date").attr("date",sunDate[1]);
	var activityLiStr="";
	$("#timeSheet_EntryDilog2_Activity").html("");
	activityLiStr += '<option value="">-- Select --</option>';
	$.each(activityList,function(index,act){
		activityLiStr += '<option value="'+act.activityId+'">'+act.activity+'</option>';
	});
	
	var subactivityLiStr="";
	$("#timeSheet_EntryDilog2_Activity").html(activityLiStr);
	$("#timeSheet_EntryDilog2_Subactivity").html("");
	subactivityLiStr += '<option value="">-- Select --</option>';
	$("#timeSheet_EntryDilog2_Subactivity").html(subactivityLiStr);
/*	var subactivityLiStr="";
	$("#timeSheet_EntryDilog2_Subactivity").html("");
	$.each(subactivityList,function(index,subact){
		subactivityLiStr += '<option value="'+subact.subactivityId+'">'+subact.subactivity+'</option>';
	});
	$("#timeSheet_EntryDilog2_Subactivity").html(subactivityLiStr);*/
	loadDialogTableData2(taskId,sunDate[1]);
	clearDialog2();
	$("#timeSheet_EntryDilog2_Div").dialog("open");	
}

function clearDialog2(){
	$("#timeSheet_EntryDilog2_THour").val("")
	$("#timeSheet_EntryDilog2_TMin").val("");
	$("#timeSheet_EntryDilog2_TaskDscrptn").val("");
}

function loadDialogTableData2(taskId,date){
	$("#timeSheet_EntryDilog2_Task_Tale tbody").html("");
	if(subActIndexedFor2[taskId]){
		var timeSheetSubActIndexed=subActIndexedFor2[taskId];
		for (var key in timeSheetSubActIndexed) {
		    if (timeSheetSubActIndexed.hasOwnProperty(key)) {
		    	if(timeSheetSubActIndexed[key].date == date){
		    		taletbody = '<tr class="table-task">'+
						        '<td>'+timeSheetSubActIndexed[key].activity+'</td>'+
						        '<td style="display:none">'+timeSheetSubActIndexed[key].activityId+'</td>'+
						        '<td>'+timeSheetSubActIndexed[key].subActivity+'</td>'+
						        '<td style="display:none">'+timeSheetSubActIndexed[key].subActivityId+'</td>'+
						        '<td style="display:none">'+timeSheetSubActIndexed[key].date+'</td>'+
						        '<td>'+timeSheetSubActIndexed[key].time+'</td>'+
						        '<td>'+timeSheetSubActIndexed[key].details+'</td>'+
						        '<td><button type="button" id="" class="btn btn-success timeSheet_EntryDilog2_Edit" style="padding:1% 10% 1% 10%">Edit</button>'+
			        			 '<button type="button" id="" class="btn btn-info timeSheet_EntryDilog2_Save" style="display:none;padding:1% 10% 1% 10%">Save</button></td></tr>';
			        $("#timeSheet_EntryDilog2_Task_Tale tbody").append(taletbody);
		    	}  			
		    }
		}
	}
	dilg2eventHandlers2();
}

function saveTimeSheetEntry2(){
	var hour=$("#timeSheet_EntryDilog2_THour").val();
	var min=$("#timeSheet_EntryDilog2_TMin").val();
	var activityId=$("#timeSheet_EntryDilog2_Activity option:selected").val();
	var subActivityId=$("#timeSheet_EntryDilog2_Subactivity option:selected").val();
	
	if(activityId == "" || subActivityId == ""){
		pnotify("Validation Error","Please select the Activity and Sub activity","error");
	}
	else if(isNaN(hour) || isNaN(min) || parseInt(hour) < 0 ||  parseInt(min) < 0 || parseInt(hour) > 23 ||  parseInt(min) > 59 || hour == "" || min == ""){
		pnotify("Validation Error","Enter the valid timings","error");
	}
	else if(parseInt(hour) == 0 &&  parseInt(min) == 0){
		pnotify("Validation Error","Enter the valid timings","error");
	}
	else{
		var subActivity=$("#timeSheet_EntryDilog2_Subactivity option:selected").text();
		
		var activity=$("#timeSheet_EntryDilog2_Activity option:selected").text();
		
		var details=$("#timeSheet_EntryDilog2_TaskDscrptn").val();
		var date=$("#timeSheet_EntryDilog2_Date").attr("date");
		if(parseInt(hour) < 10){
			if(hour.length==1)
			hour="0"+hour;
		}
		if(parseInt(min) < 10){
			if(hour.length==1)
			min="0"+min;
		}
		var taskId=$("#timeSheet_EntryDilog2_Taskname").attr("taskId");
		var timeSheetEntry={taskId:taskId,activity:activity,activityId:activityId,subActivity:subActivity,
							subActivityId:subActivityId,details:details,date:date,time:hour+":"+min}
		if(subActIndexedFor2[taskId]){
			subActIndexedFor2[taskId][subActivityId+"_"+date]=timeSheetEntry;
		}
		else{
			subActIndexedFor2[taskId]={};
			subActIndexedFor2[taskId][subActivityId+"_"+date]=timeSheetEntry;
		}
		loadDialogTableData2(taskId,date);
		clearDialog2();
		pnotify("Save Sucess","Time sheet entry details saved sucessfully","success");
		//$("#timeSheet_EntryDilog2_Div").dialog("close");	
	}
}

function editTimeSheetData2($this){
	var tds=$this.parent().siblings();
	var i=0;
	$.each(tds,function(index,td){
		var $tddum=td;
		switch(i){
			case 5:
				var time=$tddum.innerHTML;
				var hour=time.split(":")[0];
				var min=time.split(":")[1];
				var htmlStr='<input type="text"  id="timeSheet_EntryDilog2_ETHour" value='+hour+' class="timeSheet_Text" style="width:30%;">Hr'+
				'<input type="text"  id="timeSheet_EntryDilog2_ETMin" value='+min+' class="timeSheet_Text" style="width: 30%;">Min';
				$tddum.innerHTML=htmlStr;
				break;
			case 6:
				var details=$tddum.innerHTML;
				$tddum.innerHTML='<textarea rows="2" cols=""  value='+$tddum.innerHTML+' id="timeSheet_EntryDilog2_ETaskDscrptn"></textarea>';
				$("#timeSheet_EntryDilog2_ETaskDscrptn").val(details)
				break;
		}i++;
	})

	$this.hide();
	$this.next().show();
}

function SaveEditedTimeSheetData2($this){
	var tds=$this.parent().siblings();
	var activity="";
	var activityId=null;
	var subActivity="";
	var subActivityId=null;
	var date="";
	var taskId=$("#timeSheet_EntryDilog2_Taskname").attr("taskId");
	var i=0;
	var hour=$("#timeSheet_EntryDilog2_ETHour").val();
	var min=$("#timeSheet_EntryDilog2_ETMin").val();
	if(isNaN(hour) || isNaN(min) || parseInt(hour) < 0 ||  parseInt(min) < 0 || parseInt(hour) > 23 ||  parseInt(min) > 59 || hour == "" || min == ""){
		pnotify("Validation Error","Enter the valid timings","error")
	}
	else{
		var details=$("#timeSheet_EntryDilog2_ETaskDscrptn").val();
		$.each(tds,function(index,td){
			var $tddum=td;
			switch(i){
				case 0:
					activity=$tddum.innerHTML;
					break;
				case 1:
					activityId=$tddum.innerHTML;
					break;
				case 2:
					subActivity=$tddum.innerHTML;
					break;
				case 3:
					subActivityId=$tddum.innerHTML;
					break;
				case 4:
					date=$tddum.innerHTML;
					break;
				case 5:
					$tddum.innerHTML=hour+":"+min;
					break;
				case 6:
					$tddum.innerHTML=details;
					break;
			}i++;
		});
		
		var timeSheetEntry={taskId:taskId,activity:activity,activityId:activityId,subActivity:subActivity,
				subActivityId:subActivityId,details:details,date:date,time:hour+":"+min};
		subActIndexedFor2[taskId][subActivityId+"_"+date]=timeSheetEntry;
		pnotify("Edit Sucess","Time sheet entry details edited sucessfully","info");
		$this.hide();
		$this.prev().show();
	}
}

function loadSubActivities2(activityId){
	var subactivityLiStr="";
	if(subActivities[activityId]){
		var subactivityList=subActivities[activityId];
		$("#timeSheet_EntryDilog2_Subactivity").html("");
		$.each(subactivityList,function(index,subact){
			subactivityLiStr += '<option value="'+subact.subactivityId+'">'+subact.subactivity+'</option>';
		});
		$("#timeSheet_EntryDilog2_Subactivity").html(subactivityLiStr);
	}
	else{
		$("#timeSheet_EntryDilog2_Subactivity").html("");
		subactivityLiStr += '<option value="">-- Select --</option>';
		$("#timeSheet_EntryDilog2_Subactivity").html(subactivityLiStr);
	}
}

function loadSubActivities1(activityId){
	var subactivityLiStr="";
	if(subActivities[activityId]){
		var subactivityList=subActivities[activityId];
		$("#timeSheet_EntryDilog_Subactivity").html("");
		$.each(subactivityList,function(index,subact){
			subactivityLiStr += '<option value="'+subact.subactivityId+'">'+subact.subactivity+'</option>';
		});
		$("#timeSheet_EntryDilog_Subactivity").html(subactivityLiStr);
	}
	else{
		$("#timeSheet_EntryDilog_Subactivity").html("");
		subactivityLiStr += '<option value="">-- Select --</option>';
		$("#timeSheet_EntryDilog_Subactivity").html(subactivityLiStr);
	}
	
	
}

function totalTimeCnt(){
    	var taskTimes=$("#timeSheet_Task_Tale tbody tr");

		var monCnt=0;
		var tueCnt=0;
		var wedCnt=0;
		var thurCnt=0;
		var friCnt=0;
		var satCnt=0;
		var sunCnt=0;
		$.each(taskTimes,function(index,$task1){
			var mon= $(this).find("td:nth-child(3)").text();
			var tue= $(this).find("td:nth-child(4)").text();
			var wed= $(this).find("td:nth-child(5)").text();
			var thur= $(this).find("td:nth-child(6)").text();
			var fri= $(this).find("td:nth-child(7)").text();
			var sat= $(this).find("td:nth-child(8)").text();
			var sun= $(this).find("td:nth-child(9)").text();
			
			var hours=0;
			var minutes=0;
			
			hours=mon.split(":")[0];
			minutes=mon.split(":")[1];
			if(hours){monCnt+=parseInt(hours)*60}
			if(minutes){monCnt+=parseInt(minutes)}
			
			hours=tue.split(":")[0];
			minutes=tue.split(":")[1];
			if(hours){tueCnt+=parseInt(hours)*60}
			if(minutes){tueCnt+=parseInt(minutes)}
			
			hours=wed.split(":")[0];
			minutes=wed.split(":")[1];
			if(hours){wedCnt+=parseInt(hours)*60}
			if(minutes){wedCnt+=parseInt(minutes)}
			
			hours=thur.split(":")[0];
			minutes=thur.split(":")[1];
			if(hours){thurCnt+=parseInt(hours)*60}
			if(minutes){thurCnt+=parseInt(minutes)}
			
			hours=fri.split(":")[0];
			minutes=fri.split(":")[1];
			if(hours){friCnt+=parseInt(hours)*60}
			if(minutes){friCnt+=parseInt(minutes)}
			
			hours=sat.split(":")[0];
			minutes=sat.split(":")[1];
			if(hours){satCnt+=parseInt(hours)*60}
			if(minutes){satCnt+=parseInt(minutes)}
			
			hours=sun.split(":")[0];
			minutes=sun.split(":")[1];
			if(hours){sunCnt+=parseInt(hours)*60}
			if(minutes){sunCnt+=parseInt(minutes)}
		});
		
		var hours=0;
		var minutes=0;
		
		hours=monCnt/60;
		minutes=monCnt%60;
		hours=Math.floor(hours)
		if(hours < 10)
			hours="0"+hours;
		if(minutes < 10)
			minutes="0"+minutes;
		var monCntSTr=hours+":"+minutes;

		hours=tueCnt/60;
		minutes=tueCnt%60;
		hours=Math.floor(hours)
		if(hours < 10)
			hours="0"+hours;
		if(minutes < 10)
			minutes="0"+minutes;
		var tueCntSTr=hours+":"+minutes;
		
		hours=wedCnt/60;
		minutes=wedCnt%60;
		hours=Math.floor(hours)
		if(hours < 10)
			hours="0"+hours;
		if(minutes < 10)
			minutes="0"+minutes;
		var wedCntSTr=hours+":"+minutes;
		
		hours=thurCnt/60;
		minutes=thurCnt%60;
		hours=Math.floor(hours)
		if(hours < 10)
			hours="0"+hours;
		if(minutes < 10)
			minutes="0"+minutes;
		var thurCntSTr=hours+":"+minutes;
		
		hours=friCnt/60;
		minutes=friCnt%60;
		hours=Math.floor(hours)
		if(hours < 10)
			hours="0"+hours;
		if(minutes < 10)
			minutes="0"+minutes;
		var friCntSTr=hours+":"+minutes;
		
		hours=satCnt/60;
		minutes=satCnt%60;
		hours=Math.floor(hours)
		if(hours < 10)
			hours="0"+hours;
		if(minutes < 10)
			minutes="0"+minutes;
		var satCntSTr=hours+":"+minutes;
		
		hours=sunCnt/60;
		minutes=sunCnt%60;
		hours=Math.floor(hours)
		if(hours < 10)
			hours="0"+hours;
		if(minutes < 10)
			minutes="0"+minutes;
		var sunCntSTr=hours+":"+minutes;
		
		console.log(monCntSTr,tueCntSTr,wedCntSTr,thurCntSTr,friCntSTr,satCntSTr,sunCntSTr)
		
		$("#timeSheet_Task_Tale tfoot tr:first").find("th:nth-child(3)").text(monCntSTr);
		$("#timeSheet_Task_Tale tfoot tr:first").find("th:nth-child(4)").text(tueCntSTr);
		$("#timeSheet_Task_Tale tfoot tr:first").find("th:nth-child(5)").text(wedCntSTr);
		$("#timeSheet_Task_Tale tfoot tr:first").find("th:nth-child(6)").text(thurCntSTr);
		$("#timeSheet_Task_Tale tfoot tr:first").find("th:nth-child(7)").text(friCntSTr);
		$("#timeSheet_Task_Tale tfoot tr:first").find("th:nth-child(8)").text(satCntSTr);
		$("#timeSheet_Task_Tale tfoot tr:first").find("th:nth-child(9)").text(sunCntSTr);

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
					{defId:"08",defName:"def_08",status:"open",defcreatedDate:"2017/04/11",mon:"00:30",tue:"01:00",wed:"02:00",thu:"08:00",fri:"10:40",sat:"05:00",sun:"05:00"},
					{defId:"10",defName:"def_10",status:"open",defcreatedDate:"2017/02/28",mon:"00:30",tue:"01:00",wed:"02:00",thu:"08:00",fri:"10:40",sat:"05:00",sun:"05:00"},
					{defId:"11",defName:"def_11",status:"open",defcreatedDate:"2017/03/15",mon:"00:30",tue:"01:00",wed:"02:00",thu:"08:00",fri:"10:40",sat:"05:00",sun:"05:00"},
					{defId:"12",defName:"def_12",status:"open",defcreatedDate:"2017/03/19",mon:"00:30",tue:"01:00",wed:"02:00",thu:"08:00",fri:"10:40",sat:"05:00",sun:"05:00"}];


var activityList=[	{activityId:"01",activity:"Act_01"},{activityId:"02",activity:"Act_02"},
					{activityId:"03",activity:"Act_03"},{activityId:"04",activity:"Act_04"}];

var subactivityList=[	{subactivityId:"01",subactivity:"subAct_01"},{subactivityId:"02",subactivity:"subAct_02"},
					{subactivityId:"03",subactivity:"subAct_03"},{subactivityId:"04",subactivity:"subAct_04"}];

var subActivities={	"01":[{subactivityId:"01",subactivity:"subAct_01"},{subactivityId:"02",subactivity:"subAct_02"}],
					"02":[{subactivityId:"03",subactivity:"subAct_03"},{subactivityId:"04",subactivity:"subAct_04"},
					      {subactivityId:"05",subactivity:"subAct_05"},{subactivityId:"06",subactivity:"subAct_06"}],
			      	"03":[{subactivityId:"07",subactivity:"subAct_07"},{subactivityId:"08",subactivity:"subAct_08"}],
					"04":[{subactivityId:"09",subactivity:"subAct_09"},{subactivityId:"10",subactivity:"subAct_10"},{subactivityId:"11",subactivity:"subAct_11"}]};
var timeSheetEntryIndexed={};

var subActTimeIndexed={};

var subActIndexedFor2={}












