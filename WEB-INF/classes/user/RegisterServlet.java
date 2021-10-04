package user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns="/register")
public class RegisterServlet extends HttpServlet {

	//public static String jspView = "/register.jsp";
	private UserDao userDao;
	
	@Override
	public void init() throws ServletException {
		try {
			this.userDao = new UserDaoSqlite("");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.service(req, resp);
	}

	@Override
	public void doGet(HttpServletRequest request,
					  HttpServletResponse response) throws ServletException,IOException{
		doPost(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO : create user using dao and redirect to login page
		String firstname = req.getParameter("firstname");
		String lastname = req.getParameter("lastname");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String passwordConfirm = req.getParameter("confirmPassword");
		User user = new User();
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setEmail(email);
		if(password!=passwordConfirm){

			RequestDispatcher dispatcher=req.getRequestDispatcher("register.jsp");
			dispatcher.forward(req,resp);
			return;
		}
		else if (firstname==null || lastname==null || email==null || password==null || passwordConfirm==null){
			RequestDispatcher dispatcher=req.getRequestDispatcher("register.jsp");
			dispatcher.forward(req,resp);
			return;
		}
		else if(userDao.exists(email)==-1){
			RequestDispatcher dispatcher=req.getRequestDispatcher("register.jsp");
			dispatcher.forward(req,resp);
			return;
		}
		else{
			userDao.add(user , password);
			RequestDispatcher dispatcher=req.getRequestDispatcher("register.jsp");
			dispatcher.forward(req,resp);
			return;
		}


	}


}
