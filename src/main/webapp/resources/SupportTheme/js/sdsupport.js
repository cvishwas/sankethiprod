/**
 * 
 */
	function loadCreateUser() {
		location.href=window.location+'/../../../nav/User/CreateUser';	

	}
	
	function loadModifyRoles() {
		location.href=window.location+'/../../../nav/ModifyUser/roles';
	}	
	
	function loadEnableDisableUsr() {
		location.href=window.location+'/../../../nav/ModifyUser/ed';		
	}
	
	function loadVault() {
		location.href=window.location+'/../../../nav/ModifyUser/vault';
	}
	
	function loadImagingStatusByShipment(){
		location.href=window.location+'/../../../nav/imaging/shipment';
	}
	
	function loadImagingStatusByDocument(){
		location.href=window.location+'/../../../nav/imaging/document';
	}
	
	function loadImagingStatusByDuration(){
		location.href=window.location+'/../../../nav/imaging/duration';
	}
	
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
