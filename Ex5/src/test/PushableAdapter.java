package test;


public class PushableAdapter implements Pushable {
    private Moveable moveable;

    public PushableAdapter(Moveable o){
        moveable = o;
    }

    @Override
    public void push(int heading) {
        moveable.moveMe(getDirection(heading));
    }

    private Moveable.Direction getDirection(int heading){
        if(heading >= 45 && heading <= 134)
            return Moveable.Direction.right;
        else  if(heading >= 135 && heading <=224)
            return Moveable.Direction.down;
        else if(heading >= 225 && heading<=314)
            return Moveable.Direction.left;
        else
            return Moveable.Direction.up;

    }
}
