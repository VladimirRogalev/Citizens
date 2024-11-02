package telran.citizens.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import telran.citizens.model.Person;

public class CitizensImpl implements Citizens {
	private List <Person> idList;
	private List<Person> lastNameList;
	private List<Person> ageList;
	private static Comparator<Person> lastNameComparator = (o1, o2) ->{
		int res = o1.getLastName().compareTo(o2.getLastName());
		return res == 0 ? Integer.compare(o1.getId(), o2.getId()): res;
	};
	private static Comparator<Person> ageComparator = (o1, o2) -> {
		int res = Integer.compare(o1.getAge(), o2.getAge());
		return res ==0 ? Integer.compare(o1.getId(), o2.getId()): res;
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

		if(person == null || find(person.getId()) !=null) {
			return false;
		}
		return idList.add(person)&&lastNameList.add(person)&&ageList.add(person);
	}

	@Override
	public boolean remove(int id) {
		Person victim = find(id);
		
		
		return idList.remove(victim)&&lastNameList.remove(victim)&&ageList.remove(victim);
	}

	@Override
	public Person find(int id) {
		Person pattern = new Person(id, null, null, LocalDate.now());
		Collections.sort(idList);
		int index = Collections.binarySearch(idList, pattern);
		return index < 0 ? null : idList.get(index);
	}

	@Override
	public Iterable<Person> find(int minAge, int maxAge) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Person> find(String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Person> getAllPersonSortedById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Person> getAllPersonSortedByLastName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Person> getAllPersonSortedByAge() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return idList.size();
	}
  

}
