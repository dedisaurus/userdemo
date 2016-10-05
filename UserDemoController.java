/**
 * 
 */
package com.userdemo.controller;


import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.userdemo.dao.IUserDao;
import com.userdemo.model.User;
/**
 * @author shivraj-patil
 *
 */
@Controller
@RequestMapping("/userdemo")
public class UserDemoController {

	   @Autowired
	    private IUserDao userDaoImpl;

	    @RequestMapping(method = RequestMethod.GET)
	    public String goHome() {
	        return "userdemo";
	    }

	    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
	    public String toDaftar(ModelMap model) {
	        model.addAttribute("users", userDaoImpl.getAll());
	        return "findAll";
	    }

	    @RequestMapping(value = "/insert", method = RequestMethod.GET)
	    public String goInsert() {
	        return "insert";
	    }

	    @RequestMapping(value = "/save", method = RequestMethod.POST)
	    public String goSave(HttpServletRequest request) {
	    	 int id = 0;
	    	if(request.getParameter("id")!=null) id = Integer.parseInt(request.getParameter("id"));
	        String firstName = request.getParameter("firstName");
	        String lastName = request.getParameter("lastName");
	        int age = 0;
	    	if(request.getParameter("age")!=null) 
	    		age  = Integer.parseInt(request.getParameter("age"));
	    	
	        User user = new User(id, firstName, lastName, age);
	        userDaoImpl.save(user);
	        return "redirect:/userdemo/findAll";
	    }

	    @RequestMapping(value = "/delete", method = RequestMethod.GET)
	    public String goDelete(HttpServletRequest request) {
	        int id = Integer.valueOf(request.getParameter("id"));
	        userDaoImpl.deleteById(id);
	        return "redirect:/userdemo/findAll";
	    }

	    @RequestMapping(value = "/edit", method = RequestMethod.GET)
	    public String goEdit(HttpServletRequest request, ModelMap model) {
	    	 int id = 0;
	    	if(request.getParameter("id")!=null) id = Integer.parseInt(request.getParameter("id"));
	        model.addAttribute("user", userDaoImpl.getById(id));
	        return "edit";
	    }

	    @RequestMapping(value = "/update", method = RequestMethod.POST)
	    public String goUpdate(HttpServletRequest request) {
	    	 int id = 0;
	    	if(request.getParameter("id")!=null) id = Integer.parseInt(request.getParameter("id"));
	        String firstName = request.getParameter("firstName");
	        String lastName = request.getParameter("lastName");
	        int age = 0;
	    	if(request.getParameter("age")!=null) 
	    		age  = Integer.parseInt(request.getParameter("age"));
	
	        User user = new User(id, firstName, lastName, age);
	        userDaoImpl.update(user);
	        return "redirect:/userdemo/findAll";
	    }
	    
}
