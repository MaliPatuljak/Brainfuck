package tuk.patuljak.brainfuck;

interface Brainfuck{
    static void main(String[]a){
        for(var b:a){
            char p=0,h=0,s=0,l=0,n=91,i=0,c;
            for(char[]m=new char[n],
                y=b.toCharArray();
                i<y.length;
                c=y[i++],
                    h=s>0?s+=c==n?1:c>n?-1:0:c<44?++m[p]:
                        c<46?--m[p]:c==60?--p:c==62?++p:c>n
                            ?m[p]!=0?i=m[n-l]:--l:c==n
                            ?m[p]!=0?m[n-++l]=i:++s:h,
                    System.out.print(c==46?m[p]:"")
            );
        }
    }
}