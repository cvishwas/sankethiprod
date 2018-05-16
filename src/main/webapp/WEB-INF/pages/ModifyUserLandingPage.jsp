<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

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
	<div class="easyui-panel" style="padding: 5px; width: 500px;">
		<a href="#" class="easyui-menubutton" data-options="menu:'#mm1'">User</a>
		<a href="#" class="easyui-menubutton" data-options="menu:'#mm2'">Delete</a>
		<a href="#" class="easyui-menubutton" data-options="menu:'#mm3'">File
			Imaging</a>
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
	<c:choose>
		<c:when test="${modify == 'roles'}">
			<form id="ff1">
				<div id="modRolesPnl" class="easyui-panel" title="Modify User Roles"
					style="width: 100%; max-width: 400px; padding: 30px 60px;"
					style="display:none;">
					<div style="margin-bottom: 20px">
						<input class="easyui-searchbox"
							data-options="prompt:'Please Input User Id',searcher:doSearchRoles"
							style="width: 100%">
					</div>
					<div style="margin-bottom: 20px">
						<input class="easyui-textbox" id="roles1" name="roles1"
							style="width: 100%; height: 60px"
							data-options="label:'Roles:',multiline:true">
					</div>
					<input type="hidden" id="UserId1" name="UserId1" />
					<div style="text-align: center; padding: 5px 0">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							onclick="submitUpdateRoleForm()" style="width: 80px">Submit</a> <a
							href="javascript:void(0)" class="easyui-linkbutton"
							onclick="clearUserForm('#ff1')" style="width: 80px">Clear</a>
					</div>
				</div>
			</form>
		</c:when>
	</c:choose>
	<c:choose>
		<c:when test="${modify == 'enabledisable'}">
			<form id="ff2">
				<div id="edPnl" class="easyui-panel" title="Enable/Disable Users"
					style="width: 100%; max-width: 400px; padding: 30px 60px;"
					style="display:none;">
					<div style="margin-bottom: 20px">
						<input class="easyui-searchbox"
							data-options="prompt:'Please Input User Id',searcher:doSearchUserStatus"
							style="width: 100%">
					</div>
					<div style="margin-bottom: 20px">
						<input class="easyui-textbox" id="enabled2" name="enabled2"
							style="width: 100%;"
							data-options="label:'Enabled(Y/N):',required:true">
					</div>
					<input type="hidden" id="UserId2" name="UserId2" />
					<div style="text-align: center; padding: 5px 0">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							onclick="submitUpdateEnabledDisabledForm()" style="width: 80px">Submit</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							onclick="clearUserForm('#ff2')" style="width: 80px">Clear</a>
					</div>
				</div>
			</form>
		</c:when>
	</c:choose>
	<c:choose>
	<c:when test="${modify == 'vault'}">
		<form id="ff3">
			<div id="vaultPnl" class="easyui-panel" title="Change vault for User"
				style="width: 100%; max-width: 400px; padding: 30px 60px;"
				style="display:none;">
				<div style="margin-bottom: 20px">
					<input class="easyui-searchbox"
						data-options="prompt:'Please Input User Id',searcher:doSearchUserVault"
						style="width: 100%">
				</div>
				<div style="margin-bottom: 20px">
					<input class="easyui-textbox" id="vltId3" name="vltId3"
						style="width: 100%;"
						data-options="label:'Vault Id:',required:true">
				</div>
				<input type="hidden" id="UserId3" name="UserId3" />
				<div style="text-align: center; padding: 5px 0">
					<a href="javascript:void(0)" class="easyui-linkbutton"
						onclick="submitUpdateUserVaultForm()" style="width: 80px">Submit</a>
					<a href="javascript:void(0)" class="easyui-linkbutton"
						onclick="clearUserForm('#ff3')" style="width: 80px">Clear</a>
				</div>
			</div>
		</form>
	</c:when>
	</c:choose>

</body>
</html>