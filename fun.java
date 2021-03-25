package digital;
import java.util.*;
public class fun {
	int s=0;
 
	int BinaryNo (int x) {
		int counter=0;
		while (x>0) {
			if (x%2==1)
				counter++;
			x/=2;
		}
		return counter;
	}
 
	LinkedList <Integer> ReturnComma (int arr[][]) {
		LinkedList <Integer> comma = new LinkedList <Integer> ();
		for (int i=0 ;i<arr.length ;i++) {
			if (arr[i][0]==-5)
				comma.add(i);
		}
		for (int i=comma.size()-1;i>0;i--) {
			if (comma.get(i)-comma.get(i-1)==1) 
				comma.remove(i);
			else
				break;
 
		}
		return comma;
	}
 
	Boolean compare (int no1,int no2) {
		String a =Integer.toString(no1);
		String b = Integer.toString(no2);
		char [] a2= a.toCharArray();
		char [] b2= b.toCharArray();
		Arrays.sort(a2);
		Arrays.sort(b2);
		if (Arrays.equals(a2,b2))
			return true;
		return false ;
	}
 
	 int [][] split (ArrayList <Integer> [] StepOne){
		 int n=0;
		 for(int i=0;i<StepOne.length;i++) {
			  n+= StepOne[i].size();
		 }
		int x [][] = new int [n][3];
		int c=0;
		for(int i=0;i<StepOne.length;i++) {
			if (StepOne[i].get(0)==-5) 
				x[c++][0]=-5;
			for (int j=1;j<StepOne[i].size();j++) {
				x[c][0]=StepOne[i].get(0);
				x[c++][1]=StepOne[i].get(j);
 
			}
 
		}
		return x;
 
	}
 
	 ArrayList <Integer> [] collect (int arr[][],LinkedList <Integer> prime){
		 ArrayList <Integer>[] StepOne = new ArrayList [arr.length];
			for(int i=0;i<arr.length;i++) {
				StepOne[i]=new ArrayList <Integer>();
				StepOne[i].add(arr[i][0]);
 
 
			}
			LinkedList <Integer> comma = new LinkedList <Integer>();
			comma = ReturnComma (arr);
			int o=0, y=comma.get(o)+1;
			int border=0;
			for(int i=0; i<comma.get(comma.size()-1) ;i++) { 
				while (arr[i][0]==-5) {
					i++;
					o++;
					y=comma.get(o)+1;
				}
			for(int j=y ; (j<arr.length) && (arr[j][0]!=-5) ;j++) {
				int diff=arr[j][0]-arr[i][0];
				if ((compare(arr[j][1],arr[i][1]) && BinaryNo(diff)==1) && arr[i][0]<arr[j][0] ) {
					int k=diff;
					int c=0;
					while (k>0) {
						k/=10;
						c++;
					}
					StepOne [i].add(diff+arr[i][1]*(int)(Math.pow(10, c+1)));
					arr[i][2]=1;
					arr[j][2]=1;
				}
			}
			if ((StepOne[i].size()==1)&&(StepOne[i].get(0)!=-5)&&(arr[i][2]==0)) {
 
				prime.add(arr[i][0]);
				prime.add(arr[i][1]);
			}
			border=i;
 
		}
			for (int i=border+1;i<arr.length;i++) {
				if ((arr[i][2]==0)&&(arr[i][0]!=-5)&&(arr[i][0]!=0)) {
					prime.add(arr[i][0]);
					prime.add(arr[i][1]);
				}
 
			}
 
		return StepOne;
	}
 
	 int [][] merge (int arr [][],LinkedList <Integer> prime){
		 int flag=0;
		 ArrayList <Integer>[] StepOne =collect (arr,prime);
		 int arr2 [][]=split(StepOne);
 
		 for(int i=0;i<arr.length;i++) {
				if(arr[i][2]==1)
					flag=1;
			}
		 if (flag==1)
			merge (arr2,prime);
 
 
		 return arr2;
	 }
 
