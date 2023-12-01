package org.gbadske.lenguyen.utility;

import java.util.Random;

import lombok.NoArgsConstructor;


@NoArgsConstructor
public class RandomGenerator {
	Random rnd = new Random();
	public Long generateNumber() {
		
	    Long number = rnd.nextLong(111111111L,999999999999L);
	    // this will convert any number sequence into 9 character.
	    return Long.valueOf(String.format("%012d", number));
	}
}
