import java.util.ArrayList;
import java.util.Iterator;
import java.util.*;

/**
 * Monitor counts of different types of animal.
 * Sightings are recorded by spotters.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29 (imperative)
 */
public class AnimalMonitor 
{
    // Records of all the sightings of animals.
    private ArrayList<Sighting> sightings;
    
    /**
     * Create an AnimalMonitor.
     */
    public AnimalMonitor()
    {
        this.sightings = new ArrayList<>();
    }
    
    /**
     * Add the sightings recorded in the given filename to the current list.
     * @param filename A CSV file of Sighting records.
     */
    public void addSightings(String filename)
    {
        SightingReader reader = new SightingReader();
        sightings.addAll(reader.getSightings(filename));
    }
    
    
    /*private ArrayList<String> animals;
        return sightings.stream()
         .filter (sightings -> sightings.getCount() > 0 )
         .filter (sightings -> sightings.getDetails() )
        parameter list-> (sightings) ->
        {
        /*what we want to do with ->System.print.outln(sightings);
        }
        public void elephants(int Spotter, int day, String elephant){
    sightings.stream()
             .filter(record -> record.getAnimal().equals("Elephant"))
             .filter(record -> record.getSpotter().equals("Spotter")) 
             .filter(record -> record.getPeriod() == day)
             .map(record -> record.getCount())
             .reduce(0, (a,b) -> a + b );
        */
    
    
    
    /**
     * Print details of all the sightings.
     */
    public void printList()
    {
        sightings.stream()
                 .forEach(sightings -> System.out.println (sightings.getDetails() ) );
    }
    
    /**
     * Print the details of all the sightings of the given animal.
     * @param animal The type of animal.
     */
    public void printSightingsOf(String animal)
    {
        for(Sighting record : sightings) {
            if(animal.equals(record.getAnimal())) {
                System.out.println(record.getDetails());
            }
        }
    }
    
    
    public void printSightingsOfNew(String animal)
    {
        sightings.stream()
            .filter ( record -> animal.equals(record.getAnimal() ) )
            .forEach( record -> System.out.println(record.getDetails()) );
    }
    
    public void printSightingsOfPeriod(int day) {
        sightings.stream()
            .filter ( record -> record.getPeriod() == day ) 
            .forEach( record -> System.out.println(record.getDetails()) );
    }
    
    public void printSightingsInPeriod(String animal, int day) {
        sightings.stream()
            .filter ( record -> animal.equals(record.getAnimal() ) )
            .filter ( record -> record.getPeriod() == day ) 
            .forEach( record -> System.out.println(record.getDetails()) );
    }
    
    public void printCounts(String animal){
        System.out.println(sightings.stream()
            .filter( sighting -> animal.equals(sighting.getAnimal()) )
            .map( sighting -> sighting.getCount())
            .reduce(0, (total,count) -> total + count ));   
    }
    
    
    
    /**
     * Print all the sightings by the given spotter.
     * @param spotter The ID of the spotter.
     */
    public void printSightingsBy(int spotter)
    {
        for(Sighting record : sightings) {
            if(record.getSpotter() == spotter) {
                System.out.println(record.getDetails());
            }
        }        
    }
    
    /**
     * Print a list of the types of animal considered to be endangered.
     * @param animalNames A list of animals names.
     * @param dangerThreshold Counts less-than or equal-to to this level
     *                        are considered to be dangerous.
     */
    public void printEndangered(ArrayList<String> animalNames, 
                                int dangerThreshold)
    {
        for(String animal : animalNames) {
            if(getCount(animal) <= dangerThreshold) {
                System.out.println(animal + " is endangered.");
            }
        }
    }
    
    /**
     * Return a count of the number of sightings of the given animal.
     * @param animal The type of animal.
     * @return The count of sightings of the given animal.
     */
    public int getCount(String animal)
    {
        int total = 0;
        for(Sighting sighting : sightings) {
            if(animal.equals(sighting.getAnimal())) {
                total = total + sighting.getCount();
            }
        }
        return total;
    }
    
    /**
     * Remove from the sightings list all of those records with
     * a count of zero.
     */
    public void removeZeroCounts()
    {
        Iterator<Sighting> it = sightings.iterator();
        while(it.hasNext()) {
            Sighting record = it.next();
            if(record.getCount() == 0) {
                it.remove();
            }
        }
    }
    
    /**
     * Return a list of all sightings of the given type of animal
     * in a particular area.
     * @param animal The type of animal.
     * @param area The ID of the area.
     * @return A list of sightings.
     */
    public ArrayList<Sighting> getSightingsInArea(String animal, int area)
    {
        ArrayList<Sighting> records = new ArrayList<>();
        for(Sighting record : sightings) {
            if(animal.equals(record.getAnimal())) {
                if(record.getArea() == area) {
                    records.add(record);
                }
            }
        }
        return records;
    }
    
    /**
     * Return a list of all the sightings of the given animal.
     * @param animal The type of animal.
     * @return A list of all sightings of the given animal.
     */
    public ArrayList<Sighting> getSightingsOf(String animal)
    {
        ArrayList<Sighting> filtered = new ArrayList<>();
        for(Sighting record : sightings) {
            if(animal.equals(record.getAnimal())) {
                filtered.add(record);
            }
        }
        return filtered;
    }
    
}
