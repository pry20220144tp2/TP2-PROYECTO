package pe.edu.upc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Account;
import pe.edu.upc.entity.Enterprise;
import pe.edu.upc.serviceinterface.IAccountService;
import pe.edu.upc.serviceinterface.IEnterpriseService;

@Controller
@RequestMapping("/enterprises")
@Secured("ROLE_ADMINGLOBAL")
public class EnterpriseController {
	@Autowired
	private IEnterpriseService cS;
	/*GET USER DATA*/
	private Account cuenta;
	@Autowired
	private IAccountService usuarioService;

	@GetMapping("/new")
	public String newEnterprise(Model model) {
		model.addAttribute("enterprise", new Enterprise());
		return "enterprise/enterprise";
	}
	
	@PostMapping("/save")
	public String saveEnterprise (@Validated Enterprise enterprise, BindingResult result, Model model) throws Exception {
		if(result.hasErrors()) {
			//model.addAttribute("listEnterprises", cS.list());
			return "enterprise/enterprise";
		}
		
		else {
			int rpta = cS.insert(enterprise);
			if (rpta > 0) {
				model.addAttribute("mensaje2", "Ya existe");
				return "enterprise/enterprise";
			} else {
				model.addAttribute("listEnterprises", cS.list());
				model.addAttribute("mensaje", "La empresa se registró correctamente");
				return "enterprise/listEnterprises";
			}
		}
	}

	@GetMapping("/list")
	public String listEnterprises(Model model) {
		Authentication auth = SecurityContextHolder
	            .getContext()
	            .getAuthentication();
	    UserDetails  userDetail = (UserDetails) auth.getPrincipal();
	    cuenta = this.usuarioService.getAccount(userDetail.getUsername());
	    model.addAttribute("cuenta","Bienvenido "+ cuenta.getNameAccount());
		try {
			model.addAttribute("enterprise", new Enterprise());// Por el buscar
			model.addAttribute("listEnterprises", cS.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "enterprise/listEnterprises";
	}

	@RequestMapping("/delete/{id}")
	public String deleteEnterprise(Model model, @PathVariable(value = "id") int id) {
		try {
			model.addAttribute("enterprise", new Enterprise());
			if (id > 0) {
				cS.delete(id);
			}
			model.addAttribute("mensaje", "La empresa se eliminó correctamente");
		} catch (Exception e) {
			model.addAttribute("mensaje2", "Ocurrió un error, la empresa no puede ser eliminada");
		}
		model.addAttribute("listEnterprises", cS.list());
		return "enterprise/listEnterprises";
	}

	@RequestMapping("/irupdate/{id}")
	public String irupdate(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Enterprise> objAut = cS.searchId(id);
		if (objAut == null) {
			objRedir.addFlashAttribute("mensaje2", "Ocurrió un error");
			return "redirect:/enterprises/list";
		} else {
			model.addAttribute("listEnterprises", cS.list());
			model.addAttribute("enterprise", objAut.get());
			return "enterprise/enterprise";
		}
	}

	@RequestMapping("/search")
	public String searchEnterprises(Model model, @Validated Enterprise enterprise) throws Exception {
		List<Enterprise> listEnterprises;
		listEnterprises = cS.findNameEnterpriseFull(enterprise.getNameEnterprise());
		if (listEnterprises.isEmpty()) {
			model.addAttribute("mensaje2", "No hay registros para su búsqueda");
		}
		model.addAttribute("listEnterprises", listEnterprises);
		return "enterprise/listEnterprises";
	}
	
	@GetMapping(value = "/view/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes flash) {

		Optional<Enterprise> enterprise = cS.searchId(id);
		if (enterprise == null) {
			flash.addFlashAttribute("error", "La empresa no existe en la base de datos");
			return "redirect:/enterprises/list";
		}

		model.addAttribute("enterprise", enterprise.get());

		return "enterprise/view";
	}
	
}
