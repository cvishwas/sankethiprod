<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
// 	function loadUser() {
// 		$('#ff1').hide();
// 		$('#ff2').hide();
// 		$('#ff3').hide();
// 		$('#usrPnl').show();
// 		$('#edPnl').hide();
// 		$('#modRolesPnl').hide();
// 		$('#vaultPnl').hide();		
// 		$('#ff').show();
// 		$('#ff4').hide();		

// 	}
	
// 	function loadModifyRoles() {
// 		$('#modRolesPnl').show();
// 		$('#usrPnl').hide();
// 		$('#edPnl').hide();
// 		$('#vaultPnl').hide();
// 		$('#ff').hide();
// 		$('#ff1').show();
// 		$('#ff2').hide();
// 		$('#ff3').hide();
// 		$('#ff4').hide();		
// 	}	
	
// 	function loadEnableDisableUsr() {
// 		$('#edPnl').show();
// 		$('#usrPnl').hide();
// 		$('#modRolesPnl').hide();
// 		$('#vaultPnl').hide();		
// 		$('#ff').hide();
// 		$('#ff1').hide();
// 		$('#ff2').show();
// 		$('#ff3').hide();
// 		$('#ff4').hide();		
// 	}
	
// 	function loadVault() {
// 		$('#vaultPnl').show();	
// 		$('#usrPnl').hide();
// 		$('#edPnl').hide();
// 		$('#modRolesPnl').hide();		
// 		$('#ff').hide();
// 		$('#ff1').hide();
// 		$('#ff2').hide();
// 		$('#ff3').show();
// 		$('#ff4').hide();		
// 	}
	
// 	function loadImagingStatus(){
// 		$('#vaultPnl').hide();	
// 		$('#usrPnl').hide();
// 		$('#edPnl').hide();
// 		$('#modRolesPnl').hide();
// 		$('#imgSttsShpmntSrchPnl').show();	
// 		$('#ff').hide();
// 		$('#ff1').hide();
// 		$('#ff2').hide();
// 		$('#ff3').hide();
// 		$('#ff4').show();

