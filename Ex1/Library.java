/**
 * This class represents a library, which hold a collection of books. 
 * Patrons can register at the library to be able to check out books, 
 * if a copy of the requested book is available.
 */
class Library
{
	/** An array of books in the library. */
	Book[] library;
	/** An array of patrons registered to this library. */
	Patron[] patrons;
	/** The maximal number of books this library can hold. */
	final int libraryMaxBookCapacity;
	/** The maximal number of books this library allows a 
	 * single patron to borrow at the same time. */
	final int libraryMaxBorrowedBooks;
	/** The maximal number of registered patrons this library can handle. */
	final int libraryMaxPatronCapacity;
	/** The current number of books in the library. */
	int currentNumberOfBooksInLibrary;
	/** The current number of patrons registered */
	int currentNumberOfPatronsRegistered;
	
	
	final int emptyLibrary = 0;
	final int noPatronsRegistered = 0;
	final int patronIsNotRegistered = -1;
	final int patronsArrayIsFull = -1;
	final int bookIsNotInLibrary = -1;
	final int libraryIsFull = -1;
	final int firstIdInLibrary = 0;
	final int firstIdInOfPatrons = 0;
	final int isNotBorrowed = -1;
	
	
	/*----=  Constructors  =-----*/
   
   
	/**
	 * 
	 * Creates a new library with the given parameters.
	 * @param maxBookCapacity The maximal number of books this library can hold.
	 * @param maxBorrowedBooks The maximal number of books this library allows
	 *  a single patron to borrow at the same time.
	 * @param maxPatronCapacity The maximal number of 
	 * registered patrons this library can handle.
	 */
	Library(int maxBookCapacity, int maxBorrowedBooks, int maxPatronCapacity)
	{
		this.libraryMaxBookCapacity = maxBookCapacity;
		this.libraryMaxBorrowedBooks = maxBorrowedBooks;
		this.libraryMaxPatronCapacity = maxPatronCapacity;
		this.library = new Book[this.libraryMaxBookCapacity];
		this.patrons = new Patron[this.libraryMaxPatronCapacity];
		this.currentNumberOfBooksInLibrary = emptyLibrary;
		this.currentNumberOfPatronsRegistered = noPatronsRegistered;
		initiateArrays();
	}//end of constructor Library.
   

