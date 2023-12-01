package org.gbadske.lenguyen.database;

import java.util.List;

import org.gbadske.lenguyen.beans.EnrichedTerm;
import org.gbadske.lenguyen.beans.Term;
import org.gbadske.lenguyen.beans.TermDtoOutput;

public interface DatabaseAccess {
	
	public List<Term> getTerm(Long id);
	public List<String> getAllEnrichedSpecies(Long termId);
	public List<Term> findAll();
	public List<EnrichedTerm> findAllEnrichedTerm();
	public List<Term> searchTermByName(String species);
	public List<Term> searchTermByNameCountryYear(String species, String country, String year);
	public List<EnrichedTerm>searchAllEnrichedTerm(Long termId);
	public List<EnrichedTerm>searchAllEnrichedTerm(Long termId, String country, String year);
	public List<EnrichedTerm> searchAllEnrichedTerm(Long termId, String species, String country, String year);
	public Long save(Term term);
	public void saveTerms(List<Term> termList);
	public Long saveEnrichedTerm(EnrichedTerm enrichedTerm);
	public void saveEnrichedTerms(List<EnrichedTerm> enrichedTermList);
	public void deleteAllTerm();
	public void deleteAllEnrichedTerm();
	public Long countEnrichedTerm();
	public Long countEnrichedTerm(Long termId, String country, String year);
	public Long countTerm();
}
