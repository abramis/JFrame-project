
public class Student implements uni.db.Persistable{
	



	private String name,AM,telephone,adress;
	
	public Student(String am,String n,String tel,String a)
	{
		name=n;
		AM=am;
		telephone=tel;
		adress=a;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAM() {
		return AM;
	}

	public void setAM(String aM) {
		AM = aM;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return AM;
	}

}
