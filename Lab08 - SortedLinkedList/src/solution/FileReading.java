package solution;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Sample program to show how to read the comma delimited text file pets.txt and
 * break the lines up into a name, age, and weight.
 * 
 * @author Justin Pretlow
 * @version April 23, 2020
 * 
 */
public class FileReading
{
    private static final int NUM = 300;
    private Scanner fileIn = null;
    private SortedLinkedList <Book> books = new SortedLinkedList<Book>();
    /**
     * Constructor reads from filename.
     * 
     * @param filename
     *            The name of the file.
     */
    public FileReading(String filename)
    {
        // open the input file
        try
        {
            setFileIn(new Scanner(new FileReader(filename)));
        }
        catch (IOException ioe)
        {
            System.out.println("Could not open the input file." + filename);
            System.exit(0);
        }

        // read the data
        readLines();

        // close the input file
        fileIn.close();
    }

    /**
     * Sets the input scanner.
     * 
     * @param input
     *            The input scanner.
     */
    public void setFileIn(Scanner input)
    {
        fileIn = input;
    }

    /**
     * Read lines of the file.
     */
    public void readLines()
    {
        String[] line;
        String author;
        String title;
        int numberOfPages;
        // as long as there are lines to read
        while (fileIn.hasNextLine())
        {
            // read a line from the file and split it
            // into an array of Strings around the commas
            line = fileIn.nextLine().split(",");
            // put the data before the first comma in petName
            author = line[0];
            // put the data before the second comma in petAge
            // after converting the String to an integer
            title = line[1];
            // put the data before the third comma in petWeight
            // after converting the String to a Double
            numberOfPages = Integer.parseInt(line[2]);
            // print out the data for testing to make sure it worked
            System.out.print("Author: " + author + " ");
            System.out.print("Title: " + title + " ");
            System.out.println("Number Of Pages: " + numberOfPages);
            Book temp = new Book(author, title, numberOfPages);
            books.add(temp);
        }
    }
    /**
     * The.
     * @return books asdf
     */
    public SortedLinkedList<Book> getBooks()
    {
        return books;
    }
    /**
     * The.
     */
    public void printMoreThan300()
    {
        Book temp;
        Iterator<Book> itr = this.books.iterator();
        while (itr.hasNext())
        {
            temp = itr.next();
            if (temp.getNumberOfPages() > NUM)
            {
                System.out.println(temp.toString());
            }
        }
    }
    /**
     * The.
     * @return result asdf
     */
    public double averagePages()
    {
        double result = 0;
        int counter = 0;
        for (Book  b : books)
        {
            counter++;
            result += b.getNumberOfPages();
        }
        result /= counter;
        return result;
    }
    
    /**
     * Remove less than 200.
     * 
     */
    public void removeLessThan200()
    {
        
    }
    
    /**
     * The main program to read from pets.txt.
     * 
     * @param args
     *            unused.
     */
    public static void main(String[] args)
    {
        FileReading fr =  new FileReading("data/bookdata.txt");
        for (Book b : fr.getBooks())
        {
            System.out.println(b.toString());
        }
        System.out.println();
        fr.printMoreThan300();
    }

}