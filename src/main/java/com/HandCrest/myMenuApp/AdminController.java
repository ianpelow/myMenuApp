package com.HandCrest.myMenuApp;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

	@Autowired
	RestaurantRepository restaurantRepo;

	@Autowired
	MenuRepository menuRepo;

	@RequestMapping("/admin/logout")
	public String adminLogin(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null)
		{
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("role")) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				break;
			}
		}
		}else
		{
			System.out.println("COOKIES ARE NULL GROOT");
		}
		return "redirect:/admin";
	}

	@RequestMapping("/admin")
	public String adminEditor(@CookieValue(name = "role", defaultValue = "") String role, Model model) {
		System.out.println("Role: " + role);

		if (role == null || !role.equals("admin")) {
			return "redirect:/login";
		}

		Iterable<Menu> menus = menuRepo.findAll();
		model.addAttribute("menus", menus);
		return "admin";
	}

////	@RequestMapping(value="/admin")
	
	
}
