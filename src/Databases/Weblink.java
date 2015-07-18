package Databases;

import java.util.Calendar;

public class Weblink {

	String name;
	String url;
	String conten;
	String status;
	String cate;
	
	public Weblink(){
		this.name="";
		this.url="";
		this.conten="";
		this.status="";
		this.cate ="";
	};
	
	//Set name
	public void setName(String name){
		Calendar now = Calendar.getInstance();
		int day = now.get(Calendar.DAY_OF_MONTH);
		int hour = now.get(Calendar.HOUR_OF_DAY);
		int minute = now.get(Calendar.MINUTE);
		int second = now.get(Calendar.SECOND);
		String newname = "1"+day+hour+minute+second+"-"+name;
		this.name = newname;
	}
	
	//Get name
	public String getName(){
		return name;
	}
	
	//Set category
	public void setUrl(String _url){
		this.url = _url;
	}
	
	//Get category
	public String getUrl(){
		return url;
	}
	
	//Set client
	public void setContent(String content){
		this.conten = content;
	}
	
	//Get client
	public String getContent(){
		return conten;
	}
	
	//Set status
	public void setStatus(String status){
		this.status = status;
	}
	
	//Get status
	public String getStatus(){
		return status;
	}
	
	//Set category
	public void setCategory(String cate){
		this.cate = cate;
	}
	
	//Get status
	public String getCategory(){
		return cate;
	}
}