	/*----=  Instance Methods  =-----*/
   
	
	/**
	 * Adds the given book to this library, if there is place available, 
	 * and it isn't already in the library.
	 * @param The book to add to this library.
	 * @return a non-negative id number for the book if there was a spot 
	 * and the book was successfully added, or if the book was already in the library; 
	 * a negative number otherwise.
	 */
	int addBookToLibrary(Book book)
	{
		int bookId = getBookId(book);
		if(bookId != bookIsNotInLibrary)
		{
			return(bookId);
		}
		if(this.library.length == currentNumberOfBooksInLibrary)
		{
			return(libraryIsFull);
		}
		this.library[this.currentNumberOfBooksInLibrary] = book;
		bookId = this.currentNumberOfBooksInLibrary;
		this.currentNumberOfBooksInLibrary++;
		return(bookId);
	}//end of addBookToLibrary method.
   
	
	/**
	 * Returns the non-negative id number of the given book 
	 * if he is owned by this library, -1 otherwise.
	 * @param book The book for which to find the id number.
	 * @return a non-negative id number of the given book
	 *  if he is owned by this library, -1 otherwise.
	 */
	int getBookId(Book book)
	{
		if(book == null)
		{
			return(bookIsNotInLibrary);
		}
		for(int i=0; i<currentNumberOfBooksInLibrary; i++)
		{
			if (book.equals(library[i]))
					{
						return(i);
					}
		}
		return(bookIsNotInLibrary);
	}//end of getBookId method.
   
	
	/**
	 * Returns true if the given number is an id of 
	 * some book in the library, false otherwise.
	 * @param bookId The id to check.
	 * @return true if the given number is an id of some 
	 * book in the library, false otherwise.
	 */
	boolean isBookIdValid(int bookId)
	{
		return((bookId >= firstIdInLibrary)&& 
				(bookId < this.currentNumberOfBooksInLibrary));	
	}//end of isBookIdValid method.
   
   
	/**
	 * Returns true if the book with the given id is available, false otherwise.
	 * @param bookId The id number of the book to check.
	 * @return true if the book with the given id is available, false otherwise.
	 */
	boolean isBookAvailable(int bookId)
	{
		return((isBookIdValid(bookId)) && 
				(this.library[bookId].getCurrentBorrowerId()==isNotBorrowed));
	}//end of isBookAvailable method.
   
	
	/**
	 * Registers the given Patron to this library, if there is a spot available.
	 * @param patron The patron to register to this library.
	 * @return a non-negative id number for the patron if there was a spot 
	 * and the patron was successfully registered, a negative number otherwise.
	 */
	int registerPatronToLibrary(Patron patron)
	{
		int patronId = getPatronId(patron);
		if(patronId != patronIsNotRegistered)
		{
			return(patronId);
		}
		if(this.patrons.length == currentNumberOfPatronsRegistered)
		{
			return(patronsArrayIsFull);
		}
		this.patrons[currentNumberOfPatronsRegistered] = patron;
		patronId = currentNumberOfPatronsRegistered;
		currentNumberOfPatronsRegistered++;
		return(patronId);
	}//end of registerPatronToLibrary method.
	
	
	/**
	 * Returns the non-negative id number of the given patron 
	 * if he is registered to this library, -1 otherwise.
	 * @param patron The patron for which to find the id number.
	 * @return a non-negative id number of the given patron 
	 * if he is registered to this library, -1 otherwise.
	 */
	int getPatronId(Patron patron)
	{
		if (patron == null)
		{
			return(patronIsNotRegistered);
		}
		for(int i=0; i<currentNumberOfPatronsRegistered; i++)
		{
			if (patron.equals(patrons[i]))
					{
						return(i);
					}
		}
		return(patronIsNotRegistered);
	}//end of getPatronId method.

	
	/**
	 * Returns true if the given number is an id of a patron in the library, 
	 * false otherwise.
	 * @param patronId The id to check.
	 * @return true if the given number is an id of a patron in the library, 
	 * false otherwise.
	 */
	boolean isPatronIdValid(int patronId)
	{
		return((patronId >= firstIdInOfPatrons)&& 
				(patronId < this.currentNumberOfPatronsRegistered));	
	}//end of isPatronIdValid method.
	
	
	/**
	 * Marks the book with the given id number as borrowed by the patron 
	 * with the given patron id, if this book is available, 
	 * the given patron isn't already borrowing the maximal number of books allowed, 
	 * and if the patron will enjoy this book.
	 * @param bookId The id number of the book to borrow.
	 * @param patronId The id number of the patron that will borrow the book.
	 * @return true if the book was borrowed successfully, false otherwise.
	 */
	boolean borrowBook(int bookId, int patronId)
	{
		if((isBookAvailable(bookId))&&
				(isPatronIdValid(patronId))&&
				(numberOfBooksBorrowedByPatron(patronId)<this.libraryMaxBorrowedBooks)&&
				(patrons[patronId].willEnjoyBook(library[bookId])))
		{
			library[bookId].setBorrowerId(patronId);
			return (true);
		}	
		return(false);		
	}//end of borrowBook method.
	
	
	/**
	 * Return the given book.
	 * @param bookId The id number of the book to return.
	 * @return The book with the given book Id.
	 */
	void returnBook(int bookId)
	{
		if(isBookIdValid(bookId))
		{
			this.library[bookId].returnBook();
		}
	}//end of borrowBook method.
	
	
	/**
	 * Suggest the patron with the given id the book he will enjoy the most, 
	 * out of all available books he will enjoy, if any such exist.
	 * @param patronId The id number of the patron to suggest the book to.
	 * @return The available book the patron with the given will enjoy the most. 
	 * Null if no book is available.
	 */
	Book suggestBookToPatron(int patronId)
	{
		if(!isPatronIdValid(patronId))
		{
			return(null);
		}
		Patron patron = this.patrons[patronId];
		Book bestBook = null;
		int bestBookScore = -1;
		for(int i=0; i<currentNumberOfBooksInLibrary; i++)
		{
			if((patron.willEnjoyBook(this.library[i]))&&
					(patron.getBookScore(this.library[i]) > bestBookScore)&&
					(isBookAvailable(i)))
							
			{
				bestBook = this.library[i];
				bestBookScore = patron.getBookScore(this.library[i]);
			}
		}
		return(bestBook);
	}//end of borrowBook method.
	
	
	
	
	//added methods
	
	
	//Initiates the books and patrons arrays and ensures 
	//that both of the arrays are full with null values.
	private void initiateArrays()
	{
		for(int i=0; i<this.library.length; i++)
		{
			this.library[i] = null;
		}
		for(int i=0; i<this.patrons.length; i++)
		{
			this.patrons[i] = null;
		}
	}//end of initiateArrays method.
	
	
	//returns the number of books that are borrowed by a given patron ID.
	private int numberOfBooksBorrowedByPatron(int patronId)
	{	
		int counter = 0;
		for(int i=0; i<currentNumberOfBooksInLibrary; i++)
		{
			if (library[i].getCurrentBorrowerId() == patronId)
			{
				counter++;
			}
		}
		return(counter);
	}//end of numberOfBooksBorrowedByPatron method.
	

}//end of class Library.