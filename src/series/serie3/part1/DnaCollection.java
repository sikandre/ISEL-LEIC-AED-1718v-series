package series.serie3.part1;

public class DnaCollection{
    private String defaultDnaCharaters="ACTG";
    private DNAStucture root=null;

    public class DNAStucture{
        DNAStucture parent ;
        DNAStucture []collection=new DNAStucture[defaultDnaCharaters.length()];
        boolean prefix=false;


        public void addDNAStucture(DNAStucture parent,DNAStucture add,int index){
            this.parent=parent;
            collection[index]=add;
        }
    }

    public DnaCollection(){
        root =new DNAStucture();
    }

    public void add(String fragment){
        char aux;
        int i=0;
        DNAStucture current=root;
        while(i<fragment.length()){
            aux=fragment.charAt(i);
            int index=defaultDnaCharaters.indexOf(aux);
            if(index>=0){
                if(root.collection[index]==null){
                    DNAStucture newStruct=new DNAStucture();
                    current.addDNAStucture(current,newStruct,index);
                    current=newStruct;
                }
            }else{
                break;
            }
            ++i;
        }
        if(i==fragment.length())
            current.prefix=true;
    }
    public int prefixCount(String p){
        char aux;
        int i=0;
        int counter=0;
        DNAStucture current=root;
        while(i<p.length()){
            aux=p.charAt(i);
            int index=defaultDnaCharaters.indexOf(aux);
            if(index>=0)
                current=root.collection[index];
            else
                return -1;
            ++i;
        }
        while(current.parent!=null){
            for(DNAStucture st:current.collection)
                if(st.prefix)
                    ++counter;
            current=current.parent;
        }
        return counter;
    }
}