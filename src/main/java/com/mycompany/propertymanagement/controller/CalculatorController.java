package com.mycompany.propertymanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.propertymanagement.dto.CalculatorDTO;

@RestController
@RequestMapping("/api/v1/calculator") // Class level mapping of url to a controller class
public class CalculatorController {
    
    @GetMapping("/add") // Method level mapping of a url to a controller function
    // http://localhost:8080/api/v1/calculator/add without Parameters
    // http://localhost:8080/api/v1/calculator/add?num111=6.7&num222=1.3 with Parameters
    public Double add(@RequestParam("num111") Double num1, @RequestParam("num222") Double num2) {
        // num1 = 1.0;
        // num2 = 2.0;
        return num1 + num2;
    }

    // This techniques can be merge

    @GetMapping("/sub/{num111}/{num222}") // Map the values of url to Java variables by Path Variable method
    // http://localhost:8080/api/v1/calculator/sub/4.8/4.0 with Path Variables
    public Double substract(@PathVariable("num111") Double num1, @PathVariable("num222") Double num2) {
        Double result = null;
        if (num1 > num2) {
            result = num1 - num2;
        } else {
            result = num2 - num1;
        }
        return result;
    }

    @PostMapping("/multiply")
    public ResponseEntity<Double> multiply(@RequestBody CalculatorDTO calculatorDTO) {
        Double result = null;
        result = calculatorDTO.getNum1() * calculatorDTO.getNum2() * calculatorDTO.getNum3() * calculatorDTO.getNum4();
        ResponseEntity<Double> responseEntity = new ResponseEntity<Double>(result, HttpStatus.CREATED);
        return responseEntity;
    }
}