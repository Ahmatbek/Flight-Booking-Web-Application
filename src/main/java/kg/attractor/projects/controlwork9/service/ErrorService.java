package kg.attractor.projects.controlwork9.service;

import kg.attractor.projects.controlwork9.model.ErrorResponseBody;
import org.springframework.validation.BindingResult;

public interface ErrorService {
   ErrorResponseBody makeResponse(Exception e);
   ErrorResponseBody makeResponse(BindingResult result);

}
