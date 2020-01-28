/**
 * A class that maintains information on a book.
 * This might form part of a larger application such
 * as a library system, for instance.
 *
 * @author (Insert your name here.)
 * @version (Insert today's date here.)
 */
class Book
{
    // The fields.
    private String author;
    private String title;
    private int pages;
    private String refNumber;
    private String referenceNumber; 
    private boolean refnumber;
    /**
     * Set the author and title fields when this object
     * is constructed.
     */
    public Book(String bookAuthor, String bookTitle, int bookPages)
    {
        author = bookAuthor;
        title = bookTitle;
        pages = bookPages;
    }

    public String getAuthor(){
    return author;
    }
    
    public String getTitle(){
    return title;
    }
    
    public int getPages(){
    return pages;
    }
    
    public String getRefNumber(){
    return refNumber;
    }
    
    public void setRefNumber (String bookRefNumber){
        int length = bookRefNumber.length();
        if (length > 3){
            refNumber = bookRefNumber;
        }
        else{
        System.out.println("invalid bookRefNumber");
        }
    }
    
    
    public void ReferancenNumber(String refNumb){
        referenceNumber=refNumb; 
        refnumber=true;
    }
    
    public void printTitle(){ 
        System.out.println("Title : "+title); 
    }

    public void printAuthor(){ 
        System.out.println("Author : "+author); 
    } 
    
    public void printDetails(){ 
        printAuthor(); 
        printTitle(); 
        System.out.println("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque ac mattis libero, sed consequat eros. Nullam maximus aliquam ipsum sit amet rutrum. Sed aliquam convallis augue, ac sagittis quam. Donec sed enim vel augue consectetur tristique eget vel mi. Cras porta lectus eros, vitae dictum quam hendrerit nec."); 
        if (refnumber){ 
            System.out.println(referenceNumber); 
        } 
    }
}
