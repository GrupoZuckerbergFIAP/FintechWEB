<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Atualização de Objetivo</title>
<%@ include file="header.jsp" %>
</head>
<body>
<%@ include file="menu.jsp" %>
<div class="container">
	<h1>Edição de Objetivo</h1>
	<form action="objetivo" method="post">
		<input type="hidden" value="editarObjetivo" name="acao">
		<input type="hidden" value="${objetivo.codigo}" name="codigo">
		<div class="form-group">
			<label for="id-nome">Nome</label>
			<input type="text" name=nome id="id-nome" class="form-control" value="${objetivo.nome}" >
		</div>
		<div class="form-group">
			<label for="id-descricao">Descricao</label>
			<input type="text" name="descricao" id="id-descricao" class="form-control" value="${objetivo.descricao}" >
		</div>
		<div class="form-group">
			<label for="id-meta">Meta</label>
			<input type="text" name="meta" id="id-meta" class="form-control" value="${objetivo.meta}">
		</div>
		<div class="form-group">
			<label for="id-prazo">Prazo</label>
			<input type="text" name="prazo" id="id-prazo" class="form-control" 
				value='<fmt:formatDate value="${objetivo.prazo.time}" pattern="dd/MM/yyyy"/>'>
		</div>	
		<input type="submit" value="Salvar" class="btn btn-primary">
		<a href="objetivo?acao=listarObjetivo" class="btn btn-danger">Cancelar</a>
	</form>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>