// 	}
	
	function loadFrm() {
		$('#ff').hide();
		$('#ff1').hide();
		$('#ff2').hide();
		$('#ff3').hide();
		$('#ff4').hide();
		$('#usrPnl').hide();
		$('#vaultPnl').hide();	
		$('#edPnl').hide();
		$('#modRolesPnl').hide();	
		$('#ff4').hide();	}
	
	function submitUpdateRoleForm(){
		var formData = {
				userId : $("#UserId1").val(),
	    		roles :  $("#roles1").val()
	    	}
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : window.location+"/../../../user/UpdateUserRole",
			data : JSON.stringify(formData),
			dataType : 'text',
			success : function(result) {

				if(result == "UserRoleUpdated"){
					$.messager.alert('User Update status','User '+$("#UserId").val()+'s roles have been succesfully updated. ','info');
				}else{
					$.messager.alert('User Update status','User '+$("#UserId").val()+' could not be updated. Check the error logs for more information.','error');
				}
				console.log(result);
			},
			error : function(e) {
				$.messager.alert('User Update status','User '+$("#UserId").val()+' could not be updated. Check the error logs for more information.','error');
				console.log("ERROR: ", e);
			}
		});
		
	}
	
	function toggleEnabled2(){
		alert($("#enabled2").val());
	}
	
	function submitUpdateEnabledDisabledForm(){
		var formData = {
				userId : $("#UserId2").val(),
				enabled :  $("#enabled2").val()
	    	}

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : window.location+"/../../../user/UpdateUserED",
			data : JSON.stringify(formData),
			dataType : 'text',
			success : function(result) {

				if(result == "UserRoleUpdated"){
					$.messager.alert('User Update status','User '+$("#UserId").val()+' status has been updated. ','info');
				}else{
					$.messager.alert('User Update status','User '+$("#UserId").val()+' status could not be updated. Check the error logs for more information.','error');
				}
				console.log(result);
			},
			error : function(e) {
				$.messager.alert('User Update status','User '+$("#UserId").val()+' status could not be updated. Check the error logs for more information.','error');
				console.log("ERROR: ", e);
			}
		});
		
	}

	function submitUpdateUserVaultForm(){
		var formData = {
				userId : $("#UserId3").val(),
				vltId :  $("#vltId3").val()
	    	}

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : window.location+"/../../../user/UpdateUserVault",
			data : JSON.stringify(formData),
			dataType : 'text',
			success : function(result) {

				if(result == "UserVaultUpdated"){
					$.messager.alert('User Update status','User '+$("#UserId").val()+' vault has been updated. ','info');
				}else{
					$.messager.alert('User Update status','User '+$("#UserId").val()+' vault could not be updated. Check the error logs for more information.','error');
				}
				console.log(result);
			},
			error : function(e) {
				$.messager.alert('User Update status','User '+$("#UserId").val()+' vault could not be updated. Check the error logs for more information.','error');
				console.log("ERROR: ", e);
			}
		});
		
	}	
	
	function submitUserForm(){

		var formData = {
	    		userId : $("#UserId").val(),
	    		firstName : $("#fname").val(),
	    		lastName :  $("#lname").val(),
	    		accType :  $("#accType").val(),
	    		clientId :  $("#clientId").val(),
	    		clientName :  $("#clientName").val(),
	    		region :  $("#region").val(),
	    		wrhsCd :  $("#wrhsCd").val(),
	    		vltId :  $("#vltId").val(),
	    		roles :  $("#roles").val(),
	    		enabled :  $("#enabled").val()
	    	}

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : window.location+"/../../../user/CreateUser",
			data : JSON.stringify(formData),
			dataType : 'text',
			success : function(result) {

				if(result == "UserCreated"){
					$.messager.alert('User Save status','User '+$("#UserId").val()+' has been succesfully created. ','info');
				}else{
					$.messager.alert('User Save status','User '+$("#UserId").val()+' could not be created. Check the error logs for more information.','error');
				}
				console.log(result);
			},
			error : function(e) {
				$.messager.alert('User Save status','User '+$("#UserId").val()+' could not be created. Check the error logs for more information.','error');
				console.log("ERROR: ", e);
			}
		});
	}
		
	function clearUserForm(value){
		$(value).form('clear');
	}

    function doSearchRoles(value){
		var formData = {
	    		name : value,
	    	} 

        $.ajax({
			type : "POST",
			contentType : "application/json",
			url : window.location+"/../../../user/GetUserRoles",
			data : JSON.stringify(formData),
			dataType : 'text',
			success : function(result) {
				$('#ff1').form('load', {
					roles1:result,
					UserId1:value
	            });
				console.log(result);
			},
			error : function(e) {
				$.messager.alert('User Role Retrieval','Roles could not be retrieved for User '+$("#UserId").val()+' . Check the error logs for more information.','error');
				console.log("ERROR: ", e);
			}
		});        
    }
	
    function doSearchUserStatus(value){
		var formData = {
	    		name : value,
	    	} 

        $.ajax({
			type : "POST",
			contentType : "application/json",
			url : window.location+"/../../../user/GetUserStatus",
			data : JSON.stringify(formData),
			dataType : 'text',
			success : function(result) {
				$('#ff2').form('load', {
					enabled2:result,
					UserId2:value
	            });
				console.log(result);
			},
			error : function(e) {
				$.messager.alert('User Role Retrieval','Roles could not be retrieved for User '+$("#UserId").val()+' . Check the error logs for more information.','error');
				console.log("ERROR: ", e);
			}
		});        
    }  
    
    function doSearchUserVault(value){
		var formData = {
	    		name : value,
	    	} 

        $.ajax({
			type : "POST",
			contentType : "application/json",
			url : window.location+"/../../../user/GetUserVault",
			data : JSON.stringify(formData),
			dataType : 'text',
			success : function(result) {
				$('#ff3').form('load', {
					vltId3:result,
					UserId3:value
	            });
				console.log(result);
			},
			error : function(e) {
				$.messager.alert('User Role Retrieval','Roles could not be retrieved for User '+$("#UserId").val()+' . Check the error logs for more information.','error');
				console.log("ERROR: ", e);
			}
		});        
    }
    
    
    function doSearchImagingStatusForShipment(value){
		var formData = {
				shipmentNumber : value,
	    	} 

		$('#statusGrid').datagrid({
			title:'Shipment Status',
			width:600,
			height:100,
			fitColumns:true,
			onClickCell: getSelected,
			columns:[[
				{field:'shipmentNum',title:'Shipment Number',width:300},
				{field:'Status',title:'Status',width:100},
				{field:'count',title:'Count',width:200}
				
			]],
			url: window.location+"/../../../imaging/"+value,
			type : "GET"
		});		
		
		if($('#detExist').val() == 'Y'){
			$('#fpDetGrid').datagrid({
				title:'Imaging Status for shipment:',
				width:900,
				height:500,
				fitColumns:true,
				onClickCell: getSelected,
				columns:[[
					{field:'documentBarcode',title:'Document Barcode',width:300},
					{field:'sourceSystem',title:'Source System',width:100},
					{field:'error_cat',title:'Error',width:200}
					
				]],
				url: window.location+"/../../../imaging/detailsForStatus/na-na",
				type : "GET"
			});			
		}else{
			$('#detExist').val("Y");
		}
    }    
   
	function getSelected(){
		var row = $('#statusGrid').datagrid('getSelected');
		if (row){
			var formData = {
					shipmentNumber : row.shipmentNum,
					status : row.Status,
		    	}
			$('#fpDetGrid').datagrid({
				title:'Imaging Status for shipment:'+row.shipmentNum,
				width:900,
				height:500,
				fitColumns:true,
				onClickCell: getSelected,
				columns:[[
					{field:'documentBarcode',title:'Document Barcode',width:300},
					{field:'sourceSystem',title:'Source System',width:100},
					{field:'error_cat',title:'Error',width:200}
					
				]],
				url: window.location+"/../../../imaging/detailsForStatus/"+row.shipmentNum+"-"+row.Status,
				type : "GET"
			});	

		}
	}    
