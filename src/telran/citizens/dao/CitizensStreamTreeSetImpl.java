package telran.citizens.dao;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import telran.citizens.model.Person;

public class CitizensStreamTreeSetImpl implements Citizens {
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

	public CitizensStreamTreeSetImpl() {
		idList = new TreeSet<>();
		lastNameList = new TreeSet<>(lastNameComparator);
		ageList = new TreeSet<>(ageComparator);

	}

	public CitizensStreamTreeSetImpl(List<Person> citizens) {
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

		return idList.stream().filter(person -> person.getId() == id).findFirst().orElse(null);
	}

	@Override
	public Iterable<Person> find(int minAge, int maxAge) {
		return ageList.stream()
				.filter(person -> person.getAge() >= minAge && person.getAge() <= maxAge)
				.collect(Collectors.toList());

	}

	@Override
	public Iterable<Person> find(String lastName) {
		return lastNameList.stream()
				.filter(person -> person.getLastName().equals(lastName))
				.collect(Collectors.toList());
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
