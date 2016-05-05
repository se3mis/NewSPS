package costcenter.service;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import costcenter.ejb.CostCenterBeanRemote;
import costcenter.model.Area;
import costcenter.model.Gldeptin;
import costcenter.model.Gldeptm;
import costcenter.model.Province;

public class CostCenterEjb implements CostCenterBeanRemote{

	private Context context;
	private CostCenterBeanRemote bean; 
	public CostCenterEjb() {
		super();
		this.bean=lookupDao();
		
	}

	private CostCenterBeanRemote lookupDao() {
		try
		{
			 context = new InitialContext();
			 CostCenterBeanRemote bean = (CostCenterBeanRemote) context.lookup("CostCenterBean/remote");
			 return bean; 
		} catch (NamingException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public List<Area> getAllAreas( String webAppName) {
		return bean.getAllAreas(webAppName);
	}
	
	

	@Override
	public List<Province> getAllProvince( String webAppName) {
		return bean.getAllProvince(webAppName);
	}

	@Override
	public void createGldeptin(Gldeptin gldeptin, String webAppName) {
		bean.createGldeptin(gldeptin, webAppName);
		
	}

	@Override
	public List<Gldeptin> getAllGldeptin( String webAppName) {
		return bean.getAllGldeptin(webAppName);
	}

	@Override
	public void updateGldeptin(Gldeptin gldeptin, String webAppName) {
		bean.updateGldeptin(gldeptin, webAppName);
		
	}

	@Override
	public void removeGldeptin(Gldeptin gldeptin, String webAppName) {
		bean.removeGldeptin(gldeptin, webAppName);
		
	}

	@Override
	public void removeAll( String webAppName) {
		bean.removeAll(webAppName);
		
	}

	@Override
	public Gldeptin findById(String deptId, String webAppName) throws PersistenceException {
		return bean.findById(deptId, webAppName);
	}

	@Override
	public Gldeptin getFindByDeptId(String deptId, String webAppName) {
		return bean.getFindByDeptId(deptId, webAppName);
	}

	

	@Override
	public String findDeptName(String deptId, String webAppName) {
		return bean.findDeptName(deptId, webAppName);
	}

	@Override
	public Gldeptm findGldeptm(Gldeptm gldeptm, String webAppName) {
		return findGldeptm(gldeptm, webAppName);
	}

	@Override
	public Gldeptm findGldeptm(String deptId, String webAppName) {
		return bean.findGldeptm(deptId, webAppName);
	}

	@Override
	public List<String> findAreaDeptIdList(String deptId, String webAppName) {
		return bean.findAreaDeptIdList(deptId, webAppName);
	}

	@Override
	public List<String> findDgmDeptIdList(String deptId, String webAppName) {
		return bean.findDgmDeptIdList(deptId, webAppName);
	}

	@Override
	public List<Gldeptm> getAllGldeptm( String webAppName) {
		return bean.getAllGldeptm( webAppName);
	}

	@Override
	public List<String> findAgmDeptIdList(String deptId, String webAppName) {
		return bean.findAgmDeptIdList(deptId, webAppName);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CostCenterEjb ejb= new CostCenterEjb();
		////System.out.println(ejb.getAllAreas().size());
		//System.out.println(ejb.getAllAreas("SMCTestin"));
		//System.out.println(ejb.getAllProvince("SMCTestin").size());
		//System.out.println(ejb.getAllProvince("SMCTestin"));
		/**int count = 10;
		System.out.println(count % 2);
		System.out.println(count % 2 == 1?"****":"++++++");
		
		for(int i=1;i<=-100;i++){
			System.out.println("count : "+i);
		}
		
		//b = (byte)(a<<2);
		int n =0;
		//int x = 15;
		//n = x mod (3+2);
		
		System.out.println("n "+n);
		Math.sqrt(9);
		int intnumber = 7;
		switch(intnumber % 2){
		case 0:
			System.out.println("Even");
			break;
		case 1:
			System.out.println("Odd");
			break;
		}
		
		int counter = 2;
		do{
			System.out.println(counter);
			counter += 2;
		}while(counter<100);
		*/
		/**int sum=0,x;
		x=1;
		while(x<10){
		  
		  sum+=x;
		  ++x;
		  System.out.println("sum : " + sum + "  x : " + x);
		}
		System.out.println("the sum is : " + sum);*/
		double m[][]={
				{0*0,1*0,2*0,3*0},
				{0*1,1*1,2*1,3*1},
				{0*2,1*2,2*2,3*2},
				{0*3,1*3,2*3,3*3},
				
		};
		int i,j;
		for(i=0;i<4;i++){
			for(j=0;j<4;j++){
				System.out.println(m[i][j] +" ");
			    System.out.println();
		}
		}
		
		
		/**double c = 80.0,f;
		f=(5/9)*(c-32);*/
		//for(int i= 0;i<100;i++){
		//System.out.println(fibi(i));
		//}
		/**int i = 0;
		while(i < 10)
		{
			if(i==4)
			{
				break;
			}
			++i;
		}
		
		System.out.println("i= : " + i);*/
		
		/**double sum = 0;
		double a = 0;
		double p = 50000;
		double r = 0.1;
		double n =20;
		for(int i=1;i<=20;i++){
			a = p * Math.pow((1+0.1),i);
			p =a;
			System.out.println("interest : "+a);
			System.out.println("total : "+sum);
		}*/
		
		/**for(int i =0;i<3;i++){
			System.out.print("A");
		}
		System.out.println("*");
		*/
		//int[] a1={1,2,3};
		//int[] a2={4,5,6};
		//int[] a3={7,8,9};
		//System.out.println(a1[1]+" " + a2[1] +" " + a3[1]);
		//int
		
		/**int i=1,j=10;
		do{
			if(i>j){
				break;
			}
			j--;
		}while(++i < 5);
		System.out.println("i= "+i+" and j ="+j);*/
		/**int x= 17,a,b;
		a=x--;
		System.out.println("x= "+x+" and a ="+a +"and b =");
		b=--x;
		System.out.println("x= "+x+" and a ="+a +"and b ="+b);*/
		/**int size = 5;
		int tot = 0;
		int num[] = new int[5];
		num[0]=10;
		num[1]=12;
		num[2]=5;
		num[3]=20;
		num[4]=30;
		for(int i=0;i<4;i++){
			tot=tot+num[i];
		}
		System.out.println("total " +tot);*/
		/**int[] a1={1,2,3};
		int []a2={4,5,6};
		int a3[]={7,8,9};
		System.out.println(a1[1]+" "+a2[1] +" "+a3[1]);*/
		
		//int number = 10;
		//int digit;
		
		//while (number >0){
			//digit = number % 2;
			//System.out.print(digit);
			//number =number/2;
		//}
		/**for (int i=1; i<=10;i++) {
			for (int j=1;j<=5;j++)
			System.out.print('*');
			System.out.println();
		}*/
		
			System.out.println(factorial(20));

		
		
		//System.out.println(factorial(20));

	} 
	
	static int factorial(int n) 
	 {
	     if (n <= 1) {
	          return 1;
	     } else {
	          return n * factorial(n-1);
	     }
	     
	 }
	
	static int fibi(int n){
		if(n==0){
			return 0;
		}
		if(n==1){
			return 1;
		}
		else{
			return fibi(n-1)+fibi(n-2);
		}
	}
	
	
	
	
	
	
}
