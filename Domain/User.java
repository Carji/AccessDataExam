package Domain;

public class User extends Entity{

    private String name;
    private String lastName;
    private String email;
    private String password;


    public User() {
		super();
	}

    public User(String name, String lastName, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastName;
    }

    public void setLastname(String lastname) {
        this.lastName = lastname;
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
        this.password = password;
    }

    public void validate() {
        super.validate();
        try {
            if (this.name == null || this.name.equals("")) {
                throw new Exception("Invalid user name.");
            }
            if (this.lastName == null || this.lastName.equals("")) {
                throw new Exception("Invalid last name.");
            }
            if (this.email == null || this.email.equals("")) {
                throw new Exception("Invalid email.");
            }
            /* TODO: further validation of passwords. */
            if (password == null || password.equals("")) {
                throw new Exception("Invalid password.");
            }
        } catch (Exception excep) {
            excep.printStackTrace();
        }
    }

}
