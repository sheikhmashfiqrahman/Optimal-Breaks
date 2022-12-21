package optimalBreaks;

import java.util.ArrayList;
import java.util.*;

public class BreakSchedule {




	// helper method for totalCost 

	int[][]lookUpTable(String word, ArrayList<Integer> breakList){

		// check if we last element of the break is equal to words last index 
		// if equal increase size m+1
		// else m+2 as we add -1, and |word|-1

		int m= 0;
		if(breakList.get(breakList.size()-1)==word.length()-1) {
			m= breakList.size()+1;
			breakList.add(0, -1);
		}
		else {
			m=breakList.size()+2; 
			breakList.add(0, -1);
			breakList.add(breakList.size(), word.length()-1);
		}


		int[][]dp= new int[m][m];
		int min=0;

		//fill diagonal 0s, where length is 1 
		for(int i=1;i<dp.length;i++) {
			dp[i][i-1]=0;
		}

		// fill the table when length of the word is more than 2
		for(int l=2;l<breakList.size();l++) {

			for(int i=l;i<dp.length;i++) {

				int j=i-l;

				dp[i][j]=Integer.MAX_VALUE;

				for(int k=i-1;k>=j;k--) {

					min=dp[i][k]+dp[k][j]+breakList.get(i)-breakList.get(j);

					if(min<dp[i][j]) {

						dp[i][j]=min;
					}
				}
			}
		}//loop end

		return dp;

	}

	// Use this class to implement programs for Tasks 2 & 3. Your file must not change the package name,
	// nor the class name. You must not change the names nor the signatures of the two program stubs
	// in this file. You may augment this file with any other method or variable/data declarations that you
	// might need to implement the dynamic programming strategy requested for Tasks 2&3.
	// Make sure however that all your declarations are public.


	// Precondition: word is a string and breakList is an array of integers in strictly increasing order
	//               the last element of breakList is no more than the number of characters in word.
	// Postcondition: Return the minimum total cost of breaking the string word into |breakList|+1 pieces, where 
	//                the position of each each break is specified by the integers in breakList. 
	//                Refer to the assignment specification for how a single break contributes to the cost.

	int totalCost (String word, ArrayList<Integer> breakList) { // TODO Complete for Task 2



		if(word.length()==1||word==""||word==null||
				breakList==null||
				(breakList.size()==1 && 
				(breakList.get(0)==word.length()-1))) {

			return 0;
		}

		if(breakList.size()>=2&&breakList.get(breakList.size()-1)==word.length()-1) {
			breakList.remove(breakList.size()-1);
		}



		if(breakList.size()==1) {
			return word.length();
		}




		int min=Integer.MAX_VALUE;
		//use memo 
		for(int i =breakList.size()-1; i>=0;) {
			int head=breakList.get(i);

			ArrayList<Integer>tailList=new ArrayList<Integer>();
			ArrayList<Integer>headList=new ArrayList<Integer>();
			headList.add(head);


			for(int j=0;j<breakList.size();j++) {

				if(breakList.get(j)==head) {
					continue;

				}
				tailList.add(breakList.get(j));

			}

			int tail= tailList.get(tailList.size()-1);



			if(tail>head) {



				return min=word.length()+totalCost(word.substring(0,head+1),headList)+totalCost(word.substring(head+1),tailList);
			}

			else {



				return min=word.length()+totalCost(word.substring(0,head+1),headList)+totalCost(word.substring(0,head+1),tailList);

			}



		}


		return min;



	}




	// Precondition: word is a string and breakList is an array of integers in strictly increasing order
	//               the last element of breakList is no more than the number of characters in word.
	// Postcondition: Return the schedule of breaks so that word is broken according to the list of
	// 					breaks specified in breakList which yields the minimum total cost.

	ArrayList<Integer> breakSchedule (String word, ArrayList<Integer> breakList) { // TODO Complete for Task 3

		if(word==null||breakList==null||
				(breakList.size()==1 && 
				(breakList.get(0)>=word.length()-1))||
				word.length()==1) {


			return null;
		}

		// for storing the path right to left order

		ArrayList<Integer>ans= new ArrayList<Integer>();

		int[][]path=lookUpTable(word,breakList);		
		for(int r=path.length-1;r>1;r--) {  /// 1 is index 1 
			for(int c=0;c<path[r].length;c++) {
				if(path[r][c]==0) {
					ans.add(breakList.get(c));
					break;
				}
			}
		}


		return ans;


	}	 





}
