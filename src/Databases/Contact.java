package Databases;

import java.util.Calendar;

public class Contact {
	String name;
	String catetory;
	String status;
	String image;
	
	public Contact(){
		this.name="";
		this.catetory="";
		this.status="";
		this.image="";
	};
	
	//Set title
	public void setName(String _name){
		Calendar now = Calendar.getInstance();
		int day = now.get(Calendar.DAY_OF_MONTH);
		int hour = now.get(Calendar.HOUR_OF_DAY);
		int minute = now.get(Calendar.MINUTE);
		int second = now.get(Calendar.SECOND);
		String newName = "1"+day+hour+minute+second+"-"+_name;
		this.name = newName;
	}
	
	//Get title
	public String getName(){
		return name;
	}
	
	//Set category
	public void setCategory(String _cate){
		this.catetory = _cate;
		}
		
	//Get category
	public String getCategory(){
		return catetory;
		}
		
	//Set status
		public void setStatus(String _status){
		this.status = _status;
		}
		
	//Get status
		public String getStatus(){
		return status;
		}
	
	//Set image
		public void setImage(String _image){
			this.image = _image;
		}
	
	//Get image
		public String getImage(){
			return image;
		}
}