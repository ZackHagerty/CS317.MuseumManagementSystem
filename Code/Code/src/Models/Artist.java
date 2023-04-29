package Models;

public class Artist {
    
    public int ArtistID;
    public String Name;
    public String DOB;
    public String DOD;
    public String Info;

    public Artist(int artistID, String name, String dob, String dod, String info)
    {
        ArtistID = artistID;
        Name = name;
        DOB = dob;
        DOD = dod;
        Info = info;
    }
}
