import java.io.*;
import java.util.*;

public class assignment2
{
    int n,m,k;
    char arr[][];
    LinkedList<String> list[][];

    @SuppressWarnings("unchecked")
    void manageInput()throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String sk=br.readLine().trim();
        String no="";
        sk+=" ";
        int c=0;

        for(int i=0; i<sk.length();i++)
        {
            char x=sk.charAt(i);
            if(x==' ')
            {
                c++;
                if(c==1)
                    n=Integer.parseInt(no);
                else if(c==2)
                    m=Integer.parseInt(no);
                else if(c==3)
                    k=Integer.parseInt(no);
                no="";
            }
            else
                no+=x;
        }

        arr=new char[n][m];
        list=new LinkedList[n][m];//can use 3d array too, incase if this doesnt work
        for(int i=0;i<n;i++)
        {
            String s=br.readLine();
            for(int j=0;j<m;j++)
                arr[i][j]=s.charAt(j);
        }

    }

    boolean ivp(int x, int y)//isValidPoint
    {
        if(x<0||y<0)
            return false;
        if(x>=n||y>=m)
            return false;
        if(arr[x][y]!='.')
            return false;
        return true;
    }

    void generateList()
    {
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                System.out.println("Working on "+i+", "+j);
                if(!ivp(i,j))//means, current cell is a wall.
                    continue;
                
                if(list[i][j]==null)//initializing the elements before use
                    list[i][j]=new LinkedList<String>();

                if(ivp(i-1,j))
                    list[i][j].add((i-1)+","+(j));
                if(ivp(i+1,j))
                    list[i][j].add((i+1)+","+(j));
                if(ivp(i,j-1))
                    list[i][j].add((i)+","+(j-1));
                if(ivp(i,j+1))
                    list[i][j].add((i)+","+(j+1));
            }
        }
    }

    void print()
    {
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
                System.out.print(arr[i][j]);
            System.out.println();
        }
    }

    boolean travPossible(LinkedList<String> ls[][])
    {
        int ar[][]=new int[n][m];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(ls[i][j]==null)//covers walls
                    continue;
                else if()
                {

                }
            }
        }
        
        return true;
    }

    public static void main(String args[])throws IOException
    {
        assignment2 obj=new assignment2();
        obj.manageInput();
        obj.generateList();
        //obj.print();
    }
}