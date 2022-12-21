# Optimal-Breaks
 
### Task: 1 

## (a) Given a word = “envelope” and the list of breaks {1, 3}, if the original problem is totalCost(word[0,7], {1, 3}) identify the subproblems. How is the total minimum cost computed from these subproblems? (You must express the original problem in terms of the subproblems you have identified.) 

Ans: We are given a String S (any word) of length n and a break list B ={b0,b1,b1,…..bm-1} where the breaks are in an increasing order. We can assume that B has the length m. If we break the String S at a position b,( b taken from the break list). Then we will get two strings S[0,b] and S[b+1,n-1]. Lets identify the optimal substructure it exhibits of the subproblems we are going to find. Consider a sequence of break positions {j1, j2, j3…jm-1}. So, lets break our string S at a position B[ j1 ], B[j2]… and so on. The first break point B[ j1 ], it breaks the string into two substrings S_1 and S_2. The remaining subsequence B[ j2 ] to B[ jm-1] are smaller than B[ j1 ] breaks in S1 optimally. Subsequence B[ j2 ] to B[ jm-1] values(cost) are greater than B[ j1 ] break in S2 optimally. Given that the word ENVELOPE and list of Break B={1,3}. We have two decisions, either we choose 1 or 3. If we break at position 1 we get word[0,1] and word[2,7]. We cannot break [0,1] further. We break word[2,7] to word[2,3], [4,7] Then if we again break the word at position 3 we get word[0,3] and word [4,7]. From [0,3], we can break it to word [0,1] and word[2,3]. We can already identify our subproblems from this. In other words, subproblems will be indexed by sub lists of the list of breaks needed to be made. We try to take each possible break with minimal cost.


## (b) Write down the formula for totalCost(word[i, j], breakList) as a recursive function. (You should use your answer to (a) as a guide: what do you do arithmetically with the subproblems? Don’t worry if your answer is not something you can implement directly.)

Ans: We have taken list B and element b from the answer (a)
totalCost(word[i,j], B)= 0, if b== j or b == -1 where b belongs to B list
totalCost(word[i,j], B)
= min k∈{i+1,...,j−1} { word[i,k].cost + word[k,j].cost + B[ j ] – B[ i ] }, if 0<=
i<= b < j.
word[i,k].cost is the cost of left sub string, word[k,j].cost is the for breaking
right substring and B[ j ] – B[ i ] is the cost breaking Word[ i, j ]


## (c) Identify the termination inputs (those which return 0 for totalCost on those inputs). i.e. say what values of pairs [i, j] and breakList imply that totalCost(word[i, j], breakList) = 0

Ans:
If the break point, b >= j or b<0 or the word is an empty string or b is null the
cost will be zero. For example, if a word is HOLIDAY, break point is 6 it will
return 0 because 6 is the last index of the word HOLIDAY (b=last index of the
HOLIDAY).

## (d) Identify the simplest (non-zero) subproblem for which totalCost(word[i,j], breakList) needs no recursive call.

Ans: When the break list has just one element we can get the cost without a recursive
call or if b== j or b == -1. Already mentioned the cases in answer c

## (e) Identify row and column labels associated with a dynamic programming table for computing totalCost(word[i, j], breakList). i.e. what is the structure of a table whose entries correspond exactly with totalCost(word[i,j], breakList)? (Hint: your table entries should correspond exactly to the parameters in the function you are trying to compute.)

Ans: Lets increase the break list size of B by 2. We will add -1 at the beginning and N-1 (N is the size of the string word). In the end, we will have the substrings S0,1, S1,2, S2,3, …. Sk,k+1. This S string represents the word[ i,j] in the parameter of the function. By parameter definition,
final Si,j := S[ B[i]+1,…,B[j] ] ( for word word[i,j]).
Furthermore, size of B is increased to m+2. Our Dynamic programming
structure represents a table DP which consists a row indexing with j and column indexed with i and the optimal cost will be for breaking word[i,j] or Si,j
formula would be same as stated in answer (b) however, a little change in the
notation.
DP[i,i+1] = 0 , if 0<= i <=m
DP[i,j] = min k∈{i+1,...,j−1} { i+1,..., j−1} { DP[i,k] + DP[k,j] + B[j] – B[i] }
The minimum cost is stored in DP[0,m+1] (again m is the size of break list)
For example, if we have a word HOLIDAY where N= 7, B={0,1,2}
We will add -1 in the beginning and 6 at the end as 7-1=6.
Current B ={-1, 0, 1, 2, 6}
Let us break the word[i,j] ( HOLIDAY) now as per our definition,
Cost of breaking the word is 0 when i == m.
S0,1= word[0,..0] // cost will be 0
S1,2= word[1 to 1]// cost will be 0
S2,3 =word[2 to 2] // cost will be 0
S3,4= word [3 to 6]
Structure of the DP table layout is shown below,

0 1 2 3|
1 0|
2 0|
3 0|
4 0|

Table (1) : DP structure table i=0,1,2,3 column and j=1,2,3,4 row

### (f) How is a non-zero entry of the table computed from its other entries? (Use your answer to (b) as a guide.)

Ans: Lets expand our answer from answer (e) and (b)
We enter DP[i,i+1] = 0 into our table. Since there is no cost for that. Next nonzero
entry of the DP table, Si,i+2 we take B[i+1] as one single breakpoint.

k=choices, B ={-1, 0, 1, 2, 6}
k=1 choice,
DP[0,2]= min{ DP[0,1] + D[1,2] + B[2] – B[0]}
= 0+0+ 1-(-1) =2
DP[1,3]= min{ DP[1,2] + D[2,3] + B[3] – B[1]}
= 0+0+ 2-0 =2
DP[2,4]= min{ DP[2,3] + D[3,4] + B[4] – B[2]}
= 0+0+ 6-1 =5
k=2 choices i+1 or i+2
DP[0,3]= min{ DP[0,1] + D[1,3] + B[3] – B[0], DP[0,2] + D[2,3] + B[3]
- B[0]}
=min{ (0+2+2-(-1)) , (2+0+2-(-1)) }
=5
k=2 choices i+1 or i+2
DP[1,4]= min{ DP[1,2] + D[2,4] + B[4] – B[1], DP[1,3] + D[3,4] + B[4]
- B[1]}
=min{ (0+5+6-0) , (2+0+6-0) }
=8
k= 3 choices, i+1 or i+2 or i+3
DP[0,4]= min{ DP[0,1] + D[1,4] + B[4] – B[0], DP[0,2] + D[2,4] + B[4]
- B[0], DP[0,3] + D[3,4] + B[4] – B[0]}
=min{ (0+8+6-(-1)) , (2+5+6-(-1)) , ( 5+0+6-(-1))}
=12

0 1 2 3 |
1 0 |
2 2 0 |
3 5 2 0|
4 12 8 5 0|

we get minimum cost 12 at DP[0,4] which is DP[0,m+1]

## (g) Identify the table entries which will be non-zero. (There is a simple relationship between the row and column labels related to the problem that identifies when a table entry is non-zero.)

Ans: DP[i,j] = min k∈{i+1,...,j−1} { i+1,..., j−1} { DP[i,k] + DP[k,j] + B[j] –
B[i] } it was already mentioned in answer (e) . i is the column and j is the row
here