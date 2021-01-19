/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personelyonetim.model;

public class Gorev {
	protected int id;
	protected String gorev;
	protected Proje proje;
	protected User personel;
	
	public Gorev() {
	}
	
	public Gorev(String gorev, Proje proje, User personel) {
		super();
		this.gorev = gorev;
		this.proje = proje;
		this.personel = personel;
	}

	public Gorev(int id, String gorev, Proje proje, User personel) {
		super();
		this.id = id;
		this.gorev = gorev;
		this.proje = proje;
		this.personel = personel;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getGorev() {
		return gorev;
	}

	public void setGorev(String gorev) {
		this.gorev = gorev;
	}

	public Proje getProje() {
		return proje;
	}

	public void setProje(Proje proje) {
		this.proje = proje;
	}

	public User getPersonel() {
		return personel;
	}

	public void setPersonel(User personel) {
		this.personel = personel;
	}
}

