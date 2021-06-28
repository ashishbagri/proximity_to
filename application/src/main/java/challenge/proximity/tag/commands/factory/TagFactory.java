package challenge.proximity.tag.commands.factory;

import challenge.proximity.domains.Tag;
import challenge.proximity.domains.User;

public class TagFactory {

    public Tag create(String name, String description, User user){
        Tag tag = new Tag();
        tag.setUser(user);
        tag.setName(name);
        tag.setDescription(description);
        return tag;
    }
}
