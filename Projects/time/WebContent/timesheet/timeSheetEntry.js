$("document").ready(function(){
	eventHandlers();
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
        next.setDate( this.getDate() - this.getDay()+ d );
        return next;
    }
}

function eventHandlers(){
	$("#timeSheet_prevWeek").click(function(){
		loadPrevWeekData()
	})

	$("#timeSheet_nextWeek").click(function(){
		loadNextWeekData()
	})
}

function loadPrevWeekData(){
	
}

function loadNextWeekData(){
	
}

function loadcurrentWeekData(){
	var currentDay = new Date();
	var curMonday = currentDay.getCurentWeekDay(1);
	$("#timeSheet_Startdate").val(curMonday);
	var curSunday = currentDay.getCurentWeekDay(7);
	$("#timeSheet_Enddate").val(curSunday);
}

var firstDay = new Date("2017/04/03");
var nextWeek = new Date(firstDay.getTime() + 7 * 24 * 60 * 60 * 1000);
console.log(nextWeek)