package com.soloscholar.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank(message="First Name is required")
	private String firstName;

	@NotBlank(message="Last Name is required")
	private String lastName;

	@NotBlank(message="Email is required")
	@Email
	@Column(unique = true)
	private String email;
	
	@NotBlank(message="Id Type is required")
	private String Idtype;
	
	
	@NotBlank(message="Id Number is required")
	@Column(unique = true)
	private String Idnum;
	
	@NotNull(message="Phone number is required")
	//@Size(min=10,max=10)
	@Column(unique = true)
	private Long phone;
	
	@NotNull(message="Age is required")
	//@Size(min=18,max=60)
	private int age;
	
	@NotBlank(message="Gender is required")
	private String gender;
	
	@NotBlank(message="Address is required")
	private String address;	
	
	
	private String password;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))

	private Collection<Role> roles;

	public User() {

	}

	public User(Long id, @NotBlank(message = "First Name is required") String firstName,
			@NotBlank(message = "Last Name is required") String lastName,
			@NotBlank(message = "Email is required") @Email String email,
			@NotBlank(message = "Id Type is required") String idtype,
			@NotBlank(message = "Id Number is required") String idnum,
			@NotBlank(message = "Phone number is required") @Size(min = 10, max = 10) Long phone,
			@NotBlank(message = "Age is required") @Size(min = 18, max = 60) int age,
			@NotBlank(message = "Gender is required") String gender,
			@NotBlank(message = "Address is required") String address, String password, Collection<Role> roles) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.Idtype = idtype;
		this.Idnum = idnum;
		this.phone = phone;
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.password = encryptPassword(password);
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
        this.password = encryptPassword(password); // Encrypt the password when setting it
    }
    
    private String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
	

	public String getIdtype() {
		return Idtype;
	}

	public void setIdtype(String idtype) {
		Idtype = idtype;
	}

	public String getIdnum() {
		return Idnum;
	}

	public void setIdnum(String idnum) {
		Idnum = idnum;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	
}