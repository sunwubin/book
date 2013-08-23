package bimoku.search.worker;

public class Main {

	public static void main(String[] args){
		ReadWorker read=new ReadWorker(ProductEnum.DD);
		ReadWorker readdb=new ReadWorker(ProductEnum.DB);
		ReadWorker readam=new ReadWorker(ProductEnum.AM);
		ReadWorker readjd=new ReadWorker(ProductEnum.JD);
		ReadWorker readhd=new ReadWorker(ProductEnum.HD);
		
		Thread readx=new Thread(read);
		Thread ready=new Thread(readdb);
		Thread readz=new Thread(readam);
		Thread readj=new Thread(readjd);
		Thread readh=new Thread(readhd);
		
		
		WriteWorker write=new WriteWorker();
		
		WriteWorker write2=new WriteWorker();
		
		WriteWorker write3=new WriteWorker();
		
		WriteWorker write4=new WriteWorker();
		
		WriteWorker write5=new WriteWorker();
		
		Thread writex5=new Thread(write5);
		
		Thread writex4=new Thread(write4);
		Thread writex3=new Thread(write3);
		Thread writex2=new Thread(write2);
		Thread writex=new Thread(write);
		readx.start();
		ready.start();
		readz.start();
		readj.start();
		readh.start();
		
		writex.start();
		writex2.start();
		writex3.start();
		writex4.start();
		writex5.start();
		
		
	}
}
