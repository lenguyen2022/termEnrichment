package org.gbadske.lenguyen.database;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.gbadske.lenguyen.beans.EnrichedTerm;
import org.gbadske.lenguyen.beans.Term;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DatabaseAccessTest {
	@Autowired
	private DatabaseAccess da;
    
	
	@BeforeEach                                         
    void setUp() {
		//delete all the database.

        
    }
	
  
	public void whenInsertStudentGetStudentList() {
		// setup		
		//get current size
		int size = da.findAll().size();
		
		Term term1 = new Term();
		term1.setCountry("Canada");
		term1.setSpecies("Cattle");
		term1.setT_year("2020");
		// when
		Long id = da.save(term1);
		
		assertEquals(da.findAll().size(), ++size);
		
		
		int size1  = da.findAllEnrichedTerm().size();
		
		EnrichedTerm et = new EnrichedTerm();
		et.setSpecies("Bovine");
		et.setTerm_id(id);
		da.saveEnrichedTerm(et);
		
		EnrichedTerm et1 = new EnrichedTerm();
		et1.setSpecies("Calves");
		et1.setTerm_id(id);
		da.saveEnrichedTerm(et1);
		
		List<EnrichedTerm> eList = da.searchAllEnrichedTerm(id);
		List<EnrichedTerm> eList1 = da.searchAllEnrichedTerm(id,"Canada","2020");
		System.out.println(eList.size());
		
       
		Long numberOfEnrichedTerm = da.countEnrichedTerm(id, "Canada", "2020");
		
		List<EnrichedTerm>  eList3 = da.searchAllEnrichedTerm(id,"Bovine", "Canada", "2020");
		
		assertEquals(eList3.size(),1);
		
		
	
		
		// then (the actual test!)
		
		assertEquals(da.findAllEnrichedTerm().size(), size1 + 2);
		assertEquals(eList.size(),eList1.size());
		
		assertEquals(numberOfEnrichedTerm, 2);
		//assertEquals(da.searchAllEnrichedTerm(id).size(), 2);
	}
	
	public void numberOfEnrichtedTerm() {
		//Long numberOfEnrichedTerm = da.countEnrichedTerm(id, "Canada", "2020");
		int i = da.searchTermByNameCountryYear("Cattle", "Canada", "2020").size();
		//assertEquals(i,4);
		int j = da.getAllEnrichedSpecies(16L).size();
		assertEquals(j,2);
	}
}
