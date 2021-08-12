package Domain;

import java.util.HashSet;
import java.util.Set;

public class Pizza extends Entity {
    private String name;
    private Float price;
    private String url;
    private final Set<Ingredient> ingredients = new HashSet<Ingredient>();
	private final Set<Comment> comments = new HashSet<Comment>();

    
    public Pizza() {
		super();
	}

    public Pizza(String name,String url){
		this.name=name;
		this.url=url;
	}

    public void setIngredients(Ingredient[] ingredients){
        for (Ingredient ingredient : ingredients) {
            this.ingredients.add(ingredient);
        }
    }

    public Set<Ingredient> getIngredients(){
        return this.ingredients;
    }

    public void removeIngredient(Ingredient ingredient){
        this.ingredients.remove(ingredient);
    }

    public void setComment(Comment[] comments){
        for (Comment comment : comments) {
            this.comments.add(comment);
        }
    }

    public Set<Comment> getComment(){
        return this.comments;
    }

    public void removeComment(Comment comment){
        this.comments.remove(comment);
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getUrl(){
        return this.url;
    }

    public void setUrl(String url){
        this.url=url;
    }

    public float getPrice() {
        price = 0.0f;
        for(Ingredient lista: ingredients){
            price = (float) (price + lista.getPrice());//*lista.getAmmount());
        }
        price *= 1.0f;
        Float f = price;
        Float price = (float) (Math.round(f*100.0)/100.0);
        return price;
    }

    public void validate() {
        super.validate();
        try {
            if (this.name == null || this.name.equals("")) {
                throw new Exception("Invalid Pizza name.");
            }
            if (this.price == 0) {
                throw new Exception("Invalid Pizza price.");
            }
            if (this.url == null || this.url.equals("")) {
                throw new Exception("Invalid URL.");
            }  
        } catch (Exception excep) {
            excep.printStackTrace();
        }
    }

}
