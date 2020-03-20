/**
 * nasru
 * Item.java
 * Mar 18, 2020
 */
package com.mypractice.springreactive.document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author nasru
 *
 */



@Builder
@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Item {
	/**
	 * @param id2
	 * @param description2
	 * @param price2
	 */
	
	@Id
	public String id;
	public String description;
	public Double price;
	
}
