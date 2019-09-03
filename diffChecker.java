import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class diffChecker
{
  static final long mod=(int)1e9+7;
  public static void main(String[] args) throws Exception
  {
    Scanner ina=new Scanner(new File("a.txt"));
    Scanner inb=new Scanner(new File("b.txt"));
    PrintWriter pw=new PrintWriter(System.out);
    ArrayList<String> a=new ArrayList();
    ArrayList<String> b=new ArrayList();
    while(ina.hasNextLine())
      a.add(ina.nextLine());
    while(inb.hasNextLine())
      b.add(inb.nextLine()); 
    int[][] dp=new int[b.size()+1][a.size()+1];
    int[][] path=new int[b.size()+1][a.size()+1];
    Arrays.fill(path[0],2);
    for(int i=1;i<=b.size();i++)
      path[i][0]=1;
    for(int i=1;i<=b.size();i++)
    {
      for(int j=1;j<=a.size();j++)
      {
        if(dp[i][j-1]>=dp[i-1][j])
        {
          dp[i][j]=dp[i][j-1];
          path[i][j]=2;
        }
        else
        {
          dp[i][j]=dp[i-1][j];
          path[i][j]=1;
        }
        if(match(a.get(j-1),b.get(i-1)))
        {
          if(dp[i-1][j-1]+1>dp[i][j-1] && dp[i-1][j-1]+1>dp[i-1][j])
          {
            dp[i][j]=dp[i-1][j-1]+1;
            path[i][j]=3;
          }
        }
      }
    }
    pw.print(find(b.size(),a.size(),path,a,b));
    pw.flush();
  }

  public static String find(int i,int j,int[][] path,ArrayList<String> a,ArrayList<String> b)
  {
    if(i==0 && j==0)
      return "";

    if(path[i][j]==1)
      return find(i-1,j,path,a,b)+b.get(i-1)+"//added \n";

    if(path[i][j]==2)
      return find(i,j-1,path,a,b)+a.get(j-1)+"//deleted \n";

    return find(i-1,j-1,path,a,b)+a.get(j-1)+"\n";
  }

  static boolean match(String a,String b)
  {
    if(a.length()>b.length() || a.length()<b.length())
      return false;

    for(int i=0;i<a.length();i++)
      if(a.charAt(i)!=b.charAt(i))
        return false;

    return true;
  }
}