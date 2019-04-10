package com.victormramon.universitysocialnetwork.callback;

import com.victormramon.universitysocialnetwork.modelos.Grupos;
import com.victormramon.universitysocialnetwork.modelos.Usuario;

public interface CallbackRelationship {

    public void onFriendClick(Usuario item);

    public void onGroupClick(Grupos item);
}
