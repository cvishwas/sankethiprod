<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Smartdocs-Support</title>
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

<script>


	function doSearchImagingStatusForShipment(value) {
		var formData = {
			shipmentNumber : value,
		}

		$('#statusGrid').datagrid({
			title : 'Shipment Status',
			width : 600,
			height : 100,
			fitColumns : true,
			onClickCell : getSelected,
			columns : [ [
				{
					field : 'shipmentNum',
					title : 'Shipment Number',
					width : 300
				},
				{
					field : 'Status',
					title : 'Status',
					width : 100
				},
				{
					field : 'count',
					title : 'Count',
					width : 200
				}

			] ],
			url : window.location + "/../../../imaging/" + value,
			type : "GET"
		});

		if ($('#detExist').val() == 'Y') {
			$('#fpDetGrid').datagrid({
				title : 'Imaging Status for shipment:',
				width : 900,
				height : 500,
				fitColumns : true,
				onClickCell : getSelected,
				columns : [ [
					{
						field : 'documentBarcode',
						title : 'Document Barcode',
						width : 300
					},
					{
						field : 'sourceSystem',
						title : 'Source System',
						width : 100
					},
					{
						field : 'error_cat',
						title : 'Error',
						width : 200
					}

				] ],
				url : window.location + "/../../../imaging/detailsForStatus/na-na",
				type : "GET"
			});
		} else {
			$('#detExist').val("Y");
		}
	}

	function doSearchImagingStatusForDocument(value) {


			$('#fpDetGrid').datagrid({
				title : 'Imaging Status for shipment:',
				width : 900,
				height : 500,
				fitColumns : true,
				onClickCell : getSelected,
				columns : [ [
					{
						field : 'documentBarcode',
						title : 'Document Barcode',
						width : 300
					},
					{
						field : 'status',
						title : 'Status',
						width : 100
					},
					{
						field : 'updatedDate',
						title : 'Processed Date',
						width : 200
					},
					{
						field : 'ERROR',
						title : 'Error',
						width : 200
					}

				] ],
				url : window.location + "/../../../imaging/detailsForDocument/"+value,
				type : "GET"
			});
		
	}
	
	function getSelected() {
		var row = $('#statusGrid').datagrid('getSelected');
		if (row) {
			var formData = {
				shipmentNumber : row.shipmentNum,
				status : row.Status,
			}
			$('#fpDetGrid').datagrid({
				title : 'Imaging Status for shipment:' + row.shipmentNum,
				width : 900,
				height : 500,
				fitColumns : true,
				onClickCell : getSelected,
				columns : [ [
					{
						field : 'documentBarcode',
						title : 'Document Barcode',
						width : 300
					},
					{
						field : 'sourceSystem',
						title : 'Source System',
						width : 100
					},
					{
						field : 'error_cat',
						title : 'Error',
						width : 200
					}

				] ],
				url : window.location + "/../../../imaging/detailsForStatus/" + row.shipmentNum + "-" + row.Status,
				type : "GET"
			});

		}
	}

	function doSearchStatusForDuration() {
		var formData = {
				fromDate : $('#fromDt').val(),
				toDate : $('#toDt').val(),
			}
		$('#fpDetGrid').datagrid({
			title : 'Imaging Status for duration: From ' + $('#fromDt').val()+ '  To:'+ $('#toDt').val(),
			width : 900,
			height : 500,
			fitColumns : true,
			onClickCell : getSelected,
			columns : [ [
				{
					field : 'documentBarcode',
					title : 'Document Barcode',
					width : 300
				},
				{
					field : 'sourceSystem',
					title : 'Source System',
					width : 100
				},
				{
					field : 'error_cat',
					title : 'Error',
					width : 200
				}

			] ],
			url : window.location + "/../../../imaging/detailsForDuration",
			type : "GET"
		});		
		
	}
	
</script>
</head>
<body onload="loadFrm();">
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
		<c:when test="${imaging == 'shipment'}">
			<form id="ff4">
				<div id="imgSttsShpmntSrchPnl" class="easyui-panel"
					title="Imaging status for a Shipment"
					style="width: 400px; height: 100px; max-width: 400px; padding: 30px 60px;"
					scrolling="no" style="display:none;">
					<div style="margin-bottom: 20px">
						<input class="easyui-searchbox"
							data-options="prompt:'Please Input Shipment',searcher:doSearchImagingStatusForShipment"
							style="width: 100%">
					</div>

				</div>
				<br /> <br />
				<div>
					<table id="statusGrid"></table>
				</div>
				<br /> <br />
				<table id="fpDetGrid"></table>
				<input type="hidden" id="detExist" value="N" />

			</form>
		</c:when>
	</c:choose>
	<c:choose>
		<c:when test="${imaging == 'duration'}">
			<form id="ff5">
				<div id="imgDurationSrchPnl" class="easyui-panel"
					title="Imaging status for a Duration"
					style="width: 400px; height: 400px; max-width: 400px; padding: 30px 60px;"
					scrolling="no" style="display:none;">
					<div style="margin-bottom: 20px">
						<input id="fromDt" class="easyui-datetimebox" label="From" labelPosition="top"
							style="width: 80%;">
					</div>
					<div style="margin-bottom: 20px">
						<input id="toDt" class="easyui-datetimebox" label="To" labelPosition="top"
							style="width: 80%;">
					</div>
					<div>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							onclick="doSearchStatusForDuration()" style="width: 80px">Get Status</a>
					</div>


				</div>
				<br /> <br />
				<div>
					<table id="statusGrid"></table>
				</div>
				<br /> <br />
				<table id="fpDetGrid"></table>
				<input type="hidden" id="detExist" value="N" />

			</form>
		</c:when>
	</c:choose>
	<c:choose>
		<c:when test="${imaging == 'document'}">
			<form id="ff6">
				<div id="imgSttsShpmntSrchPnl" class="easyui-panel"
					title="Imaging status for a Document"
					style="width: 400px; height: 100px; max-width: 400px; padding: 30px 60px;"
					scrolling="no" style="display:none;">
					<div style="margin-bottom: 20px">
						<input class="easyui-searchbox"
							data-options="prompt:'Please Input Document',searcher:doSearchImagingStatusForDocument"
							style="width: 100%">
					</div>

				</div>

				<br /> <br />
				<table id="fpDetGrid"></table>
				<input type="hidden" id="detExist" value="N" />

			</form>
		</c:when>
	</c:choose>
</body>
</html>