import java.util.ArrayList;
import java.util.List;

class Memento {
    private String State;

    public Memento(String State){
        this.State = State;
    }

    public String getState() {
        return this.State;
    }
}

class Originator {
    private String State;

    public void setState(String State){
        this.State = State;
    }

    public String getState(){
        return this.State;
    }

    protected Memento saveToMemento(){
        return new Memento(State);
    }

    protected void getStateFromMemento(Memento memento){
        State = memento.getState();
    }
}

class Caretaker {
    private List<Memento> mementoList = new ArrayList<>();

    public void add (Memento State){
        mementoList.add(State);
    }

    public Memento get(int index){
        return mementoList.get(index);
    }

}

public class Prac2 {
    public static void main(String[] args) {
        Originator originator = new Originator();
        Caretaker careTaker = new Caretaker();

        originator.setState("State 1");
        careTaker.add(originator.saveToMemento());

        originator.setState("State 2");
        careTaker.add(originator.saveToMemento());

        originator.setState("State 3");
        careTaker.add(originator.saveToMemento());

        System.out.println("Current State: "+originator.getState());
    
        originator.getStateFromMemento(careTaker.get(0));
        System.out.println("First State: "+originator.getState());
    }
}
