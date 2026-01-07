package edu.j2ee.hotelcrud.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.j2ee.hotelcrud.dto.HotelDTO;
import edu.j2ee.hotelcrud.entity.Hotel;
import edu.j2ee.hotelcrud.helpers.Mapper;
import edu.j2ee.hotelcrud.repository.HotelRepository;

@Service
public class HotelService {
	@Autowired
	private HotelRepository repository;

	public Optional<Hotel> getHotel(int id) {
		return repository.findById(id);
	}

	public Hotel saveHotel(HotelDTO dto) {
		return repository.save(Mapper.toEntity(dto));
	}
}
