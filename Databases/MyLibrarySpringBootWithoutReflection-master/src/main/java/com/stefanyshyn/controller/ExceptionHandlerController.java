package com.stefanyshyn.controller;

import com.stefanyshyn.DTO.MessageDTO;
import com.stefanyshyn.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchShopException.class)
    ResponseEntity<MessageDTO> handleNoSushShopException(){
        return new ResponseEntity<MessageDTO>(new MessageDTO("Such Shop not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchProductException.class)
    ResponseEntity<MessageDTO> handleNoSushProductException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Such product not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchCustomerException.class)
    ResponseEntity<MessageDTO> handleNoSushCustomerException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Such customer not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExistsProductsForShopException.class)
    ResponseEntity<MessageDTO> handleExistsProductsForShopException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Delete imposible. There are products for this Shop"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ExistsCustomersForProductException.class)
    ResponseEntity<MessageDTO> handleExistsCustomersForProductException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Delete imposible. There are customers for this product"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ExistsProductForCustomerException.class)
    ResponseEntity<MessageDTO> handleExistsProductsForCustomerException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Delete imposible. There are products for this customer"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AlreadyExistsCustomerInProductException.class)
    ResponseEntity<MessageDTO> handleAlreadyExistsCustomerInProductExceptionException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Add imposible. The product already contain this customer"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CustomerAbsentException.class)
    ResponseEntity<MessageDTO> handleCustomerAbsentException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Now this customer is absent"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductHasNotCustomerException.class)
    ResponseEntity<MessageDTO> handleProductHasNotCustomerException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("The product hasn't this customer"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchLogException.class)
    ResponseEntity<MessageDTO> handleNoSuchLogException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Such log not found"), HttpStatus.NOT_FOUND);
    }

}
