import java.util.*;
public class hydmetro {
    static class Edge{
        int src;
        int dst;
        int wt;
        char color;
        public Edge(int src,int dst,int wt,char color){
            this.src=src;
            this.dst=dst;
            this.wt=wt;
            this.color=color;
        }
    }
    static class Pair implements Comparable<Pair>{
        int n;
        int path;
        public Pair(int n,int path){
            this.n=n;
            this.path=path;
        }
        @Override
        public int compareTo(Pair p2){
            return this.path-p2.path;
        }
                
    }
    public static void metromap(String station[]){
        for(int i=0;i<station.length;i++){
            System.out.println(i+" "+station[i]);
        }
    }
    public static void creategraph(ArrayList<Edge> graph[]){
        for(int i=0;i<graph.length;i++){
            graph[i]=new ArrayList();
        }
        for(int j=0;j<26;j++){
            graph[j].add(new Edge(j,j+1,1,'r'));
        }
        for(int j=26;j>0;j--){
            graph[j].add(new Edge(j,j-1,1,'r'));
        }
        for(int j=27;j<31;j++){
            graph[j].add(new Edge(j,j+1,1,'g'));

        }
        for(int j=31;j>27;j--){
            graph[j].add(new Edge(j,j-1,1,'g'));

        }
        graph[31].add(new Edge(31,7,1,'g'));
        graph[7].add(new Edge(7,31,1,'g'));
        graph[7].add(new Edge(7,32,1,'g'));
        graph[32].add(new Edge(32,7,1,'g'));
        for(int j=32;j<39;j++){
            graph[j].add(new Edge(j,j+1,1,'g'));

        }
        for(int j=39;j>32;j--){
            graph[j].add(new Edge(j,j-1,1,'g'));

        }
        for(int j=40;j<47;j++){
            graph[j].add(new Edge(j,j+1,1,'b'));

        }
        for(int j=47;j>40;j--){
            graph[j].add(new Edge(j,j-1,1,'b'));

        }
        graph[47].add(new Edge(47,39,1,'b'));
        graph[39].add(new Edge(39,47,1,'b'));
        graph[39].add(new Edge(39,48,1,'b'));
        graph[48].add(new Edge(48,39,1,'b'));
        for(int j=48;j<51;j++){
            graph[j].add(new Edge(j,j+1,1,'b'));

        }
        for(int j=51;j>48;j--){
            graph[j].add(new Edge(j,j-1,1,'b'));

        }
        graph[51].add(new Edge(51,16,1,'b'));
        graph[16].add(new Edge(16,51,1,'b'));
        graph[16].add(new Edge(16,52,1,'b'));
        graph[52].add(new Edge(52,16,1,'b'));
        for(int j=52;j<61;j++){
            graph[j].add(new Edge(j,j+1,1,'b'));
        }
        for(int j=61;j>52;j--){
            graph[j].add(new Edge(j,j-1,1,'b'));
        }
    }
    static int dist[]=new int[62];
    public static void dijalgo(ArrayList<Edge> graph[],int src){
        PriorityQueue<Pair> pq=new PriorityQueue<>();
        for(int i=0;i<graph.length;i++){
            if(i!=src){
                dist[i]=Integer.MAX_VALUE;
            }
        }
        boolean vis[]=new boolean[graph.length];
        pq.add(new Pair(src,0));
        while(!pq.isEmpty()){
            Pair curr=pq.remove();
            if(!vis[curr.n]){
                vis[curr.n]=true;
                for(int i=0;i<graph[curr.n].size();i++){
                    Edge e=graph[curr.n].get(i);
                    int u=e.src;
                    int v=e.dst;
                    int w=e.wt;
                    if(dist[v]>dist[u]+w){
                        dist[v]=dist[u]+w;
                        pq.add(new Pair(v,dist[v]));
                    }

                }

            }

        }

    }
    static boolean checks[]=new boolean[62];
    static ArrayList<Character> line=new ArrayList<>();
    public static boolean haspath(ArrayList<Edge>graph[],int src,int dst,boolean checks[],String station[]){
        if(src==dst){
            System.out.print(station[dst]);
            return true;
        }
        checks[src]=true;
        for(int i=0;i<graph[src].size();i++){
            Edge neigh=shortneigh(graph,src,dst);
            line.add(neigh.color);
            if(!checks[(neigh.dst)] && haspath(graph,neigh.dst,dst,checks,station)){
                System.out.print("<--"+station[neigh.src]);
                return true;
            }

        }
        return false;
    }
    public static Edge shortneigh(ArrayList<Edge>graph[],int src,int dst){
        dijalgo(graph,dst);
        Edge neigh=graph[src].get(0);
        int arr[]=new int[graph[src].size()];
        for(int i=0;i<graph[src].size();i++){
            Edge dneigh=graph[src].get(i);
            arr[i]=dist[dneigh.dst];
        }
        Arrays.sort(arr);
        for(int i=0;i<graph[src].size();i++){
            Edge xneigh=graph[src].get(i);
            if(arr[0]==dist[xneigh.dst]){
                neigh=graph[src].get(i);
            }
        }
        return neigh;
    }
     
