package search.base.beta;

import java.sql.ResultSet;
import java.util.StringTokenizer;

public class QueryGenerator {

	DatabaseHandler databaseHandler = null;

	public QueryGenerator(){
		databaseHandler = new DatabaseHandler();
	}




	public String generateQuery(String query){


		int n=0;
		String queryIn="SELECT category FROM cate WHERE ";



		StringTokenizer tokenizer=new StringTokenizer(query," ");

		while(tokenizer.hasMoreTokens()){
			if(n!=0){
				queryIn+=" OR ";
			}
			queryIn+=" keyword LIKE \'%"+tokenizer.nextToken()+"%\'";
			++n;
		}
		
		//System.out.println("$"+queryIn);


		ResultSet resultSet=databaseHandler.query(queryIn);
		queryIn="";
		try{
			while(resultSet.next()){
				queryIn+=resultSet.getString(1)+" ";
				//System.out.println(" "+queryIn);
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return queryIn;
	}

}
