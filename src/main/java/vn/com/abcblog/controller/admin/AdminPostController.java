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

import vn.com.abcblog.model.Post;

@Controller
@RequestMapping(value = "/admin/post")
public class AdminPostController {

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
		
		String postsURL = "http://localhost:8080/api/post/list?page=" + page + "&limit=" + limit;
		String totalPagesURL = "http://localhost:8080/api/post/total_pages?page=" + page + "&limit=" + limit;
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<List<Post>> entity = new HttpEntity<List<Post>>(headers);
		
		@SuppressWarnings("unchecked")
		List<Post> posts = restTemplate.exchange(postsURL, HttpMethod.GET, entity, List.class).getBody();		
		
		request.setAttribute("page", page);
		request.setAttribute("limit", limit);
		request.setAttribute("totalPages", restTemplate.getForObject(totalPagesURL, Integer.class));
		request.setAttribute("posts", posts);
		
		return "admin/post/list";
	}
	
	@RequestMapping(value = "/add")
	public String showAddForm(Model model) {
		model.addAttribute("post", new Post());
		
		return "admin/post/edit";
	}
	
	@RequestMapping(value = "/edit")
	public String showEditForm(Model model, @RequestParam("post_code") String postCode) {
		String url = "http://localhost:8080/api/post/post_code/" + postCode;
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Post> entity = new HttpEntity<Post>(headers);
		
		Post post = restTemplate.exchange(url, HttpMethod.GET, entity, Post.class).getBody();
		
		model.addAttribute("post", post);
		
		return "admin/post/edit";
	}

	@RequestMapping(value = "/save")
	public String saveOrUpdate(Model model, @ModelAttribute("post") Post post) {
		model.addAttribute("page", "1");
		
		String url = "http://localhost:8080/api/post";
		
		RestTemplate restTemplate = new RestTemplate();

		post.setFlagDelete(true);
		
		if (post.getId() == null) {
			url += "/insert";
			
			post.setCreateBy("admin");
			post.setModifiedBy("admin");
			
			if (restTemplate.postForObject(url, post, String.class) != null) {
				model.addAttribute("message", "message_post_add_success");
				model.addAttribute("alert", "success");
			} else {
				model.addAttribute("message", "message_post_add_fail");
				model.addAttribute("alert", "danger");
			}
		} else {
			url += "/update/id/" + post.getId();
			
			post.setModifiedBy("user");
			
			try {
				restTemplate.put(url, post);
				model.addAttribute("message", "message_post_edit_success");
				model.addAttribute("alert", "success");
			} catch (Exception e) {
				model.addAttribute("message", "message_post_edit_fail");
				model.addAttribute("alert", "danger");
			}
		}
		
		return "redirect:/admin/post/list";
	}

	@RequestMapping(value = "/delete")
	public String delete(Model model, @RequestParam("id") Long id) {
		model.addAttribute("page", "1");
		
		String url = "http://localhost:8080/api/post/delete/id/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			restTemplate.delete(url);
			model.addAttribute("message", "message_post_delete_success");
			model.addAttribute("alert", "success");
		} catch (Exception e) {
			model.addAttribute("message", "message_post_delete_fail");
			model.addAttribute("alert", "danger");
		}
		
		return "redirect:/admin/post/list";
	}
	
}
