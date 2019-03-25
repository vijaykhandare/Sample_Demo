class Q
{
int num; // this is line
boolean valueset=false;//this is boolean value


	public synchronized void put(int  num) 
	
	{	
		while(valueset)
		{
			
			try
			{
				
				wait();
			}catch(Exception e)
			{
				System.out.println(e);
			}
			
		}
		System.out.println("put :- "+num);
		this.num=num;
		valueset=true;
		notify();
		
		
		
	}
	
	
	public synchronized void get()
	{

		
		while(!valueset)
		{
			
			try
			{
				
				wait();
			}catch(Exception e)
			{
				System.out.println(e);
			}
			
		}
		System.out.println("get :- "+num);
		valueset=false;
		notify();
	}
}


class produce implements Runnable
{
	Q q;
	
	produce( Q q)
	{
		this.q=q;
		
	
	Thread t=new Thread(this,"produce");
	t.start();
	}
	public void run()
	{int i=0;
		while(true)
		{
			q.put(i++);
			try{
			Thread.sleep(1000);}
			catch(Exception w)
			{
				System.out.println();
			}
		}
	}
	
	
}

class consume implements Runnable
{

	Q q;
	consume(Q q)
	{
		this.q=q;

		Thread t=new Thread(this,"consume");
		t.start();
	}
	
	
	public void run()
	{
	
		
		while(true)
		{
			q.get();
			try{
			Thread.sleep(10000);
			}catch(Exception w)
			{
				
			}
			
		}
	}
}
public class A1 
{
	public static void main(String[] args) {
		Q q=new Q();
		new produce(q);
		new consume(q);
	}
	
}


