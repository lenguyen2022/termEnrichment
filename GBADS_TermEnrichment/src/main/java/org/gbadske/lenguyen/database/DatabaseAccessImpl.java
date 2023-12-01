package org.gbadske.lenguyen.database;

import java.util.List;

import org.gbadske.lenguyen.beans.TermDtoOutput;
import org.gbadske.lenguyen.beans.EnrichedTerm;
import org.gbadske.lenguyen.beans.Term;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;



/**
 * This class is  used to provide data access functionality to the MySQL database.
 */
@Repository
public class DatabaseAccessImpl implements DatabaseAccess {
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;

	
	/** This method is used to get a list of term in Term table.
	 * @param id an id of a term record
	 * @return List<Term> a list of term in the database
	 */
	public List<Term> getTerm(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM term WHERE id = :id;";
		namedParameters.addValue("id", id);
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Term>(Term.class));
	}
	public List<Term> findAll(){
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM term;";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Term>(Term.class));
	}
	public List<EnrichedTerm> findAllEnrichedTerm(){
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM enriched_term;";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<EnrichedTerm>(EnrichedTerm.class));
	
	}
	public List<Term> searchTermByName(String species) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM term WHERE species = :species;";
		namedParameters.addValue("species", species);
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Term>(Term.class));
	}
	
	public List<Term> searchTermByNameCountryYear(String species, String country, String year) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM term WHERE species = :species and country = :country and t_year = :year;";
		namedParameters.addValue("species", species);
		namedParameters.addValue("country", country);
		namedParameters.addValue("year", year);
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Term>(Term.class));
	}

	public List<EnrichedTerm>searchAllEnrichedTerm(Long termId, String country, String year){
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT enriched_term.* FROM term, enriched_term WHERE term.term_id = enriched_term.term_id"
				+ " and term.term_id = :term_id and term.country = :country and term.t_year = :year ;";
		namedParameters.addValue("term_id", termId);
		namedParameters.addValue("country", country);
		namedParameters.addValue("year", year);

		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<EnrichedTerm>(EnrichedTerm.class));
	}
	
	public List<EnrichedTerm> searchAllEnrichedTerm(Long termId, String species, String country, String year){
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT enriched_term.* FROM term, enriched_term WHERE term.term_id = enriched_term.term_id"
				+ " and term.country = :country and term.t_year = :year and enriched_term.species = :species and term.term_id = :termId;";
		namedParameters.addValue("species", species);
		namedParameters.addValue("country", country);
		namedParameters.addValue("year", year);
		namedParameters.addValue("termId", termId);

		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<EnrichedTerm>(EnrichedTerm.class));
	}
	
	public List<String> getAllEnrichedSpecies(Long termId){
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT enriched_term.species FROM enriched_term WHERE enriched_term.term_id =  :term_id;" ;
		namedParameters.addValue("term_id", termId);

		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<String>(String.class));
	}
	
	public List<EnrichedTerm>searchAllEnrichedTerm(Long termId){
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT enriched_term.* FROM term, enriched_term WHERE term.term_id = enriched_term.term_id"
				+ " and term.term_id = :term_id ;" ;
		namedParameters.addValue("term_id", termId);

		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<EnrichedTerm>(EnrichedTerm.class));
	}
	/** This method is used to save a term in Term table.
	 * @param a term object
	 * @return id of the record term
	 */	
	public Long save(Term term) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		String query = "INSERT INTO term(species, country, t_year) VALUES(:species,:country,:year);";
		namedParameters.addValue("species", term.getSpecies());
		namedParameters.addValue("year", term.getT_year());
		namedParameters.addValue("country", term.getCountry());
		jdbc.update(query, namedParameters, generatedKeyHolder);
		return generatedKeyHolder.getKey().longValue();
	}
	public void saveTerms(List<Term> termList) {
		termList.forEach(t->save(t));
	}
	public Long saveEnrichedTerm(EnrichedTerm enrichedTerm) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		String query = "INSERT INTO enriched_term(species,term_id) VALUES(:species,:term_id);";
		namedParameters.addValue("species", enrichedTerm.getSpecies());
		namedParameters.addValue("term_id", enrichedTerm.getTerm_id());
		
		jdbc.update(query, namedParameters, generatedKeyHolder);
		return generatedKeyHolder.getKey().longValue();
		
	}
	public void saveEnrichedTerms(List<EnrichedTerm> enrichedTermList) {
		enrichedTermList.forEach(et->saveEnrichedTerm(et));
	}
	public Long countEnrichedTerm() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT count(*) FROM enriched_term ;";
		return jdbc.queryForObject(query, namedParameters, Long.TYPE);
	}
	
	public Long countEnrichedTerm(Long termId, String country, String year) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT count(*) FROM term,enriched_term WHERE term.term_id = enriched_term.term_id and term.term_id  = :termId and term.country  = :country and term.t_year = :year ;";
		namedParameters.addValue("termId", termId);
		namedParameters.addValue("country", country);
		namedParameters.addValue("year", year);
		return jdbc.queryForObject(query, namedParameters, Long.TYPE);
	}
	
	public Long countTerm() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT count(*) FROM term;";
		return jdbc.queryForObject(query, namedParameters, Long.TYPE);
	}
	
	public void deleteAllTerm() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "DELETE FROM term;";
		jdbc.update(query, namedParameters);
	}
	
	public void deleteAllEnrichedTerm() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "DELETE FROM enriched_term;";
		jdbc.update(query, namedParameters);
	}
	
	


}
