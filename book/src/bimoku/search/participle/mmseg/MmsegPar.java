package bimoku.search.participle.mmseg;

import java.io.File;
import java.io.StringReader;

import com.chenlb.mmseg4j.ComplexSeg;
import com.chenlb.mmseg4j.Dictionary;
import com.chenlb.mmseg4j.MMSeg;
import com.chenlb.mmseg4j.Seg;
import com.chenlb.mmseg4j.Word;

public class MmsegPar {
	
	public static void main(String[] args) throws Exception{
		String txt = ""; 
		txt = "那个好看的笑容里面全是悲伤，他在行尸走肉的活着，他的故事悲伤的像一场没有结局的黑白电影，他是她小说里的主角， 她懂他，他爱过她，她不知道自己是爱他的的外表，还是爱他的故事，还是爱他身上的那个自己。"; 
		File file = new File(MmsegPar.class.getResource("").toString()+"data");//词典的目录
		//System.out.println(MmsegPar.class.getResource("").toString()+"data");  
		Dictionary dic = Dictionary.getInstance(file);//建立词典实例，与比较老的版本中不相同。不能直接new。
		Seg seg = null; 
		//			seg = new SimpleSeg(dic); 
		//seg = new ComplexSeg(dic);
		seg = new ComplexSeg(dic);
		MMSeg mmSeg = new MMSeg(new StringReader(txt), seg); 
		Word word = null;
		while((word = mmSeg.next())!=null) {
			
				if(word != null) { 
					System.out.print(word + "|"); 
				
			} 
		}

	}

}
