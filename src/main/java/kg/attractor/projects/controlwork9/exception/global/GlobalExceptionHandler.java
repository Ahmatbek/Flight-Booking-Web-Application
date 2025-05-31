package kg.attractor.projects.controlwork9.exception.global;

import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.projects.controlwork9.model.ErrorResponseBody;
import kg.attractor.projects.controlwork9.service.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final ErrorService errorService;

    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException(Model model, HttpServletRequest request) {
        model.addAttribute("status", HttpStatus.NOT_FOUND.value());
        model.addAttribute("reason", HttpStatus.NOT_FOUND.getReasonPhrase());
        model.addAttribute("details", request);
        return "errors/error";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseBody> validationHandler(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(errorService.makeResponse(e.getBindingResult()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ErrorResponse emptyHandler(EmptyResultDataAccessException e) {
        return ErrorResponse.builder(e, HttpStatus.NOT_FOUND, e.getMessage()).build();
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Boolean b = auth.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"));
        Boolean company =  auth.getAuthorities().contains(new SimpleGrantedAuthority("COMPANY"));
        Boolean user = auth.getAuthorities().contains(new SimpleGrantedAuthority("USER"));
        if(Boolean.TRUE.equals(b)){
            model.addAttribute("authenticated",b);
        }
        if(Boolean.TRUE.equals(company)){
            model.addAttribute("company",company);
        }
        if(Boolean.TRUE.equals(user)){
            model.addAttribute("userAuth",user);
        }
    }



}
