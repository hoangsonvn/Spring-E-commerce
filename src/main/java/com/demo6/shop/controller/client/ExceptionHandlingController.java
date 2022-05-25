package com.demo6.shop.controller.client;

import com.demo6.shop.controller.admin.PermissionController;
import com.demo6.shop.dto.ErrorMessage;
import org.hibernate.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.NoResultException;

@ControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(PermissionController.class);

  /*  @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleAllException(Exception ex, WebRequest request) {
                 //do something
        return new ErrorMessage(10000, ex.getLocalizedMessage());
    }*/
  /*  @ExceptionHandler(NoResultException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handNoResultException(Exception ex, WebRequest request) {
        //do something
        return new ErrorMessage(10000, ex.getLocalizedMessage());
    }*/

   /* @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleEntityException() {
        logger.error("khong tim thay product");
        return new ErrorMessage(500, "product not found");

    }*/

}
