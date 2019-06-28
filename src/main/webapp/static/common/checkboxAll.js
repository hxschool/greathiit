function checkAll(e, itemName) {
	var repairId = document.getElementsByName(itemName); // 获取全选复选框
	for (var i = 0; i < repairId.length; i++) {
		repairId[i].checked = e.checked; // 改变所有复选框的状态为全选复选框的状态
	}
}
function selectSingle() {
	var k = 0;
	var oInput = document.getElementsByName("ids");
	for (var i = 0; i < oInput.length; i++) {
		if (oInput[i].checked == false) {
			k = 1;
			break;
		}
	}
	if (k == 0) {
		document.getElementById("checkId").checked = true;

	} else {
		document.getElementById("checkId").checked = false;
	}
}

function checkdel() {
	var num = $("input[type='checkbox']:checked").length;
	if (num == 0) {
		confirmx("请选择你要删除的数据");
	} else {
		confirmx('确定要删除已选中的信息吗？', repairDel);
	}

}
