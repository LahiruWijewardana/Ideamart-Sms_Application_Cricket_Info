package services;

import model.Users;
import mappers.UsersMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.sql.SQLException;
import java.io.BufferedReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SmsServlet
 */

public class SmsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SmsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Hello");
		
		BufferedReader reader = request.getReader();
        Gson gson = new GsonBuilder().create();
        
        Users user = gson.fromJson(reader, Users.class);
        
        System.out.println(user.getMsisdn());
		System.out.println(user.getType());

        UsersMapper userMapper = new UsersMapper();
        
        try {
            if(userMapper.findUser(user)) {
                response.getWriter().write("The User already existing in the database");
                userMapper.updateUser(user);
                response.getWriter().write("User updated");;
            } else {
                userMapper.addUser(user);
                response.getWriter().write("User Registered");
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
