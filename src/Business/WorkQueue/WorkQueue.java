/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import java.util.ArrayList;

/**
 *
 * @author raunak
 */
public class WorkQueue {
    
    private ArrayList<WorkRequest> workRequestList;

    public WorkQueue() {
        workRequestList = new ArrayList();
    }

    public ArrayList<WorkRequest> getWorkRequestList() {
        return workRequestList;
    }
    
    public WorkRequest addWorkRequest()
    {
        WorkRequest wq = new WorkRequest();
        workRequestList.add(wq);
        return wq;
    }
    
    public void removeWorkRequest(WorkRequest wq){
        workRequestList.remove(wq);
    }
}