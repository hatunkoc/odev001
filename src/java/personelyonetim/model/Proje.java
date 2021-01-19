/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personelyonetim.model;

import java.sql.Date;

public class Proje {
	protected int id;
	protected String ad;
	protected User yonetici;
	protected Date baslangicTarihi;
	protected Date bitisTarihi;
	protected Long butce;
	
	public Proje() {
	}
	
	public Proje(String ad, User yonetici, Date baslangicTarihi, Date bitisTarihi, Long butce) {
		super();
		this.ad = ad;
		this.yonetici = yonetici;
		this.baslangicTarihi = baslangicTarihi;
		this.bitisTarihi = bitisTarihi;
		this.butce = butce;
	}

	public Proje(int id, String ad, Date baslangicTarihi, Date bitisTarihi, User yonetici, Long butce) {
		super();
		this.id = id;
		this.ad = ad;
		this.yonetici = yonetici;
		this.baslangicTarihi = baslangicTarihi;
		this.bitisTarihi = bitisTarihi;
		this.butce = butce;
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

	public User getYonetici() {
		return yonetici;
	}

	public void setYonetici(User yonetici) {
		this.yonetici = yonetici;
	}

	public Date getBaslangicTarihi() {
		return baslangicTarihi;
	}

	public void setBaslangicTarihi(Date baslangicTarihi) {
		this.baslangicTarihi = baslangicTarihi;
	}

	public Date getBitisTarihi() {
		return bitisTarihi;
	}

	public void setBitisTarihi(Date bitisTarihi) {
		this.bitisTarihi = bitisTarihi;
	}

	public Long getButce() {
		return butce;
	}

	public void setButce(Long butce) {
		this.butce = butce;
	}
}