	String [][] change (int n,LinkedList<Integer>prime,LinkedList<String> cirLines,int pos){
 
		String [][]Letters =new String [2][n];
		String[][] cost=new String [prime.size()/2][2];
		for (int j=0;j<n;j++) {
			Letters[0][j]=Character.toString((char)((j%26)+65))+Character.toString((char)((j/26)+49));
			if (pos==0)
			cirLines.add(Letters[0][j]);
		}
		int s=0;
		for (int i=0;i<prime.size()-1;i=i+2) {
		for (int j=0;j<n;j++) {
			Letters[1][j]=Character.toString('0');
		}
			int element =prime.get(i);
			int k=n-1;
			while ((element>=0)&&(k>=0)&&(pos==0)) {
				Letters[1][k]=Integer.toString(element%2);
				element=element/2;
				k--;
			}
			while ((element>=0)&&(k>=0)&&(pos==1)) {
				if(element%2==0)
				Letters[1][k]=Integer.toString(1);
				else
					Letters[1][k]=Integer.toString(0);
				element=element/2;
				k--;
			}
			int diff=prime.get(i+1);
			int h=10;
			int d,count=-1;
			while (diff>=1) {
				if (diff%h==diff%(10*h)) {
					d=diff%h;
					while(d>0) {
						d=d/2;
						count++;
					}
					Letters[1][n-1-count]=Character.toString('*');
					h=h*10;
					diff=diff/h;
					count=-1;
					h=10;
				}
				else {
					h=10*h;
				}
	    	}
			int v=1;
			int counter=0;
			for (int m=0;m<n;m++) {
				if(Letters[1][m].equals("0")) {
					if(cost[s][0]==null)
					cost[s][0]=Letters[0][m]+Character.toString('`');
					else {
						cost[s][0]=cost[s][0]+Character.toString( ' ')+Letters[0][m]+Character.toString('`');
						counter=1;
					}
					v=v+2;
			
				}
				else if (Letters[1][m].equals("1")) { 
					if(cost[s][0]==null)
					cost[s][0]=Letters[0][m];
					else {
						cost[s][0]=cost[s][0]+Character.toString( ' ')+Letters[0][m];
						counter=1;
					}
					v=v+1;
				}
			}
			if (counter==0)
			cost[s][1]=Integer.toString(v-1);
			else
			cost[s][1]=Integer.toString(v);
			v=1;
			counter=0;
			s++;
		}	
		return cost;
	}
 
	ArrayList<Integer>[]  PreComb(LinkedList <Integer> prime){
		ArrayList<Integer>[] ToComb = new ArrayList[prime.size()/2];
		int count =0;
		for(int i=0;i<prime.size();i++) {
			ToComb[count]=new ArrayList<Integer>();
			ToComb[count].add(prime.get(i));
			i++; int h=10;
			int diff=prime.get(i);
			while (diff>=1) {
				if (diff%h==diff%(10*h)) {
					ToComb[count].add(diff%h);
					h=h*10;
					diff=diff/h;
					h=10;
				}
 
				else {
					h=10*h;
				}
	    	}
			count++;
		}
		return ToComb;
	}
 
	void Combination (int a[],int b[],int k,int n,int table[][],int first,int count) {
		if(k==n-1) {
			a[k]=0; GetSum(a, b,n,table,first,count);
			a[k]=1; GetSum(a, b,n,table,first,count);
			return;
		}
		a[k]=0; Combination(a,b,k+1,n,table,first,count);
		a[k]=1; Combination(a,b,k+1,n,table,first,count);
	}
 
	void GetSum (int a[],int b[] ,int n, int table[][],int first,int count) {
		int sum=0;
		for(int i=0;i<n;i++) {
			if(a[i]==1) 
				sum+=b[i];		
		}
		sum+=first;
		for(int j=0;j<table[0].length-1;j++) {
			if(table[0][j]==sum)
				table[count][j]=1;
		}
	}
 
	int [][] GetTable (LinkedList <Integer>Min, String[][]cost,LinkedList<Integer>Max,int pos){
		int u;
        if (pos==0)
        	 u=Min.size()+1;
        else
        	 u=Max.size()+1;
		int [][] table = new int [cost.length+2][u];
		int n = cost.length+2;
		int m = u;
		if (pos==0) {
		for(int j=0;j<m-1;j++)
			table[0][j]=Min.get(j);
		}
		else {
			for(int j=0;j<m-1;j++)
				table[0][j]=Max.get(j);	
		}
		for(int i=1;i<n-1;i++) {
			table[i][m-1]=Integer.parseInt(cost[i-1][1]);
		}
		return table;
	}
 
