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

import vn.com.abcblog.model.Tag;

@Controller
@RequestMapping(value = "/admin/tag")
public class AdminTagController {

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
		
		String postsURL = "http://localhost:8080/api/tag/list?page=" + page + "&limit=" + limit;
		String totalPagesURL = "http://localhost:8080/api/tag/total_pages?page=" + page + "&limit=" + limit;
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<List<Tag>> entity = new HttpEntity<List<Tag>>(headers);
		
		@SuppressWarnings("unchecked")
		List<Tag> tags = restTemplate.exchange(postsURL, HttpMethod.GET, entity, List.class).getBody();		
		
		request.setAttribute("page", page);
		request.setAttribute("limit", limit);
		request.setAttribute("totalPages", restTemplate.getForObject(totalPagesURL, Integer.class));
		request.setAttribute("tags", tags);
		
		return "admin/tag/list";
	}
	
	@RequestMapping(value = "/add")
	public String showAddForm(Model model) {
		model.addAttribute("tag", new Tag());
		
		return "admin/tag/edit";
	}
	
	@RequestMapping(value = "/edit")
	public String showEditForm(Model model, @RequestParam("id") Long id) {
		String url = "http://localhost:8080/api/tag/id/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Tag> entity = new HttpEntity<Tag>(headers);
		
		Tag tag = restTemplate.exchange(url, HttpMethod.GET, entity, Tag.class).getBody();
		
		model.addAttribute("tag", tag);
		
		return "admin/tag/edit";
	}
	
	@RequestMapping(value = "/save")
	public String saveOrUpdate(Model model, @ModelAttribute("tag") Tag tag) {
		model.addAttribute("page", 1);
		
		String url = "http://localhost:8080/api/tag";

		RestTemplate restTemplate = new RestTemplate();

		if (tag.getId() == null) {
			url += "/insert";
			
			tag.setCreateBy("admin");
			tag.setModifiedBy("admin");
			
			if (restTemplate.postForObject(url, tag, String.class) != null) {
				model.addAttribute("message", "message_tag_add_success");
				model.addAttribute("alert", "success");
			} else {
				model.addAttribute("message", "message_tag_add_fail");
				model.addAttribute("alert", "danger");
			}
		} else {
			url += "/update/id/" + tag.getId();
			
			tag.setModifiedBy("user");
			
			try {
				restTemplate.put(url, tag);
				model.addAttribute("message", "message_tag_edit_success");
				model.addAttribute("alert", "success");
			} catch (Exception e) {
				model.addAttribute("message", "message_tag_edit_fail");
				model.addAttribute("alert", "danger");
			}
		}
		
		return "redirect:/admin/tag/list";
	}
	

	@RequestMapping(value = "/delete")
	public String delete(Model model, @RequestParam("id") Long id) {
		model.addAttribute("page", 1);
		
		String url = "http://localhost:8080/api/tag/delete/id/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			restTemplate.delete(url);
			model.addAttribute("message", "message_tag_delete_success");
			model.addAttribute("alert", "success");
		} catch (Exception e) {
			model.addAttribute("message", "message_tag_delete_fail");
			model.addAttribute("alert", "danger");
		}
		
		return "redirect:/admin/tag/list";
	}
	
}
