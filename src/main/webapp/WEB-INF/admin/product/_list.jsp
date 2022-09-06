<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="panel panel-default">
	<div class="panel-heading">
	<form action="${pageContext.request.contextPath}index#list">
		<div class="input-group">
			<label class="input-group-addon">Category: </label>
			<select name="category_id" class="form-control" onchange="this.form.submit()">
				<c:forEach var="c" items="${cates}">
					<option value="${c.id}" ${product.category.id == c.id ? 'selected' : ''}>${c.name}</option>
				</c:forEach>
			</select>
		</div>
	</form>
	</div>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Unit Price</th>
				<th>Quantity</th>
				<th>Discount</th>
				<th>Available</th>
				<th>Category</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${list}">
			<tr>
				<td>${item.id}</td>
				<td>${item.name}</td>
				<td><f:formatNumber value="${item.unitPrice}" pattern="#,### VND"/></td>
				<td>${item.quantity}</td>
				<td>${item.discount*100}%</td>
				<td>${item.available?'Yes':'No'}</td>
				<td>${item.category.name}</td>
				<td class="text-center">
					<a href="${prefix}/edit/${item.id}" class="btn btn-sm btn-info">
						<span class="glyphicon glyphicon-edit"></span>
					</a>
					<a href="${prefix}/delete/${item.id}" class="btn btn-sm btn-danger">
						<span class="glyphicon glyphicon-trash"></span>
					</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>