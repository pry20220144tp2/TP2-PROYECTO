package pe.edu.upc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.entity.Role;
import pe.edu.upc.serviceinterface.IRoleService;

@Controller
@RequestMapping("/users/roles")
@Secured("ROLE_ADMINGLOBAL")
public class RoleController {
	@Autowired
	private IRoleService cS;
	
	@GetMapping("/new")
	public String newRole (Model model) {
		model.addAttribute("role", new Role());
		return "user/role";
	}
	
	@PostMapping("/save")
	public String saveRole (@Validated Role role, BindingResult result, Model model) throws Exception {
		if(result.hasErrors()) {
			return "user/role";
		}
		
		else {
			int rpta = cS.insert(role);
			if (rpta > 0) {
				model.addAttribute("mensaje2", "Ya existe");
				return "user/role";
			} else {
				model.addAttribute("listRoles", cS.list());
				model.addAttribute("mensaje", "El rol se registró correctamente");
				return "user/listRoles";
			}
		}
	}
	
	@GetMapping("/list")
	public String listRole(Model model) {
		try {
			model.addAttribute("listRoles", cS.list());
		}catch(Exception e) {
			model.addAttribute("error",e.getMessage());
		}
		return "user/listRoles";
	}
	
}
