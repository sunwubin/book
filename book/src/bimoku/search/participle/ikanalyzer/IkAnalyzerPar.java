package bimoku.search.participle.ikanalyzer;

import java.io.StringReader;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import bimoku.search.util.log.LogOutPut;

public class IkAnalyzerPar {
	
	public static void main(String[] args){
		//独立Lucene实现
		String text = "管理与实践";
		StringReader re = new StringReader(text);
		IKSegmenter ik = new IKSegmenter(re,true);
		Lexeme lex = null;
		try {
		    while((lex=ik.next())!=null){
		    System.out.print(lex.getLexemeText()+"|");
		}
	}catch(Exception e){
		e.printStackTrace();
		LogOutPut.outError(e.getMessage());
	}
	}

}
