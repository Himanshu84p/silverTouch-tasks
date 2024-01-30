interface ChangeState{
    public void change();
}

class Context {
    public ChangeState changestate;

    public void setState(ChangeState changeState){
        this.changestate = changeState;
    }

    public void change(){
        changestate.change();
    }
}

class StateA implements ChangeState{
    @Override
    public void change(){
        System.out.println("State is change to State A");
    }
}

class StateB implements ChangeState{
    @Override
    public void change(){
        System.out.println("State is change to State B");
    }
}

public class Prac7 {
    
    public static void main(String[] args) {
        Context context = new Context();

        context.setState(new StateA());
        context.change();

        context.setState(new StateB());
        context.change();
    }
}
