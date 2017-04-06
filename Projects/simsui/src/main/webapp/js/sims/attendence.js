/*$(document).ready(function () {
	alert("here we go");
            $("#atten_attendence_table").jqGrid({
                url: 'http://trirand.com/blog/phpjqgrid/examples/jsonp/getjsonp.php?callback=?&qwery=longorders',
                mtype: "GET",
                datatype: "jsonp",
                colModel: [
                    { label: 'OrderID', name: 'OrderID', key: true, width: 75 },
                    { label: 'Customer ID', name: 'CustomerID', width: 150 },
                    { label: 'Order Date', name: 'OrderDate', width: 150 },
                    { label: 'Freight', name: 'Freight', width: 150 },
                    { label:'Ship Name', name: 'ShipName', width: 150 }
                ],
				viewrecords: true,
                width: 780,
                height: 250,
                rowNum: 20,
                pager: "#atten_attendfooter_div"
            });
        });*/
$(document).ready(function() {
	$("#attendanceYear").multiselect({
		multiple: false,
		minWidth:150,
		header: "Choose Year",
		noneSelectedText: "Choose Year",
		selectedList: 1
	});
	attendanceRecord();
});

function attendanceRecord(){
	console.info("attendance");
	//$("#atten_attendence_table").jqGrid("GridUnload");
	var sampleData = '[{"month":"January"},{"month":"Febrauary"},{"month":"March"},{"month":"April"},{"month":"May"},{"month":"June"},{"month":"July"},{"month":"August"},{"month":"September"},{"month":"October"},{"month":"November"},{"month":"December"}]';
	
	$.jgrid.gridUnload("#atten_attendence_table");
	$("#atten_attendence_table").jqGrid({
        url: '',
        mtype: "GET",
        datastr:sampleData,
        datatype: "jsonstring",
        colNames:["Month","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","No. of Present","No. of Absent"],
        colModel: [
            { name: 'month', index: 'month',key: true,width:70},
            { name: 'one', index: 'one',width:25},
            { name: 'two', index: 'two',width:25},
            { name: 'three', index: 'three',width:25},
            { name: 'four', index: 'four',width:25},
            { name: 'five', index: 'five',width:25},
            { name: 'six', index: 'six',width:25},
            { name: 'seven', index: 'seven',width:25},
            { name: 'eight', index: 'eight',width:25},
            { name: 'nine', index: 'nine',width:25},
            { name: 'ten', index: 'ten',width:25},
            { name: 'eleven', index: 'eleven',width:25},
            { name: 'twelve', index: 'twelve',width:25},
            { name: 'thirteen', index: 'thirteen',width:25},
            { name: 'fourteen', index: 'fourteen',width:25},
            { name: 'fifteen', index: 'fifteen',width:25},
            { name: 'sixteen', index: 'sixteen',width:25},
            { name: 'seventeen', index: 'seventeen',width:25},
            { name: 'eighteen', index: 'eighteen',width:25},
            { name: 'ninteen', index: 'ninteen',width:25},
            { name: 'twenty', index: 'twenty',width:25},
            { name: 'twentyone', index: 'twentyone',width:25},
            { name: 'twentytwo', index: 'twentytwo',width:25},
            { name: 'twentythree', index: 'twentythree',width:25},
            { name: 'twentyfour', index: 'twentyfour',width:25},
            { name: 'twentyfive', index: 'twentyfive',width:25},
            { name: 'twentysix', index: 'twentysix',width:25},
            { name: 'twentyseven', index: 'twentyseven',width:25},
            { name: 'twentyeight', index: 'twentyeight',width:25},
            { name: 'twentynine', index: 'twentynine',width:25},
            { name: 'thirty', index: 'thirty',width:25},
            { name: 'thirtyone', index: 'thirtyone',width:25},
            { name: 'noofpresent', index: 'noofpresent',width:50},
            { name: 'noofabsent', index: 'noofabsent',width:50}
        ],
		viewrecords: true,
        width: 945,
        height: 'auto',
    });
};
