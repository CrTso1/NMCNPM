package src.model;

public class Employee extends Person{
	private String Role;
	private String shift;
	private long Bonus;
	private long salary;
	
	private String EmployeeName;
	private String passWord;

	public Employee()	{

	}
	public Employee(String ID, String name, String DoB, String address, String phone, String Role, String shift, long salary, String EmployeeName, String passWord) {
		super(ID, name, DoB, address, phone);
		this.Role = Role;
		this.shift = shift;
		this.salary = salary;
		this.EmployeeName = EmployeeName;
		this.passWord = passWord;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public long getBonus() {
		return Bonus;
	}

	public void setBonus(long bonus) {
		Bonus = bonus;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	public String getEmployeeName() {
		return EmployeeName;
	}

	public void setEmployeeName(String EmployeeName) {
		this.EmployeeName = EmployeeName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	

}
