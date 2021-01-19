/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personelyonetim.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import personelyonetim.dao.DepartmanDAO;
import personelyonetim.dao.GorevDAO;
import personelyonetim.dao.IzinDAO;
import personelyonetim.dao.ProjeDAO;
import personelyonetim.dao.UserDAO;
import personelyonetim.model.Departman;
import personelyonetim.model.Gorev;
import personelyonetim.model.Izin;
import personelyonetim.model.Proje;
import personelyonetim.model.User;

@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final String LOGGED_USER = "loggedUser";
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	private DepartmanDAO departmanDAO;
	private ProjeDAO projeDAO;
	private GorevDAO gorevDAO;
	private IzinDAO izinDAO;
	
	public void init() {
		userDAO = new UserDAO();
		departmanDAO = new DepartmanDAO();
		projeDAO = new ProjeDAO();
		gorevDAO = new GorevDAO();
		izinDAO = new IzinDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
                
		User loggedUser = (User)request.getSession().getAttribute(LOGGED_USER);
		try {
			if(loggedUser == null) {
                            System.out.println(action);
				switch (action) {
					case "/loginUser":
						loginAccount(request, response);
						break;
					default:
						diplayLoginPage(request, response);
						break;
				}
			}
			else {
				request.setAttribute("user", loggedUser);
				switch (action) {
					case "/cikisYap":
						cikisYap(request, response);
						break;
					case "/profilSayfam":
						showProfilSayfam(request, response);
						break;
					case "/yeniKullanici":
						showNewForm(request, response);
						break;
					case "/kullaniciEkle":
						insertUser(request, response);
						break;
					case "/kullaniciSil":
						deleteUser(request, response);
						break;
					case "/kullaniciDuzenle":
						showEditForm(request, response);
						break;
					case "/kullaniciGuncelle":
						updateUser(request, response);
						break;
					case "/kullaniciListele":
						listUser(request, response);
						break;
					case "/yeniDepartman":
						showNewDepartmanForm(request, response);
						break;
					case "/departmanEkle":
						insertDepartman(request, response);
						break;
					case "/departmanSil":
						deleteDepartman(request, response);
						break;
					case "/departmanDuzenle":
						showEditDepartmanForm(request, response);
						break;
					case "/departmanGuncelle":
						updateDepartman(request, response);
						break;
					case "/departmanListele":
						listDepartman(request, response);
						break;
					case "/yeniProje":
						showNewProjeForm(request, response);
						break;
					case "/projeEkle":
						insertProje(request, response);
						break;
					case "/projeSil":
						deleteProje(request, response);
						break;
					case "/projeDuzenle":
						showEditProjeForm(request, response);
						break;
					case "/projeGuncelle":
						updateProje(request, response);
						break;
					case "/projeListele":
						listProje(request, response);
						break;
					case "/yeniGorev":
						showNewGorevForm(request, response);
						break;
					case "/gorevEkle":
						insertGorev(request, response);
						break;
					case "/gorevSil":
						deleteGorev(request, response);
						break;
					case "/gorevDuzenle":
						showEditGorevForm(request, response);
						break;
					case "/gorevGuncelle":
						updateGorev(request, response);
						break;
					case "/gorevListele":
						listGorev(request, response);
						break;
					case "/yeniIzin":
						showNewIzinForm(request, response);
						break;
					case "/izinEkle":
						insertIzin(request, response);
						break;
					case "/izinSil":
						deleteIzin(request, response);
						break;
					case "/izinDuzenle":
						showEditIzinForm(request, response);
						break;
					case "/izinGuncelle":
						updateIzin(request, response);
						break;
					case "/izinListele":
						listIzin(request, response);
						break;
					default:
						showProfilSayfam(request, response);
						break;
				}
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		} catch (ClassNotFoundException ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
	}

	private void diplayLoginPage(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		request.getRequestDispatcher("login-form.jsp").forward(request, response);
	}

	private void loginAccount(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		String email = request.getParameter("email");
		String sifre = request.getParameter("sifre");
		User newUser = userDAO.authtenticateUser(email, sifre);
		if(newUser == null) {
			request.setAttribute("error", "Yanlış email veya parola!");
			diplayLoginPage(request, response);
		}
		else {
			request.getSession().setAttribute(LOGGED_USER, newUser);
			response.sendRedirect("profilSayfam");
		}
	}

	private void cikisYap(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {

		request.getSession().setAttribute(LOGGED_USER, null);
		diplayLoginPage(request, response);
	}
	
	private void showProfilSayfam(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		User loggedUser = (User)request.getSession().getAttribute(LOGGED_USER);
		request.setAttribute("user", loggedUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("profil-form.jsp");
		dispatcher.forward(request, response);
	}
	
	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<User> listUser = userDAO.selectAllUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Departman> departmanListesi = departmanDAO.selectAllDepartman();
		request.setAttribute("listDepartman", departmanListesi);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		User existingUser = userDAO.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		request.setAttribute("user", existingUser);
		List<Departman> departmanListesi = departmanDAO.selectAllDepartman();
		request.setAttribute("listDepartman", departmanListesi);
		dispatcher.forward(request, response);

	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ClassNotFoundException {
		String name = request.getParameter("ad");
		String surname = request.getParameter("soyad");
		String email = request.getParameter("email");
		String telefon = request.getParameter("telefon");
		String sifre = request.getParameter("sifre");
		Integer cinsiyet = Integer.parseInt(request.getParameter("cinsiyet"));
		Integer departman = Integer.parseInt(request.getParameter("departman"));
		Boolean isManager = Boolean.valueOf(request.getParameter("isManager"));
		Date iseBaslamaTarihi = Date.valueOf(request.getParameter("baslangicTarihi"));
		Date dogumTarihi = Date.valueOf(request.getParameter("dogumTarihi"));
		
		Departman dep = new DepartmanDAO().selectDepartman(departman);
		User newUser = new User(name, surname, telefon, email, sifre, isManager, cinsiyet,dep, dogumTarihi, iseBaslamaTarihi);
		userDAO.insertUser(newUser);
		response.sendRedirect("kullaniciListele");
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, ClassNotFoundException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("ad");
		String surname = request.getParameter("soyad");
		String email = request.getParameter("email");
		String telefon = request.getParameter("telefon");
		String sifre = request.getParameter("sifre");
		Integer cinsiyet = Integer.parseInt(request.getParameter("cinsiyet"));
		Integer departman = Integer.parseInt(request.getParameter("departman"));
		Boolean isManager = Boolean.valueOf(request.getParameter("isManager"));
		Date iseBaslamaTarihi = Date.valueOf(request.getParameter("baslangicTarihi"));
		Date dogumTarihi = Date.valueOf(request.getParameter("dogumTarihi"));

		Departman dep = new DepartmanDAO().selectDepartman(departman);
		User book = new User(id, name, surname, telefon, email, sifre, isManager, cinsiyet,dep, dogumTarihi, iseBaslamaTarihi);
		userDAO.updateUser(book);
		
		request.getSession().setAttribute(LOGGED_USER,book);
		User loggedUser = (User)request.getSession().getAttribute(LOGGED_USER);
		if(loggedUser.getIsManager() == true)
			response.sendRedirect("kullaniciListele");
		if(loggedUser.getIsManager() == false)
			response.sendRedirect("profilSayfam");
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		userDAO.deleteUser(id);
		response.sendRedirect("kullaniciListele");

	}
	
	private void listDepartman(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Departman> listDepartman = departmanDAO.selectAllDepartman();
		request.setAttribute("listDepartman", listDepartman);
		RequestDispatcher dispatcher = request.getRequestDispatcher("departman-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewDepartmanForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("departman-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditDepartmanForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Departman existingDepartman = departmanDAO.selectDepartman(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("departman-form.jsp");
		request.setAttribute("departman", existingDepartman);
		dispatcher.forward(request, response);

	}

	private void insertDepartman(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ClassNotFoundException {
		String ad = request.getParameter("ad");
		
		Departman newDepartman = new Departman(ad);
		departmanDAO.insertDepartman(newDepartman);
		response.sendRedirect("departmanListele");
	}

	private void updateDepartman(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String ad = request.getParameter("ad");

		Departman newDepartman = new Departman(id,ad);
		departmanDAO.updateDepartman(newDepartman);
		response.sendRedirect("departmanListele");
	}

	private void deleteDepartman(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		departmanDAO.deleteDepartman(id);
		response.sendRedirect("departmanListele");

	}
	
	private void listProje(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Proje> listProje = projeDAO.selectAllProje();
		request.setAttribute("listProje", listProje);
		RequestDispatcher dispatcher = request.getRequestDispatcher("proje-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewProjeForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<User> personelListesi = userDAO.selectAllUsers();
		request.setAttribute("listUser", personelListesi);
		RequestDispatcher dispatcher = request.getRequestDispatcher("proje-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditProjeForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		List<User> personelListesi = userDAO.selectAllUsers();
		request.setAttribute("listUser", personelListesi);
		int id = Integer.parseInt(request.getParameter("id"));
		Proje existingProje = projeDAO.selectProje(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("proje-form.jsp");
		request.setAttribute("proje", existingProje);
		dispatcher.forward(request, response);

	}

	private void insertProje(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ClassNotFoundException {
		String ad = request.getParameter("ad");
		Date baslangicTarihi = Date.valueOf(request.getParameter("baslangicTarihi"));
		Date bitisTarihi = Date.valueOf(request.getParameter("bitisTarihi"));
		Long butce = Long.valueOf(request.getParameter("butce"));
		Integer yoneticiId = Integer.parseInt(request.getParameter("yonetici"));

		User yonetici = userDAO.selectUser(yoneticiId);
		Proje newProje = new Proje(ad,yonetici,baslangicTarihi,bitisTarihi,butce);
		projeDAO.insertProje(newProje);
		
		response.sendRedirect("projeListele");
	}

	private void updateProje(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String ad = request.getParameter("ad");
		Date baslangicTarihi = Date.valueOf(request.getParameter("baslangicTarihi"));
		Date bitisTarihi = Date.valueOf(request.getParameter("bitisTarihi"));
		Long butce = Long.valueOf(request.getParameter("butce"));
		Integer yoneticiId = Integer.parseInt(request.getParameter("yonetici"));

		User yonetici = userDAO.selectUser(yoneticiId);
		Proje newProje = new Proje(id,ad,baslangicTarihi,bitisTarihi,yonetici,butce);
		projeDAO.updateProje(newProje);
		response.sendRedirect("projeListele");
	}

	private void deleteProje(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		projeDAO.deleteProje(id);
		response.sendRedirect("projeListele");

	}
	
	private void listGorev(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Gorev> listGorev = new ArrayList<Gorev>();
		User loggedUser = (User)request.getSession().getAttribute(LOGGED_USER);
		if(loggedUser.getIsManager() == false) {
			listGorev = gorevDAO.selectAllGorevByAccount(loggedUser.getId());
		}
		else {
			listGorev = gorevDAO.selectAllGorev();
		}
		request.setAttribute("listGorev", listGorev);
		RequestDispatcher dispatcher = request.getRequestDispatcher("gorev-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewGorevForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<User> personelListesi = userDAO.selectAllUsers();
		request.setAttribute("listUser", personelListesi);
		List<Proje> listProje = projeDAO.selectAllProje();
		request.setAttribute("listProje", listProje);
		RequestDispatcher dispatcher = request.getRequestDispatcher("gorev-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditGorevForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		List<User> personelListesi = userDAO.selectAllUsers();
		request.setAttribute("listUser", personelListesi);
		List<Proje> listProje = projeDAO.selectAllProje();
		request.setAttribute("listProje", listProje);
		
		int id = Integer.parseInt(request.getParameter("id"));
		Gorev existingProje = gorevDAO.selectGorev(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("gorev-form.jsp");
		request.setAttribute("gorev", existingProje);
		dispatcher.forward(request, response);

	}

	private void insertGorev(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ClassNotFoundException {
		String gorev = request.getParameter("gorev");
		Integer personelId = Integer.parseInt(request.getParameter("personel"));
		Integer projeId = Integer.parseInt(request.getParameter("proje"));

		User personel = userDAO.selectUser(personelId);
		Proje proje = projeDAO.selectProje(projeId);
		Gorev newGorev = new Gorev(gorev,proje,personel);
		gorevDAO.insertGorev(newGorev);
		
		response.sendRedirect("gorevListele");
	}

	private void updateGorev(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String gorev = request.getParameter("gorev");
		Integer personelId = Integer.parseInt(request.getParameter("personel"));
		Integer projeId = Integer.parseInt(request.getParameter("proje"));

		User personel = userDAO.selectUser(personelId);
		Proje proje = projeDAO.selectProje(projeId);

		Gorev newGorev = new Gorev(id,gorev,proje,personel);
		gorevDAO.updateGorev(newGorev);
		response.sendRedirect("gorevListele");
	}

	private void deleteGorev(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		gorevDAO.deleteGorev(id);
		response.sendRedirect("gorevListele");

	}
	
	private void listIzin(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		User loggedUser = (User)request.getSession().getAttribute(LOGGED_USER);
		request.setAttribute("user", loggedUser);
		List<Izin> listIzin = izinDAO.selectAllIzinByAccount(loggedUser.getId());
		request.setAttribute("listIzin", listIzin);
		RequestDispatcher dispatcher = request.getRequestDispatcher("izin-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewIzinForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<User> personelListesi = userDAO.selectAllUsers();
		request.setAttribute("listUser", personelListesi);
		User loggedUser = (User)request.getSession().getAttribute(LOGGED_USER);
		request.setAttribute("user", loggedUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("izin-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditIzinForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		List<User> personelListesi = userDAO.selectAllUsers();
		request.setAttribute("listUser", personelListesi);
		User loggedUser = (User)request.getSession().getAttribute(LOGGED_USER);
		request.setAttribute("user", loggedUser);
		
		int id = Integer.parseInt(request.getParameter("id"));
		Izin existingIzin = izinDAO.selectIzin(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("izin-form.jsp");
		request.setAttribute("izin", existingIzin);
		dispatcher.forward(request, response);

	}

	private void insertIzin(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ClassNotFoundException {
		String sebep = request.getParameter("sebep");
		Date baslangicTarihi = Date.valueOf(request.getParameter("baslangicTarihi"));
		Date bitisTarihi = Date.valueOf(request.getParameter("bitisTarihi"));
		Integer personelId = Integer.parseInt(request.getParameter("personel"));

		User personel = userDAO.selectUser(personelId);
		Izin newIzin = new Izin(sebep,baslangicTarihi,bitisTarihi,personel);
		izinDAO.insertIzin(newIzin);
		
		response.sendRedirect("izinListele");
	}

	private void updateIzin(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String sebep = request.getParameter("sebep");
		Date baslangicTarihi = Date.valueOf(request.getParameter("baslangicTarihi"));
		Date bitisTarihi = Date.valueOf(request.getParameter("bitisTarihi"));
		Integer personelId = Integer.parseInt(request.getParameter("personel"));

		User personel = userDAO.selectUser(personelId);
		Izin newIzin = new Izin(id,sebep,baslangicTarihi,bitisTarihi,personel);

		izinDAO.updateIzin(newIzin);
		response.sendRedirect("izinListele");
	}

	private void deleteIzin(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		izinDAO.deleteIzin(id);
		response.sendRedirect("izinListele");

	}

}

