#include <stdio.h>

struct Node //for linked list
{
    double data;
    struct Node* next;
}
typedef struct Node* node;

node companies[10];

node newnode(double val)
{
    node nd;
    nd=(node)malloc(sizeof(struct node));

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
            res[i]=inp[i];
        else
            break;
    }

    res[i]='\0';
    com=atoi(res);

    int j=i;
    for(;i<len;i++)
    {
        if(inp[i]==' ')
            j=i;
    }
    j++;
    i=0;

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
        while(curr->next!=null)
        {
            curr=curr->next;
        }
        curr->next=req;
    }
}

int main()
{
    char inp[1000], chr;


    scanf(" %[^\n]",inp);

    while(inp!=EOF)
    {
        process(inp);
        scanf(" %[^\n]",inp);
    }

    return 0;
}