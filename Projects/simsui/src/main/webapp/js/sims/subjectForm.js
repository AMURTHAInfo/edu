$(document).ready(function() {
	subjectList();
});

$(window).resize(resizeTableWidth("subListGridDiv","subjectList_table"));

function subjectList() {
	console.info("subjectList");
	var sampleData = '[{"subject":"Logic Design"},{"subject":"Network Analysis"},{"subject":"Field Theory"},{"subject":"VLSI"},{"subject":"Communication Networks"}]';

	$.jgrid.gridUnload("#subjectList_table");
	$("#subjectList_table").jqGrid({
		url : '',
		mtype : "GET",
		datastr : sampleData,
		datatype : "jsonstring",
		colNames : [ "Subject", "SubjectCode", "Detail", "Link" ],
		colModel : [ {
			name : 'subject',
			index : 'subject',
			key : true,
			width : 155
		}, {
			name : 'subjectCode',
			index : 'subjectCode',
			width : 155
		}, {
			name : 'detail',
			index : 'detail',
			width : 155
		}, {
			name : 'link',
			index : 'link',
			width : 155
		} ],
		viewrecords : true,
		shrinkToFit : true,
		forceFit:true,
		width : $("#content-2").width(),
		height : 'auto',
		rowNum : 10,
		pager : "#subjectListPager"
	});
	resizeTableWidth("subListGridDiv","subjectList_table")
};

// handles the grid resize on window resize
function resizeTableWidth(divId,tableId){
	var mainWidth = $("#content-2").width();
	var totalColWidth = 0;
	$("#"+divId).find("th").each(function() {
		totalColWidth += $(this).width();
	});
	if(mainWidth > totalColWidth){
		$("#"+tableId).jqGrid('setGridWidth',parseInt(mainWidth));
		$("#"+tableId).jqGrid('setGridParam',{shrinkToFit:true});
		$("#"+tableId).trigger("reloadGrid");
		console.info(divId,tableId);
	}else{
		$("#"+tableId).jqGrid('setGridWidth',parseInt(mainWidth));
		$("#"+tableId).jqGrid('setGridParam',{shrinkToFit:true});
		$("#"+tableId).trigger("reloadGrid");
		console.info("divId",divId,tableId);
	}
};
