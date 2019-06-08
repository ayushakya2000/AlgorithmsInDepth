#include <stdio.h>
#include <string.h>
#include <stdlib.h>
struct Node //for linked list
{
    double data;
    struct Node *next;
};
typedef struct Node *node;

node companies[100000];
int entries[100000];
int maxcom=0;

node newnode(double val)
{
    node nd;
    nd=(node)malloc(sizeof(struct Node));

    nd->data=val;
    nd->next=NULL;
    return nd;
}

void process(char inp[1000])
{
    int len=strlen(inp);
    int com;

    char res[100], end[100];
    int i;

    for(i=0;i<len;i++)
    {
        if(inp[i]!=' ')
            res[i]=inp[i];//i goes upto first space
        else
            break;
    }

    res[i]='\0';//company no in string
    com=atoi(res)-1;
    if(maxcom<com)
        maxcom=com;
    
    //printf("com is== %d \n",com);
    
    int j=i;
    for(;i<len;i++)
    {
        if(inp[i]==' ')
            j=i;//j has last space
    }
    j++;
    i=0;//reusing i

    for(;j<len;j++,i++)
    {
        end[i]=inp[j];
    }

    end[i]='\0';

    double val=atof(end);
    
    //printf("val is== %lf \n",val);
    
    node req=newnode(val);
    //printf("val is== %lf \n",req->data);

    if(companies[com]==NULL)
        companies[com]=req;
    else
    {
        node curr=companies[com];
        while(curr->next!=NULL)
        {
            curr=curr->next;
        }
        curr->next=req;
    }
    entries[com]++;
    //printf("val head is== %lf \n",companies[com]->data);
}

void print()
{
    for(int i=0;i<=maxcom;i++)
    {
        node k=companies[i];
        printf("%d ",i+1);
        
        while(k!=NULL)
        {
            printf("%lf ",k->data);
            k=k->next;
        }
        printf("\n");
    }
}

node merge(node a, node b)
{
    node head=NULL, tail=NULL;
    
    while(a!=NULL)
    {
        double v;
        if(b!=NULL)
        {
            if(b->data<a->data)
            {
                v=b->data;
                b=b->next;
            }
            else
            {
                v=a->data;
                a=a->next;
            }
        }
        else
        {
            v= a->data;
            a=a->next;
        }
        
        if(head==NULL)
        {
            head=tail=newnode(v);
        }
        else
        {
            tail->next=newnode(v);
            tail=tail->next;
        }
    }
    
    while(b!=NULL)
    {
        tail->next=newnode(b->data);
        tail=tail->next;
        b=b->next;
    }
    
    return head;
}

node mergeSort(node head, int n)
{
    if(n>1)
    {
        int half=n/2;
        node a=head;
        node b=a;
        int i=1;
        node c=a;//to store node just before b, where link between a and b is broken...
        //we want the b node at position half+1(not index, position)
        
        while(i<=half)
        {
            if(i<half)
                c=c->next;
            b=b->next;
            i++;
        }
        
        c->next=NULL;
        
        a=mergeSort(a, half);
        b=mergeSort(b, n-half);
        
        return merge(a,b);
    }
}

void sort()
{
    for(int i=0;i<=maxcom;i++)
       companies[i]=mergeSort(companies[i], entries[i]);
}

int main()
{
    char inp[1000]="", chr;
    int i=0;
    
   chr=getchar();
   
   while(chr!=EOF)
   {
       if(chr!='\n')
        inp[i++]=chr;
        else
        {
            inp[i]='\0';
            i=0;
            process(inp);
            //printf("%s \n",inp);
            strcpy(inp,"");
        }
        chr=getchar();
   }

    inp[i]='\0';
    i=0;
    process(inp);
    //printf("%s \n",inp);
    strcpy(inp,"");
    
    sort();
    
    print();

    return 0;
}