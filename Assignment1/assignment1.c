#include <stdio.h>
#include <string.h>
#include <stdlib.h>
struct Node //for linked list
{
    double data;
    struct Node* next;
};
typedef struct Node *node;

node companies[100000];
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

    res[i]='\0';
    com=atoi(res);
    if(maxcom<com)
        maxcom=com;

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
    node req=newnode(val);

    node head=companies[com];

    if(head==NULL)
        head=req;
    else
    {
        node curr=head;
        while(curr->next!=NULL)
        {
            curr=curr->next;
        }
        curr->next=req;
    }
}

void print()
{
    for(int i=0;i<maxcom;i++)
    {
        node k=companies[i];
        printf("%d ",i);
        while(k!=NULL)
        {
            printf("%lf ",k->data);
        }
        printf("\n");
    }
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
            printf("%s \n",inp);
            strcpy(inp,"");
        }
        chr=getchar();
   }

    inp[i]='\0';
    i=0;
    process(inp);
    printf("%s \n",inp);
    strcpy(inp,"");
    print();

    return 0;
}