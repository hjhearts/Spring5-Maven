package ch11.login;

public class MemberRegisterRequest {
    private String[] favoriteOs;
    public String[] getFavoriteOs(){
        return favoriteOs;
    }
    public void setFavoriteOs(String[] favoriteOs){
        this.favoriteOs = favoriteOs;
    }
}
