<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<script type="text/javascript">
$(document).ready(function() {
			    $('#greathiit_area_edu').cxSelect({ 
					  url: '${ctx}/sys/office/treeClassLink',
					  selects: ['department', 'specialty','grade',"clazz"], 
					  jsonName: 'name',
					  jsonValue: 'value',
					  jsonSub: 'sub'
					});
})
			    </script>
<div id="greathiit_area_edu">

	<div class="control-group">
		<label class="control-label">所属学院:</label>
		<div class="controls">
			<select class="department input-large" name="department"><option>请选择</option></select>
		</div>
	</div>

	<div class="control-group">
		<label class="control-label">所属专业:</label>
		<div class="controls">
			<select id="specialty" class="specialty input-large" name="specialty"><option>请选择</option></select>
		</div>
	</div>

	<div class="control-group">
		<label class="control-label">年级:</label>
		<div class="controls">
			<select id="grade" class="grade input-large"><option>请选择</option></select>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">选择班级:</label>
		<div class="controls">
			<select id="clazz" class="clazz input-large" name="clazz"><option>请选择</option></select>
		</div>
	</div>

</div>