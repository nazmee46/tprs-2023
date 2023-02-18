package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import Model.Committee;

import DAO.CommitteeDAO;

@WebServlet("/Update_Account_Servlet")
public class Update_Account_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommitteeDAO committeedao;
	HttpSession session;
	
    public Update_Account_Servlet() {
        super();
        committeedao = new CommitteeDAO();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Committee committee_info = new Committee();
		session = request.getSession(true);
		
		committee_info.setCommID(Integer.parseInt(request.getParameter("commID")));
		committee_info.setCommPass(request.getParameter("commPass"));
		committee_info.setCommName(request.getParameter("commName"));
		
		if(request.getParameter("commPhoneNo") != "") {
			committee_info.setCommPhoneNo(request.getParameter("commPhoneNo"));
		}
		if(request.getParameter("commAddress") != "") {
			committee_info.setCommAddress(request.getParameter("commAddress"));
		}
		if(request.getParameter("presidentID") != "") {
			committee_info.setPresidentID(request.getParameter("presidentID"));
		}
		
		session.setAttribute("session_status", committeedao.updatecommittee(committee_info));
		response.sendRedirect("Redirect_Servlet?action=account&id=" + request.getParameter("commID"));
	}
}