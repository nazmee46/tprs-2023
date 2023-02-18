package Servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import DAO.Department_DAO;
import DAO.Equipment_DAO;
import DAO.Equipment_Type_DAO;
import DAO.Location_DAO;
import DAO.CommitteeDAO;
import DAO.Supplier_DAO;

@WebServlet("/Redirect_Servlet")
public class Redirect_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Department_DAO departmentdao;
	private Equipment_DAO equipmentdao;
	private Equipment_Type_DAO equipmenttypedao;
	private Location_DAO locationdao;
	private CommitteeDAO committeedao;
	private Supplier_DAO supplierdao;
	HttpSession session;
	
    public Redirect_Servlet() {
        super();
        departmentdao = new Department_DAO();
        equipmentdao = new Equipment_DAO();
        equipmenttypedao = new Equipment_Type_DAO();
        locationdao = new Location_DAO();
        committeedao = new CommitteeDAO();
        supplierdao = new Supplier_DAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		String location_id = null;
		String equipmenttype_id = null;
		String commID = null;
		String equipmentav_idnum = null;
		String equipmentac_idnum = null;
		String equipment_department = null;
		String department_id = null;
		String supplier_id = null;
		RequestDispatcher view;
		
		session = request.getSession(true);
		String java_session_value = (String)session.getAttribute("session_idnum");
		
		try {
			switch(action) {
			case "lecturer_location":
				request.setAttribute("locationlist", locationdao.viewlocationlist());
//				request.setAttribute("equipmentdisplaylist", equipmentdao.viewequipmentdisplay());
//				request.setAttribute("equipmentcapacitylist", equipmentdao.viewequipmentcapacity());
				view = request.getRequestDispatcher("Lecturer_location.jsp");
				view.forward(request, response);
				break;
				
			case "location":
				request.setAttribute("locationlist", locationdao.viewlocationlist());
				view = request.getRequestDispatcher("Staff_location.jsp");
				view.forward(request, response);
				break;
				
			case "location_update":
				location_id = request.getParameter("id");
				request.setAttribute("locationinfo", locationdao.viewlocation(location_id));
				request.setAttribute("locationequipmentlist", equipmentdao.viewequipmentlocation(location_id));
				view = request.getRequestDispatcher("Staff_location_update.jsp");
				view.forward(request, response);
				break;
				
			case "location_delete":
				location_id = request.getParameter("id");
				session.setAttribute("session_status", locationdao.deletelocation(location_id));
				request.setAttribute("locationlist", locationdao.viewlocationlist());
				view = request.getRequestDispatcher("Staff_location.jsp");
				view.forward(request, response);
				break;
				
			case "equipmentav":
				request.setAttribute("equipmentlist", equipmentdao.viewequipmentlist("equipmentav"));
				request.setAttribute("locationlist", locationdao.viewlocationlist());
				request.setAttribute("equipmenttypelist", equipmenttypedao.viewequipmenttypelist());
				request.setAttribute("supplierlist", supplierdao.viewsupplierlist());
				view = request.getRequestDispatcher("Staff_equipment_av.jsp");
				view.forward(request, response);
				break;
				
			case "equipmentav_update":
				equipmentav_idnum = request.getParameter("id");
				request.setAttribute("equipmentavinfo", equipmentdao.viewequipmentav(equipmentav_idnum));
				request.setAttribute("stafflist", committeedao.viewcommitteelist());
				request.setAttribute("locationlist", locationdao.viewlocationlist());
				request.setAttribute("equipmenttypelist", equipmenttypedao.viewequipmenttypelist());
				request.setAttribute("supplierlist", supplierdao.viewsupplierlist());
				view = request.getRequestDispatcher("Staff_equipment_av_update.jsp");
				view.forward(request, response);
				break;
				
			case "equipmentav_delete":
				equipmentav_idnum = request.getParameter("id");
				equipment_department = request.getParameter("dept");
				session.setAttribute("session_status", equipmentdao.deleteequipment(equipmentav_idnum, equipment_department));
				request.setAttribute("equipmentlist", equipmentdao.viewequipmentlist("equipmentav"));
				request.setAttribute("locationlist", locationdao.viewlocationlist());
				request.setAttribute("equipmenttypelist", equipmenttypedao.viewequipmenttypelist());
				view = request.getRequestDispatcher("Staff_equipment_av.jsp");
				view.forward(request, response);
				break;
				
			case "equipmentac":
				request.setAttribute("equipmentlist", equipmentdao.viewequipmentlist("equipmentac"));
				request.setAttribute("locationlist", locationdao.viewlocationlist());
				request.setAttribute("equipmenttypelist", equipmenttypedao.viewequipmenttypelist());
				request.setAttribute("supplierlist", supplierdao.viewsupplierlist());
				view = request.getRequestDispatcher("Staff_equipment_ac.jsp");
				view.forward(request, response);
				break;
				
			case "equipmentac_update":
				equipmentac_idnum = request.getParameter("id");
				request.setAttribute("equipmentacinfo", equipmentdao.viewequipmentac(equipmentac_idnum));
				request.setAttribute("stafflist", committeedao.viewcommitteelist());
				request.setAttribute("locationlist", locationdao.viewlocationlist());
				request.setAttribute("equipmenttypelist", equipmenttypedao.viewequipmenttypelist());
				request.setAttribute("supplierlist", supplierdao.viewsupplierlist());
				view = request.getRequestDispatcher("Staff_equipment_ac_update.jsp");
				view.forward(request, response);
				break;
				
			case "equipmentac_delete":
				equipmentac_idnum = request.getParameter("id");
				equipment_department = request.getParameter("dept");
				session.setAttribute("session_status", equipmentdao.deleteequipment(equipmentac_idnum, equipment_department));
				request.setAttribute("equipmentlist", equipmentdao.viewequipmentlist("equipmentac"));
				request.setAttribute("locationlist", locationdao.viewlocationlist());
				request.setAttribute("equipmenttypelist", equipmenttypedao.viewequipmenttypelist());
				view = request.getRequestDispatcher("Staff_equipment_ac.jsp");
				view.forward(request, response);
				break;
				
			case "equipmenttype":
				request.setAttribute("departmentlist", departmentdao.viewdepartmentlist());
				request.setAttribute("equipmenttypelist", equipmenttypedao.viewequipmenttypelist());
				view = request.getRequestDispatcher("Staff_equipment_type.jsp");
				view.forward(request, response);
				break;
				
			case "equipmenttype_update":
				equipmenttype_id = request.getParameter("id");
				request.setAttribute("equipmenttypeinfo", equipmenttypedao.viewequipmenttype(equipmenttype_id));
				request.setAttribute("departmentlist", departmentdao.viewdepartmentlist());
				view = request.getRequestDispatcher("Staff_equipment_type_update.jsp");
				view.forward(request, response);
				break;
				
			case "equipmenttype_delete":
				equipmenttype_id = request.getParameter("id");
				session.setAttribute("session_status", equipmenttypedao.deleteequipmenttype(equipmenttype_id));
				request.setAttribute("departmentlist", departmentdao.viewdepartmentlist());
				request.setAttribute("equipmenttypelist", equipmenttypedao.viewequipmenttypelist());
				view = request.getRequestDispatcher("Staff_equipment_type.jsp");
				view.forward(request, response);
				break;
				
			case "maintenance":
				request.setAttribute("equipmentmaintenancelist", equipmentdao.viewequipmentmaintenance(""));
				request.setAttribute("departmentlist", departmentdao.viewdepartmentlist());
				view = request.getRequestDispatcher("Staff_maintenance.jsp");
				view.forward(request, response);
				break;
				
			case "report":
				request.setAttribute("equipmenttypelist", equipmenttypedao.viewequipmenttypelist());
				request.setAttribute("departmentlist", departmentdao.viewdepartmentlist());
				request.setAttribute("locationlist", locationdao.viewlocationlist());
				request.setAttribute("supplierlist", supplierdao.viewsupplierlist());
				request.setAttribute("equipmentlist", equipmentdao.viewequipmentlist(""));
				view = request.getRequestDispatcher("Staff_report.jsp");
				view.forward(request, response);
				break;
				
			case "department":
				request.setAttribute("departmentlist", departmentdao.viewdepartmentlist());
				request.setAttribute("locationlist", locationdao.viewlocationlist());
				view = request.getRequestDispatcher("Staff_department.jsp");
				view.forward(request, response);
				break;
				
			case "department_delete":
				department_id = request.getParameter("id");
				session.setAttribute("session_status", departmentdao.deletedepartment(department_id));
				request.setAttribute("departmentlist", departmentdao.viewdepartmentlist());
				request.setAttribute("locationlist", locationdao.viewlocationlist());
				view = request.getRequestDispatcher("Staff_department.jsp");
				view.forward(request, response);
				break;
				
			case "supplier":
				request.setAttribute("supplierlist", supplierdao.viewsupplierlist());
				view = request.getRequestDispatcher("Staff_supplier.jsp");
				view.forward(request, response);
				break;
				
			case "supplier_update":
				supplier_id = request.getParameter("id");
				request.setAttribute("supplierinfo", supplierdao.viewsupplier(supplier_id));
				view = request.getRequestDispatcher("Staff_supplier_update.jsp");
				view.forward(request, response);
				break;
				
			case "supplier_delete":
				supplier_id = request.getParameter("id");
				session.setAttribute("session_status", supplierdao.deletesupplier(supplier_id));
				request.setAttribute("supplierlist", supplierdao.viewsupplierlist());
				view = request.getRequestDispatcher("Staff_supplier.jsp");
				view.forward(request, response);
				break;
				
			case "committee":
				request.setAttribute("committeelist", committeedao.viewcommitteelist());
				view = request.getRequestDispatcher("Staff_user.jsp");
				view.forward(request, response);
				break;
				
			case "committee_delete":
				commID = request.getParameter("commID");
				session.setAttribute("session_status", committeedao.deletecommittee(commID));
				request.setAttribute("committeelist", committeedao.viewcommitteelist());
				view = request.getRequestDispatcher("Staff_user.jsp");
				view.forward(request, response);
				break;
				
			case "account":
				request.setAttribute("committeeinfo", committeedao.viewcommittee(java_session_value));
				view = request.getRequestDispatcher("Staff_account.jsp");
				view.forward(request, response);
				break;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}