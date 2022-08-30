package pe.edu.upc.controller;

import java.util.Date;
import java.util.Map;

import javax.validation.Valid;

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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Account;
import pe.edu.upc.entity.Exemplary;
import pe.edu.upc.entity.Loan;
import pe.edu.upc.entity.LoanDetails;
import pe.edu.upc.serviceinterface.ILoanService;
import pe.edu.upc.serviceinterface.IExemplaryService;
import pe.edu.upc.serviceinterface.IAccountService;

@Controller
@RequestMapping("/loans")
public class LoanController {
	
	@Autowired
	private ILoanService lS;
	
	@Autowired
	private IExemplaryService eS;
	
	@Autowired
	private IAccountService aS;

	/*GET USER DATA*/
	private Account cuenta;
	@Autowired
	private IAccountService usuarioService;
	/*
	
	public void init() {
	    Authentication auth = SecurityContextHolder
	            .getContext()
	            .getAuthentication();
	    UserDetails  userDetail = (UserDetails) auth.getPrincipal();
	    cuenta = this.usuarioService.getAccount(userDetail.getUsername());
	    System.out.println(cuenta);
	    System.out.println(cuenta.getCorreoAccount());
	}*/
	/**/
	
	@GetMapping("/new")
	public String newLoan(Model model) {
		model.addAttribute("loan",new Loan());
		
		return "loan/loan";
		
		
	}
	
	@Secured("ROLE_ADMINGLOBAL")
	@RequestMapping("/reports")
	public String Report()
	{
		return "reports/reports";
	}
	@PostMapping("/save")
	public String saveLoan(@Validated Loan loan, BindingResult result,SessionStatus status, Model model) throws Exception{
		Date requestday = new Date();
		if (result.hasErrors()) {

			model.addAttribute("mensaje","Prestamo no se registró correctamente");
			return "loan/loan";
		}else {
			Authentication auth = SecurityContextHolder
		            .getContext()
		            .getAuthentication();
		    UserDetails  userDetail = (UserDetails) auth.getPrincipal();
		    cuenta = this.usuarioService.getAccount(userDetail.getUsername());
		    loan.setAccount(cuenta);
			loan.setLoanDate(requestday);
			lS.insert(loan);
			model.addAttribute("mensaje","Prestamo se registró correctamente");
			return "redirect:/loans/list";
			
		}
		
		
	}
	
	
	@GetMapping("/list")
	public String listLoans(Model model) {
		   Authentication auth = SecurityContextHolder
		            .getContext()
		            .getAuthentication();
		    UserDetails  userDetail = (UserDetails) auth.getPrincipal();
		    cuenta = this.usuarioService.getAccount(userDetail.getUsername());
			//Loan imp = lS.listarIdUsuario(cuenta.getIdAccount());
		try {
			if(cuenta.getRoleAccount().getIdRole()==1)
			model.addAttribute("listLoans", lS.list());
			else {
				model.addAttribute("listLoans", lS.listarIdUsuario(cuenta.getIdAccount()));
			}
			
			
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "loan/listLoans";

	}
	
	//detalle del prestamo
	@RequestMapping("/newexemplary/{id}")
	public String irNewProduct(@PathVariable(value = "id") int id, Map<String, Object> model) {
		   Authentication auth = SecurityContextHolder
		            .getContext()
		            .getAuthentication();
		    UserDetails  userDetail = (UserDetails) auth.getPrincipal();
		    cuenta = this.usuarioService.getAccount(userDetail.getUsername());
		    
		model.put("detail", new LoanDetails());
		model.put("exemplaries", eS.list());
		model.put("accounts", cuenta);
		Loan objloa = lS.listarId(id);
		model.put("loan", objloa);

		return "loan/details/detailForm";
	}
	
	//boton de detalle
	@GetMapping("/detail/{id}")
	public String detailImportation(@PathVariable(value = "id") int id, Map<String, Object> model,
			RedirectAttributes flash) {
		Loan imp = lS.listarId(id);

		if (imp == null) {
			flash.addFlashAttribute("error", "El Detalle no existe en la base de datos");
			return "loan/listLoans"; 
		}
		model.put("loan", imp);
		model.put("titulo", "Detalle de prestamo #" + imp.getIdLoan());

		return "loan/details/listDetail"; 
	}
	
	//prestamo
	@PostMapping("/saveexemplary{id}")
	public String newProductXImportation(@PathVariable(value = "id") int id, @Valid LoanDetails loandet,
			RedirectAttributes flash, BindingResult result, Model model, SessionStatus status) {
		Loan imp = lS.listarId(id);
		if (result.hasErrors()) {
			flash.addFlashAttribute("error", "El valor debe ser positivo");
			String cadena1 = "redirect:/loan/newexemplary/" + id;
			return cadena1;
		}
		try {
			Loan lol = new Loan();
			lol = imp;
			lol.setIdLoan(id);
			loandet.setLoan(lol);
			imp.addDetailImportation(loandet);
			lS.insert(imp);
			status.isComplete();
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			System.out.println(e.getMessage());
		}
		String cadena = "redirect:/loans/detail/" + id;
		return cadena;
	}
}
