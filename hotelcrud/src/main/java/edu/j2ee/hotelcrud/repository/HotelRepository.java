package edu.j2ee.hotelcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.j2ee.hotelcrud.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {

}
