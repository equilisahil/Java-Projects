package edu.j2ee.hotelcrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.j2ee.hotelcrud.dto.HotelDTO;
import edu.j2ee.hotelcrud.entity.Hotel;
import edu.j2ee.hotelcrud.service.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {
	@Autowired
	private HotelService service;

	@GetMapping("/{id}")
	public Hotel getHotel(@PathVariable int id) {
		return service.getHotel(id).get();
	}
	@PostMapping
	public Hotel saveHotel(@RequestBody HotelDTO dto) {
		return service.saveHotel(dto);
	}
}
