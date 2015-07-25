package Databases;

import java.util.Calendar;

public class Category {

	String title;
	String status;
	String access;
	String language;
	
	public Category(){
		this.title="";
		this.status = "";
		this.access = "";
		this.language = "";
	};
	
	//Set title
	public void setTitle(String title){
		Calendar now = Calendar.getInstance();
		int day = now.get(Calendar.DAY_OF_MONTH);
		int hour = now.get(Calendar.HOUR_OF_DAY);
		int minute = now.get(Calendar.MINUTE);
		int second = now.get(Calendar.SECOND);
		String newTitle = "1"+day+hour+minute+second+"-"+title;
		this.title = newTitle;
	}
	
	//Get title
	public String getTitle(){
		return title;
	}
	
	//Set status
		public void setStatus(String status){
			this.status = status;
		}
		
	//Get status
	public String getStatus(){
			return status;
		}
	
	//Set access
			public void setAccess(String access){
				this.access = access;
			}
			
	//Get status
		public String getAccess(){
				return access;
			}
		
	//Set status
		public void setLanguage(String language){
					this.language = language;
				}
				
	//Get status
		public String getLanguage(){
					return language;
				}
}
