package challenge.proximity.tag.queries;

import challenge.proximity.DataService;
import challenge.proximity.domains.Tag;
import org.springframework.beans.factory.annotation.Autowired;

public class TagDetailQuery {

    @Autowired
    private DataService dataService;

    public TagDetailModel execute(long tag_id){
        Tag tag = dataService.findTagById(tag_id).get();
        if(tag == null)
            return new TagDetailModel();
        TagDetailModel tagDetailModel = new TagDetailModel();
        tagDetailModel.setId(tag.getId());
        tagDetailModel.setName(tag.getName());
        tagDetailModel.setDescription(tag.getDescription());
        tagDetailModel.setUser_name(tag.getUser().getName());
        return tagDetailModel;
    }
}
