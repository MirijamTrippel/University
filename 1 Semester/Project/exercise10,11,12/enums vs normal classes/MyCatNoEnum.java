
/**
 * A regular Cat Class, for the NoEnum Version.
 * At first glance this class looks almost the same as the
 * enum version (MyCatExtended) but with this class
 * I have not created a list of Cats yet! I need to
 * do that in another class.
 */
public class MyCatNoEnum
{
    private String name;
    private int age;
    private String color;
    
    public MyCatNoEnum(String name, int age, String colorDescription)
    {
        this.name = name;
        this.age = age;
        this.color = colorDescription;
    }

    public String toString()
    {
        return name + " is " + age + " years old and looks like so: "
                    + color;
    }
}