</script>
</head>
<body onload="loadFrm();">
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
	<form id="ff" method="post">
		<div id="usrPnl" class="easyui-panel" title="User"
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
			<input type="hidden" id="UserId1" name="UserId1"/>
			<div style="text-align: center; padding: 5px 0">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="submitUpdateRoleForm()" style="width: 80px">Submit</a> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					onclick="clearUserForm('#ff1')" style="width: 80px">Clear</a>
			</div>
		</div>
	</form>
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
			<input type="hidden" id="UserId2" name="UserId2"/>
			<div style="text-align: center; padding: 5px 0">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="submitUpdateEnabledDisabledForm()" style="width: 80px">Submit</a> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					onclick="clearUserForm('#ff2')" style="width: 80px">Clear</a>
			</div>
		</div>
	</form>
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
					style="width: 100%;" data-options="label:'Vault Id:',required:true">
			</div>
			<input type="hidden" id="UserId3" name="UserId3"/>
			<div style="text-align: center; padding: 5px 0">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="submitUpdateUserVaultForm()" style="width: 80px">Submit</a> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					onclick="clearUserForm('#ff3')" style="width: 80px">Clear</a>
			</div>
		</div>
	</form>
	<form id="ff4">
		<div id="imgSttsShpmntSrchPnl" class="easyui-panel" title="Imaging status for Shipment"
			style="width: 400px;height:100px; max-width: 400px; padding: 30px 60px;" scrolling="no"
			style="display:none;">
			<div style="margin-bottom: 20px">
				<input class="easyui-searchbox"
					data-options="prompt:'Please Input Shipment',searcher:doSearchImagingStatusForShipment"
					style="width: 100%">
			</div>
				
		</div>
		<br/><br/>
		<div><table id="statusGrid"></table></div><br/><br/>
		<table id="fpDetGrid"></table>
		<input type="hidden" id="detExist" value="N"/>
		
	</form>	
	


</body>
</html>