$(document).ready(function() {
	var types = document.getElementById("adduser:userSelect").value;
	if (types == "General Evaluator") {
		$(".papers").removeClass('hidden');
		$(".topics").addClass('hidden');
		$(".show").removeClass('hidden');
	} else if (types == "General Admin") {
		$(".papers").addClass('hidden');
		$(".topics").removeClass('hidden');
		$(".show").removeClass('hidden');
	} else {
		$(".papers").addClass('hidden');
		$(".topics").addClass('hidden');
		$(".show").addClass('hidden');
	}
});
function filterLegend() {
	var types = document.getElementById("adduser:userSelect").value;

	if (types == "General Evaluator") {
		$(".papers").removeClass('hidden');
		$(".topics").addClass('hidden');
		$(".show").removeClass('hidden');
	} else if (types == "General Admin") {
		$(".papers").addClass('hidden');
		$(".topics").removeClass('hidden');
		$(".show").removeClass('hidden');
	} else {
		$(".papers").addClass('hidden');
		$(".topics").addClass('hidden');
		$(".show").addClass('hidden');
	}
}
