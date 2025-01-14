public class SlaveCore {
    int coreID;
    MasterCore masterCore;
    Process currentProcess;
    boolean idle;

    public SlaveCore(int coreID, MasterCore masterCore) {
        this.coreID = coreID;
        this.masterCore = masterCore;
        this.idle = true;
    }
    public boolean isIdle() {
        return idle;
    }
    public void assignProcess(Process process) {
        currentProcess = process;
        idle = false;
    }
    public Process executeProcess(int quantum) {
        if (currentProcess != null) {
            currentProcess.execute(quantum);
            if (currentProcess.isDone()) {
                masterCore.handleCompletedProcess(currentProcess );
                idle = true;
                currentProcess = null; // Clear the completed process
            }
        } else {
            idle = true; // Ensure idle is true if no process is assigned
        }
        return currentProcess;
    }

}
