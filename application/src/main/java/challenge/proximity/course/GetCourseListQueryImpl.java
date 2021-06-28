package challenge.proximity.course;

import challenge.proximity.DataService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GetCourseListQueryImpl implements GetCourseListQuery {

    @Autowired
    private DataService dataService;

    @Override
    public List<CourseModel> execute() {
        List<CourseModel> list = new ArrayList<>();
        dataService.getCourses().forEach(c -> list.add(new CourseModel(c.getId(),c.getName())));
        return list;
    }
}
