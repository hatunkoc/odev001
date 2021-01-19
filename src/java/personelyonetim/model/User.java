/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personelyonetim.model;

import java.sql.Date;

public class User {
	protected int id;
	protected String ad;
	protected String soyad;
	protected String telefon;
	protected String email;
	protected String sifre;
	protected Boolean isManager;
	protected Integer cinsiyet;
	protected Departman departman;
	protected Date dogumTarihi;
	protected Date iseBaslamaTarihi;
	
	
	public User() {
	}
	
	public User(String ad, String soyad, String telefon, String email, String sifre, Boolean isManager, Integer cinsiyet, Departman departman, Date dogumTarihi, Date iseBaslamaTarihi) {
		super();
		this.ad = ad;
		this.soyad = soyad;
		this.telefon = telefon;
		this.email = email;
		this.sifre = sifre;
		this.isManager = isManager;
		this.cinsiyet = cinsiyet;
		this.departman = departman;
		this.dogumTarihi = dogumTarihi;
		this.iseBaslamaTarihi = iseBaslamaTarihi;
	}

	public User(int id, String ad, String soyad, String telefon, String email, String sifre, Boolean isManager, Integer cinsiyet, Departman departman, Date dogumTarihi, Date iseBaslamaTarihi) {
		super();
		this.id = id;
		this.ad = ad;
		this.soyad = soyad;
		this.telefon = telefon;
		this.email = email;
		this.sifre = sifre;
		this.isManager = isManager;
		this.cinsiyet = cinsiyet;
		this.departman = departman;
		this.dogumTarihi = dogumTarihi;
		this.iseBaslamaTarihi = iseBaslamaTarihi;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getSoyad() {
		return soyad;
	}

	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSifre() {
		return sifre;
	}

	public void setSifre(String sifre) {
		this.sifre = sifre;
	}

	public Boolean getIsManager() {
		return isManager;
	}

	public void setIsManager(Boolean isManager) {
		this.isManager = isManager;
	}

	public Integer getCinsiyet() {
		return cinsiyet;
	}

	public void setCinsiyet(Integer cinsiyet) {
		this.cinsiyet = cinsiyet;
	}

	public Departman getDepartman() {
		return departman;
	}

	public void setDepartman(Departman departman) {
		this.departman = departman;
	}

	public String getIsManagerStr() {
		if(isManager == true) return "Yönetici";
		else return "Kullanıcı";
	}

	public Date getDogumTarihi() {
		return dogumTarihi;
	}

	public void setDogumTarihi(Date dogumTarihi) {
		this.dogumTarihi = dogumTarihi;
	}

	public Date getIseBaslamaTarihi() {
		return iseBaslamaTarihi;
	}

	public void setIseBaslamaTarihi(Date iseBaslamaTarihi) {
		this.iseBaslamaTarihi = iseBaslamaTarihi;
	}
}
