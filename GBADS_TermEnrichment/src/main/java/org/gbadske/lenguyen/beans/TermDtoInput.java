package org.gbadske.lenguyen.beans;

import java.util.List;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This bean class is to capture input data for the web services.
 */
@Data
@NoArgsConstructor
public class TermDtoInput {
	private Set<String> species;
	private Set<String> years;
	private Set<String> countries;
}
