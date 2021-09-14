package ca.bcit.comp2522.project.question2;

import java.util.ArrayList;

public class Team {
    private final ArrayList<Transformer> members = new ArrayList<>();

    public ArrayList<Transformer> getMembers() {
        return members;
    }

    public void addMember(Transformer member){
        members.add(member);
    }

    public void clear(){
        members.clear();
    }
}
