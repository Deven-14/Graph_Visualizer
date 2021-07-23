package backEnd;

import algorithm.Graph;
import datastructure.Sync;

public class GraphThread extends Thread{

    Sync sync;

    public GraphThread(Sync sync)
    {
        this.sync = sync;
    }

    @Override
    public void run() {
        
        Graph g = new Graph(sync);
        g.createRandomGraph();
        g.BFS(1);
        
    }


}
