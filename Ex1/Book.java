/**
 * This class represents a book, which has a title, author, 
 * year of publication and different literary aspects.
 */
class Book
{
	/** The title of this book. */
	final String title;
	/** The name of the author of this book. */
	final String author;
	/** The year this book was published. */
	final int yearOfPublication;
	/** The comic value of this book. */
	int comicValue;
	/** The dramatic value of this book. */
	int dramaticValue;
	/** The educational value of this book. */
	int educationalValue;
	final int isNotBorrowed = -1; //The value that a returned Book has.
	/** The id of the current borrower of this book. */
	int currentBorrowerId = isNotBorrowed;	
	
	
	/*----=  Constructors  =-----*/
   
	
	/**
	 * Creates a new book with the given characteristic.
	 * @param bookTitle The title of the book.
	 * @param bookAuthor The name of the author of the book.
	 * @param bookYearOfPublication The year the book was published.
	 * @param bookComicValue The comic value of the book.
	 * @param bookDramaticValue The dramatic value of the book.
	 * @param bookEducationalValue The educational value of the book.
	 */
	Book(String bookTitle, String bookAuthor, int bookYearOfPublication, 
			int bookComicValue, int bookDramaticValue, int bookEducationalValue)
	{
		this.title = bookTitle;
		this.author = bookAuthor;
		this.yearOfPublication = bookYearOfPublication;
		this.comicValue = bookComicValue;
		this.dramaticValue = bookDramaticValue;
		this.educationalValue = bookEducationalValue;
	}//end of constructor Book.
      

	/*----=  Instance Methods  =-----*/
   
	
	/**
	 * Returns a string representation of the book, which is a sequence
	 * of the title, author, year of publication and the total literary value of the book, 
	 * separated by commas, inclosed in square brackets. For example, if the book is 
	 * titled "Monkey Feet", was written by Ernie Douglas, 
	 * published in 1987 and has a comic value of 7, 
	 * dramatic value of 3 and an educational value of 1, 
	 * this method will return the string: 
	 * "[MonkeyFeet,Ernie Douglas,1987,11]" 
	 * @return the String representation of this book.
	 */
	String stringRepresentation()
	{
		return "["+this.title+","+this.author+","
				+this.yearOfPublication+","+this.getLiteraryValue()+"]";
	}//end of stringRepresentation method.
	
	
	/**
	 * @return the literary value of this book, 
	 * which is defined as the sum of its comic value, its dramatic
	 * value and its educational value.
	 */
	int getLiteraryValue()
	{
		return(this.comicValue + this.educationalValue + this.dramaticValue);
	}//end of getLiteraryValue method.
   
	
	/**
	 * Sets the given id as the id of the current borrower of this book,
	 *  -1 if no patron is currently borrowing
	 * it.
	 * @param borrowerId
	 */
	void setBorrowerId(int borrowerId)
	{
		if(this.currentBorrowerId == isNotBorrowed )
		{
			this.currentBorrowerId = borrowerId;
		}
		
	}//end of setBorrowerId method.
   
	
	/**
	 * @return the id of the current borrower of this book.
	 */
	int getCurrentBorrowerId()
	{
		return(this.currentBorrowerId);
	}//end of getCurrentBorrowerId method.
   
	
	/**
	 * Marks this book as returned.
	 */
	void returnBook()
	{
		if (this.currentBorrowerId != isNotBorrowed)
		{
			this.currentBorrowerId = isNotBorrowed;
		}
	}//end of returnBook method.
  
   	
	//added methods

	
	/**
	 * @return the comic value of this book.
	 */
	int getComicValue()
	{
		return(this.comicValue);
	}//end of getComicValue method.
	
	
	/**
	 * @return the dramatic value of this book.
	 */
	int getDramaticValue()
	{
		return(this.dramaticValue);
	}//end of getDramaticValue method.
   
	
	/**
	 * @return the educational value of this book.
	 */
	int getEducationalValue()
	{
		return(this.educationalValue);
	}//end of getEducationalValue method.
   
	
}//end of class Book.