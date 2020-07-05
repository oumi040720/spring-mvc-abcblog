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

import vn.com.abcblog.model.Comment;

@Controller
@RequestMapping(value = "/admin/comment")
public class AdminCommentController {

	@RequestMapping(value = "/list")
	public String showList(HttpServletRequest request)  {
		String message = request.getParameter("message");
		String alert = request.getParameter("alert");

		String page = request.getParameter("page");
		String limit = "2";
		
		if (message != null && alert != null) {
			request.setAttribute("message", message.replaceAll("_", "."));
			request.setAttribute("alert", alert);
		}
		
		String postsURL = "http://localhost:8080/api/comment/list?page=" + page + "&limit=" + limit;
		String totalPagesURL = "http://localhost:8080/api/comment/total_pages?page=" + page + "&limit=" + limit;
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<List<Comment>> entity = new HttpEntity<List<Comment>>(headers);
		
		@SuppressWarnings("unchecked")
		List<Comment> comments = restTemplate.exchange(postsURL, HttpMethod.GET, entity, List.class).getBody();		
		
		request.setAttribute("page", page);
		request.setAttribute("limit", limit);
		request.setAttribute("totalPages", restTemplate.getForObject(totalPagesURL, Integer.class));
		request.setAttribute("comments", comments);
		
		return "admin/comment/list";
	}	
	
	@RequestMapping(value = "/add")
	public String showAddForm(Model model) {
		model.addAttribute("comment", new Comment());
		
		return "admin/comment/edit";
	}
	
	@RequestMapping(value = "/edit")
	public String showEditForm(Model model, @RequestParam("id") String id) {
		String url = "http://localhost:8080/api/comment/id/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Comment> entity = new HttpEntity<Comment>(headers);
		
		Comment comment = restTemplate.exchange(url, HttpMethod.GET, entity, Comment.class).getBody();
		
		model.addAttribute("comment", comment);
		
		return "admin/comment/edit";
	}
	
	@RequestMapping(value = "/save")
	public String saveOrUpdate(Model model, @ModelAttribute("comment") Comment comment) {
		model.addAttribute("page", 1);
		
		String url = "http://localhost:8080/api/comment";

		RestTemplate restTemplate = new RestTemplate();

		comment.setFlagDelete(true);
		
		if (comment.getId() == null) {
			url += "/insert";
			
			comment.setCreateBy("admin");
			comment.setModifiedBy("admin");
			
			if (restTemplate.postForObject(url, comment, String.class) != null) {
				model.addAttribute("message", "message_comment_add_success");
				model.addAttribute("alert", "success");
			} else {
				model.addAttribute("message", "message_comment_add_fail");
				model.addAttribute("alert", "danger");
			}
		} else {
			url += "/update/id/" + comment.getId();
			
			comment.setModifiedBy("user");
			
			try {
				restTemplate.put(url, comment);
				model.addAttribute("message", "message_comment_edit_success");
				model.addAttribute("alert", "success");
			} catch (Exception e) {
				model.addAttribute("message", "message_comment_edit_fail");
				model.addAttribute("alert", "danger");
			}
		}
		
		return "redirect:/admin/comment/list";
	}
	
	@RequestMapping(value = "/delete")
	public String delete(Model model, @RequestParam("id") Long id) {
		model.addAttribute("page", 1);
		
		String url = "http://localhost:8080/api/comment/delete/id/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			restTemplate.delete(url);
			model.addAttribute("message", "message_comment_delete_success");
			model.addAttribute("alert", "success");
		} catch (Exception e) {
			model.addAttribute("message", "message_comment_delete_fail");
			model.addAttribute("alert", "danger");
		}
		
		return "redirect:/admin/comment/list";
	}
	
}
