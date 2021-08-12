package Domain;

public class Ingredient extends Entity{

    private String name;
    private Float price;
    // private Float ammount;


    public Ingredient() {
		super();
	}
    
    public Ingredient(String name, float f) {
        this.name = name;
        this.price = f;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // public Float getAmmount() {
    //     return ammount;
    // }

    // public void setAmmount(Float ammount) {
    //     this.ammount = ammount;
    // }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
    public void validate(){
        super.validate();
        try {
            if (this.price == 0) {
                throw new Exception("Invalid ingredient price.");
            }
            if (this.name == null || this.name.equals("")) {
                throw new Exception("Invalid ingredient name.");
            }

        } catch (Exception excep) {
            excep.printStackTrace();
        }
    }

    // @Override
    // public Entity create(Entity) {
    //     Entity entity = new Entity();
    //     entity.id = UUID.randomUUID();
    //     return entity;
    // }

}
