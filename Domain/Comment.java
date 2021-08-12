package Domain;

import java.util.Date;

public class Comment extends Entity{

    private String text;
    private Float score;
    private User user;
    private Pizza pizza;
    private Date date;


    public Comment() {
		super();
	}

    public Comment(String text, Float score, User user, Pizza pizza, Date date) {
        this.text = text;
        this.score = score;
        this.user = user;
        this.pizza = pizza;
        this.date = date;
    }

    public String getText() {
        return text;
    }
    public Float getScore() {
        return score;
    }
    public User getUser() {
        return user;
    }
    public Pizza getPizza() {
        return pizza;
    }
    public Date getDate() {
        return date;
    }

    public void setScore(Float score) {
        this.score = score;
    }
    public void setText(String text) {
        this.text = text;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }
    public void setDate(Date date) {
        this.date = date;
    }


    public void validate(){
        super.validate();
        try {
            if (this.text == null || this.text.equals("")) {
                throw new Exception("Invalid comment.");
            }
            if (this.score == null || this.score.equals(0.0f)) {
                throw new Exception("Invalid score.");
            }
        } catch (Exception excep) {
            excep.printStackTrace();
        }
    }
}

