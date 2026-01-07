package edu.j2ee.hotelcrud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HotelDTO {
	private String name;
	private double rating;
}
