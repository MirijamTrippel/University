/**
 * TicketMachine models a naive ticket machine that issues
 * flat-fare tickets.
 * The price of a ticket is specified via the constructor.
 * It is a naive machine in the sense that it trusts its users
 * to insert enough money before trying to print a ticket.
 * It also assumes that users enter sensible amounts.
 *
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
public class TicketMachine
{
    private int price;
    private int balance;
    private int total;
    private int tickets;
    /**
     * Create a machine that issues tickets of the given price.
     * Note that the price must be greater than zero, and there
     * are no checks to ensure this.
     */
    public TicketMachine(int ticketPrice)
    {
        price = ticketPrice;
        balance = 0;
        total = 0;
    }

    /**
     * Return the price of a ticket.
     */
    public int getPrice()
    {
        return price;
    }

    /**
     * Return the amount of money already inserted for the
     * next ticket.
     */
    public int getAmount()
    {
        return balance;
    }

    /**
     * Receive an amount of money from a customer.
     */
    public void insertMoney(int amount)
    {
        balance = balance + amount;
    }

    public int getTotal(){
        return total;
    }   
    
    public void discount (int amount)
 {
     price = price - amount;
 }
 
    public int prompt (){
    System.out.println("Please insert the correct amount of money.");
    return price;
    }
 
    public int HowManyTickets(){
        return tickets;
    }
    
    /**
     * Print a ticket.
     * Update the total collected and
     * reduce the balance to zero.
     */
    public void printTicket()
    {
        if (balance >= price){
        // Simulate the printing of a ticket.
        System.out.println("##################");
        System.out.println("# The HTW Line");
        System.out.println("# Ticket");
        System.out.println("# " + price + " cents.");
        System.out.println("##################");
        System.out.println();
        
        balance = balance - price;
        // Update the total collected with the balance.
        total = total + price;
        // Clear the balance.
        tickets++;
    }
    }
    
    public void empty(){
    balance = 0;
    return;
    }
    
    
    public void printPrice(){
        System.out.println( "The price is: " + price + "cents.");
        return;
    }
}
