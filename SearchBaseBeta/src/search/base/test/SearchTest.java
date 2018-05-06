package search.base.test;

import java.util.Scanner;

import search.base.beta.SearchBase;

public class SearchTest {

	public static void main(String[] args) {

		SearchBase searchBase = new SearchBase();

		//searchBase.printCate();

		//searchBase.fitQuery("SELECT * FROM entertainment");

		//searchBase.printTheQueryResult();
		while(true){
			@SuppressWarnings("resource")
			Scanner sr=new  Scanner(System.in);
			System.out.println("Enter the query");
			String s=sr.nextLine();
			searchBase.search(s);
		}



	}

}
