<%@ include file="menu.jsp"%>

<form action="Add" method="post">
	<table id="formTable" class="formTable">
		<tr>
			<td>Nimi: </td>
			<td><input id="nameBox" name="name" /></td>
		</tr>
		<tr>
			<td>Kood: </td>
			<td><input id="codeBox" name="code" /></td>
		</tr>
		<tr>
			<td align="right" colspan="2">
				<br />
				<input id="addButton" type=submit value="Lisa"/>
			</td>
		</tr>
	</table>
</form>