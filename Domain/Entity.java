package Domain;

import java.util.*;

public abstract class Entity{

    private UUID id;
    
//    public abstract Entity create();
    public void setUUID(UUID id){
//        UUID uuid = UUID.randomUUID();
        this.id = id;//UUID.randomUUID();
    }    

    // public void setUUID(Entity entity){
    //     UUID uuid = UUID.randomUUID();
    //     entity.id = uuid;//UUID.randomUUID();
    // }    




    public UUID getId(){
//    public String getId(){
        return this.id;//.toString().replace("-", "");
    }
  
    public void setId(UUID uuid) {
        this.id = uuid;
    }
    public void validate(){
        try{
            if(id == null){
                throw new Exception("Invalid UUID, cannot validate.");
            }
        }catch(Exception excep){
            excep.printStackTrace();
        }
    }


//Overriding HashCode & equals methods. Failure to do so will result 
// in a violation of the general contract for Object.

    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Entity)){
            return false;
        }
        Entity temp = (Entity) object;
        return this.getId().equals(temp.getId());
    }


}
 