	LinkedList<String> essential_table(int table[][],String [][]cost,LinkedList<String>repeat) {
		int n=table.length;
		int m = table[0].length;
		LinkedList<String> essential = new LinkedList ();
		for(int j=0;j<m-1;j++) {
			int counter=0;
			int index=1;
			for(int i=1;i<n-1;i++) {
				if(table[i][j]==1) {
					counter++;
					index =i;
				}
			}
			if (counter==1) {
				int flag=0;
				  for(int l=0;l<essential.size();l++) {
					 if(cost[index-1][0].equals(essential.get(l)))
						flag=1;
				 }
				 if(flag==0){
					essential.add(cost[index-1][0]);
					inverter(table, essential.get(essential.size()-1), cost,repeat,essential);
					table[n-1][j]=-1;
					for(int k =0;k<m-1;k++) {
						if(table[index][k]==1) {
							table[n-1][k]=-1;
						}
					}
				 }
			}
			else if (counter==0) {
				table[n-1][j]=-1;
				
			}
		}
		System.out.println("essential implicants are:");
		for(int i=0;i<essential.size();i++) 
			System.out.println(essential.get(i));
		return essential;
	}
 
	void coulmn (int[][]table){
		ArrayList<Integer>[] Ones =new ArrayList[table[0].length-1];
		for(int i=0;i<table[0].length-1;i++) {
			Ones[i]=new ArrayList <Integer>();
		}
		int s=0;
		for (int j=0;j<table[0].length-1;j++) {
			int counter=0;
			if (table[table.length-1][j]==0) {
				for (int i=1;i<table.length-1;i++) {
					if (table[i][j]==1) {
						Ones[s].add(i);
						counter++;
					}
 
				}
				Ones[s].add(0, j);
				Ones[s].add(1, counter);
				s++;
			}
		}
		for(int j=0;j<s;j++) {
			if (table[table.length-1][Ones[j].get(0)]!=-1) {
			for (int k=0;k<s;k++) {
				if (Ones[j].get(0)!=Ones[k].get(0)) {
				if (Ones[j].get(1)>0) {
					if(Ones[j].get(1)<=Ones[k].get(1)) {
				        int flag2=0;
						for (int f=2;f<(2+Ones[j].get(1));f++) {
							int flag=0;
							for(int y=2;y<(2+Ones[k].get(1));y++) {
								if(Ones[j].get(f)==Ones[k].get(y))
								    flag=1;
							}
							if (flag==1)
							flag2++;
						}
						if(flag2==Ones[j].get(1)) {
							table[table.length-1][Ones[k].get(0)]=-1;
						}
					}
					}
				}
 
				}
			}
		}
	}	
 
	void row (int [][] table) {
		ArrayList<Integer>[] Ones =new ArrayList[table.length-1];
		for(int i=0;i<table.length-1;i++) 
			Ones[i]=new ArrayList <Integer>();
		int s=0;
		for (int i=0;i<table.length-1;i++) {
			int counter=0;
				for (int j=0;j<table[0].length-1;j++) {
					if ((table[i][j]==1)&&(table[table.length-1][j]!=-1)) {
						Ones[s].add(j);
						counter++;
					}
 
				}
					Ones[s].add(0, i);
					Ones[s].add(1, counter);
					s++;
				}
			for(int j=0;j<s;j++) {
				if (table[j][table[0].length-1]!=0) {
				for (int k=0;k<s;k++) {
					if ((Ones[j].get(0)!=Ones[k].get(0))&&(table[k][table[0].length-1]!=0)) {
					if (Ones[j].get(1)>0) {
					if((Ones[j].get(1)<=Ones[k].get(1))&&(table[Ones[j].get(0)][table[0].length-1]>table[Ones[k].get(0)][table[0].length-1])) {
				        int flag2=0;
						for (int f=2;f<(2+Ones[j].get(1));f++) {
							int flag=0;
							for(int y=2;y<(2+Ones[k].get(1));y++) {
								if(Ones[j].get(f)==Ones[k].get(y))
								    flag=1;
							}
							if (flag==1)
							flag2++;
						}
						if(flag2==Ones[j].get(1)) {
							for (int l=0;l<table[0].length;l++) {
							table[Ones[j].get(0)][l]=0;
							}
						}
					}
					}
					}
				}
				}
			}
	}
 
	Boolean compare2 (String a,String b) {
		char [] a2= a.toCharArray();
		char [] b2= b.toCharArray();
		Arrays.sort(a2);
		Arrays.sort(b2);
		if (Arrays.equals(a2,b2))
			return true;
		return false ;
	}
 
