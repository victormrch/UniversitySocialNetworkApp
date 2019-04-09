package com.victormramon.universitysocialnetwork.modelos;

import java.io.Serializable;
import java.util.List;

public class Sugerencias implements Serializable {

    private List<Usuario> friendsSuggested;
    private List<Grupos> groupsSuggested;

    public Sugerencias() {
    };

    public Sugerencias(List<Usuario> friendsSuggested, List<Grupos> groupsSuggested) {
        this.friendsSuggested = friendsSuggested;
        this.groupsSuggested = groupsSuggested;
    }

    public List<Usuario> getFriendsSuggested() {
        return friendsSuggested;
    }

    public void setFriendsSuggested(List<Usuario> friendsSuggested) {
        this.friendsSuggested = friendsSuggested;
    }

    public List<Grupos> getGroupsSuggested() {
        return groupsSuggested;
    }

    public void setGroupsSuggested(List<Grupos> groupsSuggested) {
        this.groupsSuggested = groupsSuggested;
    }
}
