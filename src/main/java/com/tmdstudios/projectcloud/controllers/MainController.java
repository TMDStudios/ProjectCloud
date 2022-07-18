package com.tmdstudios.projectcloud.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tmdstudios.projectcloud.models.Cloud;
import com.tmdstudios.projectcloud.models.Comment;
import com.tmdstudios.projectcloud.models.Language;
import com.tmdstudios.projectcloud.models.LoginUser;
import com.tmdstudios.projectcloud.models.Project;
import com.tmdstudios.projectcloud.models.Tag;
import com.tmdstudios.projectcloud.models.User;
import com.tmdstudios.projectcloud.services.CloudService;
import com.tmdstudios.projectcloud.services.CommentService;
import com.tmdstudios.projectcloud.services.LanguageService;
import com.tmdstudios.projectcloud.services.ProjectService;
import com.tmdstudios.projectcloud.services.TagService;
import com.tmdstudios.projectcloud.services.UserService;

@Controller
public class MainController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private LanguageService languageService;
	
	@Autowired
	private TagService tagService;
	
	@Autowired
	private CloudService cloudService;
	
	@GetMapping("/")
	public String index(Model model, HttpSession session) {
	    return "index.jsp";
	}
	
	@GetMapping("/login")
	public String authLogin(Model model) {
	    model.addAttribute("newUser", new User());
	    model.addAttribute("newLogin", new LoginUser());
	    return "login.jsp";
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
			BindingResult result, Model model, HttpSession session) {
	     
		User user = userService.login(newLogin, result);
	 
	    if(result.hasErrors() || user==null) {
	        model.addAttribute("newUser", new User());
	        return "login.jsp";
	    }
	     
	    session.setAttribute("userId", user.getId());
	 
	    return "redirect:/home";
	}
	
	@GetMapping("/register")
	public String authRegister(Model model) {
	    model.addAttribute("newUser", new User());
	    model.addAttribute("newLogin", new LoginUser());
	    return "register.jsp";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, 
			BindingResult result, Model model, HttpSession session) {

	    User user = userService.register(newUser, result);
	     
	    if(result.hasErrors()) {
	        model.addAttribute("newLogin", new LoginUser());
	        return "register.jsp";
	    }

	    session.setAttribute("userId", user.getId());
	 
	    return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.setAttribute("userId", null); 
	    return "redirect:/";
	}
	
	@RequestMapping(value="/home", method = { RequestMethod.GET, RequestMethod.POST })
	public String home(HttpSession session, Model model) {
		model.addAttribute("cloud", tagService.allTags());
		return "home.jsp";
	}
	
	@GetMapping("/projects")
	public String projects(HttpSession session, Model model) {
		model.addAttribute("projects", projectService.allProjects());
		return "projects.jsp";
	}
	
	@GetMapping("/projects/new")
	public String newProject(@ModelAttribute("project") Project project, HttpSession session, Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
//		if(session.getAttribute("userId") != null) {
//			Long userId = (Long) session.getAttribute("userId");		
//			User user = userService.findById(userId);
//			//model.addAttribute("myCoins", user.getCoins());
//		}
		List<Tag> tags = tagService.allTags();
		model.addAttribute("tags", tags);
		List<Language> languages = languageService.allLanguages();
		model.addAttribute("languages", languages);
		return "new_project.jsp";
	}
	
	@PostMapping("/projects/new")
	public String createProject(
			@Valid @ModelAttribute("project") Project project, 
			BindingResult result, 
			@RequestParam(value="listOfTags") String listOfTags,
			@RequestParam(value="listOfLanguages") String listOfLanguages,
			HttpSession session, 
			Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		List<Tag> tags = tagService.allTags();
		model.addAttribute("tags", tags);
		List<Language> languages = languageService.allLanguages();
		model.addAttribute("languages", languages);
		if(result.hasErrors()) {
			return "new_project.jsp";
		}else {
			List<Tag> projectTags = checkTags(listOfTags);
			List<Language> projectLanguages = checkLanguages(listOfLanguages);
			Long userId = (Long) session.getAttribute("userId");
			User user = userService.findById(userId);
			if(projectTags!=null&&projectLanguages!=null) {	
				Project newProject = new Project(project.getName(), project.getSummary(), project.getDescription(), user);
				newProject.setTags(projectTags);
				newProject.setLanguages(projectLanguages);
				projectService.addProject(newProject);
			}else {
				model.addAttribute("errorMsg", "You must include at least one tag and one language");
				return "new_project.jsp";
			}
			return "redirect:/home";
		}
	}
	
	private List<Tag> checkTags(String tagsString){
		String[] splitTags = tagsString.trim().split(",");
		ArrayList<Tag> tempTags = new ArrayList<>();
		
		for(String s: splitTags) {
			s = s.trim().toLowerCase();
			cloudService.addCloudWord(new Cloud(s));
			try {
				if(tagService.findByName(s)==null && s.length()>0) {
					tempTags.add(new Tag(s));
				}else {
					tempTags.add(tagService.findByName(s));
				}
			}catch(IncorrectResultSizeDataAccessException e) {
//				System.out.println("ISSUE: "+e);
			}
		}
		
		return tempTags;
	}
	
	private List<Language> checkLanguages(String languagesString){
		String[] splitLanguages = languagesString.trim().split(",");
		ArrayList<Language> tempLanguages = new ArrayList<>();
		
		for(String s: splitLanguages) {
			s = s.trim().toLowerCase();
			try {
				if(languageService.findByName(s)==null && s.length()>0) {
					tempLanguages.add(new Language(s));
				}else {
					tempLanguages.add(languageService.findByName(s));
				}
			}catch(IncorrectResultSizeDataAccessException e) {
//				System.out.println("ISSUE: "+e);
			}
		}
		
		return tempLanguages;
	}
	
	@GetMapping("/projects/{id}")
	public String viewProject(HttpSession session, Model model, @PathVariable("id") Long id, @ModelAttribute("comment") Comment comment) {
		model.addAttribute("project", projectService.findById(id));
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		return "project_details.jsp";
	}
	
	@GetMapping("/comments/{id}")
	public String comments(@PathVariable("id") Long id, Model model) {
		model.addAttribute("project", projectService.findById(id));
		return "comments.jsp";
	}
	
	@PostMapping("/project/{id}/comment")
	public String addComment(
			@PathVariable("id") Long id, 
			@Valid @ModelAttribute("comment") Comment comment,
			BindingResult result, 
			HttpSession session, 
			Model model,
			RedirectAttributes redirectAttributes) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		if(comment.getText().length()<5) {
			redirectAttributes.addFlashAttribute("error", "Your comment must be 5 characters or longer");
			return "redirect:/projects/"+id;
		}
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findById(userId);
		Comment newComment = new Comment(comment.getText(), comment.getProject());
		newComment.setUser(user);
		commentService.addComment(newComment);
		return "redirect:/projects/"+id;
	}
	
	@RequestMapping("/project/{id}/add")
	public String addToMyProjects(
			@PathVariable("id") Long id, 
			HttpSession session, 
			Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findById(userId);
		Project project = projectService.findById(id);
		if(project.getContributors().contains(user)) {
			project.getContributors().remove(user);
		}else {
			project.getContributors().add(user);		
		}
		projectService.updateProject(project);
		return "redirect:/projects/"+id;
	}
	
	@GetMapping("/projects/{id}/edit")
	public String editProject(@ModelAttribute("project") Project project, HttpSession session, Model model, @PathVariable("id") Long id) {
		model.addAttribute("project", projectService.findById(id));
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		return "edit_project.jsp";
	}
	
	@PostMapping("/projects/{id}/edit")
	public String updateProject(
			@Valid @ModelAttribute("project") Project project,
			BindingResult result,
			@RequestParam(value="listOfTags") String listOfTags,
			@RequestParam(value="listOfLanguages") String listOfLanguages,
			HttpSession session, 
			Model model, 
			@PathVariable("id") Long id) {
		Project currentProject = projectService.findById(id);
		model.addAttribute("project", currentProject);
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		List<Tag> tags = tagService.allTags();
		model.addAttribute("tags", tags);
		List<Language> languages = languageService.allLanguages();
		model.addAttribute("languages", languages);
		if(result.hasErrors()) {
			return "edit_project.jsp";
		}else {
			List<Tag> projectTags = checkTags(listOfTags);
			List<Language> projectLanguages = checkLanguages(listOfLanguages);
			project.setTags(projectTags);
			project.setLanguages(projectLanguages);
			project.setContributors(currentProject.getContributors());
			projectService.updateProject(project);
			return "redirect:/projects/"+id;
		}
	}
	
	@GetMapping("/my-projects")
	public String myProjects(HttpSession session, Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findById(userId);
		model.addAttribute("projects", projectService.findByContributor(user));
		return "my_projects.jsp";
	}

}
