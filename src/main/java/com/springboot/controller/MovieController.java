package com.springboot.controller;


import java.security.Principal;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.dto.HistoryDTO;
import com.springboot.dto.MovieDTO;
import com.springboot.dto.UserRegistrationDto;
import com.springboot.global.Hg;
import com.springboot.model.Category;
import com.springboot.model.CustomUserDetails;
import com.springboot.model.History;
import com.springboot.model.Movie;
import com.springboot.model.User;
import com.springboot.repository.CategoryRepository;
import com.springboot.service.CategoryService;
import com.springboot.service.HistoryService;
import com.springboot.service.MovieService;
import com.springboot.service.UserService;

@Controller
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private CategoryService categoryService;
	
	
	@Autowired
	private HistoryService historyService;
	
	
	
	
	@Autowired
	ServletContext application;

	
	@GetMapping({"/","/home"})
	public String homePage(Model model) {
		long id=movieService.getAllMovies().size();
		Movie m=movieService.findByMId(id);
		model.addAttribute("m", m);
		model.addAttribute("listMovies", movieService.getAllMovies());
		model.addAttribute("categories",categoryService.getAllCategory());
		return "home";
	}
	
	@GetMapping("/genre/{n}-{i}")
	public String catePage(@PathVariable(value="i") long id,Model model) {
		List<Movie> m=movieService.getAllMovieByCategory_id(id);
		Category c=categoryService.getCategoryById(id).get();
		model.addAttribute("cate",c);
		model.addAttribute("listMovies",m);
		return "searchByCate";
	}
	
	@GetMapping({"/home/watch/{n}-{id}"})
	public String detailPage(@PathVariable(value="id") long id, Model model) {
		Movie movie=movieService.getMovieById(id);
		Long cid=movie.getCategory().getId();
		List<Movie> listM=movieService.getAllMovieByCategory_id(cid);
		model.addAttribute("movie",movie);
		model.addAttribute("listM",listM);
		return "detail";
	}
	
	@GetMapping({"/home/watch-tv/{n}-{id}"})
	public String watchPage(@PathVariable(value="id") long id, Model model) {
		Movie movie=movieService.getMovieById(id);
		Long cid=movie.getCategory().getId();
		List<Movie> listM=movieService.getAllMovieByCategory_id(cid);
		HistoryDTO h= new HistoryDTO();
//		MovieDTO m= new MovieDTO(movie.getId(),movie.getName(),movie.getDescription(),movie.getImage(),movie.getLinkFilm(),movie.getCategory().getId());
//		String username=principal.getName();
//		System.err.print("user: "+username);
//		User logineduser= (User) ((Authentication) principal).getPrincipal();
//		History his= historyService.getHistoryByUser_id(logineduser.getId());
//		movie.setHistory(his);
		
		h.addtoH(movie);
		Hg.lmovie.add(movie);
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails && ((UserDetails) principal).getUsername() != null) {
			String currentUsername = ((UserDetails)principal).getUsername();
			System.err.println("User: "+currentUsername);
			int id_u=((CustomUserDetails)principal).getUser().getId();
			System.err.println("ID: "+id_u);
			History his;
			his=historyService.getHistoryByUser_id(id_u);
			if(his==null) {
				his=new History();
				his.setUser(((CustomUserDetails)principal).getUser());
			}
			System.out.println(his);
			movie.setHistory(his);
			movieService.saveMovie(movie);
//			his.setMovie(Hg.lmovie);
//			model.addAttribute("listMovies",mh);
		}		
		model.addAttribute("movie",movie);
		model.addAttribute("listM",listM);
		
		return "watch";
	}
	
	@GetMapping("/history")
	public String historyPage(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails && ((UserDetails) principal).getUsername() != null) {
			String currentUsername = ((UserDetails)principal).getUsername();
			System.err.println("User: "+currentUsername);
			int id=((CustomUserDetails)principal).getUser().getId();
			System.err.println("ID: "+id);
			History his= new History(); 
				his=historyService.getHistoryByUser_id(id);
				List<Movie> mh  = his.getMovie();
			model.addAttribute("listMovies",mh);
		}
		return "history";
	}
	
	@RequestMapping("/search")
	public String searchPage( @Param("keyword") String keyword,Model model) {
		List<Movie> movies=movieService.findMovieByNameLike(keyword);
		model.addAttribute("listMovies", movies);
		model.addAttribute("key",keyword);
		return "search";
	}	
	
	@GetMapping("/login")
	public String loginPage(Model model,String error,String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";		
	}
	@GetMapping("/403")
	public String registerPage(Model model) {
		return "403";
	}	
	
	
}
