package telran.citizens.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import telran.citizens.model.Person;

public class CitizensTreeSetImpl implements Citizens {
	private TreeSet<Person> idList;
	private TreeSet<Person> lastNameList;
	private TreeSet<Person> ageList;
	private static Comparator<Person> lastNameComparator = (o1, o2) -> {
		int res = o1.getLastName().compareTo(o2.getLastName());
		return res == 0 ? Integer.compare(o1.getId(), o2.getId()) : res;
	};
	private static Comparator<Person> ageComparator = (o1, o2) -> {
		int res = Integer.compare(o1.getAge(), o2.getAge());
		return res == 0 ? Integer.compare(o1.getId(), o2.getId()) : res;
	};

	public CitizensTreeSetImpl() {
		idList = new TreeSet<>();
		lastNameList = new TreeSet<>(lastNameComparator);
		ageList = new TreeSet<>(ageComparator);

	}

	public CitizensTreeSetImpl(List<Person> citizens) {
		this();
		citizens.forEach(t -> add(t));
	}

	@Override
	public boolean add(Person person) {
		if (person == null || person.getId() < 0 || idList.contains(person)) {
			return false;
		}

		idList.add(person);
		lastNameList.add(person);
		ageList.add(person);
		return true;
	}

	@Override
	public boolean remove(int id) {
		Person victim = find(id);
		if (victim == null) {
			return false;
		}
		idList.remove(victim);
		lastNameList.remove(victim);
		ageList.remove(victim);
		return true;
	}

	@Override
	public Person find(int id) {
		Person pattern = new Person(id, null, null, null);
		Person person = idList.ceiling(pattern);
		return pattern.equals(person) ? person : null;
	}

	@Override
	public Iterable<Person> find(int minAge, int maxAge) {
		LocalDate now = LocalDate.now();
//		Person pattern = new Person(Integer.MIN_VALUE, null, null, now.minusYears(minAge));
//		Person from =ageList.ceiling(pattern);
//		pattern = new Person(Integer.MAX_VALUE, null, null, now.minusYears(maxAge));
//		Person to = ageList.ceiling(pattern);
		Person from =new Person(idList.first().getId(), null, null, now.minusYears(minAge));
		Person to = new Person(idList.last().getId(), null, null, now.minusYears(maxAge));
		return ageList.subSet(from, true, to, true);
	
	}

	@Override
	public Iterable<Person> find(String lastName) {
		Person from =new Person (Integer.MIN_VALUE, null, lastName, null);
		Person to =new Person (Integer.MAX_VALUE, null, lastName, null);
		return lastNameList.subSet(from ,true, to, true);
	}

	@Override
	public Iterable<Person> getAllPersonSortedById() {
		return idList;
	}

	@Override
	public Iterable<Person> getAllPersonSortedByLastName() {
		return lastNameList;
	}

	@Override
	public Iterable<Person> getAllPersonSortedByAge() {
		return ageList;
	}

	@Override
	public int size() {
		return idList.size();
	}

}
