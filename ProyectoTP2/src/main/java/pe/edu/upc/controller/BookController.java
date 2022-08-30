package pe.edu.upc.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

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
import pe.edu.upc.serviceimpl.JpaUserDetailsService;
import pe.edu.upc.serviceinterface.IAccountService;
import pe.edu.upc.serviceinterface.IAuthorService;
import pe.edu.upc.serviceinterface.IBookService;
import pe.edu.upc.serviceinterface.IUploadFileService;

@Secured("ROLE_ADMINGLOBAL")
@Controller
@RequestMapping("/books")
public class BookController {
	@Autowired
	private IBookService cS;
	@Autowired
	private IAuthorService aU;
	@Autowired
	private IUploadFileService uploadFileService;
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
	public String newBook(Model model) {
		/*
		 * model.addAttribute("book", new Book()); author = new Author();
		 * model.addAttribute("author", author); // CAMBIO HECHO List<Author> authors =
		 * aU.list(); model.addAttribute("authors", authors);
		 */
		model.addAttribute("author", new Author());
		model.addAttribute("listAuthors", aU.list());
		model.addAttribute("book", new Book());
		model.addAttribute("listBooks", cS.list());
		return "book/book";
	}
	
	@PostMapping("/save")
	public String saveBook(@Validated Book book, BindingResult result, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			/*
			 * List<Author> authors = aU.list(); model.addAttribute("authors", authors);
			 */
			model.addAttribute("listAuthors", aU.list());
			return "book/book";
		} else {
			if (!foto.isEmpty()) {

				if (book.getIdBook() > 0 && book.getFoto() != null && book.getFoto().length() > 0) {

					uploadFileService.delete(book.getFoto());
				}

				String uniqueFilename = null;
				try {
					uniqueFilename = uploadFileService.copy(foto);
				} catch (IOException e) {
					e.printStackTrace();
				}

				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				book.setFoto(uniqueFilename);
			}
			cS.insert(book);
			/*model.addAttribute("mensaje", "Libro se registro correctamente");
			model.addAttribute("book", new Book());
			model.addAttribute("listAuthors", aU.list());*/
			model.addAttribute("listBooks", cS.list());
			status.setComplete();
			return "book/listBooks";
		}//aca puede faltar algo
		//return "book/book";
		
	}
	@Secured({"ROLE_ADMINGLOBAL","ROLE_USUARIORED"})
	@GetMapping("/list")
	public String listBooks(Model model) {
		Authentication auth = SecurityContextHolder
	            .getContext()
	            .getAuthentication();
	    UserDetails  userDetail = (UserDetails) auth.getPrincipal();
	    cuenta = this.usuarioService.getAccount(userDetail.getUsername());
	    //System.out.println(cuenta);
	   // System.out.println(cuenta.getCorreoAccount());
	    model.addAttribute("cuenta","Bienvenido "+ cuenta.getNameAccount());
		try {
			model.addAttribute("book", new Book());// Por el buscar
			model.addAttribute("listBooks", cS.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
			
		return "book/listBooks";
	}

	@RequestMapping("/delete/{id}")
	public String deleteBook(Model model, @PathVariable(value = "id") int id) {
		try {
			model.addAttribute("author", new Author());
			model.addAttribute("book", new Book());
			if (id > 0) {
				cS.delete(id);
			}
			model.addAttribute("mensaje", "Se eliminó correctamente");
		} catch (Exception e) {
			model.addAttribute("mensaje2", "Ocurrió un error, no se pudo eliminar");
		}
		model.addAttribute("listBooks", cS.list());
		model.addAttribute("listAuthors", aU.list());
		return "book/listBooks";// Mod pq con el buscar no funcaba
	}

	@RequestMapping("/irupdate/{id}")
	public String irupdate(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Book> objBook = cS.searchId(id);
		if (objBook == null) {
			objRedir.addFlashAttribute("mensaje2", "Ocurrió un error");
			return "redirect:/books/list";
		} else {
			model.addAttribute("listAuthors", aU.list());
			model.addAttribute("listBooks", cS.list());
			/* model.addAttribute("authors",aU.list()); */
			model.addAttribute("book", objBook.get());
			return "book/book";
		}
	}
	@Secured({"ROLE_ADMINGLOBAL","ROLE_USUARIORED"})
	@RequestMapping("/search")
	public String searchBooks(Model model, @Validated Book book) throws Exception {
		List<Book> listBooks;
		listBooks = cS.findNameBookFull(book.getNameBook());
		if (listBooks.isEmpty()) {
			model.addAttribute("mensaje2", "No hay registros para su búsqueda");
		}
		model.addAttribute("listBooks", listBooks);
		return "book/listBooks";
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

		Optional<Book> book = cS.searchId(id);
		if (book == null) {
			flash.addFlashAttribute("error", "El Libro no existe en la base de datos");
			return "redirect:/books/list";
		}

		model.addAttribute("book", book.get());

		return "book/view";
	}
	
	@RequestMapping("/reporte3")
	public String categoryTop(Map<String, Object> model) {
		model.put("listBooksTop", cS.booktop());
		return "reports/bookTop";
	}

}
