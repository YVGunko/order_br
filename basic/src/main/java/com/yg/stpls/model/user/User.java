package com.yg.stpls.model.user;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@NaturalIdCache
@Cache(
    usage = CacheConcurrencyStrategy.READ_WRITE
)
public class User implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5113923763634991903L;

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

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public Boolean getSuperUser() {
		return superUser;
	}

	public void setSuperUser(Boolean superUser) {
		this.superUser = superUser;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getDt() {
		return dt;
	}

	public User() {
		super();
	}
	
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", superUser=" + superUser + '}';
    }
	
	public User(Long id, String name, String pswd, Boolean superUser, Employee employee, Date dt, Boolean external) {
		super();
		this.id = id;
		this.name = name;
		this.pswd = pswd;
		this.superUser = superUser;
		this.external = external;
		this.employee = employee;
		this.dt = dt;
	}
	public User(Long id) {
		super();
		this.id = id;
		this.name = "";
		this.pswd = "";
		this.superUser = false;
		this.external = false;
		this.employee = null;
		this.dt = new Date();
	}

	@Access(AccessType.PROPERTY)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonProperty("_id")
	public Long id;
	
	@JsonProperty("name")
	@NotNull
	@NaturalId //Cache
	private String name;
	
	@JsonProperty("pswd")
	@NotNull
	@ColumnDefault("1234")
	private String pswd;
	
	@JsonProperty("superUser")
	@ColumnDefault("false")
	Boolean superUser;
	
	@JsonProperty("external")
	@ColumnDefault("false")
	Boolean external;
	
	@JsonProperty("Id_s")
	@ManyToOne(fetch = FetchType.EAGER)
	@ColumnDefault("0")
	public Employee employee;
	
	@JsonProperty("DT")
	@JsonFormat(pattern="dd.MM.yyyy HH:mm:ss",timezone="Europe/Moscow")
	@Column(name = "dt", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	Date dt = new Date();

	public Boolean getExternal() {
		return external;
	}

	public void setExternal(Boolean external) {
		this.external = external;
	}
	@Size(min=5, message = "Не меньше 5 знаков")
	private String password;
    @Transient
    private String passwordConfirm;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getRoles();
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<PriceUser> priceUsers = new HashSet<>();
	/*
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }*/
}
