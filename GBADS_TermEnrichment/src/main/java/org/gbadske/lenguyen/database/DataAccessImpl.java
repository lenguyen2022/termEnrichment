package org.gbadske.lenguyen.database;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.gbadske.lenguyen.beans.TermDtoOutput;
import org.gbadske.lenguyen.utility.RandomGenerator;
import org.springframework.stereotype.Repository;



@Repository
public class DataAccessImpl {
	
	ArrayList<TermDtoOutput> termList = new ArrayList<TermDtoOutput>();
	
	
	public int makeTermList() {
		return 0;
	}
	
	public TermDtoOutput searchSpeciesByName(String speciesName) {
		
		for(TermDtoOutput t: termList) {

		}
		return null;
	}
	
	public TermDtoOutput getSpeciesTerm(Long id) {
		
		Stream<TermDtoOutput> term =  termList.stream().filter((n)-> n.getId() == id);
		List<TermDtoOutput> tempList = (List<TermDtoOutput>) term.collect(Collectors.toList());
		
		if(tempList.size()>0)
			return tempList.get(0);
		else
			return null;
		
	}
	public Long saveTerms(List<TermDtoOutput> sList) {
		sList.forEach(i-> save(i));
		return (long)termList.size();
	}
	public Long save(TermDtoOutput speciesTerm) {
		speciesTerm.setId(new RandomGenerator().generateNumber());
		termList.add(speciesTerm);
		return (long) termList.size();
		
	}
	public List<TermDtoOutput> findAll(){
		return termList;
	}
	public Long count() {
		return (long) termList.size();
	}

}
