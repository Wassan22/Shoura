package rebat.shoura;

/**
 * Created by wassanalluhaidan on 2/14/17.
 */

public class ManageMutator {
    private String Name;

    public ManageMutator(String Name){
        this.setname(Name);
    }

    public String getname() {
        return Name;
    }

    public void setname(String name) {
        Name = name;
    }
}