    public static void interchange(){
        int a=0;
        int b=0;
        int c=0;
        for(int i=1;i<line.size();i++){
            if((line.get(i-1)=='r' && line.get(i)=='b')|| (line.get(i-1)=='b' && line.get(i)=='r')){
                if(b==0){
                   System.out.println("Interchange at Ameerpet");
                }
                b++;
            }
            else if((line.get(i-1)=='b' && line.get(i)=='g') || (line.get(i-1)=='g' && line.get(i)=='b') ){
                if(a==0){
                   System.out.println("Interchange at parade grounds");
                }
                a++;
            }
            else if((line.get(i-1)=='r' && line.get(i)=='g') || (line.get(i-1)=='g' && line.get(i)=='r') ){
                if(c==0){
                   System.out.println("Interchange at mgbusstation");
                }
                c++;
            }
            

        }
    }
    public static void exit(){
        System.out.println("EXIT");
        
    }
    public static void main(String args[]){
        System.out.println("                  WELCOME TO HYDERABAD METRO                 ");
        System.out.print(" press 1 to display list of stations : ");
        Scanner sc =new Scanner(System.in);
        int press=sc.nextInt();
        String station[]={"L.B.Nagar","Victoria Memorial", "Chaitanya Puri", "Dilsikhnagar", "Musarambag", "New Market", "Malakpet", "M.G.Bus Station",  "Osmania College", "Gandhi Bhavan" ,"Nampally", "Assembly", "Lakdi - ka -Pul","Khairtabad","Irrum Manzil", "Punjagutta", "Ameerpet",  "S.R.Nagar", "ESI Hospital", "Erragadda", "Bharat Nagar", "Moosapet", "Balanagar", "Kukatpally", "KPHB Colony", "JNTUCollege", "Miyapur" , "Falaknuma",  "Shamsheer Gunj", "Shalibanda","Charminar", "Salarjung Museum" ,"Sultan Bazar", "Narayanguda", "Chikkadapally", "RTC X Raods" ,"Musheerabad", "Gandhi Hospital" ,"Secunderabad West", "Parade Ground" ,"Nagole", "Uppal" ,"Stadium","NGRI", "Habsiguda", "Tarnaka","Mettuguda", "Secbad east", "Paradise", "Rasoolpura" ,"Prakash Nagar" ,"Begumpet" ,"Madhura Nagar", "Yousufguda", "Road # 5 - Jubilee Hills", "Jubilee hills Check post", "Peddamma Temple","Madhapur", "Durgam Cheruvu","Hi tech city", "Cyber Gateway", "Rai Durg"};
        if(press==1){
            metromap(station);
            ArrayList<Edge> graph[]=new ArrayList[62];
            creategraph(graph);
            System.out.print("Enter source station id : ");
            int s=sc.nextInt();
            System.out.print("Enter destination station id : ");
            int d=sc.nextInt();
            System.out.print("No of Intermediate stations : ");
            dijalgo(graph,s);
            for(int i=0;i<dist.length;i++){
               if(i==d){
                   System.out.print(dist[i]-1+" ");
               }
            }
        System.out.println();

            System.out.println("ROUTE MAP : ");
            haspath(graph,s,d,checks,station);
            System.out.println();
            System.out.print("Interchanges : ");
            interchange();
        }else{
            exit();
        }
        
    }
    
}
