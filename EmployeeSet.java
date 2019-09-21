import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * @author Nathaniel Mouttet
 * 9-13-19
 * CS272
 */

public class EmployeeSet 
{
	private int size;
	private int capacity;
	private static Employee[] employee;

	public EmployeeSet()
	{
		//Initializes size to 0, capicity to 10, and the Employee[] to length "capacity"(10)
		size = 0;
		capacity = 10;
		employee = new Employee[capacity];
	}
	
	/*
	 * @author Nathaniel Mouttet
	 * 
	 * @param obj
	 * object to be copied
	 * 
	 * @precondition
	 * obj needs to be not null and an instance of EmployeeSet
	 */
	//Copy Constructor
	public EmployeeSet(Object obj)
	{
		if((obj != null) && (obj instanceof EmployeeSet))
		{
			EmployeeSet empSet = (EmployeeSet)obj;
			
			setSize(empSet.getSize());
			setCapacity(empSet.getCapacity());
			for(int i = 0; i < size; i++ )
				add(empSet.getEmployee()[i]);			
		}
	}//End of constructor
	
	//Accessors
	public int getSize()
	{
		return size;
	}
	public int getCapacity()
	{
		return capacity;
	}
	public Employee[] getEmployee()
	{
		return employee;
	}
	
	//Mutators
	public void setSize(int newSize)
	{
		size = newSize;
	}
	public void setCapacity(int newCapacity)
	{
		capacity = newCapacity;
	}
	/*
	 * @author Nathaniel Mouttet
	 * 
	 * @param minimumCapacity
	 * New Capacity of the Employee Array
	 * 
	 * @precondition
	 * minuimumCapacity must be positive and non-Zero
	 * 
	 */
	public void ensureCapacity(int minimumCapacity)
	{
		if(minimumCapacity < 1)
			throw new IllegalArgumentException("The minimum capacity should be positive and non-zero");
		
		if(minimumCapacity > capacity)
		{
			setCapacity(minimumCapacity);
			//Create a new array with extra index
		    Employee[] newArray = new Employee[getCapacity()];

		    //Copy the integers from original to newArray    
		    System.arraycopy(employee, 0, newArray, 0, getSize());
		    employee = newArray;
		}//end of if(minimumCapacity > getCapacity())
		
	}//end of ensureCapacity
	/*
	 * @author Nathaniel Mouttet
	 * 
	 * @param emp
	 * Employee object to be added to the set
	 * 
	 * @precondition
	 * emp must not be null
	 * 
	 */
	public void add(Employee emp)
	{
		if(emp == null)
			throw new NullPointerException("The add method must contain a non null Employee object");
		
		if(size <= capacity)
		{
			if(!contains(emp))
				employee[size++] = emp;
		}
		else
		{
			ensureCapacity(capacity * 2);
			if(!contains(emp))
				employee[size++] = emp;
		}
	}//End of Add
	public boolean remove(int eno)
	{
		for(int i = 0; i < employee.length; i++)
		{
			if(employee[i].getNum() == eno)
			{
				employee[i] = employee[size--];
				return true;
			}
		}
		return false;
	}
	//End of Mutators
		
	//Checks a single element against an entire array
	public boolean contains(Employee emp)
	{
		if(emp == null || getEmployee() == null || getEmployee()[0] == null)
			return false;
		for(int i = 0; i < getSize(); i++)
		{
			int temp = getEmployee()[i].getNum();
			if(temp == emp.getNum())
				return true;
		}
		return false;
	}//End of Contains
	/*
	 * @author Nathaniel Mouttet
	 * 
	 * @param emp
	 * Employee object
	 * 
	 * @preconditions
	 * emp isn't null
	 * employee is sorted(done inside the addOrdered method)
	 */
	public void addOrdered(Employee emp)
	{
		boolean sorted = false;
		Employee temp;
		
		while(!sorted)
		{
			sorted = true;
			for(int i = 0; i < size - 1; i++)
			{
				if(employee[i].getNum() > employee[i + 1].getNum())
				{
					temp = employee[i];
					employee[i] = employee[i+1];
					employee[i+1] = temp;
					sorted = false;
				}//end of if
			}//end of for
		}//end of while(!sorted)
		add(emp);		
	}//end of addOrdered
	

	public static void Read(String fileName, EmployeeSet empset){
		
		int count = 0; 
		String line = "";
		try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                
            	if(count > 301)
            	{
            		break;
            	}
            	
            	String[] unmodified = line.split(",");
            	
            	String temp = unmodified[0] + "," + unmodified[1];
            	temp.replaceAll("\"", "");
            	
            	String[] rowString = {temp, unmodified[2], unmodified[3], unmodified[4],
            						  unmodified[6]};
            	
            	if(count > empset.getCapacity())
            	{
            		empset.ensureCapacity(count*2);
            		System.out.println("Doubling capacity");
            	}
            	
            	if(count > 0) {
            		Employee employee = new Employee();
            		employee.setAge(Short.parseShort(rowString[4].trim()));
            		employee.setName(rowString[0]);
            		employee.setNum(Integer.parseInt(rowString[1].trim()));
            		employee.setState(rowString[2]);
            		employee.setZipCode(Integer.parseInt(rowString[3].trim()));
            		empset.add(employee);
            	}
            	count++;
            }   
            bufferedReader.close();
    		System.out.println("Finish reading entities from "+ fileName);       
        }catch(FileNotFoundException exeception) {
            System.out.println("Unable to open '" +  fileName + "'");                
        }catch(IOException exeception) {
            System.out.println("Error reading '" + fileName + "'");                  
        }
	}//End Read
	
	public static void main(String[] args) 
	{
		//Testing constructor and mutators
		System.out.println("Creating empty EmployeeSet then filling it with the csv data");
		System.out.println();
		EmployeeSet empSet = new EmployeeSet();
		Read("core_dataset.csv", empSet);
		
		System.out.println("Testing Accessors");
		
		System.out.println("Size: " + empSet.getSize());
		System.out.println("Capacity: " + empSet.getCapacity());
		for(int i = 0; i < empSet.getSize(); i++)
			System.out.println("Employee " + i + " is: " + empSet.getEmployee()[i]);
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Testing addOrdered...");
		System.out.println();
		Employee employee = new Employee();
		employee.setAge(34);
		employee.setName("Nathaniel Mouttet");
		employee.setNum(197834612);
		employee.setState("NM");
		employee.setZipCode(88001);
		empSet.addOrdered(employee);
		System.out.println("Added new employee and sorted");
		System.out.println("Size: " + empSet.getSize());
		System.out.println("Capacity: " + empSet.getCapacity());
		for(int i = 0; i < empSet.getSize(); i++)
			System.out.println("Employee " + i + " is: " + empSet.getEmployee()[i]);
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Testing the copy constructor...");
		EmployeeSet newEmpSet = new EmployeeSet(empSet);
		System.out.println("Size: " + newEmpSet.getSize());
		System.out.println("Capacity: " + newEmpSet.getCapacity());
		for(int i = 0; i < newEmpSet.getSize(); i++)
			System.out.println("Employee " + i + " is: " + newEmpSet.getEmployee()[i]);
		
	}
}
