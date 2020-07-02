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

import vn.com.abcblog.model.Category;
import vn.com.abcblog.model.Role;

@Controller
@RequestMapping(value = "/admin/category")
public class AdminCategoryController {

	@RequestMapping(value = "/list")
	public String showList(HttpServletRequest request)  {
		String message = request.getParameter("message");
		String alert = request.getParameter("alert");

		String page = request.getParameter("page");
		String limit = "10";
		
		if (message != null && alert != null) {
			request.setAttribute("message", message.replaceAll("_", "."));
			request.setAttribute("alert", alert);
		}
		
		String categoriesURL = "http://localhost:8080/api/category/list?page=" + page + "&limit=" + limit;
		String totalPagesURL = "http://localhost:8080/api/category/total_pages?page=" + page + "&limit=" + limit;
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<List<Category>> entity = new HttpEntity<List<Category>>(headers);
		
		@SuppressWarnings("unchecked")
		List<Category> categories = restTemplate.exchange(categoriesURL, HttpMethod.GET, entity, List.class).getBody();		
		
		request.setAttribute("page", page);
		request.setAttribute("limit", limit);
		request.setAttribute("totalPages", restTemplate.getForObject(totalPagesURL, Integer.class));
		request.setAttribute("categories", categories);
		
		return "admin/category/list";
	}
	
	@RequestMapping(value = "/add")
	public String showAddForm(Model model) {
		model.addAttribute("category", new Category());
		
		return "admin/category/edit";
	}
	
	@RequestMapping(value = "/edit")
	public String showEditForm(Model model, @RequestParam("category_code") String categoryCode) {
		String url = "http://localhost:8080/api/category/category_code/" + categoryCode;
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Role> entity = new HttpEntity<Role>(headers);
		
		Category category = restTemplate.exchange(url, HttpMethod.GET, entity, Category.class).getBody();
		
		model.addAttribute("category", category);
		
		return "admin/category/edit";
	}
	
	@RequestMapping(value = "/save")
	public String saveOrUpdate(Model model, @ModelAttribute("category") Category category) {
		model.addAttribute("page", "1");
		
		String url = "http://localhost:8080/api/category";
		
		RestTemplate restTemplate = new RestTemplate();

		if (category.getId() == null) {
			url += "/insert";
			
			category.setCreateBy("admin");
			category.setModifiedBy("admin");
			
			if (restTemplate.postForObject(url, category, String.class) != null) {
				model.addAttribute("message", "message_category_add_success");
				model.addAttribute("alert", "success");
			} else {
				model.addAttribute("message", "message_category_add_fail");
				model.addAttribute("alert", "danger");
			}
		} else {
			url += "/update/id/" + category.getId();
			
			category.setModifiedBy("user");
			
			try {
				restTemplate.put(url, category);
				model.addAttribute("message", "message_category_edit_success");
				model.addAttribute("alert", "success");
			} catch (Exception e) {
				model.addAttribute("message", "message_category_edit_fail");
				model.addAttribute("alert", "danger");
			}
		}
		
		return "redirect:/admin/category/list";
	}
	
	@RequestMapping(value = "/delete")
	public String delete(Model model, @RequestParam("id") Long id) {
		model.addAttribute("page", "1");
		
		String url = "http://localhost:8080/api/category/delete/id/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			restTemplate.delete(url);
			model.addAttribute("message", "message_category_delete_success");
			model.addAttribute("alert", "success");
		} catch (Exception e) {
			model.addAttribute("message", "message_category_delete_fail");
			model.addAttribute("alert", "danger");
		}
		
		return "redirect:/admin/category/list";
	}
	
}
