/**
 * This class represents a library patron that has a name and assigns 
 * values to different literary aspects of books.
 */
class Patron
{
	/** The first name of this Patron. */
	final String firstName;
	/** The last name of this Patron. */
	final String lastName;
	/** The weight the patron assigns to the comic aspects of books. */
	final int patronComicTendency;
	/** The weight the patron assigns to the dramatic aspects of books. */
	int patronDramaticTendency;
	/** The weight the patron assigns to the educational aspects of books. */
	int patronEducationalTendency;
	/** The minimal literary value a book must have for this patron to enjoy it. */
	int EnjoymentThreshold;
   

	/*----=  Constructors  =-----*/
   
   
	/**
	 * Creates a new patron with the given characteristics.
	 * @param patronFirstName The first name of the patron.
	 * @param patronLastName
	 * @param comicTendency
	 * @param dramaticTendency
	 * @param educationalTendency
	 * @param patronEnjoymentThreshold
	 */
	Patron(String patronFirstName, String patronLastName, 
			int comicTendency, int dramaticTendency, 
			int educationalTendency, int patronEnjoymentThreshold)
	{
		this.firstName = patronFirstName;
		this.lastName = patronLastName;
		this.patronComicTendency = comicTendency;
		this.patronDramaticTendency = dramaticTendency;
		this.patronEducationalTendency = educationalTendency;
		this.EnjoymentThreshold = patronEnjoymentThreshold;
	}//end of constructor Patron.
   

	/*----=  Instance Methods  =-----*/
   
	
	/**
	 * Returns a string representation of the patron, 
	 * which is a sequence of its first and last name, 
	 * separated by a single white space. For example, 
	 * if the patron's first name is "Ricky" and his last name is "Bobby", 
	 * this method will return the String "Ricky Bobby".
	 * @return the String representation of this patron.
	 */
	String stringRepresentation()
	{
		return (this.firstName+" "+this.lastName);
	}//end of stringRepresentation method.
   
   
	/**
	 * Returns the literary value this patron assigns to the given book.
	 * the literary value this patron assigns to the given book.
	 * @param book The book to asses.
	 * @return the literary value this patron assigns to the given book.
	 */
	int getBookScore(Book book)
	{
		return(this.patronComicTendency*book.getComicValue() + 
				this.patronDramaticTendency*book.getDramaticValue() + 
				this.patronEducationalTendency*book.getEducationalValue() );
	}//end of getBookScore method.
   
   
	/**
	 * Returns true of this patron will enjoy the given book, false otherwise.
	 * @param book The book to asses.
	 * @return true of this patron will enjoy the given book, false otherwise.
	 */
	boolean willEnjoyBook(Book book)
	{
		return(getBookScore(book)>=this.EnjoymentThreshold);
	}//end of willEnjoyBook method.
   
   
}//end of class Patron.