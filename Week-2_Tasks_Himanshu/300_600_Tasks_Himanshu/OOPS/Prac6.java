import java.util.ArrayList;
import java.util.List;

class object {
    private String name;
    private int objectID;

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setID(int _id){
        this.objectID = _id;
    }
    public int getID(){
        return objectID;
    }

    @Override
    public String toString(){
        return "Object [objectID = "+objectID+", Name = "+name+"]";
    }
}

class ObjectCollection implements Cloneable {
    private String objname;
    List<object> objects = new ArrayList<>();

    public String getCollectionName(){
        return objname;
    }

    public void setCollectionName(String name){
        this.objname = name;
    }

    public List<object> getObjects(){
        return objects;
    }

    public void setObjects(List<object> objects){
        this.objects = objects;
    }

    public void loadObject(){
        for(int i=1;i<=3;i++){
            object obj = new object();
            obj.setID(i);
            obj.setName("Obj "+i);
            getObjects().add(obj);
        }
    }

    @Override
    protected ObjectCollection clone() throws CloneNotSupportedException{
        ObjectCollection objcollection = new ObjectCollection();
        for(object obj: this.getObjects()){
            objcollection.getObjects().add(obj);
        }
        return objcollection;
    }

    @Override
    public String toString(){
        return "ObjectCollection [ObjectCollection Name = "+objname+", objects = "+objects+" ]";
    }

}

public class Prac6 {
    public static void main(String[] args) throws CloneNotSupportedException {
        ObjectCollection objcollection = new ObjectCollection();
        objcollection.setCollectionName("My Object.");
        objcollection.loadObject();

        /*
         Shallow clonning means 
             clonning same object for new object in clone method Above when you give type Only Object then it will give shallow CLonning
         */
        
        ObjectCollection objcollection2 = objcollection.clone(); // Deep Clonning
        objcollection2.setCollectionName("New Collection.");
        // objcollection.getObjects().remove(1);

        System.out.println(objcollection);
        System.out.println(objcollection2);
    }
    
}
