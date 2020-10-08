package com.ITP.IFKFbackend.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ITP.IFKFbackend.model.Payment;
import com.ITP.IFKFbackend.repository.paymentRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class paymentController {
	
	@Autowired
	private paymentRepository PaymentRepository;
	
	@GetMapping("/payments")
	public List<Payment> getAllPayments(){
		return PaymentRepository.findAll();
	}
	
	@GetMapping("/payments/{studentId}")
	public Payment getPayments(@PathVariable String studentId){
		return PaymentRepository.findBystudentID(studentId);
	}
	
	
	
	@PostMapping("/payments/insert")
	public ResponseEntity<Void> insertPayment(@RequestBody Payment payment){
		Payment created = PaymentRepository.save(payment);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(created.getPaymentID()).toUri();
		
		return ResponseEntity.created(uri).build(); 
	}
	
	
	@DeleteMapping("/payments/{paymentID}")
	public ResponseEntity<?> deletePayment(@PathVariable Long paymentID){
		
		PaymentRepository.deleteById(paymentID);
		return ResponseEntity.noContent().build();
		
		
	}
	
	@PutMapping("/payments/{paymentID}")
	public ResponseEntity<Payment> updatePayments(
			@PathVariable Long paymentID, @RequestBody Payment payment){
		
			Payment result = PaymentRepository.save(payment);
		
			return new ResponseEntity<Payment>(payment, HttpStatus.OK);
	}
	

}
