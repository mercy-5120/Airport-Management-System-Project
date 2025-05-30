public class User {
private String fullname,email,password;
private DatabaseManager dbManager=new DatabaseManager();
public User(String fullname,String email,String password){
    this.fullname=fullname;
    this.email=email;
    this.password=password;
}

public String login(){
    String role=dbManager.checkUserCredentials(email,password);
    if(role!=null){
        return role;
        }else{
        return null;
    }
}

public void register(){
    dbManager.insertUserCredentials(fullname,email,password);
}
}
