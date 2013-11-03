<%@ include file="menu.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			<td>Ülemüksus: </td>
			<td>
			<select Id="superUnitCode" name="superUnitCode">
				<option value="" selected="selected" name=" "/>
						<c:forEach var="unit" items="${units}">
							  <option value="${unit.code}" ${selected}>${unit.name}</option>
						</c:forEach>
					</select>
			</td>
		</tr>
		
		<tr>
			<td>Alamüksus: </td>
			<td>
			</td>
		</tr>
		
		<tr>
			<td align="right" colspan="2">
				<br />
				<input id="addButton" name="addButton" type=submit value="Lisa"/>
			</td>
		</tr>

	</table>
</form>