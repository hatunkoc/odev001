<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Personel Yönetim Sistemi</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div class="col-3">
				<a href="#" class="navbar-brand"> Personel Yönetim Sistemi </a>
			</div>
			
			<div class="col-6">
				<ul class="navbar-nav">
					<c:if test="${user.isManager==true }">
						<li><a href="<%=request.getContextPath()%>/kullaniciListele" class="nav-link">Personeller</a></li>
						<li><a href="<%=request.getContextPath()%>/projeListele" class="nav-link">Projeler</a></li>
						<li><a href="<%=request.getContextPath()%>/gorevListele" class="nav-link">Görevler</a></li>
						<li><a href="<%=request.getContextPath()%>/departmanListele" class="nav-link">Departmanlar</a></li>
					</c:if>
					<c:if test="${user.isManager==false }">
						<li><a href="<%=request.getContextPath()%>/gorevListele" class="nav-link">Görevlerim</a></li>
					</c:if>

					<li><a href="<%=request.getContextPath()%>/izinListele" class="nav-link">İzinlerim</a></li>
				</ul>
			</div>

			<div class="col-3">
				<ul class="nav navbar-nav navbar-righ">
					<li><a href="<%=request.getContextPath()%>/profilSayfam" class="nav-link">Profil Sayfam</a></li>
					<li><a href="<%=request.getContextPath()%>/cikisYap" class="nav-link" style="float:right">Çıkış Yap <i class="fa fa-sign-out-alt"></i></a></li>
				</ul>
			</div>
		</nav>
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">Personel Listesi</h3>
			<hr>
			<div class="container text-left">
				 <a href="<%=request.getContextPath()%>/yeniKullanici" class="btn btn-success">Yeni Personel Ekle</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Soyad</th>
						<th>Email</th>
						<th>Telefon</th>
						<th>Account Type</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="user" items="${listUser}">

						<tr>
							<td><c:out value="${user.id}" /></td>
							<td><c:out value="${user.ad}" /></td>
							<td><c:out value="${user.soyad}" /></td>
							<td><c:out value="${user.email}" /></td>
							<td><c:out value="${user.telefon}" /></td>
							<td><c:out value="${user.getIsManagerStr()}" /></td>
							<td><a href="kullaniciDuzenle?id=<c:out value='${user.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="kullaniciSil?id=<c:out value='${user.id}' />">Delete</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>
