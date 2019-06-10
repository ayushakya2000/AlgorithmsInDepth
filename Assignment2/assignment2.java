import java.io.*;
import java.util.*;

public class assignment2
{
    static int n,m,k;
    static char arr[][];
    boolean op=false;
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
        generateList(list,arr);

    }
    
    @SuppressWarnings("unchecked")
    void generatePattern(int l, char[][] ar2)//recursive function to generate all patterns
    {
        if(l>0)
        {
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<m;j++)
                {
                    if(ar2[i][j]=='.')
                    {
                        char[][] temp1=new char[n][m];

                        for(int i1=0;i1<n;i1++)//copying values into temp1.
                            for(int j1=0;j1<m;j1++)
                                temp1[i1][j1]=ar2[i1][j1];

                        temp1[i][j]='X';

                        generatePattern(l-1,temp1);
                        if(op)
                            return;
                    }
                }
            }
        }
        else
        {
            LinkedList<String> ls[][]=new LinkedList[n][m];
            generateList(ls, ar2);
            if(travPossible(ls))
            {
                op=true;
                print(ar2);
            }
        }
    }

    boolean ivp(int x, int y, char[][] mat)//isValidPoint
    {
        if(x<0||y<0)
            return false;
        if(x>=n||y>=m)
            return false;
        if(mat[x][y]!='.')
            return false;
        return true;
    }

    void generateList(LinkedList<String> ls[][], char mat[][])
    {
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(!ivp(i,j,mat))//means, current cell is a wall.
                    continue;
                
                if(ls[i][j]==null)//initializing the elements before use
                    ls[i][j]=new LinkedList<String>();

                if(ivp(i-1,j,mat))
                    ls[i][j].add((i-1)+","+(j));
                if(ivp(i+1,j,mat))
                    ls[i][j].add((i+1)+","+(j));
                if(ivp(i,j-1,mat))
                    ls[i][j].add((i)+","+(j-1));
                if(ivp(i,j+1,mat))
                    ls[i][j].add((i)+","+(j+1));
            }
        }
    }

    void print(char[][] ar2)
    {
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
                System.out.print(ar2[i][j]);
            System.out.println();
        }
    }

    int getX(String s)
    {
        return Integer.parseInt(s.substring(0,s.indexOf(',')));
    }

    int getY(String s)
    {
        return Integer.parseInt(s.substring(s.indexOf(',')+1));
    }

    boolean travPossible(LinkedList<String> ls[][])//checks if complete bfs traversal is possible or not
    {
        int ar[][]=new int[n][m];
        int i,j=0;//to remove j might not be initialized error
  out:  for(i=0;i<n;i++)
        {
            for(j=0;j<m;j++)
            {
                if(ls[i][j]==null)//covers walls
                    continue;
                else
                    break out;
            }
        }

        LinkedList<String> queue=new LinkedList<String>();

        if(i>=n||j>=m)
            return false;

        ar[i][j]=1;//visited...
        String s=i+","+j;
        queue.add(s);

        while(queue.size()!=0)
        {
            s=queue.poll();
            i=getX(s);
            j=getY(s);
            int kt=0;

            while(kt<ls[i][j].size())
            {
                String temp=ls[i][j].get(kt++);
                int x1=getX(temp);
                int y1=getY(temp);
                if(ar[x1][y1]==0)
                {
                    ar[x1][y1]=1;
                    queue.add(x1+","+y1);
                }
            }
        }

        for(i=0;i<n;i++)
        {
            for(j=0;j<m;j++)
            {
                if(ls[i][j]!=null&&ar[i][j]==0)
                    return false;// means graph is segmented, not complete
            }
        }

        return true;
    }

    public static void main(String args[])throws IOException
    {
        assignment2 obj=new assignment2();
        obj.manageInput();
        obj.generatePattern(k,arr);
    }
}