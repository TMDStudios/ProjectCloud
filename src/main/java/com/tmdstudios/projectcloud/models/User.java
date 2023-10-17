package com.tmdstudios.projectcloud.models;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="users")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message="Username is required!")
    @Size(min=3, max=30, message="Username must be between 5 and 30 characters")
    private String username;
    
    private String email;
    
    private String gitHub;
    
    @NotEmpty(message="Password is required!")
    @Size(min=8, max=128, message="Password must be between 8 and 128 characters")
    private String password;
    
    @Transient
    @NotEmpty(message="Confirm Password is required!")
    @Size(min=8, max=128, message="Confirm Password must be between 8 and 128 characters")
    private String confirm;
    
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    
    @Column(updatable=false)
    @OneToMany(mappedBy="creator", fetch=FetchType.LAZY)
    @JsonManagedReference
    private List<Project> projectIdeas;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JsonManagedReference
	@JoinTable(
			name = "user_active_projects",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "project_id")
	)
    private List<Project> activeProjects;
    
    @Column(updatable=false)
    @OneToMany(mappedBy="user", fetch=FetchType.LAZY)
    @JsonManagedReference
    private List<Comment> comments;
  
    public User() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGitHub() {
		return gitHub;
	}

	public void setGitHub(String gitHub) {
		this.gitHub = gitHub;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public List<Project> getProjectIdeas() {
		return projectIdeas;
	}

	public void setProjectIdeas(List<Project> projectIdeas) {
		this.projectIdeas = projectIdeas;
	}

	public List<Project> getActiveProjects() {
		return activeProjects;
	}

	public void setActiveProjects(List<Project> activeProjects) {
		this.activeProjects = activeProjects;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}
