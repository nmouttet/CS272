/**
 * @author nmouttet
 * 8-28-19
 */


import java.io.*;

class DataRow{
	//Employee Name, Employee Number, State, Zip, Age, and	Sex
	String number;
	String name;
	String state;
	String zip;
	short age;
	String sex;
}

public class EmployeeFileOp {
	private static DataRow[] dataRow = null;
	
	public static void main(String[] args) {
		
		Read("core_dataset.csv");
		Write("young_employee.csv");
	}//End of main
	
	public static void Read(String fileName){
		
		int count = 0; 
		String line = "";
		try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            dataRow = new DataRow[301];
            while((line = bufferedReader.readLine()) != null) {
                
            	if(count > 301)
            	{
            		break;
            	}
            	
            	String[] unmodified = line.split(",");
            	
            	String temp = unmodified[0] + "," + unmodified[1];
            	temp.replaceAll("\"", "");
            	
            	String[] rowString = {temp, unmodified[2], unmodified[3], unmodified[4],
            						  unmodified[6], unmodified[7]};
            	
            	if(count > dataRow.length)
            	{
            		System.out.println("There are too many enties in the file");
            		break;
            	}
            	
            	if(count > 0) {
            		dataRow[count - 1] = new DataRow();
                	dataRow[count - 1].age = Short.parseShort(rowString[4].trim());
                	dataRow[count - 1].name = rowString[0];
                	dataRow[count - 1].number = rowString[1];
                	dataRow[count - 1].sex = rowString[5];
                	dataRow[count - 1].state = rowString[2];
                	dataRow[count - 1].zip = rowString[3];
            	}
            	count++;
            }   
            bufferedReader.close();        
        }catch(FileNotFoundException exeception) {
            System.out.println("Unable to open '" +  fileName + "'");                
        }catch(IOException exeception) {
            System.out.println("Error reading '" + fileName + "'");                  
        }
		System.out.println("Finish reading entities from "+ fileName);
	}//End Read
	
	public static void Write(String fileName){
		try {
			File file = new File(fileName);
			
			FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
			
			//WRITE METADATA FIRST
			bufferWriter.write("Employee Name" + "," + 
							   "Employee Number" + "," + 
							   "Age" + "," + 
							   "Sex" + "," + 
							   "State" + "," + 
							   "Zip" + "\n");
			for(int i = 0; i < dataRow.length; i++) 
			{
				if(dataRow[i].age <= 30) {
					//System.out.println(i + dataRow[i].name + "," + "\n");
					bufferWriter.write(dataRow[i].name + "," + 
							   dataRow[i].number + "," + 
							   dataRow[i].age + "," + 
							   dataRow[i].sex + "," + 
							   dataRow[i].state + "," + 
							   dataRow[i].zip + "\n");	
				}				
			}
			
			bufferWriter.close();
			fileWriter.close();
		} catch (IOException exeception) {
			System.out.println("There has been an IO Exception");
		}
	}//End of Write
}
