package vn.com.abcblog.controller.admin;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import vn.com.abcblog.model.Role;
import vn.com.abcblog.model.User;

@Controller
@RequestMapping(value = "/admin/user")
public class AdminUserController {

	public void getRoles(Model model) {
		String url = "http://localhost:8080/api/role";

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<List<Role>> entity = new HttpEntity<List<Role>>(headers);

		@SuppressWarnings("unchecked")
		List<Role> roles = restTemplate.exchange(url, HttpMethod.GET, entity, List.class).getBody();

		model.addAttribute("roles", roles);
	}

	@RequestMapping(value = "/list")
	public String showList(HttpServletRequest request) {
		String message = request.getParameter("message");
		String alert = request.getParameter("alert");

		String page = request.getParameter("page");
		String limit = "10";

		if (message != null && alert != null) {
			request.setAttribute("message", message.replaceAll("_", "."));
			request.setAttribute("alert", alert);
		}

		String url = "http://localhost:8080/api/user/list?page=" + page + "&limit=" + limit;
		String totalPagesURL = "http://localhost:8080/api/user/total_pages?page=" + page + "&limit=" + limit;

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<List<User>> entity = new HttpEntity<List<User>>(headers);

		@SuppressWarnings("unchecked")
		List<User> users = restTemplate.exchange(url, HttpMethod.GET, entity, List.class).getBody();

		request.setAttribute("page", page);
		request.setAttribute("totalPages", restTemplate.getForObject(totalPagesURL, Integer.class));
		request.setAttribute("users", users);

		return "admin/user/list";
	}

	@RequestMapping(value = "/add")
	public String showAddForm(Model model) {
		getRoles(model);

		model.addAttribute("user", new User());

		return "admin/user/edit";
	}

	@RequestMapping(value = "/edit")
	public String showEditForm(Model model, @RequestParam("username") String username) {
		getRoles(model);

		String url = "http://localhost:8080/api/user/username/" + username;

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<User> entity = new HttpEntity<User>(headers);

		User user = restTemplate.exchange(url, HttpMethod.GET, entity, User.class).getBody();

		model.addAttribute("user", user);

		return "admin/user/edit";
	}

	@RequestMapping(value = "/save")
	public String saveOrUpdate(Model model, @ModelAttribute("user") User user) {
		model.addAttribute("page", 1);
		
		String url = "http://localhost:8080/api/user";

		RestTemplate restTemplate = new RestTemplate();

		user.setFlagDelete(true);
		
		if (user.getId() == null) {
			url += "/insert";
			
			user.setCreateBy("admin");
			user.setModifiedBy("admin");
			
			if (restTemplate.postForObject(url, user, String.class) != null) {
				model.addAttribute("message", "message_user_add_success");
				model.addAttribute("alert", "success");
			} else {
				model.addAttribute("message", "message_user_add_fail");
				model.addAttribute("alert", "danger");
			}
		} else {
			url += "/update/id/" + user.getId();
			
			user.setModifiedBy("user");
			
			try {
				restTemplate.put(url, user);
				model.addAttribute("message", "message_user_edit_success");
				model.addAttribute("alert", "success");
			} catch (Exception e) {
				model.addAttribute("message", "message_user_edit_fail");
				model.addAttribute("alert", "danger");
			}
		}
		
		return "redirect:/admin/user/list";
	}
	
	@RequestMapping(value = "/delete")
	public String delete(Model model, @RequestParam("id") Long id) {
		model.addAttribute("page", 1);
		
		String url = "http://localhost:8080/api/user/delete/id/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			restTemplate.delete(url);
			model.addAttribute("message", "message_user_delete_success");
			model.addAttribute("alert", "success");
		} catch (Exception e) {
			model.addAttribute("message", "message_user_delete_fail");
			model.addAttribute("alert", "danger");
		}
		
		return "redirect:/admin/user/list";
	}

}
