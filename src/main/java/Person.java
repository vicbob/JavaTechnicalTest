public class Person {
    private String name;
    private String id;

    public Person(String name, String id){
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return  name + ","+id;
    }

    @Override
    public boolean equals(Object obj){
        Person p = (Person)obj;
        if(this.id.equals(p.id)){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode(){
        return id.hashCode();
    }
}
