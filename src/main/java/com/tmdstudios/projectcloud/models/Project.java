package com.tmdstudios.projectcloud.models;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="projects")
public class Project {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotEmpty(message="Project name is required!")
    @Size(min=3, max=50, message="The project name must be between 3 and 50 characters")
    private String name;
	
	@NotEmpty(message="Please include a summary")
    @Size(min=5, message="The summary must be at least 5 characters long")
    private String summary;
	
	private String description;
	
	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
        this.contributors = Arrays.asList(this.creator);
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name="user_project_id")
    private User creator;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JsonBackReference
	@JoinTable(
			name = "user_active_projects",
			joinColumns = @JoinColumn(name = "project_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
	)
    private List<User> contributors;
    
    @Column(updatable=false)
    @OneToMany(mappedBy="project", fetch=FetchType.LAZY)
    @JsonManagedReference
    private List<Comment> comments;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(
			name = "languages_projects",
			joinColumns = @JoinColumn(name = "project_id"),
			inverseJoinColumns = @JoinColumn(name = "language_id")
	)
    private List<Language> languages;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(
			name = "tags_projects",
			joinColumns = @JoinColumn(name = "project_id"),
			inverseJoinColumns = @JoinColumn(name = "tag_id")
	)
    private List<Tag> tags;
    
    public Project() {}
    
    public Project(String name, String summary, String description, User creator) {
    	this.name = name;
    	this.summary = summary;
    	this.description = description;
    	this.creator = creator;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public List<User> getContributors() {
		return contributors;
	}

	public void setContributors(List<User> contributors) {
		this.contributors = contributors;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
}
