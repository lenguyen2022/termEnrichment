package org.gbadske.lenguyen.beans;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * This bean class is to capture information in Term table.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Term {
	private Long term_id;
	private String species;
	private String country;
	private String t_year;
}
