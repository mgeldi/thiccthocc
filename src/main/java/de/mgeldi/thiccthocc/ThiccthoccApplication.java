package de.mgeldi.thiccthocc;

import de.mgeldi.thiccthocc.exceptions.UserAlreadyExistsException;
import de.mgeldi.thiccthocc.exceptions.UserNotFoundException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@ControllerAdvice
public class ThiccthoccApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThiccthoccApplication.class, args);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException exception) {
        System.out.println(exception.toString());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//	@Configuration
//	protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//			http
//					.httpBasic()
//					.and()
//					.authorizeRequests()
//					.antMatchers("/index.html", "/", "/home", "/login").permitAll()
//					.anyRequest().authenticated();
//		}
//	}
}
