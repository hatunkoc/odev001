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
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<caption>
					<h2>
           				Profil Bilgilerim
					</h2>
				</caption>

				<div class="row pt-3">
					<div class="col">
						<label><strong>Ad :</strong> </label>
						<label>${user.ad}</label>
					</div>
					<div class="col">
						<label><strong>Soyad :</strong> </label>
						<label>${user.soyad}</label>
					</div>
				</div>
				<div class="row pt-3">
					<div class="col">
						<label><strong>Email :</strong> </label>
						<label>${user.email}</label>
					</div>
					<div class="col">
						<label><strong>Telefon :</strong> </label>
						<label>${user.telefon}</label>
					</div>
				</div>
				<div class="row pt-3">
					<div class="col">
						<label><strong>Hesap Tipi :</strong> </label>
						<label>${user.getIsManagerStr()}</label>
					</div>
					<div class="col">
						<label><strong>Cinsiyet :</strong> </label>
						<c:if test="${user.cinsiyet == 0}">
							<label>Erkek</label>
						</c:if>
						<c:if test="${user.cinsiyet != 0}">
							<label>Kadın</label>
						</c:if>
					</div>
				</div>
				<div class="row pt-3">
					<div class="col">
						<label><strong>İşe Başlama Tarihi :</strong> </label>
						<label>${user.iseBaslamaTarihi}</label>
					</div>
					<div class="col">
						<label><strong>Doğum Tarihi :</strong> </label>
						<label>${user.dogumTarihi}</label>
					</div>
				</div>
				<div class="row pt-3">
					<div class="col">
						<label><strong>Departman :</strong> </label>
						<label>${user.departman.ad}</label>
					</div>
					<div class="col">
						
					</div>
				</div>
				<div class="row pt-3">
					<div class="col">
						<a class="btn btn-success" href="kullaniciDuzenle?id=<c:out value='${user.id}' />">Bilgilerimi Düzenle</a>
					</div>
					<div class="col">
						
					</div>
				</div>
				
			</div>
		</div>
	</div>
</body>
<script>
	(function() {
		document.getElementById("departman").value = ${user.departman.id}
})();
</script>
</html>
