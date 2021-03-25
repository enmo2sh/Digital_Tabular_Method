package digital;
import java.util.*;
 
 
public class one {
	public static void program(int n,LinkedList<Integer>Min,LinkedList<Integer>DC,LinkedList<String>essential,LinkedList<String>essential2,LinkedList<String>repeat,LinkedList<String>repeat2) {
		int totalCost1=0;
		int totalCost2=0;
		int pos=0;
		
		 LinkedList<String>cirLines=new LinkedList<String>();
		fun check = new fun ();
		LinkedList<Integer>Max=new LinkedList<Integer>();
		 for (int p=0;p<Math.pow(2, n);p++) {
			 int flag1=0;
			 int flag2=0;
			 for (int f=0;f<Min.size();f++) {
				 if (p==Min.get(f)) 
				 flag1=1;
			 }
			 for(int k=0;k<DC.size();k++) {
				 if (p==DC.get(k))
					 flag2=1;
			 }
			if ((flag1==0)&&(flag2==0))
				 Max.add(p); 
		 }
		 while(pos<2) {
	 int s ;
	 if (pos==0)
	 s= Min.size()+DC.size();
	 else
		 s=Max.size()+DC.size();
	int arr [][] = new int [n+1][s+1]; 

	if(pos==0) {
	for (int i=0 ;i<Min.size() ; i++) {
		int z= check.BinaryNo(Min.get(i));
		arr[z][arr[z][s]] = Min.get(i);
		arr[z][s]++;
	}}
	else {
		for (int i=0 ;i<Max.size() ; i++) {
			int z= check.BinaryNo(Max.get(i));
			arr[z][arr[z][s]] = Max.get(i);
			arr[z][s]++;
	}}
	for (int i=0 ;i<DC.size() ; i++) {
		int z= check.BinaryNo(DC.get(i));
		arr[z][arr[z][s]] = DC.get(i);
		arr[z][s]++;
	}

	int total =0;
	for(int i =0;i<n+1;i++) {
		total+= arr[i][s];
	}
	int arr2 [][] = new int [total+n+1][3];
	int c=0;

	for(int i=0;i<n+1;i++) {
		for(int j=0;j<arr[i][s];j++) {
			arr2[c++][0]=arr[i][j];
		}
				arr2[c++][0]=-5;

	}
	LinkedList<Integer> prime= new LinkedList <Integer>();

	int finished [][]= check.merge(arr2,prime);
	for (int i=1;i<prime.size()-2;i=i+2) {
		for (int j=i+2;j<prime.size();j+=2) {
			boolean z;
			z=check.compare(prime.get(i), prime.get(j));
			if (z&&(prime.get(i-1)==prime.get(j-1))) {
				prime.remove(i);
				prime.remove(i-1);
				i=i-2;
				break;
			}
		}
	}

	for (int i=0;i<prime.size();i++) {
		System.out.println(prime.get(i) );
	}

	ArrayList<Integer> []ToComb=new ArrayList[prime.size()/2];
	ToComb = check.PreComb(prime);

	for(int i=0 ;i<ToComb.length;i++) {
		for(int j=0;j<ToComb[i].size();j++)
			System.out.print(ToComb[i].get(j) + " ");
		System.out.println();
	}

	String[][]cost=new String [prime.size()/2][2];

	cost=check.change(n,prime,cirLines,pos);
	System.out.println("prime implicants are:");
	for(int i=0;i<cost.length;i++) {
		for(int j=0;j<2;j++) {
			System.out.print(cost[i][j]+' ');
		}
		System.out.println();

	}
	int u;
   if (pos==0)
   	 u=Min.size()+1;
   else
   	 u=Max.size()+1;
	int [][] table = new int [cost.length+2][u];
	table=check.GetTable(Min, cost,Max,pos);
	int row=1;
	for(int i =0;i<ToComb.length;i++) {
		if (ToComb[i].size()>1) {
		int a[]=new int [ToComb[i].size()-1];
		int b[]=new int [ToComb[i].size()-1];
		int no=0;
		for(int j=1;j<ToComb[i].size();j++) {
			b[no++]=ToComb[i].get(j);
		}
		check.Combination (a,b,0,b.length,table,ToComb[i].get(0),row);
		row++;

		}
		else {
			for(int j=0;j<table[0].length;j++) {
				if(table[0][j]==ToComb[i].get(0)) {
					table[i+1][j]=1;
					break;
				}
			}
			row++;
		}
	}
 for (int i=0;i<table.length;i++) {
	 for (int j=0;j<table[0].length;j++) {
		 System.out.print(table[i][j]);
	 }
	 System.out.println();
 }
	check.coulmn(table);
	if(pos==0) {
	essential=check.essential_table(table, cost,repeat);
	check.row(table);
	essential=check.essential_table(table, cost,repeat);
	}
	else {
		essential2=check.essential_table(table, cost,repeat2);
		check.row(table);
		essential2=check.essential_table(table, cost,repeat2);
	}

	for(int i=0;i<table.length;i++) {
		for(int j=0;j<table[i].length;j++)
			System.out.print(table[i][j]+" ");
		System.out.println();
	}
	if(pos==0)
		check.petrike(table, cost,essential,repeat);
	else
		check.petrike(table, cost,essential2,repeat2);	

	System.out.println();
	for(int i=0;i<table.length;i++) {
		for(int j=0;j<table[i].length;j++)
			System.out.print(table[i][j]+" ");
		System.out.println();
	}
	if(pos==0) {
	for (int i=0;i<essential.size();i++)
		System.out.println(essential.get(i));
	}
	else {
		for (int i=0;i<essential2.size();i++)
			System.out.println(essential2.get(i));
	}
	if (pos==0) {
	for (int i=0;i<cost.length;i++) {
		for (int k=0;k<essential.size();k++) {
			if (check.compare2(cost[i][0],essential.get(k) ))
		      totalCost1=totalCost1+table[i+1][table[0].length-1];
	}
	}
	System.out.println("the total cost of sop "+totalCost1);
	}
	else {
		for (int i=0;i<cost.length;i++) {
			for (int k=0;k<essential2.size();k++) {
				if (check.compare2(cost[i][0],essential2.get(k) ))
			      totalCost2=totalCost2+table[i+1][table[0].length-1];
		}
		}
		System.out.println("the total cost of pos "+totalCost2);

	}
	if(pos==0&&(n==6||n>6)) {
		for (int j=0;j<repeat.size();j++) {
			for (int h=0;h<cirLines.size();h++) {
				String temp=cirLines.get(h)+'`';
				if (check.compare2(temp, repeat.get(j))) {
					cirLines.add(h+1, temp);
				}
			}
		}
	int lines = cirLines.size();
	int and =essential.size();
	GUI go=new GUI() ;
	LinkedList<String> essential3 = new LinkedList<String>();
	go.setProps(and,lines,cirLines,essential,essential3,totalCost1,0);
	pos+=2;
	}
	pos++;
}
		 if(n<6) {
		 if (totalCost1<totalCost2) {
			 for (int j=0;j<repeat.size();j++) {
					for (int h=0;h<cirLines.size();h++) {
						String temp=cirLines.get(h)+'`';
						if (check.compare2(temp, repeat.get(j))) {
							cirLines.add(h+1, temp);
						}
					}
				}
			int lines = cirLines.size();
			int and =essential.size();
			GUI go=new GUI() ;
			go.setProps(and,lines,cirLines,essential,essential2,totalCost1,totalCost2);

		 }
		 else {
			 for (int j=0;j<repeat2.size();j++) {
					for (int h=0;h<cirLines.size();h++) {
						String temp=cirLines.get(h)+'`';
						if (check.compare2(temp, repeat2.get(j))) {
							cirLines.add(h+1, temp);
						}
					}
				}
			int lines = cirLines.size();
			int and =essential2.size();
			GUIP go=new GUIP() ;
			go.setProps(and,lines,cirLines,essential2,essential,totalCost1,totalCost2);

		 }
		 System.out.println("the minimization of the circuit is:");
		 if (totalCost1<totalCost2) {
			 System.out.print('('+essential.get(0)+')');
			for (int i=1;i<essential.size();i++)
				System.out.print("+"+ '('+essential.get(i)+')');
			System.out.println();
			System.out.print("the total cost of the new circuit:"+totalCost1);

		 }
		 else if (totalCost1==totalCost2) {
			 System.out.print('('+essential.get(0)+')');
				for (int i=1;i<essential.size();i++)
					System.out.print("+"+ '('+essential.get(i)+')');
				System.out.println();
				System.out.println(" OR");
				 for (int i=0;i<essential2.size();i++) {
					 String[] parts=(essential2.get(i)).split(" ");
					 System.out.print('('+parts[0]);
					 for (int k=1;k<parts.length;k++) {
						 System.out.print('+'+parts[k]);	 
					 }
					 System.out.print(')') ;
				 }
				 System.out.println();
					System.out.print("the total cost of the new circuit:"+totalCost2);

		 }
		 else {
			 for (int i=0;i<essential2.size();i++) {
				 String[] parts=(essential2.get(i)).split(" ");
				 System.out.print('('+parts[0]);
				 for (int k=1;k<parts.length;k++) {
					 System.out.print('+'+parts[k]);	 
				 }
				 System.out.print(')') ;
			 }
			 System.out.println();
				System.out.print("the total cost of the new circuit:"+totalCost2);
		 }


		
}
}
}