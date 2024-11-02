package telran.citizens.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.citizens.dao.Citizens;
import telran.citizens.dao.CitizensImpl;
import telran.citizens.model.Person;

class CitizensTest {
	Citizens citizens;
	LocalDate dateNow = LocalDate.now();
	

	@BeforeEach
	void setUp() throws Exception {
		citizens = new CitizensImpl(
				Arrays.asList(new Person(347, "Vladimir", "Rogalev", dateNow.minusYears(31)),
						new Person(348, "Mariia", "Rogalev", dateNow.minusYears(31)),
						new Person(395, "Maya", "Rogalev", dateNow.minusYears(6)),
						new Person(1, "Goldi", "Rogalev", dateNow.minusYears(3)),
						new Person(245, "Mira", "Rogalev", dateNow.minusYears(0)))
				);
		
	}

	@Test
	void testAdd() {
		assertFalse(citizens.add(new Person(347, "Vladimir", "Rogalev", dateNow.minusYears(31))));
		assertEquals(5, citizens.size());
		assertTrue(citizens.add(new Person(397, "Donald", "Trump", dateNow.minusYears(68))));
		assertEquals(6, citizens.size());
		assertFalse(citizens.add(null));
	}

	@Test
	void testRemove() {
	assertFalse(citizens.remove(411));
	assertEquals(5, citizens.size());
	assertTrue(citizens.remove(348));
	assertEquals(4, citizens.size());
	}

	@Test
	void testFindInt() {
		Person person = citizens.find(245);
		assertEquals(245, person.getId());
		assertEquals(0, person.getAge());
	    assertNull(citizens.find(5));
	}

	@Test
	void testFindIntInt() {
		fail("Not yet implemented");
	}

	@Test
	void testFindString() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAllPersonSortedById() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAllPersonSortedByLastName() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAllPersonSortedByAge() {
		fail("Not yet implemented");
	}

	@Test
	void testSize() {
		fail("Not yet implemented");
	}

}