	void inverter (int [][]table,String temp,String [][]cost,LinkedList <String> repeat,LinkedList<String>essential) {
		 String[] parts = temp.split(" ");
		 int count=0;
		 for(int j=0;j<parts.length;j++) {
			 int flag=0;
			 char [] a= parts[j].toCharArray();
			 if(a[a.length-1]=='`') {
				 for(int l=0;l<repeat.size();l++) {
					 if(compare2(parts[j],repeat.get(l)))
						flag=1;
				 }
				 if(flag==0) {
					 repeat.add(parts[j]);
				 count++;
				 }
			 }
		 }
		 for(int i=0;i<cost.length;i++) {
				int flag=0;
				 for(int b=0;b<essential.size();b++) {
				 if(compare2(cost[i][0],essential.get(b))) 
					 flag=1;
				 }
				 if(flag==0) {
				 String[] part = cost[i][0].split(" ");
				  for(int j=0;j<part.length;j++) {
					 char [] a= part[j].toCharArray();
					 if(a[a.length-1]=='`') {
						 for(int k=repeat.size()-1;k>=repeat.size()-count;k--) {
							 if(compare2(part[j],repeat.get(k))) {
								 table[i+1][table[0].length-1]--;
		 
							 }
		 
						 }
		 
					 }
				  }
				 }
			 	}
			}
 
	void petrike (int[][] table,String[][]cost,LinkedList<String>essential,LinkedList<String>repeat) {
		int count=0;
		for (int i=0;i<table[0].length-1;i++) {
			if (table[table.length-1][i]==0) {
				count++;
			}
			else {
				for(int j=1;j<table.length-1;j++) {
					table[j][i]=0;
				}
			}
		}
		if(count>0) {
			LinkedList<Integer> merda = new LinkedList <Integer>();
			for(int i=1;i<table.length-1;i++) {
				for(int j=0;j<table[0].length-1;j++) {
					if (table[i][j]==1) {
						merda.add(i);
						break;
					}
				}
 
			}
			int []a=new int [merda.size()];
			 ArrayList <Integer> [] choose=new ArrayList [(int) Math.pow(2, merda.size())];
			for(int h=0;h<choose.length;h++)
				choose[h]=new ArrayList<Integer>();
			comb_petrike(a,merda,0,merda.size(),table,choose);
			int less [][]=new int [choose.length][2];
			for (int i=1;i<choose.length;i++) {
				less[i][0]=i;
				int z=choose[i].size()-1;
				less[i][1]=choose[i].get(z);
			}
			for (int i=0;i<less.length-1;i++) {
				for (int j=0;j<less.length-1-i;j++) {
					if (less[j][1]>less[j+1][1]) {
						int temp=less[j][1];
						less[j][1]=less[j+1][1];
						less[j+1][1]=temp;
						int temp2=less[j][0];
						less[j][0]=less[j+1][0];
						less[j+1][0]=temp2;
					}
			}
			}
			for (int i=0;i<less.length;i++) {
				if (cover(less[i][0],choose,table)) {
					for (int j=0;j<choose[less[i][0]].size()-1;j++) {
						essential.add(cost[choose[less[i][0]].get(j)-1][0]);
						inverter(table,essential.get(essential.size()-1),cost,repeat,essential);
					}
					break;
				}
			}
		}
 
	}
 
	void comb_petrike(int[]a,LinkedList <Integer>merda,int k,int n,int [][]table,ArrayList<Integer>[]choose){
		if(k==n-1) {
			a[k]=0; Cost_petrike(a, merda,table,choose);
			a[k]=1; Cost_petrike(a, merda,table,choose);
			return;
		}
		a[k]=0; comb_petrike(a,merda,k+1,n,table,choose);
		a[k]=1; comb_petrike(a,merda,k+1,n,table,choose);
	}
 
	void Cost_petrike(int[]a,LinkedList<Integer>merda,int[][]table,ArrayList<Integer>[]choose) {
		int cost=0;
		for (int i=0;i<a.length;i++) {
			if (a[i]==1) {
				choose[s].add(merda.get(i));
				cost+=table[merda.get(i)][table[0].length-1];
			}
		}
		choose[s].add(cost);
		s++;
	}
 
	Boolean cover (int n, ArrayList<Integer>[]choose,int [][]table) {

		int [][]fake=new int [table.length][table[0].length];
		for (int i=0;i<table.length;i++) {
			for(int j=0;j<table[0].length;j++)
				fake[i][j]=table[i][j];
		}
		for (int i=0;i<choose[n].size()-1;i++) {
			int m=choose[n].get(i);
			for (int k=0;k<table[0].length-1;k++) {
				if (fake [m][k]==1) 
					fake [table.length-1][k]=-1;	
			}
		}
		for (int i=0;i<table[0].length-1;i++) {
			if (fake[table.length-1][i]==0)
				return false;
		}
 
		return true;
 
	}
 
}	
