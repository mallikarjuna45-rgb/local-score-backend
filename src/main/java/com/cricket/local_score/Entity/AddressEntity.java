package com.cricket.local_score.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AddressEntity {
		@Id
		@GeneratedValue(strategy =GenerationType.IDENTITY)
		private Integer id;
		private Integer dist_id;
		private Integer state_id;
		private Integer pincode;
		private String address2;
}
