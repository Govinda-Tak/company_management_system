package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Address {
	
	@Column(nullable = false,length = 255)
	private String address;
	@Column(nullable = false,length = 50)
	private String state;
	@Column(nullable = false,length = 50)
	private String country;
	@Column(nullable = false,length = 6)
	@Pattern(regexp = "^[1-9][0-9]{5}$")
	private String pinCode;

}
