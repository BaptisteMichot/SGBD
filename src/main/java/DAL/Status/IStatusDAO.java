package main.java.DAL.Status;

import java.util.ArrayList;
import main.java.BL.Status.Status;

public interface IStatusDAO {
    
    public ArrayList<Status> getStatus();
    
    public int getIDStatus(String nom);

    public boolean updateStatus(int id, String nom);

    public boolean deleteStatus(int id);
    
    public boolean addStatus(String nom);
}

