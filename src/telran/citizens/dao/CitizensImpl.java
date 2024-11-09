package telran.citizens.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import telran.citizens.model.Person;

public class CitizensImpl implements Citizens {
	private List<Person> idList;
	private List<Person> lastNameList;
	private List<Person> ageList;
	private static Comparator<Person> lastNameComparator = (o1, o2) -> {
		int res = o1.getLastName().compareTo(o2.getLastName());
		return res == 0 ? Integer.compare(o1.getId(), o2.getId()) : res;
	};
	private static Comparator<Person> ageComparator = (o1, o2) -> {
		int res = Integer.compare(o1.getAge(), o2.getAge());
		return res == 0 ? Integer.compare(o1.getId(), o2.getId()) : res;
	};

	public CitizensImpl() {
		idList = new ArrayList<>();
		lastNameList = new ArrayList<>();
		ageList = new ArrayList<>();

	}

	public CitizensImpl(List<Person> citizens) {
		this();
		citizens.forEach(t -> add(t));
	}

	@Override
	public boolean add(Person person) {
		if (person == null) {
			return false;
		}
		int index = Collections.binarySearch(idList, person);
		if (index >= 0 || person.getId() < 0) {
			return false;
		}
		index = -index - 1;
		idList.add(index, person);
		index = Collections.binarySearch(lastNameList, person, lastNameComparator);
		index = -index - 1;
		lastNameList.add(index, person);
		index = Collections.binarySearch(ageList, person, ageComparator);
		index = -index - 1;
		ageList.add(index, person);
		return true;
	}

	@Override
	public boolean remove(int id) {
		Person victim = find(id);
		if(victim == null) {
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
		int index = Collections.binarySearch(idList, pattern);
		return index < 0 ? null : idList.get(index);
	}

	@Override
	public Iterable<Person> find(int minAge, int maxAge) {
		LocalDate now = LocalDate.now();
		Person pattern = new Person(Integer.MIN_VALUE, null, null, now.minusYears(minAge));
		int from = - Collections.binarySearch(ageList, pattern, ageComparator)-1;
		 pattern = new Person(Integer.MAX_VALUE, null, null, now.minusYears(maxAge));
		int to = - Collections.binarySearch(ageList, pattern, ageComparator)-1;
			return ageList.subList(from, to);
	}

	@Override
	public Iterable<Person> find(String lastName) {
		Person pattern = new Person(Integer.MIN_VALUE, null, lastName, null);
		int from = - Collections.binarySearch(lastNameList, pattern, lastNameComparator)-1;
		 pattern = new Person(Integer.MAX_VALUE, null, lastName, null);
		int to = - Collections.binarySearch(lastNameList, pattern, lastNameComparator)-1;
			return lastNameList.subList(from, to);
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
