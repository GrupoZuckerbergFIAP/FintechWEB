<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Atualiza��o de Receita</title>
<%@ include file="header.jsp" %>
</head>
<body>
<%@ include file="menu.jsp" %>
<div class="container">
	<h1>Edi��o de Receita</h1>
	<form action="receita" method="post">
		<input type="hidden" value="editarReceita" name="acao">
		<input type="hidden" value="${receita.codigo}" name="codigo">
		<div class="form-group">
			<label for="id-descricao">Descricao</label>
			<input type="text" name="descricao" id="id-descricao" class="form-control" value="${receita.descricao}" >
		</div>
		<div class="form-group">
			<label for="id-valor">Valor</label>
			<input type="text" name="valor" id="id-valor" class="form-control" value="${receita.valor}">
		</div>
		<div class="form-group">
			<label for="id-categoria">Categoria</label>
			<select name="categoria" id="id-categoria" class="form-control">
				<option value="0">Selecione</option>
				<c:forEach items="${categorias}" var="c">
					<c:if test="${c.codigo == receita.categoria.codigo}">
						<option value="${c.codigo}" selected>${c.descricao}</option>
					</c:if>
					<c:if test="${c.codigo != receita.categoria.codigo}">
						<option value="${c.codigo}">${c.descricao }</option>
					</c:if>
				</c:forEach>
			</select>
		</div>
		<div class="form-group">
			<label for="id-dataDeVencimento">Vencimento</label>
			<input type="text" name="dataTransacao" id="id-dataTransacao" class="form-control" 
				value='<fmt:formatDate value="${receita.dataTransacao.time}" pattern="dd/MM/yyyy"/>'>
		</div>	
		<input type="submit" value="Salvar" class="btn btn-primary">
		<a href="receita?acao=listarReceita" class="btn btn-danger">Cancelar</a>
	</form>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>