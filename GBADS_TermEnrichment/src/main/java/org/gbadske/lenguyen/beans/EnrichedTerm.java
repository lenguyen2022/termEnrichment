package org.gbadske.lenguyen.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This bean class is to capture information of  enriched_term table.
 */
@Data
@NoArgsConstructor
public class EnrichedTerm {
	private Long enriched_term_id;
	private String species;
	private Long term_id;
}
