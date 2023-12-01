Test Case 1:

Case 1:

From NER:{
  "species": [
    "Cattle"
  ],
  "years": [
    "2019",
    "2011"
  ],
  "countries": [
    "Canada"
  ]
}

Return:

From ontology:{
"enrichedSpecies": ["Cows","Beef"]
}

1. Insert two records for term
Cattle for year 2019, 2011.
Each year with term Cows and Beef.
INSERT INTO term(species, country,t_year) values("Cattle","Canada","2011"),("Cattle","Canada","2019") ;
Insert into enriched_term(species,term_id) values("Cows",22),("Beef",23);
Input to webservices:

{
  "species": [
    "Cattle"
  ],
  "years": [
    "2011", "2019"
  ],
  "countries": [
    "Canada"
  ]
}

Output:
{
  "enrichedSpecies": [
    "Cows",
    "Beef"
  ]
}

Case 2:

