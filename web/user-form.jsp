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
				<c:if test="${user != null}">
					<form action="kullaniciGuncelle" method="post">
				</c:if>
				<c:if test="${user == null}">
					<form action="kullaniciEkle" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${user != null}">
            			Personel Bilgilerini Düzenle
            		</c:if>
						<c:if test="${user == null}">
            			Yeni Personel Ekle
            		</c:if>
					</h2>
				</caption>

				<c:if test="${user != null}">
					<input type="hidden" name="id" value="<c:out value='${user.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Ad</label> <input type="text"
						value="<c:out value='${user.ad}' />" class="form-control"
						name="ad" required="required">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Soyad</label> <input type="text"
						value="<c:out value='${user.soyad}' />" class="form-control"
						name="soyad" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Email</label> <input type="text"
						value="<c:out value='${user.email}' />" class="form-control"
						name="email">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Telefon</label> <input type="text"
						value="<c:out value='${user.telefon}' />" class="form-control"
						name="telefon" required="required">
				</fieldset>
				
				<c:if test="${user.isManager==true }">				
					<fieldset class="form-group">
				</c:if>				
				<c:if test="${user.isManager==false }">				
					<fieldset class="form-group" style="display:none">
				</c:if>
						<label>İşe Başlama Tarihi</label> <input type="date"
							value="<c:out value='${user.iseBaslamaTarihi}' />" class="form-control"
							name="baslangicTarihi" required="required">
					</fieldset>
				
				<fieldset class="form-group">
					<label>Doğum Tarihi</label> <input type="date"
						value="<c:out value='${user.dogumTarihi}' />" class="form-control"
						name="dogumTarihi" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Cinsiyet</label>
					<c:if test="${user != null}">
						<select name="cinsiyet" id="cinsiyet" class="form-control">
							<c:if test="${user.cinsiyet == 0}">
							    <option value="0" selected>Erkek</option>
							    <option value="1">Kadın</option>
		            		</c:if>
		            		<c:if test="${user.cinsiyet == 1}">
							    <option value="0">Erkek</option>
							    <option value="1" selected>Kadın</option>
		            		</c:if>
						</select>
	            	</c:if>
	            	<c:if test="${user == null}">
						<select name="cinsiyet" id="cinsiyet" class="form-control">
						    <option value="0" selected>Erkek</option>
						    <option value="1">Kadın</option>
						</select>
	            	</c:if>
				</fieldset>
				
				<c:if test="${user.isManager==true }">
					<fieldset class="form-group">
				</c:if>
				<c:if test="${user.isManager==false }">
					<fieldset class="form-group" style="display:none">
				</c:if>
						<label>Hesap Tipi</label>
						<c:if test="${user != null}">
							<select name="isManager" id="isManager" class="form-control">
								<c:if test="${user.isManager == false}">
								    <option value="false" selected>Kullanıcı</option>
								    <option value="true">Yönetici</option>
			            		</c:if>
			            		<c:if test="${user.isManager == true}">
								    <option value="false">Kullanıcı</option>
								    <option value="true" selected>Yönetici</option>
			            		</c:if>
							</select>
		            	</c:if>
		            	<c:if test="${user == null}">
							<select name="isManager" id="isManager" class="form-control">
							    <option value="false" selected>Kullanıcı</option>
							    <option value="true">Yönetici</option>
							</select>
		            	</c:if>
					</fieldset>
				
				
				<c:if test="${user.isManager==true }">
					<fieldset class="form-group">
				</c:if>
				<c:if test="${user.isManager==false }">
					<fieldset class="form-group" style="display:none">
				</c:if>
						<label>Departman</label>
						<select name="departman" id="departman" class="form-control">
							<c:forEach var="departman" items="${listDepartman}">
								<option value="${departman.id}">${departman.ad}</option>
							</c:forEach>
						</select>
					</fieldset>
				
				<fieldset class="form-group">
					<label>Parola</label> <input type="password"
						value="<c:out value='${user.sifre}' />" class="form-control"
						name="sifre">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
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
