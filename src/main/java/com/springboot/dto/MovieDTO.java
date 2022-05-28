 package com.springboot.dto;

 
 
 
public class MovieDTO {
	private long id;
	private String name;
	private String description;
	private String image;
	private String linkfilm;
	private long categoryid;
	private long historyid;
	
	public MovieDTO() {
		// TODO Auto-generated constructor stub
	}

	public MovieDTO(long id, String name, String description, String image,String linkfilm ,long categoryid) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.image = image;
		this.linkfilm=linkfilm;
		this.categoryid = categoryid;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public long getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(long categoryid) {
		this.categoryid = categoryid;
	}

	public String getLinkfilm() {
		return linkfilm;
	}

	public void setLinkfilm(String linkfilm) {
		this.linkfilm = linkfilm;
	}

	public long getHistoryid() {
		return historyid;
	}

	public void setHistoryid(long historyid) {
		this.historyid = historyid;
	}
	
	
}
