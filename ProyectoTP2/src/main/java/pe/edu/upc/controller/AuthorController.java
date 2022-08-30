package pe.edu.upc.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Account;
import pe.edu.upc.entity.Author;
import pe.edu.upc.entity.Book;
import pe.edu.upc.serviceinterface.IAccountService;
import pe.edu.upc.serviceinterface.IAuthorService;
import pe.edu.upc.serviceinterface.IUploadFileService;

@Controller
@RequestMapping("/authors")
@Secured("ROLE_ADMINGLOBAL")
public class AuthorController {
	@Autowired
	private IAuthorService cS;
	@Autowired
	private IUploadFileService uploadFileService;
	/*GET USER DATA*/
	private Account cuenta;
	@Autowired
	private IAccountService usuarioService;

	@GetMapping("/new")
	public String newAuthor(Model model) {
		model.addAttribute("author", new Author());
		return "author/author";
	}
	
	@PostMapping("/save")
	public String saveAuthor(@Validated Author author, BindingResult result, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listAuthors", cS.list());
			return "author/author";
		} else {
			if (!foto.isEmpty()) {

				if (author.getIdAuthor() > 0 && author.getFoto() != null && author.getFoto().length() > 0) {

					uploadFileService.delete(author.getFoto());
				}
				String uniqueFilename = null;
				try {
					uniqueFilename = uploadFileService.copy(foto);
				} catch (IOException e) {
					e.printStackTrace();
				}
				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				author.setFoto(uniqueFilename);
			}
			int rpta = cS.insert(author);
			if (rpta > 0) {
				model.addAttribute("mensaje2", "El autor ya existe");
				return "author/author";
			} else {
				cS.insert(author);
				model.addAttribute("mensaje", "El autor se registró correctamente");
				model.addAttribute("author", new Author());
				model.addAttribute("listAuthors", cS.list());
				//status.setComplete();
				return "author/listAuthors";
			}
		}
	}

	@GetMapping("/list")
	public String listAuthors(Model model) {
		Authentication auth = SecurityContextHolder
	            .getContext()
	            .getAuthentication();
	    UserDetails  userDetail = (UserDetails) auth.getPrincipal();
	    cuenta = this.usuarioService.getAccount(userDetail.getUsername());
	    model.addAttribute("cuenta","Bienvenido "+ cuenta.getNameAccount());
		try {
			model.addAttribute("author", new Author());// Por el buscar
			model.addAttribute("listAuthors", cS.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "author/listAuthors";
	}

	@RequestMapping("/delete/{id}")
	public String deleteAuthor(Model model, @PathVariable(value = "id") int id) {
		try {
			model.addAttribute("author", new Author());
			if (id > 0) {
				cS.delete(id);
			}
			model.addAttribute("mensaje", "El autor se eliminó correctamente");
		} catch (Exception e) {
			model.addAttribute("mensaje2", "Ocurrió un error, el autor no puede ser eliminado");
		}
		model.addAttribute("listAuthors", cS.list());
		return "author/listAuthors";// Mod pq con el buscar no funcaba
	}

	@RequestMapping("/irupdate/{id}")
	public String irupdate(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Author> objAut = cS.searchId(id);
		if (objAut == null) {
			objRedir.addFlashAttribute("mensaje2", "Ocurrió un error");
			return "redirect:/authors/list";
		} else {
			model.addAttribute("listAuthors", cS.list());// OJO A LO QUE DICE LA PROFESORA
			model.addAttribute("author", objAut.get());
			return "author/author";
		}
	}

	@RequestMapping("/search")
	public String searchAuthors(Model model, @Validated Author author) throws Exception {
		List<Author> listAuthors;
		listAuthors = cS.findNameAuthorFull(author.getNameAuthor());
		if (listAuthors.isEmpty()) {
			model.addAttribute("mensaje2", "No hay registros para su búsqueda");
		}
		model.addAttribute("listAuthors", listAuthors);
		return "author/listAuthors";
	}
	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

		Resource recurso = null;

		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}
	@GetMapping(value = "/view/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes flash) {

		Optional<Author> author = cS.searchId(id);
		if (author == null) {
			flash.addFlashAttribute("error", "El Autor no existe en la base de datos");
			return "redirect:/authors/list";
		}

		model.addAttribute("author", author.get());

		return "author/view";
	}
	
	
	@RequestMapping("/reporte2")
	public String categoryTop(Map<String, Object> model) {
		model.put("listAuthorsTop", cS.authortop());
		return "reports/authorTop";
	}
	
	@RequestMapping("/reporte3")
	public String authorbookTop(Map<String, Object> model) {
		model.put("listAuthorsTop2", cS.authorbooktop());
		return "reports/authorTop2";
	}
}
