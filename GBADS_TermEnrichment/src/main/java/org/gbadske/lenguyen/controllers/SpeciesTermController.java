package org.gbadske.lenguyen.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.gbadske.lenguyen.beans.TermDtoOutput;
import org.gbadske.lenguyen.beans.EnrichedTerm;
import org.gbadske.lenguyen.beans.Term;
import org.gbadske.lenguyen.beans.TermDtoInput;
import org.gbadske.lenguyen.database.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;



/**
 * This class provides the Rest API
 */
@RestController
@RequestMapping("/api")
public class SpeciesTermController {
	@Autowired
	DatabaseAccess da;

	@GetMapping("/list")
	public List<Term> getSpeciesCollection() {
		return da.findAll();
	}

	
	@GetMapping(value = "/{name}") // "value" only here to illustrate our
	public List<Term> getIndividualSpeciesTerm(@PathVariable String name) {
		return da.searchTermByName(name);
	}
	

	@PostMapping("/postGetEnrichedTerm")
	@ResponseBody
	public TermDtoOutput postGetEnrichedTerm(@RequestBody TermDtoInput term) throws JsonMappingException, JsonProcessingException {
	    
		Set <String> speciesSet = term.getSpecies();
		Set <String> countrySet = term.getCountries();
		Set <String> yearSet = term.getYears();
		
		Set<Term> termSet = new HashSet<Term>();
		Set<EnrichedTerm> enrichedTermSet = new HashSet<EnrichedTerm>();
		
		Set<String> allEnrichedTermSet = new HashSet<String>();
		TermDtoOutput output = new TermDtoOutput();
		
		
		for(String species : speciesSet) {
			for(String country: countrySet) {
				for (String year: yearSet) {
					if( species != null && !species.trim().isEmpty() 
							&& country !=  null && !country.trim().isEmpty() 
							&& year != null && !year.trim().isEmpty()) {
						//Do the cases when all species, country, year exists
						//Search database and return
						List<Term> termList = da.searchTermByNameCountryYear(species, country, year);
						termList.forEach(t->{
							List<EnrichedTerm> enrichedTermList = da.searchAllEnrichedTerm(t.getTerm_id());
		
							enrichedTermList.forEach(et-> {
								  
								allEnrichedTermSet.add(et.getSpecies());	
								
							});

						});
						
					}
				}
			}
		}
		
		output.setEnrichedSpecies(new ArrayList<String>(allEnrichedTermSet));
	
		
	    return output;
	}

	
	@PutMapping("/insertSpeciesTerm")
	public String insertSpeciesTerm(@RequestBody Term speciesTerm) {
		da.save(speciesTerm);
		return "Total Records: " + da.countTerm();
	}
	@PutMapping("/insertMultipleSpeciesTerm")
	public String insertMultipleSpeciesTerm(@RequestBody List<Term> speciesTerm) {
		da.saveTerms(speciesTerm);
		return "Total Records: " + da.countTerm();
	}
	
	@PutMapping("/insertEnrichedTerm")
	public String putIndividualEnrichedTerm(@RequestBody EnrichedTerm enrichedTerm) {
		da.saveEnrichedTerm(enrichedTerm);
		return "Total Records: " + da.countEnrichedTerm();
	}
	
	@PutMapping("/insertMultipleEnrichedTerm")
	public String insertMultipleEnrichedTerm(@RequestBody EnrichedTerm enrichedTerm) {
		da.saveEnrichedTerm(enrichedTerm);
		return "Total Records: " + da.countEnrichedTerm();
	}
	

}
