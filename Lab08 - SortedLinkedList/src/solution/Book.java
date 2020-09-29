package solution;

/**
 * 
 * @author Justin Pretlow
 * @version April 23, 2020
 *
 */
public class Book implements Comparable<Book>
{
    private String author;
    private String title;
    private int numberOfPages;
    
    /**
     * Constructor for Book.
     * @param author author
     * @param title title
     * @param numberOfPages numberOfPages
     */
    public Book(String author, String title, int numberOfPages)
    {
        this.author = author;
        this.title = title;
        this.numberOfPages = numberOfPages;
    }
    
    /**
     * Accessor for author.
     * @return title
     */
    public String getAuthor()
    {
        return this.author;
    }

    /**
     * Accessor for title.
     * @return title
     */
    public String getTitle()
    {
        return this.title;
    }
    
    /**
     * Accessor for page count.
     * @return numberOfPages
     */
    public int getNumberOfPages()
    {
        return this.numberOfPages;
    }
    /**
     * Compare two books.
     * @param b other book to compare
     * @return int books comparison
     */
    @Override
    public int compareTo(Book b)
    {
        int authorCompare = this.author.compareTo(b.author);
        
        return authorCompare == 0 ? this.title.compareTo(b.title) :
            authorCompare; 
    }
    
    /**
     * Compare two books are equal.
     * @param b other book to compare
     * @return boolean books are equal
     */
    public boolean equals(Book b)
    {
        return this.author == b.author && this.title == b.title;
    }
    
    /**
     * Print book to string.
     * @return string the author, title, and number of pages
     */
    public String toString()
    {
        return this.author + ", " + this.title + ", " + this.numberOfPages;
    }
}