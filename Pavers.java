import java.util.*;
import java.io.*;
class Main {
  public static void main(String[] args) throws FileNotFoundException {
    
    Scanner sc = new Scanner(new File("paver.dat"));
    int t =sc.nextInt();
    sc.nextLine();
    for(int i = 0; i < t; i++){
    int price = sc.nextInt();
    sc.nextLine();
    int numBuild = sc.nextInt();
    sc.nextLine();
    int streets = sc.nextInt();
    sc.nextLine();

      int[][] graph = new int[numBuild][streets];

            for(int j = 0; j < numBuild; j++)
                for(int k = 0; k < streets;k++)
                    graph[j][k] = -1;
           
      
    for(int j = 0; j < streets; j++){

    String[] tokens = sc.nextLine().split(" ");
    int start = Integer.parseInt(tokens[0]);
    int end = Integer.parseInt(tokens[1]);
    int len = Integer.parseInt(tokens[2]);
    graph[start-1][end-1] = len* price;
    graph[end-1][start-1] = len* price;

    }

      


    int[] mst = new int[numBuild];

            Set<Integer> used = new HashSet<>();

            boolean visitedAll = false;

            for(int j = 0; j < numBuild; j++)
                mst[j] = Integer.MAX_VALUE;

            mst[0] = 0;

            

            while(!visitedAll){
                //find minimum distance
                //bug
                int minI = -1;
                int min = Integer.MAX_VALUE;
                for(int j = 0; j < numBuild; j++) {
                    if (mst[j] < min && !used.contains(j)){
                        minI = j;
                        min = mst[j];
                        //System.out.println("Minimum index: " + (minI+1) + " Min Val:" + min);
                    }
                }
               // System.out.println(minI);
                used.add(minI);
            
                //update neighbors
                
                for(int j = 0; j < numBuild; j++){
                  
                    if(graph[minI][j] != -1 && graph[minI][j] < mst[j]){
                        mst[j] = graph[minI][j];
                       //System.out.println("It costs" + graph[minI][j] + " to go from " + (minI+1) + " to " + (j+1));
                       
                    }
                        
                }

                
            for(int j = 0; j < numBuild; j++)
                //System.out.println(mst[j]);
                

                //check if all visited
                //System.out.println(used.size());
                visitedAll = used.size() == numBuild;



            }
            int sum = 0;
            for(int d : mst){
                //System.out.print(d + " ");
                    sum += d;
            }

            System.out.println(sum);


        }

    }
  }
