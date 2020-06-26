import java.sql.Blob;

class Hospital{
	public int iD;
	public String fname;
	public String lname;
	public Double aadhar;
	public Double contact_num;
	public String email;
	public String add;
	public Double pin;
	public String problem;
	public String admitted;
	public byte[] image;

	public String category_1;
	public String category_2;
	public String category_3;
	public String category_4;
	public String category_5;

public Hospital(){
	iD = 0;
	fname = "";
	lname = "";
	aadhar = 0.0;
	contact_num = 0.0;
	email = "";
	add = "";
	pin = 0.0;
	problem = "";
	admitted = "";

	category_1 = "";
	category_2 = "";
	category_3 = "";
	category_4 = "";
	category_5 = "";

}

public Hospital(int iD, String fname, String lname, Double aadhar, Double contact_num, String email, String add, Double pin, String admitted, String category_1, String category_2, String category_3, String category_4, String category_5, byte[] image){
	this.iD = iD;
	this.fname = fname;
	this.lname = lname;
	this.aadhar = aadhar;
	this.contact_num = contact_num;
	this.email = email;
	this.add = add;
	this.pin = pin;
	this.problem = problem;
	this.admitted = admitted;

	this.category_1 = category_1;
	this.category_2 = category_2;
	this.category_3 = category_3;
	this.category_4 = category_4;
	this.category_5 = category_5;
}

public int getiD(){
	return iD;
}

public void setiD(int iD){
	this.iD = iD;
}

public String getfname(){
	return fname;
}

public void setfname(String fname){
	this.fname = fname;
}

public String getlname(){
	return lname;
}

public void setlname(String lname){
	this.lname = lname;
}

public Double getaadhar(){
	return aadhar;
}

public void setaadhar(Double aadhar){
	this.aadhar = aadhar;
}


public Double getcontact_num(){
	return contact_num;
}

public void setcontact_num(Double contact_num){
	this.contact_num = contact_num;
}


public String getemail(){
	return email;
}

public void setemail(String email){
	this.email = email;
}

public String getadd(){
	return add;
}

public void setadd(String add){
	this.add = add;
}

public Double getpin(){
	return pin;
}

public void setpin(Double pin){
	this.pin = pin;
}

public String getproblem(){
	return problem;
}

public void setproblem(String problem){
	this.problem = problem;
}

public String getadmitted(){
	return admitted;
}

public void setadmitted(String admitted){
	this.admitted = admitted;
}



public String getcategory_1(){
	return category_1;
}

public void setcategory_1(String category_1){
	this.category_1 = category_1;
}

public String getcategory_2(){
	return category_2;
}

public void setcategory_2(String category_2){
	this.category_2 = category_2;

}
public String getcategory_3(){
	return category_3;
}

public void setcategory_3(String category_3){
	this.category_3 = category_3;
}

public String getcategory_4(){
	return category_4;
}

public void setcategory_4(String category_4){
	this.category_4 = category_4;
}

public String getcategory_5(){
	return category_5;
}

public void setcategory_5(String category_5){
	this.category_5 = category_5;
}


public byte[] getImage(){
	return image;
}

public void setImage(byte[] image){
	this.image = image;
}
}
