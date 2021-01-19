/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personelyonetim.model;

public class Departman {
	protected int id;
	protected String ad;
	
	public Departman() {
	}
	
	public Departman(String ad) {
		super();
		this.ad = ad;
	}

	public Departman(int id, String ad) {
		super();
		this.id = id;
		this.ad = ad;
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
}

