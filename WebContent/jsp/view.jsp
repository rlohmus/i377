<%@ include file="menu.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form action="Add" method="post">
	<table id="formTable" class="formTable">
		<tr>
			<td>Nimi:</td>
			<td><input id="nameBox" name="name" value="${uName}"
				disabled='disabled' /></td>
		</tr>
		<tr>
			<td>Kood:</td>
			<td><input id="codeBox" name="code" value="${uKood}"
				disabled='disabled' /></td>
		</tr>
		<tr>
			<td>Ülemüksus:</td>
			<td><select id="superUnitCode" name="superUnitCode"
				disabled='disabled'>
					<option value="${superUnit}" select="selected">
						<c:out value="${superUnit.name}"></c:out>
					</option>

			</select></td>
		</tr>

		<tr>
			<td>Alamüksus:</td>
			<td><c:forEach var="unit" items="${subUnits}" varStatus="status">
					<span id="sub_${unit.code }">${unit.code}</span>
					<c:if test="${status.last == false}">, </c:if>
				</c:forEach></td>
		</tr>

		<tr>
			<td align="right" colspan="2"><br /> <a href="Search"
				id="backLink" name="backLink">Tagasi</a></td>
			</td>
		</tr>

	</table>
</form>