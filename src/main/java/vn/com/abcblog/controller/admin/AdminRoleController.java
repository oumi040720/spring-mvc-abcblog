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

@Controller
@RequestMapping(value = "/admin/role")
public class AdminRoleController {

	@RequestMapping(value = "/list")
	public String showList(HttpServletRequest request)  {
		String message = request.getParameter("message");
		String alert = request.getParameter("alert");
		
		if (message != null && alert != null) {
			request.setAttribute("message", message.replaceAll("_", "."));
			request.setAttribute("alert", alert);
		}
		
		String url = "http://localhost:8080/api/role";
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<List<Role>> entity = new HttpEntity<List<Role>>(headers);
		
		@SuppressWarnings("unchecked")
		List<Role> roles = restTemplate.exchange(url, HttpMethod.GET, entity, List.class).getBody();		
		
		request.setAttribute("roles", roles);
		
		return "admin/role/list";
	}
	
	@RequestMapping(value = "/add")
	public String showAddForm(Model model) {
		model.addAttribute("role", new Role());
		
		return "admin/role/edit";
	}
	
	@RequestMapping(value = "/edit")
	public String showEditForm(Model model, @RequestParam("role_code") String roleCode) {
		String url = "http://localhost:8080/api/role/role_code/" + roleCode;
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Role> entity = new HttpEntity<Role>(headers);
		
		Role role = restTemplate.exchange(url, HttpMethod.GET, entity, Role.class).getBody();
		
		model.addAttribute("role", role);
		
		return "admin/role/edit";
	}
	
	@RequestMapping(value = "/save")
	public String saveOrUpdate(Model model, @ModelAttribute("role") Role role) {
		String url = "http://localhost:8080/api/role";
		
		RestTemplate restTemplate = new RestTemplate();

		if (role.getId() == null) {
			url += "/insert";
			
			role.setCreateBy("admin");
			role.setModifiedBy("admin");
			
			if (restTemplate.postForObject(url, role, String.class) != null) {
				model.addAttribute("message", "message_role_add_success");
				model.addAttribute("alert", "success");
			} else {
				model.addAttribute("message", "message_role_add_fail");
				model.addAttribute("alert", "danger");
			}
		} else {
			url += "/update/id/" + role.getId();
			
			role.setModifiedBy("user");
			
			try {
				restTemplate.put(url, role);
				model.addAttribute("message", "message_role_edit_success");
				model.addAttribute("alert", "success");
			} catch (Exception e) {
				model.addAttribute("message", "message_role_edit_fail");
				model.addAttribute("alert", "danger");
			}
		}
		
		return "redirect:/admin/role/list";
	}

	@RequestMapping(value = "/delete")
	public String delete(Model model, @RequestParam("id") Long id) {
		String url = "http://localhost:8080/api/role/delete/id/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			restTemplate.delete(url);
			model.addAttribute("message", "message_role_delete_success");
			model.addAttribute("alert", "success");
		} catch (Exception e) {
			model.addAttribute("message", "message_role_delete_fail");
			model.addAttribute("alert", "danger");
		}
		
		return "redirect:/admin/role/list";
	}
	
}
