package Models;

public class Art {
    
    public int ArtID;
    public int ArtistID;
    public String FilePath;
    public String Title;
    public String Date;
    public String Department;

    public Art(int artID, int artistID, String filePath, String title, String date, String department)
    {
        ArtID = artID;
        ArtistID = artistID;
        FilePath = filePath;
        Title = title;
        Date = date;
        Department = department; 
    }
}
