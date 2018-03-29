package P2;

public class Person {
	private final String name;

	/**
	 * Create a person with a name.
	 * 
	 * @param name
	 */
	public Person(String name) {
		this.name = name;
	}

	/**
	 * Get the name of this person.
	 * 
	 * @return the name of the person
	 */
	public String getName() {
		return this.name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
