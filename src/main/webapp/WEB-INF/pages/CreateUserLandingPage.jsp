<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Smartdocs-User-Support</title>
<spring:url value="/resources/css/easyui.css" var="easyuiCss" />
<spring:url value="/resources/css/icon.css" var="iconCss" />
<spring:url value="/resources/js/jquery.min.js" var="jqueryMinJs" />
<spring:url value="/resources/js/jquery.easyui.min.js"
	var="jqueryEasyUiJs" />
<spring:url value="/resources/js/sdsupport.js" var="sdSupportJs" />

<link rel="stylesheet" type="text/css" href="${easyuiCss}">
<link rel="stylesheet" type="text/css" href="${iconCss}">
<script src="${jqueryMinJs}"></script>
<script src="${jqueryEasyUiJs}"></script>
<script src="${sdSupportJs}"></script>

</head>
<body>
	<div class="easyui-panel" style="padding: 5px;width: 500px;">
		<a href="#" class="easyui-menubutton" data-options="menu:'#mm1'">User</a>
		<a href="#" class="easyui-menubutton" data-options="menu:'#mm2'">Delete</a>
		<a href="#" class="easyui-menubutton" data-options="menu:'#mm3'">File Imaging</a>
	</div>
	<div id="mm1" style="width: 150px;">
		<!-- <div onclick="loadUser();">Create User</div> -->
		<div onclick="loadCreateUser();">Create User</div>
		<div>
			<span>Modify User</span>
			<div>
				<div onclick="loadModifyRoles();">Modify Roles</div>
				<div onclick="loadEnableDisableUsr();">Enable/Disable Users</div>
				<div onclick="loadVault();">Modify Vault</div>
			</div>
		</div>
	</div>
	<div id="mm2" style="width: 200px;">
		<div>Delete Shipment Codes</div>
		<div>Delete Container Codes</div>
		<div>Delete Barcodes</div>
		<div>Delete Document Barcodes</div>
	</div>
	<div id="mm3" style="width: 200px;">
		<div onclick="loadImagingStatusByShipment();">Imaging Status - Shipment</div>
		<div onclick="loadImagingStatusByDuration();">Imaging Status - Duration</div>
		<div onclick="loadImagingStatusByDocument();">Imaging Status - Document</div>
	</div>
	<br></br>
	<br></br>
	<form id="ff" method="post">
		<div id="usrPnl" class="easyui-panel" title="Create User"
			style="width: 100%; max-width: 400px; padding: 30px 60px;"
			style="display:none;">
			<div style="margin-bottom: 20px">
				<input class="easyui-textbox" id="UserId" name="UserId"
					style="width: 100%" data-options="label:'User Id',required:true">
			</div>
			<div style="margin-bottom: 20px">
				<input class="easyui-textbox" id="fname" name="fname"
					style="width: 100%"
					data-options="label:'First Name:',required:true">
			</div>
			<div style="margin-bottom: 20px">
				<input class="easyui-textbox" id="lname" name="lname"
					style="width: 100%" data-options="label:'Last Name:',required:true">
			</div>
			<div style="margin-bottom: 20px">
				<input class="easyui-textbox" id="accType" name="accType"
					style="width: 100%"
					data-options="label:'Access Type:',required:true">
			</div>
			<div style="margin-bottom: 20px">
				<input class="easyui-textbox" id="clientId" name="clientId"
					style="width: 100%" data-options="label:'Client Id:',required:true">
			</div>
			<div style="margin-bottom: 20px">
				<input class="easyui-textbox" id="clientName" name="clientName"
					style="width: 100%"
					data-options="label:'Client Name:',required:true">
			</div>
			<div style="margin-bottom: 20px">
				<input class="easyui-textbox" id="region" name="region"
					style="width: 100%;" data-options="label:'Region:',required:true">
			</div>
			<div style="margin-bottom: 20px">
				<input class="easyui-textbox" id="wrhsCd" name="wrhsCd"
					style="width: 100%;" data-options="label:'WRHS CD:',required:true">
			</div>
			<div style="margin-bottom: 20px">
				<input class="easyui-textbox" id="vltId" name="vltId"
					style="width: 100%;" data-options="label:'Vault Id:',required:true">
			</div>
			<div style="margin-bottom: 20px">
				<input class="easyui-textbox" id="roles" name="roles"
					style="width: 100%; height: 60px"
					data-options="label:'Roles:',multiline:true">
			</div>
			<div style="margin-bottom: 20px">
			<input class="easyui-textbox" id="enabled" name="enabled"
					style="width: 100%;"
					data-options="label:'Enabled(Y/N):',required:true">					
			</div>
			<div style="text-align: center; padding: 5px 0">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="submitUserForm()" style="width: 80px">Submit</a> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					onclick="clearUserForm('#ff')" style="width: 80px">Clear</a>
			</div>
		</div>
	</form>

</body>
</html>