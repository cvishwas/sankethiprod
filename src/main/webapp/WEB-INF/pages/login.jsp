<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Smartdocs-Support</title>
	<spring:url value="/resources/css/easyui.css" var="easyuiCss" />
	<spring:url value="/resources/css/icon.css" var="iconCss" />
	<spring:url value="/resources/js/jquery.min.js" var="jqueryMinJs" />
	<spring:url value="/resources/js/jquery.easyui.min.js" var="jqueryEasyUiJs" />	
		
	<link rel="stylesheet" type="text/css" href="${easyuiCss}">
	<link rel="stylesheet" type="text/css" href="${iconCss}">
	<script src="${jqueryMinJs}"></script>
	<script src="${jqueryEasyUiJs}"></script>
</head>
<body>
	<h2>Login</h2>

	<div style="margin:20px 0;"></div>
	<div class="easyui-panel" title="" style="width:100%;max-width:400px;padding:30px 60px;">
		<form id="ff" method="post" enctype="multipart/form-data">
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" id ="name" name="name" style="width:100%" data-options="label:'User Name:',required:true">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-passwordbox" id = "password" name="password" style="width:100%" data-options="label:'Password:',required:true,validType:'password'">
			</div>
		</form>
		<div style="text-align:center;padding:5px 0">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">Submit</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px">Clear</a>
		</div>
	</div>
	<script>
		function submitForm(){
			
			var formData = {
		    		name : $("#name").val(),
		    		password :  $("#password").val()
		    	}
			
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : window.location + "/auth",
				data : JSON.stringify(formData),
				dataType : 'text',
				success : function(result) {
					if(result == "login"){
						window.location.replace(window.location+"/suppmenu");
					}else{
						$("#postResultDiv").html("<strong>Login Failed</strong>");
					}
					console.log(result);
				},
				error : function(e) {
					alert("Error!"+e);
					console.log("ERROR: ", e);
				}
			});
		}
			
		function clearForm(){
			$('#ff').form('clear');
		}
        $(function(){
            
        });		
	</script>
</body>
</html>