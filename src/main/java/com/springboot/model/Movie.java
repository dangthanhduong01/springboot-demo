package com.springboot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;





@Entity
@Table(name="movies")
public class Movie implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="name")
	private String Name;
	
	@Column(name="description")
	private String Description;
	
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String Image;
	
	@Column(name="linkfilm")
	private String linkFilm;
	

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;		
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="history_id",referencedColumnName="id",nullable=true)
	private History history;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getImage() {
		return Image;
	}
	public void setImage(String image) {
		Image = image;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

	public String getLinkFilm() {
		return linkFilm;
	}
	public void setLinkFilm(String linkFilm) {
		this.linkFilm = linkFilm;
	}
	public History getHistory() {
		return history;
	}
	public void setHistory(History history) {
		this.history = history;
	}
	
	

	
}
