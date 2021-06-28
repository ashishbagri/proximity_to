package challenge.proximity.tag.commands;

import challenge.proximity.DataService;
import challenge.proximity.InventoryService;
import challenge.proximity.domains.User;
import challenge.proximity.repositories.TagRespository;
import challenge.proximity.tag.commands.factory.TagFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CreateTagCommand{

    @Autowired
    private DataService dataService;

    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private TagFactory tagFactory;

    public void execute(CreateTagModel model) {
        User user = dataService.getUsersById(model.getUserId());
        String tagName = model.getName();
        String tagDescription = model.getDescription();

        dataService.addTag(tagFactory.create(tagName,tagDescription,user));
        inventoryService.notifyTagCreated(user.getuserId(), tagName);
    }
}
