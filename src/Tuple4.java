public class Tuple4<T1, T2, T3, T4> {
    private T1 first;
    private T2 second;
    private T3 third;
    private T4 fourth;
    //Tuple4 constructor
    public Tuple4(T1 first, T2 second, T3 third, T4 fourth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
    }
    //getter methods
    public T1 getFirst()  {return first;}
    public T2 getSecond() {return second;}
    public T3 getThird()  {return third;}
    public T4 getFourth() {return fourth;}
    //setter methods
    public void setFirst(T1 first)   {this.first = first;}
    public void setSecond(T2 second) {this.second = second;}
    public void setThird(T3 third)   {this.third = third;}
    public void setFourth(T4 fourth) {this.fourth = fourth;}

    @Override
    public String toString() {
        return new String("(" + this.getFirst() + ", " + this.getSecond() + ", " +
                          this.getThird() + ", " + this.getFourth() + ")");
    }
}
