package com.soloscholar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "reader", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Reader {

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message="First Name is required")
	@Size(min=5)
	private String firstName;

	@NotBlank(message="Last Name is required")
	@Size(min=5)
	private String lastName;

	@NotBlank(message="Email is required")
	@Email
	@Size(min=10)
	@Column(unique = true)
	private String email;
	
	@NotBlank(message="Id Type is required")
	private String idType;
	
	
	@NotBlank(message="Id Number is required")
	@Column(unique = true)
	private String idNum;
	
	@NotNull(message="Phone number is required")
	@Column(unique = true)
	private Long phone;
	
	@NotNull(message="Age is required")
	private int age;
	
	@NotBlank(message="Gender is required")
	private String gender;
	
	@NotBlank(message="Address is required")
	@Size(min=15)
	private String address;	
	
	@NotBlank(message="Qualification is required")
	private String qualification;
	
	private String password;

	//@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	//@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "reader_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))

	private String roles;

	public Reader() {

	}

	

	public Reader(Long id, @NotBlank(message = "First Name is required") String firstName,
			@NotBlank(message = "Last Name is required") String lastName,
			@NotBlank(message = "Email is required") @Email String email,
			@NotBlank(message = "Id Type is required") String idtype,
			@NotBlank(message = "Id Number is required") String idnum,
			@NotNull(message = "Phone number is required") Long phone, @NotNull(message = "Age is required") int age,
			@NotBlank(message = "Gender is required") String gender,
			@NotBlank(message = "Address is required") String address,
			@NotBlank(message = "Qualification is required") String qualification, String password,
			String roles) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.idType = idtype;
		this.idNum = idnum;
		this.phone = phone;
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.qualification = qualification;
		this.password = password;
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
		return idType;
	}

	public void setIdtype(String idtype) {
		idType = idtype;
	}

	public String getIdnum() {
		return idNum;
	}

	public void setIdnum(String idnum) {
		idNum = idnum;
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

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
	   this.roles="reader";
	}



	public String getQualification() {
		return qualification;
	}



	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	
}