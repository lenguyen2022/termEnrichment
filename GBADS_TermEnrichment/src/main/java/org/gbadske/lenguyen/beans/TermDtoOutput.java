package org.gbadske.lenguyen.beans;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * This bean class is to capture EnrichedTerm of output for web service
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TermDtoOutput {
	@NonNull
	@JsonIgnore
	Long id;
	private  List<String> enrichedSpecies;

}
