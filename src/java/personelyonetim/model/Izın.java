/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personelyonetim.model;

import java.sql.Date;

public class Izin {
	protected int id;
	protected String sebep;
	protected User user;
	protected Date baslangicTarihi;
	protected Date bitisTarihi;
	
	public Izin() {
	}
	
	public Izin(String sebep, Date baslangicTarihi, Date bitisTarihi, User user) {
		super();
		this.sebep = sebep;
		this.baslangicTarihi = baslangicTarihi;
		this.bitisTarihi = bitisTarihi;
		this.user = user;
	}

	public Izin(int id, String sebep, Date baslangicTarihi, Date bitisTarihi, User user) {
		super();
		this.id = id;
		this.sebep = sebep;
		this.baslangicTarihi = baslangicTarihi;
		this.bitisTarihi = bitisTarihi;
		this.user = user;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getSebep() {
		return sebep;
	}

	public void setSebep(String sebep) {
		this.sebep = sebep;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
}

