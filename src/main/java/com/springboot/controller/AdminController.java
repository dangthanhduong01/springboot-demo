package com.springboot.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.dto.MovieDTO;
import com.springboot.dto.UserRegistrationDto;
import com.springboot.model.Category;
import com.springboot.model.Movie;
import com.springboot.model.Role;
import com.springboot.model.User;
import com.springboot.service.CategoryService;
import com.springboot.service.MovieService;
import com.springboot.service.RoleService;
import com.springboot.service.UserService;

@Controller
public class AdminController {

	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	ServletContext application;
	
	@GetMapping("/admin")
	public String viewHomePage(Model model) {
		model.addAttribute("listMovies", movieService.getAllMovies());
		return "index";
	}
	
	@GetMapping("/admin/showNewMovieForm")
	public String showNewMovieForm(Model model) {
		MovieDTO moviedto=new MovieDTO();
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("movieDTO",moviedto);
		return "new_movie";
	}
	
	@PostMapping("/admin/saveMovie")
	public String saveMovie(@ModelAttribute("movieDTO") MovieDTO movie,
			@RequestParam("id") long id,
			@RequestParam("file") MultipartFile file
//			@RequestParam(name = "name", required = true) String name,
//			@RequestParam(name = "des", required = true) String des,
//			@RequestParam(name="category")String cat
			){
		//save movie to db
		Movie m=movieService.getMovieById(id);
		if(m==null) {
			m=new Movie();
		}
		String n=movie.getName();
		String descr=movie.getDescription();
//		File fi=new File(movie.getImage());
//		MultipartFile f=(MultipartFile) fi;
//		Long cate=Long.parseLong(cat);
		String link=movie.getLinkfilm();
		Category c= categoryService.getCategoryById(movie.getCategoryid()).get();
		movieService.saveMovieToDB(m,file,n,descr,link,c);
		return "redirect:/";
	}
	
	@GetMapping("/admin/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value="id") long id, Model model) {
		//get movie from the service
		Movie movie=movieService.getMovieById(id);
		MovieDTO m = new MovieDTO();
		m.setId(id);
		m.setName(movie.getName());
		m.setDescription(movie.getDescription());
		m.setImage(movie.getImage().toString());
		m.setLinkfilm(movie.getLinkFilm().toString());
		m.setCategoryid(movie.getCategory().getId());
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("movieDTO",m);
		return "update_movie";
	}
	
	@GetMapping("/admin/deleteMovie/{id}")
	public String deleteMovie(@PathVariable (value="id") long id) {
		this.movieService.deleteMovieById(id);
		return "redirect:/";	
	}
	
	@GetMapping("/admin/category")
	public String getCategory(Model model) {
		model.addAttribute("categories",categoryService.getAllCategory());
		return "category";
	}
	
	@GetMapping("/admin/category/add")
	public String getCatAdd(Model model) {
		model.addAttribute("category",new Category());
		return "categoryadd";
	}

    @PostMapping("/admin/category/add")
    public String postCatAdd(@ModelAttribute("category") Category category,
			@RequestParam(name = "name", required = true) String name){
		if(category == null) {
			category = new Category();
		}
		category.setName(name);
		category.setStatus(true);
        categoryService.updateCategory(category);
        return "redirect:/admin/category";
    }
    
    @GetMapping("/admin/category/delete/{id}")
    public String deleteCat(@PathVariable int id){
        categoryService.removeCategoryById(id);
        return "redirect:/admin/category";
    }//delete 1 category

    @GetMapping("/admin/category/update/{id}")
    public String updateCat(@PathVariable int id, Model model){
        Optional<Category> category = categoryService.getCategoryById(id);
        if(category.isPresent()){
            model.addAttribute("category", category.get());
            return "category_update";
        }else {
            return "403";
        }
    }
    
    @Autowired
    UserService userService;
    
    @Autowired(required=false)
    RoleService roleService;
    
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
    //Accounts
    @GetMapping("/admin/users")
    public String getAcc(Model model){
        model.addAttribute("users", userService.getAllUser());
        //model.addAttribute("roles", roleService.getAllRole());
        return "users";
    }
    @GetMapping("/admin/users/add")
    public String getUserAdd(Model model){
        model.addAttribute("userDTO", new UserRegistrationDto());
        model.addAttribute("roles",roleService.getAllRole());
        return "usersAdd";
    }
    @PostMapping("/admin/users/add")
    public String postUserAdd(@ModelAttribute("userDTO") UserRegistrationDto userDTO) {
        //convert dto > entity
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setUsername(userDTO.getUsername());
        Collection<Role> roles = new ArrayList<>();
        for (Integer item: userDTO.getRoleid()) {
            roles.add(roleService.findRoleById(item).get());
        }
        user.setRoles(roles);

        userService.updateUser(user);
        return "redirect:/admin/users";
    }
    
    @GetMapping("/admin/users/delete/{id}")
    public String deleteUser(@PathVariable int id){
        userService.removeUserById(id);
        return "redirect:/admin/users";
    }//delete 1 user

    @GetMapping("/admin/users/update/{id}")
    public String updateUser(@PathVariable int id, Model model){
        Optional<User> opUser = userService.getUserById(id);
        if (opUser.isPresent()){
            User user = opUser.get();
            //convert entity > dto
            UserRegistrationDto userDTO = new UserRegistrationDto();
            userDTO.setId(user.getId());
            userDTO.setEmail(user.getEmail());
            userDTO.setPassword("");
            userDTO.setUsername(user.getUsername());
            List<Integer> roleIds = new ArrayList<>();
            for (Role item:user.getRoles()) {
                roleIds.add(item.getId());
            }

            model.addAttribute("userDTO", userDTO);
            model.addAttribute("roles", roleService.getAllRole());
            return "usersAdd";
        }else {
            return "403";
        }

    }   
    
	
}
