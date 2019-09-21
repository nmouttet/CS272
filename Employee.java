/*
 * Nathaniel Mouttet
 * CS272
 * 9-6-19
 */



public class Employee {
	
	private String name;
	private int num;
	private int age;
	private String state;
	private int zipCode;
	private int[] advisors;
	
	public Employee()
	{
		//Initializes all values to defaults
		name = null;
		num = 0;
		age = 0;
		state = null;
		zipCode = 0;
		advisors = new int[0];
	}
	
	//Copy Constructor
	public Employee(Object obj)
	{
		if((obj != null) && (obj instanceof Employee))
		{
			Employee emp = (Employee)obj;
			
			setAge(emp.getAge());
			setName(emp.getName());
			setNum(emp.getNum());
			setState(emp.getState());
			setZipCode(emp.getZipCode());
			setAdvisors(emp.getAdvisors());
			
		}
	}//End of constructor
	
	//Accessors
	public String getName()
	{
		return name;
	}
	
	public int getNum()
	{
		return num;
	}
	
	public int getAge()
	{
		return age;
	}

	public String getState()
	{
		return state;
	}

	public int getZipCode()
	{
		return zipCode;
	}

	public int[] getAdvisors()
	{
		return advisors;
	}
	
	//Mutators
	public void setName(String newName)
	{
		name = new String(newName);
	}

	public void setNum(int newNum)
	{
		num = newNum;
	}

	public void setAge(int newAge)
	{
		age = newAge;
	}

	public void setState(String newState)
	{
		state = new String(newState);
	}

	public void setZipCode(int newZipCode)
	{
		zipCode= newZipCode;
	}

	public void setAdvisors(int[] newAdvisors)
	{
		System.out.println("Setting advisors...");
		if(newAdvisors.length > 3)
		{
			System.out.println("You are are setting too many Advisors.");
			return;
		}
			
		for(int i = 0; i < newAdvisors.length; i++)
		{
			if(advisors == null)
				advisors = new int[0];
			advisors = addAdvisors(advisors, newAdvisors[i]);
		}
	}
	
	//Formats string in the form
	//"Name: Thomas Hawthorn; Number: 1234087; Age: 20; State: NM; Zip Code: 87301; Advisor 1 employee number is: 9862314; "
	public String toString()
	{
		String output = new String("Name: " + this.getName() + "; " +
								   "Number: " + this.getNum() + "; " +
						           "Age: " + this.getAge() + "; " +
						           "State: " + this.getState() + "; " +
						           "Zip Code: " + this.getZipCode() + "; "
						 		  );
		int[] advisors = this.getAdvisors();
		
		for(int i = 0; i < advisors.length; i++)
		{
			output += "Advisor " + (i+1) + " employee number is: " + advisors[i] + "; ";
		}
		
		return output;
	}//End of toString
	
	//Checks to see if 2 objects have the same Employee Number
	public boolean equals(Object obj)
	{
		if(obj instanceof Employee)
		{
			Employee emp = (Employee) obj;
			return(emp.getNum() == getNum());
		}
		else
			return false;
	}//end of equals
	
	public static int[] getAllAdvisors(Employee e1, Employee e2)
	{
		int[] distinctAdvisors = new int[0];//Create array
		
		//Add all of one set into the array
		for(int i = 0; i < e1.getAdvisors().length; i++)
		{
			distinctAdvisors = addAdvisors(distinctAdvisors, e1.getAdvisors()[i]);			
		}
		
		//Check second set then if pass, add
		for(int i = 0; i < e2.getAdvisors().length; i++)
		{
			if(!contains(distinctAdvisors, e2.getAdvisors()[i]))//checks
				distinctAdvisors = addAdvisors(distinctAdvisors, e2.getAdvisors()[i]);//Adds if false
		}
		
		return distinctAdvisors;
	}//End of getAllAdivisors
	
	
	//Checks a single element against an entire array
	public static boolean contains(int[] container, int containee)
	{
		for(int i = 0; i < container.length; i++)
		{
			if(container[i] == containee)
				return false;
		}
		
		return true;
	}//End of Contains
	
	//Creates new array with a length +1 larger than original
	//Copies then returns
	public static int[] addAdvisors(int[] original, int newElement){
	    //Create a new array with extra index
	    int[] newArray = new int[original.length + 1];

	    //Copy the integers from original to newArray    
	    for (int i = 0; i < original.length; i++){
	    	newArray[i] = original[i];
	    }
	    //Add the new element to the last index     
	    newArray[newArray.length - 1] = newElement;
	    
	    return newArray;
	}
	
	public static void main(String[] args) 
	{
//		//Testing constructor and mutators
//		System.out.println("Intializing employees...");
//		Employee employee1 = new Employee();
//		employee1.setAge(34);
//		employee1.setName("Steve Brown");
//		employee1.setNum(874365298);
//		employee1.setState("MI");
//		employee1.setZipCode(49721);
//		
//		Employee employee2 = new Employee();
//		employee2.setAge(56);
//		employee2.setName("Gerald de'Croix");
//		employee2.setNum(34085235);
//		employee2.setState("ME");
//		employee2.setZipCode(3907);
//		
//		Employee employee3 = new Employee();
//		employee3.setAge(49);
//		employee3.setName("Marcus Falburg");
//		employee3.setNum(234545654);
//		employee3.setState("LA");
//		employee3.setZipCode(71486);
//		
//		Employee employee4 = new Employee();
//		employee4.setAge(43);
//		employee4.setName("Oliver Shoost");
//		employee4.setNum(25624453);
//		employee4.setState("WV");
//		employee4.setZipCode(25411);
//		
//		Employee employee5 = new Employee();
//		employee5.setAge(23);
//		employee5.setName("Jack Froost");
//		employee5.setNum(00123412);
//		employee5.setState("AK");
//		employee5.setZipCode(99723);
//		
//		System.out.println();
//		System.out.println();
//		
//		//Testing setAdvisors
//		System.out.println("Testing setAdvisors()...");
//		employee1.setAdvisors(new int[] {employee2.getNum(), employee3.getNum()});
//		employee5.setAdvisors(new int[] {employee2.getNum(), employee3.getNum(), employee4.getNum(), employee1.getNum()});
//		employee5.setAdvisors(new int[] {employee2.getNum(), employee4.getNum()});
//		System.out.println();
//		System.out.println();
//		
//		
//		//Testing toString()
//		System.out.println("Testing toString()...");
//		System.out.println();
//		
//		System.out.println(employee1.toString());
//		
//		System.out.println();
//		System.out.println();
//		
//		//Testing copy constructor
//		System.out.println("Testing copy constructor...");
//		Employee employee6 = new Employee(employee1);
//		System.out.println("Employee 6 is:");
//		System.out.println(employee6.toString());
//		
//		System.out.println();
//		System.out.println();
//		
//		//Testing equals
//		System.out.println("Testing equals()...");
//		System.out.print("Employee 1 is equal to employee 2 ...");
//		System.out.println(employee1.equals(employee2));
//		System.out.println();
//		System.out.print("Employee 1 is equal to employee 6 ...");
//		System.out.println(employee1.equals(employee6));
//		System.out.println();
//		System.out.println();
//		
//		//Testing getAllAdvisors()
//		System.out.println("Testing getAllAdivsors()...");
//		System.out.println("All of the advisors in employee1 and employee2 are:");
//		int[] advisors = Employee.getAllAdvisors(employee1, employee5);
//		for(int i = 0; i < advisors.length; i++)
//		{
//			System.out.println("Advisor " + i + " is: " + advisors[i] + ".");
//		}
		
	}

}
