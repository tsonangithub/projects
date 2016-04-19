<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>demo</title>
</head>
<body>
	<script src="jquery-2.2.3.min.js"></script>
	<script type="text/javascript">  
		<% String apiGateway = System.getenv("api_gateway_host") + ":" + System.getenv("api_gateway_port"); %>
		function getRandom() {
			$.get({
					url: "<%= apiGateway %>/random", 
					crossDomain: true
				}).done(function(response) {
					$("#randomTextBox").val(response);
				});
		}
		
		function concatenate() {
			var thisString =$("#thisString").val();
			var thatString =$("#thatString").val();
			$.get("<%= apiGateway %>/concatenate/"+thisString+"?otherString="+thatString).done(function(response) {
				$("#concatenatedString").val(response);
			});
		}
		
		function substring() {
			var inputString=$("#inputString").val();
			var start =$("#start").val();
			var end =$("#end").val();
			$.get("<%= apiGateway %>/substring/"+thisString+"?start="+start+"&end="+end).done(function(response) {
				$("#resultSubstring").val(response);
			});
		}
		
	</script>
	
	<button onClick="getRandom()">Generate Random</button>
	<br>
	<input id="randomTextBox" type="text" readonly="readonly" style="width : 50%;" />
	
	<br><br>
	
	<input id="thisString" type="text" style="width : 15%;" />
	<input id="thatString" type="text" style="width : 15%;" />
	<button onClick="concatenate()">Concatenate</button>
	<br>
	<input id="concatenatedString" type="text" style="width : 50%;" />
	
	<br><br>
	
	<input id="inputString" type="text" style="width : 20%;" />
	<label for="start">Start:</label>
	<input id="start" type="text" style="width : 2em;" />
	<label for="end">End:</label>
	<input id="end" type="text" style="width : 2em;" />
	<button onClick="substring()">Substring</button>
	<br>
	<input id="resultSubstring" type="text" style="width : 50%;" />
	

	
</body>
</html>