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
				<c:if test="${izin != null}">
					<form action="izinGuncelle" method="post">
				</c:if>
				<c:if test="${izin == null}">
					<form action="izinEkle" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${izin != null}">
            			Izin Bilgilerini Düzenle
            		</c:if>
						<c:if test="${izin == null}">
            			Yeni Izin Ekle
            		</c:if>
					</h2>
				</caption>

				<input type="hidden" name="personel" value="<c:out value='${user.id}' />" />
				<c:if test="${izin != null}">
					<input type="hidden" name="id" value="<c:out value='${izin.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>İzin Sebebi</label> <input type="text"
						value="<c:out value='${izin.sebep}' />" class="form-control"
						name="sebep" required="required">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Başlama Tarihi</label> <input type="date"
						value="<c:out value='${izin.baslangicTarihi}' />" class="form-control"
						name="baslangicTarihi" required="required">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Bitiş Tarihi</label> <input type="date"
						value="<c:out value='${izin.bitisTarihi}' />" class="form-control"
						name="bitisTarihi" required="required">
				</fieldset>
				
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
<script>
	(function() {
		document.getElementById("personel").value = ${izin.user.id}
	})();
</script>
</html>
