package search.base.beta;

import java.sql.ResultSet;
import java.util.StringTokenizer;

public class SearchBase {

	public String query;

	DatabaseHandler databaseHandler;

	public SearchBase(){
		databaseHandler = new DatabaseHandler();
	}

	public SearchBase(String query){
		this.query=query;
	}

	public void fitQuery(String query){
		this.query=query;
	}

	public void printDocuments(){

	}

	public void printTheQueryResult(){
		ResultSet resultSet = databaseHandler.query(query);
		try{
			while(resultSet.next()){
				System.out.println(" "+resultSet.getString(2));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	public void printCate(){
		ResultSet resultSet = databaseHandler.getCate();
		try{
			while(resultSet.next()){
				System.out.println(" "+resultSet.getInt(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void search(String query){
		String cate[] = new String[1024];
		int i=0;
		QueryGenerator queryGenerator = new QueryGenerator();

		String cateList=queryGenerator.generateQuery(query);

		System.out.println("Cate List  : "+cateList);

		StringTokenizer tokenizer = new StringTokenizer(cateList," ");


		while(tokenizer.hasMoreTokens()){
			cate[i++]=tokenizer.nextToken();
		}

		tokenizer = new StringTokenizer(query," ");

		String post="";
		int t=0;
		while(tokenizer.hasMoreTokens()){
			if(t!=0){
				post+=" OR ";
			}
			t++;
			post+="word LIKE \'%"+tokenizer.nextToken()+"%\'";
		}

		//System.out.println(" % "+post);

		ResultSet resultSet[] = new ResultSet[i];
		for(int j=0;j<i;++j){
			resultSet[j] = databaseHandler.query("SELECT * FROM "+cate[j]+" WHERE "+post);
		}



		System.out.println("----------------------------");

		for(int j=0;j<i;++j){
			try{
				while(resultSet[j].next()){
					System.out.println(" Title  : "+resultSet[j].getString(2)+"\n Site   : "+resultSet[j].getString(3));
					System.out.println("*****");
				}
			}catch(Exception e){
				//System.out.println(" "+e);
			}
			try{
				if(resultSet[j]!=null){
					resultSet[j].close();
				}
			}catch(Exception e){
				System.out.println(" "+e);
			}
		}

		System.out.println("-----------------------------");	



	}


}
