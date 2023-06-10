<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Atualização de Cartão</title>
<%@ include file="header.jsp" %>
</head>
<body>
<%@ include file="menu.jsp" %>
<div class="container">
	<h1>Edição de Cartão</h1>
	<form action="cartao" method="post">
		<input type="hidden" value="editarCartao" name="acao">
		<input type="hidden" value="${cartao.codigo}" name="codigo">
		<div class="form-group">
			<label for="id-nome">Nome</label>
			<input type="text" name="nome" id="id-nome" class="form-control" value="${cartao.nome}" >
		</div>
		<div class="form-group">
			<label for="id-valor">Numero</label>
			<input type="text" name="numero" id="id-numero" class="form-control" value="${cartao.numero}">
		</div>
		<div class="form-group">
			<label for="id-dataDeVencimento">Vencimento</label>
			<input type="text" name="dataDeVencimento" id="id-dataDeVencimentoo" class="form-control" 
				value='<fmt:formatDate value="${cartao.dataDeVencimento.time}" pattern="dd/MM/yyyy"/>'>
		</div>
		<div class="form-group">
			<label for="id-bandeira">Bandeira</label>
			<input type="text" name="bandeira" id="id-bandeira" class="form-control" value="${cartao.bandeira}">
		</div>		
		<div class="form-group">
			<label for="id-cvv">CVV</label>
			<input type="text" name="cvv" id="id-cvv" class="form-control" value="${cartao.cvv}">
		</div>	
		<div class="form-group">
			<label for="id-cpfTitular">CPF TItular</label>
			<input type="text" name="cpfTitular" id="id-cpfTitular" class="form-control" value="${cartao.cpfTitular}">
		</div>	
		<div class="form-group">
			<label for="id-categoria">Categoria</label>
			<select name="categoria" id="id-categoria" class="form-control">
				<option value="0">Selecione</option>
				<c:forEach items="${categorias}" var="c">
					<c:if test="${c.codigo == cartao.categoria.codigo}">
						<option value="${c.codigo}" selected>${c.descricao}</option>
					</c:if>
					<c:if test="${c.codigo != cartao.categoria.codigo}">
						<option value="${c.codigo}">${c.descricao }</option>
					</c:if>
				</c:forEach>
			</select>
		</div>
		<input type="submit" value="Salvar" class="btn btn-primary">
		<a href="cartao?acao=listarCartao" class="btn btn-danger">Cancelar</a>
	</form>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>