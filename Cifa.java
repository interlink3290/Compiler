import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.*;

public class Cifa {
	public  String txt2String(File file) throws IOException
	{
		StringBuilder result =new StringBuilder();
		try {
			BufferedReader br= new BufferedReader(new FileReader(file));
			String s=null;
			while((s=br.readLine())!=null){
				result.append(System.lineSeparator()+s);
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result.toString();
	}
	public String linkString(String[] arg) {
		String temp ="";
		int i=0,l=arg.length;
		for(i=0 ; i<l ;i++) {
			temp += arg[i] + " ";
		}
		return temp;
	}
	public  char[] preTreatment(char[] sourcefile)
	{
		char []afterfile = new char[10000];
		int index=0;
		if(sourcefile.length!=0)
		{
			for(int i=0;i<sourcefile.length;i++)
			{
				
				if(sourcefile[i]!='\n'&&sourcefile[i]!='\r'&&sourcefile[i]!='\t')
				{
					afterfile[index]=sourcefile[i];
					index++;
				}
			}
			index++;
			afterfile[index]='\0';
		}
		return afterfile;
	}
	public int isReserve(String s,String []reserve)
	{
		int index=-1;
		for(int i=0;i<reserve.length;i++)
		{
			if(s.equals(reserve[i]))
			{
				index=i;
				break;
			}
		}
		return index;
	}
	public int isSymbol(String s,String []symbol)
	{
		int index=-1;
		for(int i=0;i<symbol.length;i++)
		{
			if(s.equals(symbol[i]+""))
			{
				index=i;
				break;
			}
		}
		return index;
	}
	public  boolean isDigit(char c)
	{
		if(c>='0'&&c<='9')
		{
			return true;
		}
		else
			return false;
	}
	public boolean isLetter(char c)
	{
		if((c>='a'&&c<='z')||(c>='A'&&c<'Z'))
		{
			return true;
		}
		else
			return false;
	}
		
    public static void main(String[] args) throws IOException {
    	String []reserve={"BEGIN","END","FOR","IF","THEN","ELSE"};
    	String []isreserve={"Begin","End","For","If","Then","Else"};
    	String []symbol= { ":", "+", "*", ",", "(", ")", ":=","="};
    	String []issymbol= { "Colon", "Plus", "Star", "Comma", "LParenthesis", "RParenthesis", "Assign"};
    	Cifa la=new Cifa();
    	File file=new File(args[0]);
		String source=la.txt2String(file);
		char sourcefile[] = source.toCharArray();  
		char afterfile[]=la.preTreatment(sourcefile);
		int index=0;
		String temp="";
		while(afterfile[index]!='\0'){
			if(la.isLetter(afterfile[index]))
			{
				temp+=afterfile[index];
				while(la.isLetter(afterfile[index+1])||la.isDigit(afterfile[index+1]))
				{
					index++;
					temp+=afterfile[index];
				}
				if(la.isReserve(temp, reserve)!=-1)
					System.out.println(isreserve[la.isReserve(temp, reserve)]);
				else
					System.out.println("Ident("+temp+")");
			}
			else if(la.isDigit(afterfile[index]))
			{
				temp+=afterfile[index];
				while(la.isDigit(afterfile[index+1]))
				{
					index++;
					temp+=afterfile[index];
				}
				System.out.println("Int("+temp+")");
			}
			else if(afterfile[index]!=' ')
			{
				temp+=afterfile[index];
				while(la.isSymbol( String.valueOf(afterfile[index+1]),symbol)!=-1)
				{
					index++;
					temp+=afterfile[index];
				}
				
				if(la.isSymbol(temp, symbol)!=-1)
				{
					System.out.println(issymbol[la.isSymbol(temp, symbol)]);
					temp="";
					index++;
					continue;
				}
				else
				{
					System.out.println("Unknown");
					System.exit(0);
				}
				
			}
			temp="";
			index++;
		}
    	
    }

}

